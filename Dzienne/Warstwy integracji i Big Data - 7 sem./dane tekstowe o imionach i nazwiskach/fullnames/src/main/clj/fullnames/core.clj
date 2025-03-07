(ns fullnames.core
  (:require
   [clojure.string   :as   str]
   [clojure.data.csv :as   csv]
   [clojure.java.io  :as    io]
   [clucy.core       :as clucy]))

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defn- read-csv
  ([filename]
   (read-csv \, filename))

  ([separator filename]
   (io! (with-open [reader (io/reader filename)]
          (doall (csv/read-csv reader {:separator separator}))))))

(def PATH
  (str "/home/kongra/Devel/Projects/SAN/2024 zimowy/Dzienne/"
       "Warstwy integracji i Big Data - 7 sem./fullnames/"))

(defn csv->first-name [[first-name n gender]]
  {:first-name (str/capitalize first-name)
   :count      (Long/parseLong n)
   :gender     gender})

(def FIRST-NAMES-CSV
  (str PATH "Imiona_nadane_dzieciom_w_Polsce_w_I_połowie_2023_r._-_imię_pierwsze_1.csv"))

(def FIRST-NAMES
  (->> FIRST-NAMES-CSV
    (read-csv \;)

    ;; We skip the heading row
    (next)

    (map csv->first-name)
    (sort-by :count)
    (reverse)
    (vec)))

;; (clojure.pprint/pprint (filter #(= "K" (:gender %)) FIRST-NAMES))

(def LAST-NAMES-M-CSV (str PATH "nazwiska_męskie-osoby_żyjące.csv"))
(def LAST-NAMES-F-CSV (str PATH "nazwiska_żeńskie-osoby_żyjące.csv"))

(defn csv->last-name [[last-name n]]
  {:last-name (str/capitalize last-name)
   :count     (Long/parseLong n)})

(defn csv->last-names [csv]
  (->> csv
    (read-csv)

    ;; We skip the heading row
    (next)

    (map csv->last-name)
    (sort-by :count)
    (reverse)
    (vec)))

(def LAST-NAMES-M
  (csv->last-names LAST-NAMES-M-CSV))

(def LAST-NAMES-F
  (csv->last-names LAST-NAMES-F-CSV))

(def FIRST-NAMES-M (get (group-by :gender FIRST-NAMES) "M"))
(def FIRST-NAMES-F (get (group-by :gender FIRST-NAMES) "K"))

#_(->> FIRST-NAMES-M
       (map :count)
       (reduce +))

#_(->> FIRST-NAMES-F
       (map :count)
       (reduce +))

(defn random-first-name
  [first-names]
  (assert (vector? first-names))
  (nth first-names (rand-int (count first-names))))

;; (random-first-name FIRST-NAMES-M)
;; (repeatedly 10 #(random-first-name FIRST-NAMES-M))

(defn full-names
  [last-name first-names n]
  (map #(str (:first-name %) " " last-name)
    (repeatedly n #(random-first-name first-names))))

#_(full-names "Grzanek" FIRST-NAMES-M 10)

(def FULL-NAMES-F
  (->> LAST-NAMES-F
    (map :last-name)
    (map #(full-names % FIRST-NAMES-F 60))
    (apply concat)
    (take 1000000)
    (doall)))

(def FULL-NAMES-M
  (->> LAST-NAMES-M
    (map :last-name)
    (map #(full-names % FIRST-NAMES-M 60))
    (apply concat)
    (take 1000000)
    (doall)))

;; LUCENE INDEX
(def LUCY-PATH (str PATH "lucy/"))
(def lucy-index (clucy/disk-index LUCY-PATH))

(defn full-name->lucy!
  [id full-name]
  (assert (uuid?          id))
  (assert (string? full-name))

  (io! (clucy/add lucy-index {:full-name full-name
                              :id        id})))

#_(time
   (doseq [full-name FULL-NAMES-F]
     (full-name->lucy! (random-uuid) full-name)))

(defn full-names->lucy!
  [index full-names-]
  (io!
    (->> full-names-
      (map (fn [full-name] {:full-name full-name :id (random-uuid)}))
      (apply clucy/add index))))

(time (full-names->lucy! lucy-index FULL-NAMES-F))
(time (full-names->lucy! lucy-index FULL-NAMES-M))
(time (clucy/search lucy-index "nel~2" 100))

;; (def lucy-memindex (clucy/memory-index))
;; (time (full-names->lucy! lucy-memindex FULL-NAMES-F))
;; (time (full-names->lucy! lucy-memindex FULL-NAMES-M))

;; (time (count (clucy/search lucy-index    "kowalska" 10000)))
;; (time (clucy/search lucy-memindex "kowalska" 10000))

;; (bench (clucy/search lucy-index    "kowalska" 10000))
;; (bench (clucy/search lucy-memindex "kowalska" 10000))

(defn test-1 [index max-results]
  (->> FIRST-NAMES-F
    (map :first-name)
    (map #(count (clucy/search index (str %) max-results)))
    (reduce +)))

;; (time (test-1 lucy-index    50))
;; (time (test-1 lucy-memindex 50))

(defn- write-csv
  [filename data]
  (io! (with-open [writer (io/writer filename)]
         (doall (csv/write-csv writer data)))))

;; (write-csv "FULL-NAMES-F.csv"
;;   (map vector FULL-NAMES-F (iterate inc 0)))

;; (write-csv "FULL-NAMES-M.csv"
;;   (map vector FULL-NAMES-M (iterate inc 0)))

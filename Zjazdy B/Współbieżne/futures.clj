(ns futures)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defn thname []
  (.getName (Thread/currentThread)))

#_(defn proc-1 []
    (println "proc-1::start" (thname))
    (Thread/sleep 2000)
    (println "proc-1::end" (thname))
    55)

#_(defn main []
    (println "main::start" (thname))
    (Thread/sleep 1000)

    (let [n (proc-1)]
      (println :n n (thname)))

    (println "main::end" (thname)))

(defn proc-1 []
  (println "proc-1::start" (thname))
  (Thread/sleep 2000)
  (println "proc-1::end" (thname))
  55)

(defn main []
  (println "main::start" (thname))
  (Thread/sleep 1000)

  (let [n (future (proc-1))]
    (println :n (deref n) (thname)))

  (println "main::end" (thname)))

;; a1 {:id 12345}
;; e1
;; e2
;; e3
;; e4
;; ...
;; e5
;; e6
;; e7
;; e8
;; ...

;; a1'    = e1(a1)
;; a1''   = e2(a1')   =       e2(e1(a1))
;; a1'''  = e3(a1'')  =    e3(e2(e1(a1)))
;; a1'''' = e4(a1''') = e4(e3(e2(e1(a1))))
;; ...

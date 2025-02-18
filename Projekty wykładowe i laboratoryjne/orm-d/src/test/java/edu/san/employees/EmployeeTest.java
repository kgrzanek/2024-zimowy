// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.employees;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import edu.san.adresses.Address;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@QuarkusTest
class EmployeeTest {

  @SuppressWarnings("static-access")
  @Test
  @Transactional
  void test() {
    var address1 = new Address();
    address1.setCountry("Poland");
    address1.setStreet("Piotrkowska");
    address1.setNumber("215");
    address1.persist();

    var lecturer1 = new Lecturer();
    var addresses = Set.of(address1);
    lecturer1.setEmail("kgrzanek@san.edu.pl");
    lecturer1.setFirstName("Konrad");
    lecturer1.setLastName("Grzanek");
    lecturer1.setDegree("dr inż.");
    lecturer1.setMainAddress(address1);
    lecturer1.setAdditionalAddresses(addresses);
    lecturer1.persist();

    var admin2 = new Administrator();
    admin2.setEmail("adminek@san.edu.pl");
    admin2.setFirstName("Konrad");
    admin2.setLastName("Grzanek");
    admin2.setRole(AdministratorRole.CLERK);
    admin2.persist();

    assertThat(lecturer1).isNotNull();

    Lecturer same1 = Lecturer
        .find("email = :email", Map.of("email", "kgrzanek@san.edu.pl"))
        .firstResult();

    assertThat(same1).isNotNull();
    assertThat(same1.getDegree()).isNotBlank();
    assertThat(same1.getMainAddress()).isNotNull();

    System.out.println(same1.getMainAddress().getCountry());
  }

}

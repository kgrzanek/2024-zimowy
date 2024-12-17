package edu.san;

import edu.san.jpa.utils.AbstractEnityWithId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class MyEntity extends AbstractEnityWithId<Long, MyEntity> {

  @Id
  @GeneratedValue
  private Long id;

  @Version
  private short version;

  private String field;

  @Override
  public Long getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

}

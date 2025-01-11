// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.jpa.utils;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public abstract class AbstractEnityWithId<I, T extends AbstractEnityWithId<I, T>>
    extends PanacheEntityBase {

  public abstract I getId();

  @Override
  public final int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!this.getClass().isInstance(obj))
      return false;

    @SuppressWarnings("unchecked")
    T other = (T) obj;
    return getId().equals(other.getId());
  }

}

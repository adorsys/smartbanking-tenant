package de.adorsys.smartbanking.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@TypeAlias("smartbanking.tenant")
@Document
public class Tenant {

  @Id
  private ObjectId id;

  @Indexed(unique = true)
  private String number;
  private String name;

  private Tenant() {
  }

  public Tenant(String number, String name) {
    Objects.requireNonNull(number);
    Objects.requireNonNull(name);

    this.number = number;
    this.name = name;
  }


  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    Objects.requireNonNull(id);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    Objects.requireNonNull(number);
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tenant tenant = (Tenant) o;

    return id != null ? id.equals(tenant.id) : tenant.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Tenant{" +
      "id=" + id +
      ", number='" + number + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}

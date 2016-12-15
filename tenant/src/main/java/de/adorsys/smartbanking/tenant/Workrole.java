package de.adorsys.smartbanking.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Set of permissions for a given role
 */
@TypeAlias("smartbanking.workrole")
@Document
public class Workrole {

  @Id
  private ObjectId id;

  @Indexed(unique = true)
  private String name;
  private Set<Permission> allowedPermissions = new HashSet<>();

  private Workrole() {
  }

  public Workrole(String name) {
    this.name = name;
  }


  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Permission> getAllowedPermissions() {
    return new HashSet<>(allowedPermissions);
  }

  public void addPermission(Permission p) {
    this.allowedPermissions.add(p);
  }

  public void addPermisson(Collection<Permission> p) {
    allowedPermissions.addAll(p);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Workrole workrole = (Workrole) o;

    if (id != null ? !id.equals(workrole.id) : workrole.id != null)
      return false;
    return name != null ? name.equals(workrole.name) : workrole.name == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Workrole{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", allowedPermissions=" + allowedPermissions +
      '}';
  }

}

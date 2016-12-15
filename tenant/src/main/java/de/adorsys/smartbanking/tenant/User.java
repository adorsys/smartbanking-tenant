package de.adorsys.smartbanking.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@TypeAlias("smartbanking.user")
@Document
public class User {

  @Id
  private ObjectId id;

  private ObjectId tenant;
  private String email;
  private Set<ObjectId> workspaces = new HashSet<>();

  private User() {
  }

  public User(ObjectId tenant, String email) {
    this.tenant = tenant;
    this.email = email;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public ObjectId getTenant() {
    return tenant;
  }

  public void setTenant(ObjectId tenant) {
    this.tenant = tenant;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<ObjectId> getWorkspaces() {
    return workspaces;
  }

  public void setWorkspaces(Set<ObjectId> workspaces) {
    this.workspaces = workspaces;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return id != null ? id.equals(user.id) : user.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}

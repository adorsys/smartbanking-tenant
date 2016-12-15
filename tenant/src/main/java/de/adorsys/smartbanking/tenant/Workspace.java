package de.adorsys.smartbanking.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Workrole -> Tenant mapping
 */
@TypeAlias("smartbanking.workspace")
@Document
public class Workspace {

  @Id
  private ObjectId id;

  @DBRef
  private Tenant tenant;

  @DBRef
  private Workrole workrole;

  private Workspace() {
  }

  public Workspace(Tenant tenant, Workrole workrole) {
    this.tenant = tenant;
    this.workrole = workrole;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

  public Workrole getWorkrole() {
    return workrole;
  }

  public void setWorkrole(Workrole workrole) {
    this.workrole = workrole;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Workspace workspace = (Workspace) o;

    return id != null ? id.equals(workspace.id) : workspace.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Workspace{" +
      "id=" + id +
      ", tenant=" + tenant +
      ", workrole=" + workrole +
      '}';
  }
}

package de.adorsys.smartbanking.tenant;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EffectivePermissions {

  private Workrole role;
  private Set<Tenant> tenants = new HashSet<>();

  public EffectivePermissions(Workrole role, Set<Tenant> tenants) {
    this(role);
    Objects.requireNonNull(tenants);
    this.tenants = tenants;
  }

  public EffectivePermissions(Workrole role) {
    Objects.requireNonNull(role);
    this.role = role;
  }

  public Workrole getRole() {
    return role;
  }

  public Set<Tenant> getTenants() {
    return new HashSet<>(tenants);
  }

  @Override
  public String toString() {
    return "EffectivePermissions{" +
      "role=" + role +
      ", tenants=" + tenants +
      '}';
  }
}

package de.adorsys.smartbanking.tenant.tenant;

import de.adorsys.smartbanking.tenant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer {

  private static Logger log = LoggerFactory.getLogger(Initializer.class);

  private final PermissionRepository pr;
  private final WorkroleRepository wr;
  private final WorkspaceRepository wsr;
  private final TenantRepository tr;
  private final UserRepository ur;

  public Initializer(PermissionRepository permissionRepository, WorkroleRepository workroleRepository,
                     WorkspaceRepository workspaceRepository, TenantRepository tenantRepository,
                     UserRepository userRepository) {
    this.pr = permissionRepository;
    this.wr = workroleRepository;
    this.wsr = workspaceRepository;
    this.tr = tenantRepository;
    this.ur = userRepository;
  }

  @PostConstruct
  public void createSampleData() {
    if (pr.count() > 0) {
      log.info("MongoDB is already initialized");
      return;
    }

    log.info("Initialize MongoDB...");

    Workrole ta = new Workrole("technischer_admin");
    Set<Permission> taPermissions = new HashSet<>();
    taPermissions.addAll(Arrays.asList(new Permission("mandant.anzeigen"),
      new Permission("mandant.bearbeiten"),
      new Permission("mandant.loeschen"),
      new Permission("mandant.aktivieren"),
      new Permission("mandant.speichern"),
      new Permission("mandant.anlegen"),
      new Permission("mandant.suchen"),
      new Permission("berechtigung.anzeigen"),
      new Permission("berechtigung.bearbeiten"),
      new Permission("berechtigung.aktivieren"),
      new Permission("berechtigungen.speichern"),
      new Permission("berechtigungen.anlegen"),
      new Permission("berechtigungen.suchen"),
      new Permission("user.anzeigen"),
      new Permission("user.bearbeiten"),
      new Permission("user.loeschen"),
      new Permission("user.aktivieren"),
      new Permission("user.speichern"),
      new Permission("user.anlegen"),
      new Permission("user.suchen")));
    ta.addPermisson(taPermissions);
    ta = wr.save(ta);
    pr.save(taPermissions);

    Workrole adm = new Workrole("adm");
    HashSet<Permission> admPermissions = new HashSet<>();
    admPermissions.addAll(Arrays.asList(
      new Permission("vorgang.anzeigen"),
      new Permission("vorgang.bearbeiten"),
      new Permission("vorgang.loeschen"),
      new Permission("vorgang.aktivieren"),
      new Permission("vorgang.speichern"),
      new Permission("vorgang.anlegen"),
      new Permission("vorgang.suchen"),
      new Permission("antrag.anzeigen"),
      new Permission("antrag.bearbeiten"),
      new Permission("antrag.loeschen"),
      new Permission("antrag.aktivieren"),
      new Permission("antrag.speichern"),
      new Permission("antrag.anlegen"),
      new Permission("antrag.suchen"),
      new Permission("angebot.anzeigen"),
      new Permission("angebot.bearbeiten"),
      new Permission("angebot.loeschen"),
      new Permission("angebot.aktivieren"),
      new Permission("angebot.speichern"),
      new Permission("angebot.anlegen"),
      new Permission("angebot.suchen"),
      new Permission("bedarf.anzeigen"),
      new Permission("bedarf.bearbeiten"),
      new Permission("bedarf.loeschen"),
      new Permission("bedarf.aktivieren"),
      new Permission("bedarf.speichern"),
      new Permission("bedarf.anlegen"),
      new Permission("bedarf.suchen"),
      new Permission("dokument.anzeigen"),
      new Permission("dokument.bearbeiten"),
      new Permission("dokument.loeschen"),
      new Permission("dokument.speichern"),
      new Permission("dokument.anlegen"),
      new Permission("dokument.suchen"),
      new Permission("dokument.hochladen"),
      new Permission("dokument.download"),
      new Permission("dokument.synchronisieren"),
      new Permission("kunden.anzeigen"),
      new Permission("kunden.bearbeiten"),
      new Permission("kunden.loeschen"),
      new Permission("kunden.aktivieren"),
      new Permission("kunden.speichern"),
      new Permission("kunden.anlegen"),
      new Permission("kunden.suchen"),
      new Permission("historie.anzeigen"),
      new Permission("historie.loeschen"),
      new Permission("historie.aktivieren"),
      new Permission("historie.speichern"),
      new Permission("historie.anlegen"),
      new Permission("historie.suchen")));
    adm.addPermisson(admPermissions);
    adm = wr.save(adm);
    pr.save(admPermissions);

    Workrole idm = new Workrole("idm");
    Set<Permission> idmPermissions = new HashSet<>();
    idmPermissions.addAll(Arrays.asList(new Permission("eingereichte_vorgaenge.anzeigen"),
      new Permission("eingereichte_vorgaenge.bearbeiten"),
      new Permission("eingereichte_vorgaenge.speichern"),
      new Permission("eingereichte_vorgaenge.suchen"),
      new Permission("eingereichte_vorgaenge.archivieren")));
    idm.addPermisson(idmPermissions);
    idm = wr.save(idm);
    pr.save(idmPermissions);


    Tenant t1 = tr.save(new Tenant("0", "Test1"));
    Tenant t2 = tr.save(new Tenant("01", "Test2"));

    Workspace w1 = wsr.save(new Workspace(t1, idm));
    Workspace w2 = wsr.save(new Workspace(t1, adm));
    Workspace w3 = wsr.save(new Workspace(t1, ta));
    Workspace w4 = wsr.save(new Workspace(t2, ta));
    Workspace w5 = wsr.save(new Workspace(t2, adm));

    User u1 = new User(t1.getId(), "user1@test.de");
    u1.getWorkspaces().add(w1.getId());
    u1.getWorkspaces().add(w2.getId());
    u1.getWorkspaces().add(w3.getId());
    User u2 = new User(t2.getId(), "user2@test.de");
    u2.getWorkspaces().add(w5.getId());
    u1 = ur.save(u1);
    u2 = ur.save(u2);
  }
}

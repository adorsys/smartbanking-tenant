package de.adorsys.smartbanking.tenant;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TenantService {

  private final TenantRepository tenantRepo;
  private final WorkroleRepository workroleRepository;
  private final UserRepository userRepository;
  private final WorkspaceRepository workspaceRepository;
  private final TenantService tenantService;


  public TenantService(TenantRepository tenantRepo, WorkroleRepository workroleRepository,
                       UserRepository userRepository, WorkspaceRepository workspaceRepository,
                       TenantService tenantService) {
    this.tenantRepo = tenantRepo;
    this.workroleRepository = workroleRepository;
    this.userRepository = userRepository;
    this.workspaceRepository = workspaceRepository;
    this.tenantService = tenantService;
  }

  public List<Tenant> getChildren(ObjectId parentId) {
    Tenant parent = tenantRepo.findOne(parentId);

    if (parent == null)
      throw new NotFoundException();

    return tenantRepo.findByNumberStartingWith(parent.getNumber());
  }


  public EffectivePermissions getPermissions(ObjectId userId, ObjectId workroleId) {
    User user = userRepository.findOne(userId);
    Workrole wr = workroleRepository.findOne(workroleId);
    if (user == null || workroleId == null)
      throw new NotFoundException();

    Set<Workspace> workspaces = new HashSet<>();
    workspaceRepository.findAll(user.getWorkspaces()).forEach(workspaces::add);

    Set<Workspace> matchingWorkspaces = workspaces.stream()
      .filter(ws -> Objects.equals(ws.getWorkrole().getId(), workroleId)).collect(Collectors.toSet());

    if (matchingWorkspaces.isEmpty())
      return new EffectivePermissions(wr);

    Set<Tenant> parentTenants = matchingWorkspaces.stream().map(Workspace::getTenant).collect(Collectors.toSet());
    Set<Tenant> allTenants = parentTenants.stream().map(t -> tenantService.getChildren(t.getId()))
      .flatMap(Collection::stream).collect(Collectors.toSet());

    return new EffectivePermissions(wr, allTenants);
  }

}

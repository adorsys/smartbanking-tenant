package de.adorsys.smartbanking.tenant.tenant;

import de.adorsys.smartbanking.tenant.EffectivePermissions;
import de.adorsys.smartbanking.tenant.TenantService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("permission")
public class PermissionController {

  private final TenantService ts;

  public PermissionController(TenantService ts) {
    this.ts = ts;
  }

  @GetMapping
  public EffectivePermissions get(@RequestParam("user") ObjectId userId,
                                  @RequestParam("workrole") ObjectId workroleId) {
    return ts.getPermissions(userId, workroleId);
  }

}

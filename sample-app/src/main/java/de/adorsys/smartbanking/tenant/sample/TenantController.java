package de.adorsys.smartbanking.tenant.sample;

import de.adorsys.smartbanking.tenant.Tenant;
import de.adorsys.smartbanking.tenant.TenantRepository;
import de.adorsys.smartbanking.tenant.TenantService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "sample")
public class TenantController {

  private static Logger log = org.slf4j.LoggerFactory.getLogger(TenantController.class);


  @Autowired
  TenantRepository dao;

  @Autowired
  TenantService service;

  @GetMapping
  public List<Tenant> get() {
    return dao.findAll();
  }

  @GetMapping(params = "parent")
  public List<Tenant> getChildren(@RequestParam ObjectId parent) {
    return service.getChildren(parent);
  }
}

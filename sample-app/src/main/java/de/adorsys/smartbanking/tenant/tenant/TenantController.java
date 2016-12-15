package de.adorsys.smartbanking.tenant.tenant;

import de.adorsys.smartbanking.tenant.Tenant;
import de.adorsys.smartbanking.tenant.TenantRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

  private static Logger log = org.slf4j.LoggerFactory.getLogger(TenantController.class);


  @Autowired
  TenantRepository dao;

  @GetMapping
  public List<Tenant> get() {
    return dao.findAll();
  }

}

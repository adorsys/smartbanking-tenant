package de.adorsys.smartbanking.tenant.tenant;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.adorsys.smartbanking.tenant.Tenant;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@EnableWebMvc
@EnableWebSecurity
@EnableMongoRepositories(basePackageClasses = {TenantApplication.class, Tenant.class})
@SpringBootApplication
public class TenantApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(TenantApplication.class, args);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder
      .serializerByType(ObjectId.class, new ToStringSerializer());
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
    converters.add(converter);
  }
}

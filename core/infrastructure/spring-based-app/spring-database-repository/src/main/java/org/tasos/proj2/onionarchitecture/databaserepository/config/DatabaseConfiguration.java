package org.tasos.proj2.onionarchitecture.databaserepository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.tasos.proj2.domain.EntityScanMarker;

@Configuration
@EntityScan(basePackageClasses = {EntityScanMarker.class} )
//@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
@EnableJpaRepositories("org.tasos.proj2")
public class DatabaseConfiguration {

}

package org.tasos.proj2.onionarchitecture.databaserepository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.tasos.proj2.domain.EntityScanMarker;
import org.tasos.proj2.domain.RepositoryScanMarker;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackageClasses = {EntityScanMarker.class} )
//@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
@EnableJpaRepositories("org.tasos.proj2")
public class DatabaseConfiguration {

//    @Bean
//    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = builder
//                .setType(EmbeddedDatabaseType.H2)
//                .build();
//        return db;
//    }
}

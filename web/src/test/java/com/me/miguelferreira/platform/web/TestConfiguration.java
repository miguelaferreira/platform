package com.me.miguelferreira.platform.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.me.miguelferreira.platform.data.repositories")
@EntityScan(basePackages = "com.me.miguelferreira.platform.model")
public class TestConfiguration {
}

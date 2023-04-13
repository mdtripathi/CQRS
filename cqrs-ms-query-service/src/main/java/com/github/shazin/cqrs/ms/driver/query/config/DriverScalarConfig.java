package com.github.shazin.cqrs.ms.driver.query.config;

import graphql.scalars.ExtendedScalars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class DriverScalarConfig implements RuntimeWiringConfigurer {
    private static final Logger LOG = LoggerFactory.getLogger(DriverScalarConfig.class);

    @Override
    public void configure(graphql.schema.idl.RuntimeWiring.Builder builder) {
        LOG.trace("configure( builder: {} )", builder);
        builder.scalar(ExtendedScalars.Date);
        builder.scalar(ExtendedScalars.DateTime);
    }
}

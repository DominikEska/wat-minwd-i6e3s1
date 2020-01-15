package com.Bikes;

import com.Bikes.dao.BikeHistoryDAO;
import com.Bikes.dao.StationHistoryDAO;
import com.Bikes.dao.StationHistoryMapper;
import com.Bikes.health.TemplateHealthCheck;
import com.Bikes.repository.VeturiloRepository;
import com.Bikes.resources.BikeResource;
import com.Bikes.resources.HelloWorldResource;
import com.Bikes.resources.StationResource;
import com.Bikes.resources.VeturiloResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.knowm.dropwizard.sundial.SundialBundle;
import org.knowm.dropwizard.sundial.SundialConfiguration;
import org.skife.jdbi.v2.DBI;

public class DropwizardBikeApplication extends Application<DropwizardBikeConfiguration> {

    public static VeturiloService VETURILO_SERVICE;

    public static void main(final String[] args) throws Exception {
        new DropwizardBikeApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardBike";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardBikeConfiguration> bootstrap) {
        bootstrap.addBundle(new SundialBundle<DropwizardBikeConfiguration>() {

            @Override
            public SundialConfiguration getSundialConfiguration(DropwizardBikeConfiguration configuration) {
                return configuration.getSundialConfiguration();
            }
        });
    }

    @Override
    public void run(final DropwizardBikeConfiguration configuration, final Environment environment) {

        final HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(helloWorldResource);

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgres");
        final BikeHistoryDAO daoBike = jdbi.onDemand(BikeHistoryDAO.class);
        final StationHistoryDAO daoStation = jdbi.onDemand(StationHistoryDAO.class);
        jdbi.registerMapper(new StationHistoryMapper());
        VETURILO_SERVICE = new VeturiloService(new VeturiloRepository(daoStation, daoBike));

        final VeturiloResource veturiloResource = new VeturiloResource(VETURILO_SERVICE);
        environment.jersey().register(veturiloResource);

        final StationResource stationResource = new StationResource(VETURILO_SERVICE);
        environment.jersey().register(stationResource);

        final BikeResource bikeResource = new BikeResource(VETURILO_SERVICE);
        environment.jersey().register(bikeResource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}

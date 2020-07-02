package local.nix.solid.project.solar.system.factory;

import local.nix.solid.project.solar.system.config.AnnotationConfigurator;
import local.nix.solid.project.solar.system.config.ApplicationConfiguration;
import local.nix.solid.project.solar.system.data.abstr.AbstractPlanet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PlanetFactory {

    private static PlanetFactory instance;
    private List<AnnotationConfigurator> configurators;
    private List<AbstractPlanet> planets;

    private PlanetFactory() {
        this.configurators = ApplicationConfiguration.getInstance().getConfigurators();
        this.planets = ApplicationConfiguration.getInstance().getPlanets();
    }

    public static PlanetFactory getInstance() {
        if (instance == null) {
            instance = new PlanetFactory();
        }
        return instance;
    }

    public AbstractPlanet createObject(String nameOfPlanet) {

       AbstractPlanet result = null;
        for(AbstractPlanet planet: planets) {
           if(nameOfPlanet.equals(planet.getClass().getSimpleName())) {
               try {
                   result = planet.getClass().getDeclaredConstructor().newInstance();
                   configure(AbstractPlanet.class, result);
               } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                   throw new RuntimeException("невозможно создать класс: " + e.getClass().getName() + " " + ",msg: " + e.getMessage());
               }
           }
       }

        return result;

    }

    private <T> void configure(Class<T> type, T t) {
        configurators.forEach(annotationConfigurator -> annotationConfigurator.configure(type, t));
    }
}

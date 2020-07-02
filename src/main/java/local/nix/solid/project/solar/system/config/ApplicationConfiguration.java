package local.nix.solid.project.solar.system.config;

import local.nix.solid.project.solar.system.data.abstr.AbstractPlanet;
import local.nix.solid.project.solar.system.factory.PlanetFactory;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationConfiguration {

    private static ApplicationConfiguration instance;
    private final List<AnnotationConfigurator> configurators = new ArrayList<>();
    private final List<AbstractPlanet> planets = new ArrayList<>();
    Reflections scanner = new Reflections("local.nix.solid.project.solar.system");

    private ApplicationConfiguration() {
        for (Class<? extends AnnotationConfigurator> aClass : scanner.getSubTypesOf(AnnotationConfigurator.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("невозможно создать класс: " + e.getClass().getName() + " " + ",msg: " + e.getMessage());
            }
        }
        for (Class<? extends AbstractPlanet> aClass : scanner.getSubTypesOf(AbstractPlanet.class)) {
            try {
                planets.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("невозможно создать класс: " + e.getClass().getName() + " " + ",msg: " + e.getMessage());
            }
        }
    }

    public static ApplicationConfiguration getInstance() {
        if (instance == null) {
            instance = new ApplicationConfiguration();
        }
        return instance;
    }

    public List<AbstractPlanet> getPlanets() {
        return planets;
    }

    public List<AnnotationConfigurator> getConfigurators() {
        return configurators;
    }
}

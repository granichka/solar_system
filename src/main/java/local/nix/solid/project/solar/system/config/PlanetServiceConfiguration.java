package local.nix.solid.project.solar.system.config;

import local.nix.solid.project.solar.system.annotation.Autowired;
import local.nix.solid.project.solar.system.data.abstr.AbstractPlanet;
import local.nix.solid.project.solar.system.factory.PlanetFactory;
import local.nix.solid.project.solar.system.service.PlanetReporter;

import java.lang.reflect.Field;

public class PlanetServiceConfiguration {

    public static <T> void configure(T t, String planet) {
        for(Field field : t.getClass().getDeclaredFields()) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                field.setAccessible(true);
                try {
                    AbstractPlanet planetToAutowired = PlanetFactory.getInstance().createObject(planet);
                    field.set(t, planetToAutowired);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Возникли проблемы с инициализацией филда: " + e.getMessage());
                }
            }
        }
    }

}

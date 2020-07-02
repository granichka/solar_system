package local.nix.solid.project.solar.system.factory;

import local.nix.solid.project.solar.system.annotation.PlanetService;
import local.nix.solid.project.solar.system.config.PlanetServiceConfiguration;

import java.lang.reflect.InvocationTargetException;

public class PlanetServiceFactory {

    private static PlanetServiceFactory instance;

    private PlanetServiceFactory() {
    }

    public static PlanetServiceFactory getInstance() {
        if (instance == null) {
            instance = new PlanetServiceFactory();
        }
        return instance;
    }

    public <T> T  createObject(Class<T> classOfPlanetService, String nameOfPlanet) {
        T t = null;
        if(classOfPlanetService.isAnnotationPresent(PlanetService.class)) {
            try {
                t = classOfPlanetService.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            configure(t, nameOfPlanet);
        } else {
            throw new IllegalArgumentException("переданный класс не является сервисом, работающим с планетами");
        }

        return t;
    }

    private <T> void configure(T t, String nameOfPlanet) {
        PlanetServiceConfiguration.configure(t, nameOfPlanet);
    }


}

package local.nix.solid.project.solar.system;

import local.nix.solid.project.solar.system.factory.PlanetServiceFactory;
import local.nix.solid.project.solar.system.service.PlanetReporter;

import java.lang.reflect.InvocationTargetException;


public class Main {

    public static  void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        PlanetServiceFactory.getInstance().createObject(PlanetReporter.class, "Earth").getInformationAboutAccelerationDueToGravityValueOfPlanet();


    }
}

package local.nix.solid.project.solar.system.service;
import local.nix.solid.project.solar.system.annotation.Autowired;
import local.nix.solid.project.solar.system.annotation.PlanetService;
import local.nix.solid.project.solar.system.data.abstr.AbstractPlanet;

@PlanetService
public class PlanetReporter {

    @Autowired
    private AbstractPlanet planet;

    public  void getInformationAboutAccelerationDueToGravityValueOfPlanet(){
        System.out.println("Ускорение свободного падения " + "на планете " + planet.getName() + " равняется " + planet.getAccelerationDueToGravityValue());
    }
}

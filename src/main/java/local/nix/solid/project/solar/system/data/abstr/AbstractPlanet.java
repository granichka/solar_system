package local.nix.solid.project.solar.system.data.abstr;

import local.nix.solid.project.solar.system.annotation.Value;

public abstract class AbstractPlanet implements Planet {

    public static final double  GRAVITATIONAL_CONSTANT = 6.67E-11;

    @Value
    private String name;

    @Value
    private double averageDistanceFromTheSun;

    @Value
    private double mass;

    @Value
    private double radius;

    @Value
    private double rotationPeriodAroundTheAxis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageDistanceFromTheSun() {
        return averageDistanceFromTheSun;
    }

    public void setAverageDistanceFromTheSun(double averageDistanceFromTheSun) {
        this.averageDistanceFromTheSun = averageDistanceFromTheSun;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRotationPeriodAroundTheAxis() {
        return rotationPeriodAroundTheAxis;
    }

    public void setRotationPeriodAroundTheAxis(double rotationPeriodAroundTheAxis) {
        this.rotationPeriodAroundTheAxis = rotationPeriodAroundTheAxis;
    }

    public static double getGravitationalConstant() {
        return GRAVITATIONAL_CONSTANT;
    }

    @Override
    public double getAccelerationDueToGravityValue() {
        return (GRAVITATIONAL_CONSTANT*mass)/(radius*radius);
    }
}

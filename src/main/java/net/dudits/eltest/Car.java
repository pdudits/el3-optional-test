package net.dudits.eltest;

import java.util.Optional;

public class Car {
    private final String plate;
    private Insurance insurance;

    private Car(String plate) {
        this.plate = plate;
    }

    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }

    public String getPlate() {
        return plate;
    }

    static Car uninsured(String plate) {
        return new Car(plate);
    }

    static Car insured(String plate, Insurance i) {
        Car c = new Car(plate);
        c.insurance = i;
        return c;
    }
}

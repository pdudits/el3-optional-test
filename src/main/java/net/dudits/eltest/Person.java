package net.dudits.eltest;

import java.util.Optional;

public class Person {
    private final String name;
    private Car car;

    private Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }

    public Optional<String> getCarLicensePlate() {
        return getCar().map(Car::getPlate);
    }

    public Optional<String> getInsuranceNumber() {
        return getCar().flatMap(Car::getInsurance).map(Insurance::getNumber);
    }

    static Person withoutCar(String name) {
        Person p = new Person(name);
        return p;
    }

    static Person withCar(String name, Car c) {
        Person p = new Person(name);
        p.car = c;
        return p;
    }
}

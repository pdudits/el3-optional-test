package net.dudits.eltest;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

@Model
public class InsuranceOverview {
    private List<Person> people = new ArrayList<>();

    @PostConstruct
    void init() {
        people.add(Person.withoutCar("Carlo Carless"));
        people.add(Person.withCar("Lucky Luke", Car.uninsured("LUCKY0")));
        people.add(Person.withCar("Bob", Car.insured("BOBCAR",new Insurance("01293812"))));
    }

    public List<Person> getPeople() {
        return people;
    }
}

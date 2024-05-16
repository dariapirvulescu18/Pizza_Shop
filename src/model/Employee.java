package model;

import java.util.Set;
import java.util.Vector;

public class Employee {
    private String name;
    private int age;
    private int salary;
    private Vector<Integer> rating;
    private Set<String> skills;
    Employee(String name, int age){
        this.age=age;
        this.name=name;
    }

    void rateEmployee(int rating){
        this.rating.add(rating);
    }

    public String getName() {
        return name;
    }

    void addSkill(String skill){
        this.skills.add(skill);
    }
}

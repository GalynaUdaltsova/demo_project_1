package com.softserve.task4.models;

public class Man extends Human {
    public Man(String firstName, String lastName, float height, float weight) {
        super(true, firstName, lastName, height, weight);
    }

    public Human executeMission(Human human) {
        if (human.isGender() && this.speak(human) && this.spendTimeTogether(human)) {
            System.out.println("Birth of a son");
            System.out.println("Build a house");
            return null;
        } else if (!human.isGender() && this.speak(human) && this.spendTimeTogether(human)) {
            System.out.println("Build a house");
            return null;
        } else if (human.isGender() && this.speak(human) && !this.tolerate(human)) {
            System.out.println("Birth of a son");
            System.out.println("Plant a tree");
            return null;
        } else if (this.speak(human) && !this.tolerate(human)) {
            System.out.println("Plant a tree");
            return null;
        } else if (human.isGender()) {
            System.out.println("Birth of a son");
            return null;
        }
        else return null;
    }
}

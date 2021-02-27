package com.softserve.task4.models;

public class Man extends Human {
    public Man(String firstName, String lastName, float height, float weight) {
        super(true, firstName, lastName, height, weight);
    }

    public Human executeMission(Human human) {

        if (this.speak(human) && this.spendTimeTogether(human) && !this.tolerate(human)) {
            System.out.println("Build a house");
        } else if (this.speak(human) && !this.tolerate(human)) {
            System.out.println("Plant a tree");
        } else {
            if (this.getGender() && !human.getGender()) {
                Human child = ((Woman) human).giveBirthTo(this);
                if (child.getGender()) {
                    System.out.println("Birth of a son");
                } else {
                    return child;
                }
            } else {
                return null;
            }
        }

        return null;
    }

}

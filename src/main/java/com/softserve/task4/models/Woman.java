package com.softserve.task4.models;

public class Woman extends Human {
    public Woman(String firstName, String lastName, float height, float weight) {
        super(false, firstName, lastName, height, weight);
    }

    public Human giveBirthTo(Human human) {
        System.out.println("You are a perfect match for each other! I bet you'll have such a lovely child! \n" + getFullName(this)  + " is giving birth to a child...");
        boolean isBoy = Math.random() <= 0.5;
        String firstName = "NoName";
        String lastName = human.getLastName();
        float height;
        float weight;
        if (isBoy) {
            height = human.getHeight() + 0.1f * (this.getHeight() - human.getHeight());
            weight = human.getWeight() + 0.1f * (this.getWeight() - human.getWeight());
            return new Man(firstName, lastName, height, weight);
        } else {
            height = this.getHeight() + 0.1f * (human.getHeight() - this.getHeight());
            weight = this.getWeight() + 0.1f * (human.getWeight() - this.getWeight());
            return new Woman(firstName, lastName, height, weight);
        }
    }

}


package com.softserve.task4.models;

import java.util.Scanner;

public class Woman extends Human {
    public Woman(String firstName, String lastName, float height, float weight) {
        super(false, firstName, lastName, height, weight);
    }
//Ann:
    public Human giveBirthTo(Human human) {
        boolean isBoy = Math.random() <= 0.5;
        String firstName;
        Scanner userInput = new Scanner(System.in);
        String lastName = human.getLastName();
        float height;
        float weight;

        if (isBoy) {
            System.out.println("Congratulations! You have a BOY! Please give him a name:");
            firstName = userInput.nextLine();
            height = human.getHeight() + 0.1f * (this.getHeight() - human.getHeight());
            weight = human.getWeight() + 0.1f * (this.getWeight() - human.getWeight());
            return new Man(firstName, lastName, height, weight);
        } else {
            System.out.println("Congratulations! You have a GIRL! Please give her a name:");
            firstName = userInput.nextLine();
            height = this.getHeight() + 0.1f * (human.getHeight() - this.getHeight());
            weight = this.getWeight() + 0.1f * (human.getWeight() - this.getWeight());
            return new Woman(firstName, lastName, height, weight);
        }

    }
}


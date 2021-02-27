package com.softserve.task4.models;

public class Human {
    protected boolean gender;
    protected String firstName;
    protected String lastName;
    protected float height;
    protected float weight;

    public Human(boolean gender) {
        this.gender = gender;
    }

    public Human(boolean gender, String firstName, String lastName, float height, float weight) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }


    public boolean speak(Human human) {
        if (!this.getGender() && !human.getGender()) {
            return true;
        } else if (!this.getGender() && human.getGender()) {
            return true;
        } else if (this.getGender() && !human.getGender()) {
            return true;
        } else {
            return Math.random() <= 0.5;
        }
    }

    protected boolean tolerate(Human human) {
        if (this.gender && human.gender) {
            //if both male
            return Math.random() <= 0.056;
        } else if (!this.gender && !human.gender) {
            //if both female
            return Math.random() <= 0.05;
        } else {
            return Math.random() <= 0.7;
        }
    }

    protected float percentDifference(float n1, float n2) {
        return ((n1 - n2) / ((n1 + n2) / 2)) * 100;
    }

    protected boolean spendTimeTogether(Human human) {
        if ((percentDifference(this.getHeight(), human.getHeight())) > 10) {
            return Math.random() <= 0.95;
        } else {
            return Math.random() <= 0.85;
        }
    }

    public Human haveRelationship(Human human) {
        if (this.gender == human.gender) {
            return null;
        }
        if (this.speak(human) && this.tolerate(human) && this.spendTimeTogether(human)) {
            Human child = this.getGender() ? ((Woman) human).giveBirthTo(this) : ((Woman) this).giveBirthTo(human);
            return child;
        }
       return null;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String whatGender() {
        return this.getGender() ? "male" : "female";
    }

    public static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String getFullName(Human human) {
        return capitalizeFirstLetter(human.getFirstName()) + " " + capitalizeFirstLetter(human.getLastName());
    }

    @Override
    public String toString() {
        return "{" +
                "gender: " + whatGender() +
                ", firstName: " + capitalizeFirstLetter(firstName) +
                ", lastName: " + capitalizeFirstLetter(lastName) +
                ", height: " + height +
                ", weight: " + weight +
                '}';
    }

}

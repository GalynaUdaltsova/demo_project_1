package com.softserve.task4.models;

public class Human {
    protected boolean gender;
    protected String firstName;
    protected String lastName;
    protected float height;
    protected float weight;

    public Human(boolean gender, String firstName, String lastName, float height, float weight) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }

    public boolean speak(Human human) {

        return true;
    }

    protected boolean tolerate(Human human) {

        return true;
    }

    public float percentDifference(float n1, float n2) {

        return ((n1 - n2) / ((n1 + n2) / 2)) * 100;
    }

    public boolean spendTimeTogether(Human human) {
        if ((percentDifference(this.getHeight(), human.getHeight())) > 10) {
            return Math.random() <= 0.95;
        } else {
            return Math.random() <= 0.85;
        }
    }

    public Human haveRelationship(Human human) {

        return null;
    }

    public boolean isGender() {
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

    @Override
    public String toString() {
        return "task_at_4.models.Human{" +
                "gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}

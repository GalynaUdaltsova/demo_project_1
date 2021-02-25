package com.softserve.task4.service;

import com.softserve.task4.models.Human;

import static com.softserve.task4.models.Human.getFullName;

public class HumanService {

    public static void compatibilityTest(Human humanOne, Human humanTwo) {
        System.out.println("Checking if " + getFullName(humanOne) + " and " + getFullName(humanTwo) + " may have relationship...");
        humanOne.haveRelationship(humanTwo);
    }

}

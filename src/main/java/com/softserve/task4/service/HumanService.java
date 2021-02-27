package com.softserve.task4.service;

import com.softserve.task4.models.Human;

import static com.softserve.task4.models.Human.getFullName;

public class HumanService {

    public Human compatibilityTest(Human firstHuman, Human secondHuman) {
        return firstHuman.haveRelationship(secondHuman);
    }

}

package com.softserve.task4.service;

import com.softserve.task4.models.Human;
import com.softserve.task4.models.Man;

public class HumanService {

    public Human compatibilityTest(Human firstHuman, Human secondHuman) {
        return firstHuman.haveRelationship(secondHuman);
    }

    public Human executeMansMission(Human firstHuman, Human secondHuman) {
        if (firstHuman.getGender()) {
            return ((Man) firstHuman).executeMission(secondHuman);
        }
        if (secondHuman.getGender()) {
            return ((Man) secondHuman).executeMission(firstHuman);
        }
        return null;
    }
}

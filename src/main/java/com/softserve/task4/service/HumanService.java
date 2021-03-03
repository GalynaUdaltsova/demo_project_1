package com.softserve.task4.service;

import com.softserve.task4.models.Human;
import com.softserve.task4.models.Man;
import com.softserve.task4.models.MissionResponse;
import com.softserve.task4.models.RelationResponse;

import java.util.ArrayList;
import java.util.List;

public class HumanService {

    public RelationResponse compatibilityTest(Human firstHuman, Human secondHuman) {
        return firstHuman.haveRelationship(secondHuman);
    }

    public List<MissionResponse> executeMansMission(Human firstHuman, Human secondHuman, RelationResponse relationResponse) {
        List<MissionResponse> responses = new ArrayList<>();
        if (firstHuman.getGender()) {
            responses.add(((Man) firstHuman).executeMission(secondHuman, relationResponse));
        }
        if (secondHuman.getGender()) {
            responses.add(((Man) secondHuman).executeMission(firstHuman, relationResponse));
        }
        return responses;
    }
}

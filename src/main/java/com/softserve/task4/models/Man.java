package com.softserve.task4.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Man extends Human {

    public Man() {
        super(true);
    }

    public Man(String firstName, String lastName, float height, float weight) {
        super(true, firstName, lastName, height, weight);
    }

    public MissionResponse executeMission(Human human, Map<String, Object> resultRelationship) {
        boolean resultSpeak = (Boolean) resultRelationship.get("speak");
        boolean resultSpend = (Boolean) resultRelationship.get("spend");
        boolean resultTolerate = (Boolean) resultRelationship.get("tolerate");
        Human child = (Human) resultRelationship.get("child");

        List<String> actions = new ArrayList<>();
        if (resultSpeak && resultSpend) {
            actions.add("The house was built");
        }
        if (resultSpeak && !resultTolerate) {
            actions.add("The tree was planted");
        }
        MissionResponse missionResponse = new MissionResponse(this, actions);
        if (!human.getGender() && child.getGender()) {
            missionResponse.setMissionResult("The son was born!  The mission is executed");
        }
        return missionResponse;
    }
}

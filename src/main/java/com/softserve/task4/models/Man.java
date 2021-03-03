package com.softserve.task4.models;

import java.util.ArrayList;
import java.util.List;

public class Man extends Human {

    public Man() {
        super(true);
    }

    public Man(String firstName, String lastName, float height, float weight) {
        super(true, firstName, lastName, height, weight);
    }

    public MissionResponse executeMission(Human human, RelationResponse relationResponse) {
        boolean resultSpeak = relationResponse.isSpeakResult();
        boolean resultSpend = relationResponse.isSpendResult();
        boolean resultTolerate = relationResponse.isTolerateResult();
        Human child = relationResponse.getChild();

        List<String> actions = new ArrayList<>();
        if (resultSpeak && resultSpend) {
            actions.add("The house was built");
        }
        if (resultSpeak && !resultTolerate) {
            actions.add("The tree was planted");
        }
        MissionResponse missionResponse = new MissionResponse(this, actions);
        if (!human.getGender() && child != null && child.getGender()) {
            missionResponse.setMissionResult("The son was born!  The mission is executed");
        }
        return missionResponse;
    }
}

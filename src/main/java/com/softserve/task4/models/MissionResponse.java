package com.softserve.task4.models;

import java.util.List;

public class MissionResponse {
    private List<String> performedActions;
    private String missionResult;
    private Human human;

    public MissionResponse(Human human, List<String> performedActions) {
        this.performedActions = performedActions;
        this.human = human;
    }

    public List<String> getPerformedActions() {
        return performedActions;
    }

    public void setPerformedActions(List<String> performedActions) {
        this.performedActions = performedActions;
    }

    public String getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(String missionResult) {
        this.missionResult = missionResult;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}

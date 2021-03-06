package com.softserve.task4.service;

import com.softserve.task4.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleService implements ITesterService {

    private HumanService humanService;

    public ConsoleService(HumanService humanService) {
        this.humanService = humanService;
    }

    public void test() {
        List<Human> dataFromConsole = getDataFromConsole();
        RelationResponse relationResponse = humanService.compatibilityTest(dataFromConsole.get(0), dataFromConsole.get(1));
        List<MissionResponse> responses = humanService.executeMansMission(dataFromConsole.get(0), dataFromConsole.get(1),
                relationResponse);
        Human child = relationResponse.getChild();
        showLogs(relationResponse);
        showManActions(responses);
        if (child == null) {
            System.out.println("Nothing is happened...");
            return;
        }
        String childGender = child.getGender() ? "son" : "daughter";
        String childFirstName = getHumanData(String.format("Enter name for %s", childGender));
        System.out.println(String.format("Congratulations! We have a %s with following data: " +
                        "\nFirst name: %s, \nLast name: %s, \nHeight: %s, \nWeight: %s", childGender, childFirstName,
                child.getLastName(), child.getHeight(), child.getWeight()));
        if (child.getGender()) {
            System.out.println("The son was born, the mission is executed");
            return;
        }
        System.out.println("The son was not born, the mission is failed");
    }

    private void showManActions(List<MissionResponse> responses) {
        for (MissionResponse response : responses) {
            System.out.println("Mission result for " + response.getHuman().getFirstName() + ":");
            if (response.getPerformedActions().isEmpty()) {
                System.out.println("No action executed...");
                continue;
            }
            for (String action : response.getPerformedActions()) {
                System.out.println(action);
            }
        }
    }

    private void showLogs(RelationResponse relationResponse) {
        try {
            System.out.println(relationResponse.getSpeakResultMessage());
            Thread.sleep(2000);
            System.out.println(relationResponse.getTolerateResultMessage());
            Thread.sleep(2000);
            System.out.println(relationResponse.getSpendResultMessage());
        } catch (InterruptedException ex) {
            System.out.println("The error occurred while logs writing");
        }
    }

    private List<Human> getDataFromConsole() {
        List<Human> humans = new ArrayList<>();
        String humanOrder = "first";
        for (int i = 0; i < 2; i++) {
            System.out.println(String.format("Enter %s person info", humanOrder));
            String gender = getHumanGender();
            String firstName = getHumanData("Enter the first name:");
            String lastName = getHumanData("Enter the last name:");
            float humanHeight = getHumanMeasure("Enter the height:");
            float humanWeight = getHumanMeasure("Enter the weight:");
            if (gender.equalsIgnoreCase("male")) {
                humans.add(new Man(firstName, lastName, humanHeight, humanWeight));
            } else {
                humans.add(new Woman(firstName, lastName, humanHeight, humanWeight));
            }
            humanOrder = "second";
        }
        return humans;
    }

    private String getHumanGender() {
        while (true) {
            String gender = getHumanData("Enter the gender:");
            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                return gender;
            }
            System.out.println("Please enter the valid gender");
        }
    }

    private float getHumanMeasure(String message) {
        while (true) {
            try {
                String humanData = getHumanData(message);
                float measure = Float.parseFloat(humanData);
                if (measure <= 0) {
                    System.out.println("Please enter valid positive float number");
                    continue;
                }
                return measure;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid float number");
            }
        }
    }

    private String getHumanData(String message) {
        System.out.println(message);
        Scanner scanData = new Scanner(System.in);
        return scanData.next();
    }
}

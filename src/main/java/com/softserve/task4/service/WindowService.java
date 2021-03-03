package com.softserve.task4.service;

import com.softserve.task4.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WindowService implements ITesterService {

    private static final String MALE = "Male";
    private static final String FEMALE = "Female";
    private static final String WEIGHT = "Weight";
    private static final String HEIGHT = "Height";
    private static final String LAST_NAME = "Last Name";
    private static final String FIRST_NAME = "First Name";
    private static final String GENDER = "Gender";
    private static final int FIRST_LABEL_X_START = 310;
    private static final int SECOND_LABEL_X_START = 550;
    private static final int FIRST_TEXT_X_START = 320;
    private static final int SECOND_TEXT_X_START = 670;
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_HEIGHT = 30;
    private static final int TEXT_WIDTH = 200;
    private static final int FONT_18 = 18;
    private static final int FONT_TEXT_FRAME = 20;
    private static final int WIDTH_BUTTON = 160;
    private static final int HEIGHT_BUTTON = 40;

    private JButton resultButton;
    private JButton failedResultButton;
    private JButton closeFailedButton;
    private JButton closeChildResultButton;
    private JButton childNameOk;
    private JButton compatibilitySubmitButton;
    private JButton clearButton;
    private HumanService humanService;
    private JFrame failedResultFrame;
    private JFrame displayProcessFrame;
    private JFrame failedProcessFrame;
    private JFrame childNameFrame;
    private JFrame childResultFrame;
    private JTextField childNameText;
    private JTextField firstNameFirstText;
    private JTextField lastNameFirstText;
    private JTextField weightFirstText;
    private JTextField heightFirstText;
    private JTextField firstNameSecondText;
    private JTextField lastNameSecondText;
    private JTextField weightSecondText;
    private JTextField heightSecondText;
    private JComboBox<String> genderSecond;
    private JComboBox<String> genderFirst;
    private Human firstHuman;
    private Human secondHuman;

    public WindowService(HumanService humanService) {
        this.humanService = humanService;
    }

    public void test() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    initializeButtons();
                    initializeListeners();
                    initializeFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initializeButtons() {
        compatibilitySubmitButton = new JButton("CHECK COMPATIBILITY");
        compatibilitySubmitButton.setBackground(new Color(255, 231, 179));
        compatibilitySubmitButton.setForeground(Color.BLACK);
        compatibilitySubmitButton.setBounds(445, 430, 300, 45);
        Font compatibilityFont = new Font(Font.DIALOG, Font.PLAIN, 21);
        compatibilitySubmitButton.setFocusable(false);
        compatibilitySubmitButton.setFont(compatibilityFont);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(515, 550, WIDTH_BUTTON, HEIGHT_BUTTON);
        Font clearFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        clearButton.setFocusable(false);
        clearButton.setFont(clearFont);

        closeFailedButton = new JButton("CLOSE");
        closeFailedButton.setBounds(270, 180, WIDTH_BUTTON - 30, HEIGHT_BUTTON);
        Font closeFailedBFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        closeFailedButton.setFocusable(false);
        closeFailedButton.setFont(closeFailedBFont);

        resultButton = new JButton("RESULT");
        resultButton.setBounds(FIRST_LABEL_X_START, 450, WIDTH_BUTTON, HEIGHT_BUTTON);
        Font resultButtonFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        resultButton.setFocusable(false);
        resultButton.setFont(resultButtonFont);

        failedResultButton = new JButton("RESULT");
        failedResultButton.setBounds(FIRST_LABEL_X_START, 450, WIDTH_BUTTON, HEIGHT_BUTTON);
        failedResultButton.setFocusable(false);
        failedResultButton.setFont(resultButtonFont);

        closeChildResultButton = new JButton("CLOSE");
        closeChildResultButton.setBounds(FIRST_LABEL_X_START, 350, WIDTH_BUTTON, HEIGHT_BUTTON);
        Font closeChildResultFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        closeChildResultButton.setFocusable(false);
        closeChildResultButton.setFont(closeChildResultFont);

        childNameOk = new JButton("OK");
        childNameOk.setBounds(FIRST_LABEL_X_START, 320, WIDTH_BUTTON, HEIGHT_BUTTON);
        Font childNameOkFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        childNameOk.setFocusable(false);
        childNameOk.setFont(childNameOkFont);
    }

    private void initializeListeners() {
        compatibilitySubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String firstName = firstNameFirstText.getText();
                String lastName = lastNameFirstText.getText();
                String heightString = heightFirstText.getText();
                String weighString = weightFirstText.getText();
                boolean genderFirst = ((String) WindowService.this.genderFirst.getSelectedItem()).equalsIgnoreCase(MALE) ? true : false;
                firstHuman = buildEmptyHuman(genderFirst);
                firstHuman.setFirstName(firstName);
                firstHuman.setLastName(lastName);
                firstHuman.setHeight(Float.parseFloat(heightString));
                firstHuman.setWeight(Float.parseFloat(weighString));

                String firstNameSecond = firstNameSecondText.getText();
                String lastNameSecond = lastNameSecondText.getText();
                String heightStringSecond = heightSecondText.getText();
                String weighStringSecond = weightSecondText.getText();
                boolean genderSecondBool = ((String) genderSecond.getSelectedItem()).equalsIgnoreCase(MALE) ? true : false;
                secondHuman = buildEmptyHuman(genderSecondBool);
                secondHuman.setFirstName(firstNameSecond);
                secondHuman.setLastName(lastNameSecond);
                secondHuman.setHeight(Float.parseFloat(heightStringSecond));
                secondHuman.setWeight(Float.parseFloat(weighStringSecond));

                RelationResponse relationResponse = humanService.compatibilityTest(firstHuman, secondHuman);
                Human child = relationResponse.getChild();
                if (child == null) {
                    displayFailedProcesses(relationResponse);
                    return;
                }
                initializeResultButton(child);
                displayResultProcesses(relationResponse);
            }
        });

        closeFailedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                failedResultFrame.dispose();
            }
        });

        failedResultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                failedProcessFrame.dispose();
                displayFailedCompatibilityFrame();
            }
        });

        closeChildResultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                childResultFrame.dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weightSecondText.setText("0");
                heightSecondText.setText("0");
                lastNameSecondText.setText(null);
                firstNameSecondText.setText(null);
                firstNameFirstText.setText(null);
                lastNameFirstText.setText(null);
                heightFirstText.setText("0");
                weightFirstText.setText("0");
                genderFirst.setSelectedItem(MALE);
                genderSecond.setSelectedItem(FEMALE);
            }
        });
    }

    private void displayResultProcesses(RelationResponse relationResponse) {
        displayProcessFrame = new JFrame();
        displayProcessFrame.setVisible(true);
        displayProcessFrame.setBounds(500, 230, 800, 550);
        displayProcessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayProcessFrame.getContentPane().setLayout(null);

        JLabel processLabel = new JLabel("Activities process...");
        processLabel.setBounds(FIRST_LABEL_X_START - 50, 20, 250, 50);
        processLabel.setForeground(new Color(0, 0, 128));
        Font processLabelFont = new Font(Font.DIALOG, Font.BOLD, 26);
        processLabel.setFont(processLabelFont);
        displayProcessFrame.getContentPane().add(processLabel);

        displayProcessFrame.getContentPane().add(resultButton);
        displayProcessFrame.add(getProcessesMessage(relationResponse));
        List<MissionResponse> responses = humanService.executeMansMission(firstHuman, secondHuman, relationResponse);
        displayProcessFrame.getContentPane().add(getMissionMessage(responses));
    }

    private void displayFailedProcesses(RelationResponse relationResponse) {
        failedProcessFrame = new JFrame();
        failedProcessFrame.setVisible(true);
        failedProcessFrame.setBounds(500, 230, 800, 550);
        failedProcessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        failedProcessFrame.getContentPane().setLayout(null);

        JLabel processFailedLabel = new JLabel("Activities process...");
        processFailedLabel.setBounds(FIRST_LABEL_X_START - 50, 20, 250, 50);
        processFailedLabel.setForeground(new Color(0, 0, 128));
        Font processFailedLabelFont = new Font(Font.DIALOG, Font.BOLD, 26);
        processFailedLabel.setFont(processFailedLabelFont);
        failedProcessFrame.getContentPane().add(processFailedLabel);

        failedProcessFrame.getContentPane().add(failedResultButton);
        failedProcessFrame.add(getProcessesMessage(relationResponse));
        List<MissionResponse> responses = humanService.executeMansMission(firstHuman, secondHuman, relationResponse);
        failedProcessFrame.getContentPane().add(getMissionMessage(responses));
    }

    private void initializeResultButton(Human child) {
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProcessFrame.dispose();
                boolean gender = child.getGender();
                String childGender = gender ? "SON" : "DAUGHTER";
                addChildNameListener(child, childGender);
                displayChildNameFrame(childGender);
            }
        });
    }

    private void initializeFrame() {
        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setBounds(300, 100, 1210, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        ImageIcon imageManIcon = new ImageIcon(new ImageIcon("src/main/resources/imageMainMan.jpg")
                .getImage().getScaledInstance(280, 320, Image.SCALE_DEFAULT));
        JLabel imageManLabel = new JLabel(imageManIcon);
        imageManLabel.setIcon(imageManIcon);
        imageManLabel.setBounds(10, 60, 300, 400);
        mainFrame.add(imageManLabel);

        ImageIcon imageWomanIcon = new ImageIcon(new ImageIcon("src/main/resources/imageMainWoman.jpg")
                .getImage().getScaledInstance(280, 320, Image.SCALE_DEFAULT));
        JLabel imageWomanLabel = new JLabel(imageWomanIcon);
        imageWomanLabel.setIcon(imageWomanIcon);
        imageWomanLabel.setBounds(880, 60, 300, 400);
        mainFrame.add(imageWomanLabel);

        genderFirst = new JComboBox<>();
        genderFirst.addItem(MALE);
        genderFirst.addItem(FEMALE);
        genderFirst.setBounds(FIRST_TEXT_X_START, 160, TEXT_WIDTH, TEXT_HEIGHT);
        Font genderFirstFont = new Font(Font.DIALOG, Font.BOLD, FONT_TEXT_FRAME);
        genderFirst.setFont(genderFirstFont);
        mainFrame.getContentPane().add(genderFirst);

        firstNameFirstText = new JTextField();
        firstNameFirstText.setBounds(FIRST_TEXT_X_START, 200, TEXT_WIDTH, TEXT_HEIGHT);
        Font firstNameFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        firstNameFirstText.setFont(firstNameFirstTextFont);
        mainFrame.getContentPane().add(firstNameFirstText);
        firstNameFirstText.setColumns(15);

        lastNameFirstText = new JTextField();
        lastNameFirstText.setBounds(FIRST_TEXT_X_START, 240, TEXT_WIDTH, TEXT_HEIGHT);
        Font lastNameFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        lastNameFirstText.setFont(lastNameFirstTextFont);
        mainFrame.getContentPane().add(lastNameFirstText);
        lastNameFirstText.setColumns(15);

        heightFirstText = new JFormattedTextField(0);
        heightFirstText.setBounds(FIRST_TEXT_X_START, 280, TEXT_WIDTH, TEXT_HEIGHT);
        Font heightFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        heightFirstText.setFont(heightFirstTextFont);
        mainFrame.getContentPane().add(heightFirstText);
        heightFirstText.setColumns(5);

        weightFirstText = new JFormattedTextField(0);
        weightFirstText.setBounds(FIRST_TEXT_X_START, 320, TEXT_WIDTH, TEXT_HEIGHT);
        Font weightFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        weightFirstText.setFont(weightFirstTextFont);
        mainFrame.getContentPane().add(weightFirstText);
        weightFirstText.setColumns(3);

        genderSecond = new JComboBox<>();
        genderSecond.addItem(FEMALE);
        genderSecond.addItem(MALE);
        genderSecond.setBounds(SECOND_TEXT_X_START, 160, TEXT_WIDTH, TEXT_HEIGHT);
        Font genderSecondFont = new Font(Font.DIALOG, Font.BOLD, FONT_TEXT_FRAME);
        genderSecond.setFont(genderSecondFont);
        mainFrame.getContentPane().add(genderSecond);

        firstNameSecondText = new JTextField();
        firstNameSecondText.setBounds(SECOND_TEXT_X_START, 200, TEXT_WIDTH, TEXT_HEIGHT);
        Font firstNameSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        firstNameSecondText.setFont(firstNameSecondTextFont);
        mainFrame.getContentPane().add(firstNameSecondText);
        firstNameSecondText.setColumns(15);

        lastNameSecondText = new JTextField();
        lastNameSecondText.setBounds(SECOND_TEXT_X_START, 240, TEXT_WIDTH, TEXT_HEIGHT);
        Font lastNameSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        lastNameSecondText.setFont(lastNameSecondTextFont);
        mainFrame.getContentPane().add(lastNameSecondText);
        lastNameSecondText.setColumns(15);

        heightSecondText = new JFormattedTextField(0);
        heightSecondText.setBounds(SECOND_TEXT_X_START, 280, TEXT_WIDTH, TEXT_HEIGHT);
        Font heightSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        heightSecondText.setFont(heightSecondTextFont);
        mainFrame.getContentPane().add(heightSecondText);
        heightSecondText.setColumns(5);

        weightSecondText = new JFormattedTextField(0);
        weightSecondText.setBounds(SECOND_TEXT_X_START, 320, TEXT_WIDTH, TEXT_HEIGHT);
        Font weightSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        weightSecondText.setFont(weightSecondTextFont);
        mainFrame.getContentPane().add(weightSecondText);
        weightSecondText.setColumns(3);

        JLabel genderLabel = new JLabel(GENDER);
        genderLabel.setBounds(SECOND_LABEL_X_START + 12, 160, LABEL_WIDTH, 18);
        Font genderSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        genderLabel.setFont(genderSecondLabelFont);
        mainFrame.getContentPane().add(genderLabel);

        JLabel firstNameLabel = new JLabel(FIRST_NAME);
        firstNameLabel.setBounds(SECOND_LABEL_X_START, 200, LABEL_WIDTH, 17);
        Font firstNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        firstNameLabel.setFont(firstNameSecondLabelFont);
        mainFrame.getContentPane().add(firstNameLabel);

        JLabel lastNameLabel = new JLabel(LAST_NAME);
        lastNameLabel.setBounds(SECOND_LABEL_X_START, 240, LABEL_WIDTH, 19);
        Font lastNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        lastNameLabel.setFont(lastNameSecondLabelFont);
        mainFrame.getContentPane().add(lastNameLabel);

        JLabel heightLabel = new JLabel(HEIGHT);
        heightLabel.setBounds(SECOND_LABEL_X_START + 15, 280, LABEL_WIDTH, 21);
        Font heightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        heightLabel.setFont(heightSecondLabelFont);
        mainFrame.getContentPane().add(heightLabel);

        JLabel weightLabel = new JLabel(WEIGHT);
        weightLabel.setBounds(SECOND_LABEL_X_START + 15, 320, LABEL_WIDTH, 23);
        Font weightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_18);
        weightLabel.setFont(weightSecondLabelFont);
        mainFrame.getContentPane().add(weightLabel);

        JLabel titleLabel = new JLabel("COMPATIBILITY TEST");
        titleLabel.setBounds(430, 60, 400, 35);
        Font titleLabelFont = new Font(Font.DIALOG, Font.BOLD, 32);
        titleLabel.setFont(titleLabelFont);
        mainFrame.getContentPane().add(titleLabel);
        mainFrame.getContentPane().add(compatibilitySubmitButton);
        mainFrame.getContentPane().add(clearButton);
    }

    private void addChildNameListener(Human humanChild, String childGender) {
        childNameOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String childName = childNameText.getText();
                humanChild.setFirstName(childName);
                childNameFrame.dispose();
                String resultMessage = String.format("<html><body>THE DATA ABOUT A %s:<br>" +
                                "First name: %s<br>Last name: %s<br>Weight: %s<br>Height: %s</body></html>",
                        childGender, humanChild.getFirstName(), humanChild.getLastName(),
                        humanChild.getWeight(), humanChild.getHeight());
                displayChildResultFrame(humanChild, resultMessage);
            }
        });
    }

    private void displayChildResultFrame(Human child, String resultMessage) {
        childResultFrame = new JFrame();
        childResultFrame.setVisible(true);
        childResultFrame.setBounds(500, 280, 800, 500);
        childResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childResultFrame.getContentPane().setLayout(null);

        JLabel childResult = new JLabel(resultMessage);
        childResult.setBounds(40, 20, 450, 200);
        Font childResultFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childResult.setFont(childResultFont);
        childResultFrame.getContentPane().add(childResult);
        childResultFrame.getContentPane().add(closeChildResultButton);

        if (child.getGender()) {
            JLabel missionResult = new JLabel("<html><body>THE SON WAS BORN!<br/>" +
                    "THE MAN'S MISSION IS EXECUTED!</html></body>");
            missionResult.setBounds(40, 180, 800, 200);
            Font missionResultFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
            missionResult.setFont(missionResultFont);
            missionResult.setForeground(new Color(0, 0, 153));
            childResultFrame.getContentPane().add(missionResult);
        } else {
            JLabel missionResult = new JLabel("<html><body>THE SON WAS NOT BORN...<br/>" +
                    "THE MAN'S MISSION IS NOT EXECUTED...</html></body>");
            missionResult.setBounds(40, 180, 800, 200);
            Font missionResultFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
            missionResult.setFont(missionResultFont);
            missionResult.setForeground(new Color(0, 0, 153));
            childResultFrame.getContentPane().add(missionResult);
        }
        ImageIcon imageBabyIcon = new ImageIcon(new ImageIcon("src/main/resources/imageBaby.png")
                .getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT));
        JLabel imageBabyLabel = new JLabel(imageBabyIcon);
        imageBabyLabel.setIcon(imageBabyIcon);
        imageBabyLabel.setBounds(400, 20, 350, 200);
        childResultFrame.add(imageBabyLabel);
    }

    private void displayChildNameFrame(String childGender) {
        childNameFrame = new JFrame();
        childNameFrame.setVisible(true);
        childNameFrame.setBounds(500, 290, 800, 450);
        childNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childNameFrame.getContentPane().setLayout(null);

        ImageIcon imageCongrIcon = new ImageIcon(new ImageIcon("src/main/resources/CongratulationImage.png")
                .getImage().getScaledInstance(500, 70, Image.SCALE_DEFAULT));
        JLabel imageCongrLabel = new JLabel(imageCongrIcon);
        imageCongrLabel.setIcon(imageCongrIcon);
        imageCongrLabel.setBounds(140, 10, 500, 80);
        childNameFrame.add(imageCongrLabel);

        ImageIcon imageBalloonIcon = new ImageIcon(new ImageIcon("src/main/resources/confetti.png")
                .getImage().getScaledInstance(200, 170, Image.SCALE_DEFAULT));
        JLabel imageBalloonLabel = new JLabel(imageBalloonIcon);
        imageBalloonLabel.setIcon(imageBalloonIcon);
        imageBalloonLabel.setBounds(0, 100, 200, 170);
        childNameFrame.add(imageBalloonLabel);

        JLabel pairsInfoLabel = new JLabel("<html><body>You are a perfect match for each other!<br>" +
                "I bet you'll have such a lovely child!</body></html>");
        pairsInfoLabel.setBounds(210, 130, 800, 70);
        Font pairsInfoLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        pairsInfoLabel.setFont(pairsInfoLabelFont);
        pairsInfoLabel.setForeground(new Color(0, 0, 153));
        childNameFrame.getContentPane().add(pairsInfoLabel);

        JLabel childNameLabel = new JLabel(String.format("ENTER THE NAME FOR %s:", childGender));
        childNameLabel.setBounds(210, 240, 800, 30);
        Font childNameLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        childNameLabel.setFont(childNameLabelFont);
        childNameFrame.getContentPane().add(childNameLabel);

        childNameText = new JTextField();
        childNameText.setBounds(565, 242, TEXT_WIDTH, 30);
        Font childNameTextFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childNameText.setFont(childNameTextFont);
        childNameFrame.getContentPane().add(childNameText);
        childNameText.setColumns(15);
        childNameFrame.getContentPane().add(childNameOk);
    }

    private JLabel getMissionMessage(List<MissionResponse> responses) {
        StringBuilder actions = new StringBuilder("<html><body>");
        for (MissionResponse response : responses) {
            actions.append("<br>").append("Mission result for ").append(response.getHuman().getFirstName()).append(":<br>");
            if (response.getPerformedActions().isEmpty()) {
                actions.append("The mission is not executed...<br>");
                continue;
            }
            for (String action : response.getPerformedActions()) {
                actions.append(action).append("<br>");
            }
        }
        actions.append("<html><body>");
        JLabel missionResultLabel = new JLabel(actions.toString());
        missionResultLabel.setBounds(260, 190, 500, 180);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        missionResultLabel.setFont(font);
        return missionResultLabel;
    }

    private JLabel getProcessesMessage(RelationResponse relationResponse) {
        StringBuilder message = new StringBuilder("<html><body>");
        message.append(relationResponse.getSpeakResultMessage()).append("<br>")
                .append(relationResponse.getTolerateResultMessage()).append("<br>")
                .append(relationResponse.getSpendResultMessage());
        message.append("</body></html>");

        JLabel processResult = new JLabel(message.toString());
        processResult.setBounds(260, 100, 500, 80);
        Font childResultFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        processResult.setFont(childResultFont);
        return processResult;
    }

    private void displayFailedCompatibilityFrame() {
        failedResultFrame = new JFrame();
        failedResultFrame.setVisible(true);
        failedResultFrame.setBounds(560, 300, 700, 320);
        failedResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        failedResultFrame.getContentPane().setLayout(null);

        JLabel failedLabel = new JLabel("Nothing happened...");
        failedLabel.setBounds(100, 50, 250, 50);
        failedLabel.setForeground(new Color(0, 0, 128));
        Font failedLabelFont = new Font(Font.DIALOG, Font.BOLD, 26);
        failedLabel.setFont(failedLabelFont);
        failedResultFrame.getContentPane().add(failedLabel);
        failedResultFrame.getContentPane().add(closeFailedButton);

        ImageIcon imageMemIcon = new ImageIcon(new ImageIcon("src/main/resources/imageMem.png")
                .getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        JLabel imageMemLabel = new JLabel(imageMemIcon);
        imageMemLabel.setIcon(imageMemIcon);
        imageMemLabel.setBounds(450, 30, 150, 150);
        failedResultFrame.add(imageMemLabel);
    }

    private Human buildEmptyHuman(boolean gender) {
        if (gender) {
            return new Man();
        }
        return new Woman();
    }
}

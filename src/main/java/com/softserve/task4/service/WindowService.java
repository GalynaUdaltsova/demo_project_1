package com.softserve.task4.service;

import com.softserve.task4.models.Human;
import com.softserve.task4.models.Man;
import com.softserve.task4.models.Woman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowService implements ITesterService {

    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final String WEIGHT = "Weight";
    public static final String HEIGHT = "Height";
    public static final String LAST_NAME = "Last Name";
    public static final String FIRST_NAME = "First Name";
    public static final String GENDER = "Gender";
    private static final int FIRST_LABEL_X_START = 310;
    private static final int SECOND_LABEL_X_START = 550;
    private static final int FIRST_TEXT_X_START = 320;
    private static final int SECOND_TEXT_X_START = 670;
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_HEIGHT = 30;
    private static final int TEXT_WIDTH = 200;
    private static final int FONT_NAME_FRAME = 18;
    public static final int FONT_TEXT_FRAME = 20;

    private JButton missionButton;
    private JButton closeFailedButton;
    private JButton closeMissionButton;
    private JButton closeChildResultButton;
    private JButton childNameOk;
    private JButton compatibilitySubmitButton;
    private JButton clearButton;
    private HumanService humanService;
    private JFrame failedResultFrame;
    private JFrame missionFrame;
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
        compatibilitySubmitButton.setBackground(new Color(255, 204, 153));
        compatibilitySubmitButton.setForeground(Color.BLACK);
        compatibilitySubmitButton.setBounds(445, 420, 300, 40);
        Font compatibilityFont = new Font(Font.DIALOG, Font.PLAIN, 21);
        compatibilitySubmitButton.setFont(compatibilityFont);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(515, 550, 160, 40);
        Font clearFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        clearButton.setFont(clearFont);

        closeFailedButton = new JButton("CANCEL");
        closeFailedButton.setBounds(330, 150, 130, 40);
        Font closeFailedBFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeFailedButton.setFont(closeFailedBFont);

        missionButton = new JButton("MALE MISSION");
        Font missionBFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        missionButton.setFont(missionBFont);

        closeMissionButton = new JButton("CLOSE");
        closeMissionButton.setBounds(100, 150, 160, 40);
        Font closeMissionFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeMissionButton.setFont(closeMissionFont);

        closeChildResultButton = new JButton("CLOSE");
        closeChildResultButton.setBounds(280, 250, 160, 40);
        Font closeChildResultFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeChildResultButton.setFont(closeChildResultFont);


        childNameOk = new JButton("OK");
        childNameOk.setBounds(FIRST_LABEL_X_START, 300, 200, 40);
        Font childNameOkFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        childNameOk.setFont(childNameOkFont);
    }

    private void initializeListeners() {
        missionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (failedResultFrame != null) {
                    failedResultFrame.dispose();
                } else {
                    childResultFrame.dispose();
                }
                missionFrame = new JFrame();
                missionFrame.setVisible(true);
                missionFrame.setBounds(500, 200, 800, 300);
                missionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                missionFrame.getContentPane().setLayout(null);
                missionFrame.getContentPane().add(closeMissionButton);
                Human son = humanService.executeMansMission(firstHuman, secondHuman);
                if (son == null) {
                    JLabel missionLabel = new JLabel("Son was not born, the mission is missed");
                    missionLabel.setBounds(80, 40, 600, 40);
                    Font missionLabelFont = new Font(Font.DIALOG, Font.BOLD, 18);
                    missionLabel.setFont(missionLabelFont);
                    missionFrame.getContentPane().add(missionLabel);
                    return;
                }
                JLabel missionLabel = new JLabel("The son was born! Mission is executed");
                missionLabel.setBounds(80, 40, 600, 40);
                Font missionLabelFont = new Font(Font.DIALOG, Font.BOLD, 18);
                missionLabel.setFont(missionLabelFont);
                missionFrame.getContentPane().add(missionLabel);
            }
        });

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

                Human humanChild = humanService.compatibilityTest(firstHuman, secondHuman);
                if (humanChild == null) {
                    displayFailedFrame();
                    return;
                }
                String childGender = humanChild.getGender() ? "son" : "daughter";
                displayChildNameFrame(childGender);
                addChildNameListener(humanChild, childGender);
            }
        });

        closeFailedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                failedResultFrame.dispose();
            }
        });

        closeMissionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                missionFrame.dispose();
            }
        });

        closeChildResultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                childResultFrame.dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weightSecondText.setText(null);
                heightSecondText.setText(null);
                lastNameSecondText.setText(null);
                firstNameSecondText.setText(null);
                firstNameFirstText.setText(null);
                lastNameFirstText.setText(null);
                heightFirstText.setText(null);
                weightFirstText.setText(null);
                genderFirst.setSelectedItem(MALE);
                genderSecond.setSelectedItem(FEMALE);
            }
        });
    }

    private void initializeFrame() {
        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setBounds(300, 100, 1210, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);


        ImageIcon imageManIcon = new ImageIcon(new ImageIcon("B:\\javaStudy\\demo_project_1" +
                "\\src\\main\\resources\\imageMainMan.jpg")
                .getImage().getScaledInstance(280, 320, Image.SCALE_DEFAULT));
        JLabel imageManLabel = new JLabel(imageManIcon);
        imageManLabel.setIcon(imageManIcon);
        imageManLabel.setBounds(10, 60, 300, 400);
        mainFrame.add(imageManLabel);

        ImageIcon imageWomanIcon = new ImageIcon(new ImageIcon("B:\\javaStudy\\demo_project_1" +
                "\\src\\main\\resources\\imageMainWoman.jpg")
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
        Font genderSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        genderLabel.setFont(genderSecondLabelFont);
        mainFrame.getContentPane().add(genderLabel);

        JLabel firstNameLabel = new JLabel(FIRST_NAME);
        firstNameLabel.setBounds(SECOND_LABEL_X_START, 200, LABEL_WIDTH, 17);
        Font firstNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        firstNameLabel.setFont(firstNameSecondLabelFont);
        mainFrame.getContentPane().add(firstNameLabel);

        JLabel lastNameLabel = new JLabel(LAST_NAME);
        lastNameLabel.setBounds(SECOND_LABEL_X_START, 240, LABEL_WIDTH, 19);
        Font lastNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        lastNameLabel.setFont(lastNameSecondLabelFont);
        mainFrame.getContentPane().add(lastNameLabel);

        JLabel heightLabel = new JLabel(HEIGHT);
        heightLabel.setBounds(SECOND_LABEL_X_START + 15, 280, LABEL_WIDTH, 21);
        Font heightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        heightLabel.setFont(heightSecondLabelFont);
        mainFrame.getContentPane().add(heightLabel);

        JLabel weightLabel = new JLabel(WEIGHT);
        weightLabel.setBounds(SECOND_LABEL_X_START + 15, 320, LABEL_WIDTH, 23);
        Font weightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
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
                String resultMessage = String.format("<html><body>The data about your %s:<br>" +
                                "First name: %s<br>Last name: %s<br>Weight: %s<br>Height: %s</body></html>",
                        childGender, humanChild.getFirstName(), humanChild.getLastName(),
                        humanChild.getWeight(), humanChild.getHeight());
                displayChildResultFrame(resultMessage);
            }
        });
    }

    private void displayChildResultFrame(String resultMessage) {
        childResultFrame = new JFrame();
        childResultFrame.setVisible(true);
        childResultFrame.setBounds(500, 200, 800, 400);
        childResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childResultFrame.getContentPane().setLayout(null);

        JLabel childResult = new JLabel(resultMessage);
        childResult.setBounds(40, 20, 450, 200);
        Font childResultFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childResult.setFont(childResultFont);
        childResultFrame.getContentPane().add(childResult);
        childResultFrame.getContentPane().add(missionButton);
        missionButton.setBounds(40, 250, 180, 40);
        childResultFrame.getContentPane().add(closeChildResultButton);
    }

    private void displayChildNameFrame(String childGender) {
        childNameFrame = new JFrame();
        childNameFrame.setVisible(true);
        childNameFrame.setBounds(500, 200, 800, 450);
        childNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childNameFrame.getContentPane().setLayout(null);

        ImageIcon imageCongrIcon = new ImageIcon(new ImageIcon("B:\\javaStudy\\demo_project_1" +
                "\\src\\main\\resources\\CongratulationImage.png")
                .getImage().getScaledInstance(500, 70, Image.SCALE_DEFAULT));
        JLabel imageCongrLabel = new JLabel(imageCongrIcon);
        imageCongrLabel.setIcon(imageCongrIcon);
        imageCongrLabel.setBounds(140, 10, 500, 80);
        childNameFrame.add(imageCongrLabel);

        ImageIcon imageBalloonIcon = new ImageIcon(new ImageIcon("B:\\javaStudy\\demo_project_1" +
                "\\src\\main\\resources\\confetti.png")
                .getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT));
        JLabel imageBalloonLabel = new JLabel(imageBalloonIcon);
        imageBalloonLabel.setIcon(imageBalloonIcon);
        imageBalloonLabel.setBounds(0, 100, 230, 200);
        childNameFrame.add(imageBalloonLabel);

        JLabel pairsInfoLabel = new JLabel("You are a perfect match for each other!");
        pairsInfoLabel.setBounds(210, 100, 800, 30);
        Font pairsInfoLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        pairsInfoLabel.setFont(pairsInfoLabelFont);
        pairsInfoLabel.setForeground(new Color(0, 0, 153));
        childNameFrame.getContentPane().add(pairsInfoLabel);

        JLabel childInfoLabel = new JLabel("I bet you'll have such a lovely child!");
        childInfoLabel.setBounds(210, 140, 800, 30);
        Font childInfoLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childInfoLabel.setFont(childInfoLabelFont);
        childInfoLabel.setForeground(new Color(0, 0, 153));
        childNameFrame.getContentPane().add(childInfoLabel);

        JLabel childNameLabel = new JLabel(String.format("Enter the name for %s:", childGender));
        childNameLabel.setBounds(210, 200, 800, 30);
        Font childNameLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        childNameLabel.setFont(childNameLabelFont);

        childNameFrame.getContentPane().add(childNameLabel);

        childNameText = new JTextField();
        childNameText.setBounds(500, 202, TEXT_WIDTH, 30);
        Font childNameTextFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childNameText.setFont(childNameTextFont);
        childNameFrame.getContentPane().add(childNameText);
        childNameText.setColumns(15);
        childNameFrame.getContentPane().add(childNameOk);
    }

    private void displayFailedFrame() {
        failedResultFrame = new JFrame();
        failedResultFrame.setVisible(true);
        failedResultFrame.setBounds(550, 200, 700, 300);
        failedResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        failedResultFrame.getContentPane().setLayout(null);

        JLabel failedLabel = new JLabel("Nothing happened ...");
        failedLabel.setBounds(100, 50, 250, 50);
        failedLabel.setForeground(new Color(0, 0, 128));
        Font failedLabelFont = new Font(Font.DIALOG, Font.BOLD, 26);
        failedLabel.setFont(failedLabelFont);
        failedResultFrame.getContentPane().add(failedLabel);
        missionButton.setBounds(100, 150, 180, 40);
        failedResultFrame.getContentPane().add(missionButton);
        failedResultFrame.getContentPane().add(closeFailedButton);

        ImageIcon imageMemIcon = new ImageIcon(new ImageIcon("B:\\javaStudy\\demo_project_1" +
                "\\src\\main\\resources\\imageMem.png")
                .getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        JLabel imageMemLabel = new JLabel(imageMemIcon);
        imageMemLabel.setIcon(imageMemIcon);
        imageMemLabel.setBounds(500, 30, 150, 150);
        failedResultFrame.add(imageMemLabel);
    }

    private Human buildEmptyHuman(boolean gender) {
        if (gender) {
            return new Man();
        }
        return new Woman();
    }
}

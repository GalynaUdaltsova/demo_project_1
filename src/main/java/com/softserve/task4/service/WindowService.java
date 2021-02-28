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
    private static final int FIRST_LABEL_X_START = 320;
    private static final int SECOND_LABEL_X_START = 620;
    private static final int FIRST_TEXT_X_START = 400;
    private static final int SECOND_TEXT_X_START = 700;
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_HEIGHT = 25;
    private static final int TEXT_WIDTH = 180;
    private static final int LABEL_HEIGHT = 200;
    private static final int FONT_NAME_FRAME = 15;
    public static final int FONT_TEXT_FRAME = 14;

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
        compatibilitySubmitButton.setBackground(Color.PINK);
        compatibilitySubmitButton.setForeground(Color.BLACK);
        compatibilitySubmitButton.setBounds(480, 400, 300, 60);
        Font compatibilityFont = new Font(Font.DIALOG, Font.PLAIN, 23);
        compatibilitySubmitButton.setFont(compatibilityFont);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(550, 550, 160, 40);
        Font clearFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        clearButton.setFont(clearFont);

        missionButton = new JButton("MISSION");
        missionButton.setBounds(100, 150, 160, 40);
        Font missionBFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        missionButton.setFont(missionBFont);

        closeFailedButton = new JButton("CANCEL");
        closeFailedButton.setBounds(300, 150, 160, 40);
        Font closeFailedBFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeFailedButton.setFont(closeFailedBFont);

        closeMissionButton = new JButton("CLOSE");
        closeMissionButton.setBounds(100, 150, 160, 40);
        Font closeMissionFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeMissionButton.setFont(closeMissionFont);

        closeChildResultButton = new JButton("CLOSE");
        closeChildResultButton.setBounds(280, 150, 160, 40);
        Font closeChildResultFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        closeChildResultButton.setFont(closeChildResultFont);


        childNameOk = new JButton("OK");
        childNameOk.setBounds(FIRST_LABEL_X_START, 250, 160, 40);
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
                JLabel missionLabel = new JLabel("The son was born! Mission is execute");
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
        mainFrame.setBounds(300, 100, 1230, 700);
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
        imageWomanLabel.setBounds(900, 60, 300, 400);
        mainFrame.add(imageWomanLabel);

        genderFirst = new JComboBox<>();
        genderFirst.addItem(MALE);
        genderFirst.addItem(FEMALE);
        genderFirst.setBounds(FIRST_TEXT_X_START, 160, TEXT_WIDTH, TEXT_HEIGHT);
        Font genderFirstFont = new Font(Font.DIALOG, Font.BOLD, FONT_TEXT_FRAME);
        genderFirst.setFont(genderFirstFont);
        mainFrame.getContentPane().add(genderFirst);

        JLabel genderLabel = new JLabel(GENDER);
        genderLabel.setBounds(FIRST_LABEL_X_START, 160, LABEL_WIDTH, 15);
        Font genderLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        genderLabel.setFont(genderLabelFont);
        mainFrame.getContentPane().add(genderLabel);

        firstNameFirstText = new JTextField();
        firstNameFirstText.setBounds(FIRST_TEXT_X_START, 190, TEXT_WIDTH, TEXT_HEIGHT);
        Font firstNameFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        firstNameFirstText.setFont(firstNameFirstTextFont);
        mainFrame.getContentPane().add(firstNameFirstText);
        firstNameFirstText.setColumns(15);

        JLabel firstNameLabel = new JLabel(FIRST_NAME);
        firstNameLabel.setBounds(FIRST_LABEL_X_START, 190, LABEL_WIDTH, 17);
        Font firstNameLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        firstNameLabel.setFont(firstNameLabelFont);
        mainFrame.getContentPane().add(firstNameLabel);

        lastNameFirstText = new JTextField();
        lastNameFirstText.setBounds(FIRST_TEXT_X_START, 220, TEXT_WIDTH, TEXT_HEIGHT);
        Font lastNameFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        lastNameFirstText.setFont(lastNameFirstTextFont);
        mainFrame.getContentPane().add(lastNameFirstText);
        lastNameFirstText.setColumns(15);

        JLabel lastNameLabel = new JLabel(LAST_NAME);
        lastNameLabel.setBounds(FIRST_LABEL_X_START, 220, LABEL_WIDTH, 19);
        Font lastNameLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        lastNameLabel.setFont(lastNameLabelFont);
        mainFrame.getContentPane().add(lastNameLabel);

        heightFirstText = new JFormattedTextField(0);
        heightFirstText.setBounds(FIRST_TEXT_X_START, 250, TEXT_WIDTH, TEXT_HEIGHT);
        Font heightFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        heightFirstText.setFont(heightFirstTextFont);
        mainFrame.getContentPane().add(heightFirstText);
        heightFirstText.setColumns(5);

        JLabel heightLabel = new JLabel(HEIGHT);
        heightLabel.setBounds(FIRST_LABEL_X_START, 250, LABEL_WIDTH, 21);
        Font heightLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        heightLabel.setFont(heightLabelFont);
        mainFrame.getContentPane().add(heightLabel);

        weightFirstText = new JFormattedTextField(0);
        weightFirstText.setBounds(FIRST_TEXT_X_START, 280, TEXT_WIDTH, TEXT_HEIGHT);
        Font weightFirstTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        weightFirstText.setFont(weightFirstTextFont);
        mainFrame.getContentPane().add(weightFirstText);
        weightFirstText.setColumns(3);

        JLabel weightLabel = new JLabel(WEIGHT);
        weightLabel.setBounds(FIRST_LABEL_X_START, 280, LABEL_WIDTH, 23);
        Font weightLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        weightLabel.setFont(weightLabelFont);
        mainFrame.getContentPane().add(weightLabel);

        genderSecond = new JComboBox<>();
        genderSecond.addItem(FEMALE);
        genderSecond.addItem(MALE);
        genderSecond.setBounds(SECOND_TEXT_X_START, 160, TEXT_WIDTH, TEXT_HEIGHT);
        Font genderSecondFont = new Font(Font.DIALOG, Font.BOLD, FONT_TEXT_FRAME);
        genderSecond.setFont(genderSecondFont);
        mainFrame.getContentPane().add(genderSecond);

        JLabel genderSecondLabel = new JLabel(GENDER);
        genderSecondLabel.setBounds(SECOND_LABEL_X_START, 160, LABEL_WIDTH, 18);
        Font genderSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        genderSecondLabel.setFont(genderSecondLabelFont);
        mainFrame.getContentPane().add(genderSecondLabel);

        firstNameSecondText = new JTextField();
        firstNameSecondText.setBounds(SECOND_TEXT_X_START, 190, TEXT_WIDTH, TEXT_HEIGHT);
        Font firstNameSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        firstNameSecondText.setFont(firstNameSecondTextFont);
        mainFrame.getContentPane().add(firstNameSecondText);
        firstNameSecondText.setColumns(15);

        JLabel firstNameSecondLabel = new JLabel(FIRST_NAME);
        firstNameSecondLabel.setBounds(SECOND_LABEL_X_START, 190, LABEL_WIDTH, 17);
        Font firstNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        firstNameSecondLabel.setFont(firstNameSecondLabelFont);
        mainFrame.getContentPane().add(firstNameSecondLabel);

        lastNameSecondText = new JTextField();
        lastNameSecondText.setBounds(SECOND_TEXT_X_START, 220, TEXT_WIDTH, TEXT_HEIGHT);
        Font lastNameSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        lastNameSecondText.setFont(lastNameSecondTextFont);
        mainFrame.getContentPane().add(lastNameSecondText);
        lastNameSecondText.setColumns(15);

        JLabel lastNameSecondLabel = new JLabel(LAST_NAME);
        lastNameSecondLabel.setBounds(SECOND_LABEL_X_START, 220, LABEL_WIDTH, 19);
        Font lastNameSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        lastNameSecondLabel.setFont(lastNameSecondLabelFont);
        mainFrame.getContentPane().add(lastNameSecondLabel);

        heightSecondText = new JFormattedTextField(0);
        heightSecondText.setBounds(SECOND_TEXT_X_START, 250, TEXT_WIDTH, TEXT_HEIGHT);
        Font heightSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        heightSecondText.setFont(heightSecondTextFont);
        mainFrame.getContentPane().add(heightSecondText);
        heightSecondText.setColumns(5);

        JLabel heightSecondLabel = new JLabel(HEIGHT);
        heightSecondLabel.setBounds(SECOND_LABEL_X_START, 250, LABEL_WIDTH, 21);
        Font heightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        heightSecondLabel.setFont(heightSecondLabelFont);
        mainFrame.getContentPane().add(heightSecondLabel);

        weightSecondText = new JFormattedTextField(0);
        weightSecondText.setBounds(SECOND_TEXT_X_START, 280, TEXT_WIDTH, TEXT_HEIGHT);
        Font weightSecondTextFont = new Font(Font.DIALOG, Font.PLAIN, FONT_TEXT_FRAME);
        weightSecondText.setFont(weightSecondTextFont);
        mainFrame.getContentPane().add(weightSecondText);
        weightSecondText.setColumns(3);

        JLabel weightSecondLabel = new JLabel(WEIGHT);
        weightSecondLabel.setBounds(SECOND_LABEL_X_START, 280, LABEL_WIDTH, 23);
        Font weightSecondLabelFont = new Font(Font.DIALOG, Font.PLAIN, FONT_NAME_FRAME);
        weightSecondLabel.setFont(weightSecondLabelFont);
        mainFrame.getContentPane().add(weightSecondLabel);

        JLabel titleLabel = new JLabel("COMPATIBILITY TEST");
        titleLabel.setBounds(470, 60, 400, 35);
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
                String resultMessage = String.format("Your %s:" +
                                "\n First name: %s" +
                                "\n Last name: %s" +
                                "\n Weight: %s" +
                                "\n Height: %s", childGender, humanChild.getFirstName(), humanChild.getLastName(),
                        humanChild.getWeight(), humanChild.getHeight());
                displayChildResultFrame(resultMessage);
            }
        });
    }

    private void displayChildResultFrame(String resultMessage) {
        childResultFrame = new JFrame();
        childResultFrame.setVisible(true);
        childResultFrame.setBounds(500, 200, 800, 300);
        childResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childResultFrame.getContentPane().setLayout(null);

        JLabel childResult = new JLabel(resultMessage);
        childResult.setBounds(40, 40, 450, 40);
        childResultFrame.getContentPane().add(childResult);
        childResultFrame.getContentPane().add(missionButton);
        childResultFrame.getContentPane().add(closeChildResultButton);
    }

    private void displayChildNameFrame(String childGender) {
        childNameFrame = new JFrame();
        childNameFrame.setVisible(true);
        childNameFrame.setBounds(500, 200, 800, 400);
        childNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        childNameFrame.getContentPane().setLayout(null);

        JLabel pairsInfoLabel = new JLabel("Congratulations! You are a perfect match for each other!");
        pairsInfoLabel.setBounds(85, 40, 800, 30);
        Font pairsInfoLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        pairsInfoLabel.setFont(pairsInfoLabelFont);
        pairsInfoLabel.setForeground(Color.RED);
        childNameFrame.getContentPane().add(pairsInfoLabel);

        JLabel childInfoLabel = new JLabel("I bet you'll have such a lovely child!");
        childInfoLabel.setBounds(170, 80, 800, 30);
        Font childInfoLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childInfoLabel.setFont(childInfoLabelFont);
        childInfoLabel.setForeground(Color.RED);
        childNameFrame.getContentPane().add(childInfoLabel);

        JLabel childNameLabel = new JLabel(String.format("Enter the name for %s:", childGender));
        childNameLabel.setBounds(170, 150, 800, 30);
        Font childNameLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);
        childNameLabel.setFont(childNameLabelFont);

        childNameFrame.getContentPane().add(childNameLabel);

        childNameText = new JTextField();
        childNameText.setBounds(500, 152, TEXT_WIDTH, 30);
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
        failedLabel.setBounds(220, 20, 250, 50);
        failedLabel.setForeground(Color.RED);
        Font failedLabelFont = new Font(Font.DIALOG, Font.BOLD, 26);
        failedLabel.setFont(failedLabelFont);
        failedResultFrame.getContentPane().add(failedLabel);
        failedResultFrame.getContentPane().add(missionButton);
        failedResultFrame.getContentPane().add(closeFailedButton);
    }

    private Human buildEmptyHuman(boolean gender) {
        if (gender) {
            return new Man();
        }
        return new Woman();
    }
}

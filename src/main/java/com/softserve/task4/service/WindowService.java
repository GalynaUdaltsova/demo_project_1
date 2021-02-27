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
    private static final int FIRST_LABEL_X_START = 30;
    private static final int SECOND_LABEL_X_START = 300;
    private static final int FIRST_TEXT_X_START = 130;
    private static final int SECOND_TEXT_X_START = 400;
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_HEIGHT = 20;
    private static final int TEXT_WIDTH = 120;
    private static final int LABEL_HEIGHT = 14;

    private JButton btnExecMiss;
    private JButton closeFailedFrame;
    private HumanService humanService;
    private JFrame failedResultFrame;
    private Human firstHuman;
    private Human secondHuman;

    public WindowService(HumanService humanService) {
        this.humanService = humanService;
    }

    public void test() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    initialize();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100, 100, 650, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JComboBox<String> gender = new JComboBox<>();
        gender.addItem(MALE);
        gender.addItem(FEMALE);
        gender.setBounds(FIRST_TEXT_X_START, 50, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(gender);

        JLabel genderLabel = new JLabel(GENDER);
        genderLabel.setBounds(FIRST_LABEL_X_START, 50, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(genderLabel);

        JTextField firstNameText = new JTextField();
        firstNameText.setBounds(FIRST_TEXT_X_START, 80, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(firstNameText);
        firstNameText.setColumns(15);

        JLabel firstNameLabel = new JLabel(FIRST_NAME);
        firstNameLabel.setBounds(FIRST_LABEL_X_START, 80, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(firstNameLabel);

        JTextField lastNameText = new JTextField();
        lastNameText.setBounds(FIRST_TEXT_X_START, 110, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(lastNameText);
        lastNameText.setColumns(15);

        JLabel lastNameLabel = new JLabel(LAST_NAME);
        lastNameLabel.setBounds(FIRST_LABEL_X_START, 110, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(lastNameLabel);

        JTextField heightText = new JFormattedTextField(0);
        heightText.setBounds(FIRST_TEXT_X_START, 140, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(heightText);
        heightText.setColumns(5);

        JLabel heightLabel = new JLabel(HEIGHT);
        heightLabel.setBounds(FIRST_LABEL_X_START, 140, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(heightLabel);

        JTextField weightText = new JFormattedTextField(0);
        weightText.setBounds(FIRST_TEXT_X_START, 170, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(weightText);
        weightText.setColumns(3);

        JLabel weightLabel = new JLabel(WEIGHT);
        weightLabel.setBounds(FIRST_LABEL_X_START, 170, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(weightLabel);

        JComboBox<String> genderSecond = new JComboBox<>();
        genderSecond.addItem(MALE);
        genderSecond.addItem(FEMALE);
        genderSecond.setBounds(SECOND_TEXT_X_START, 50, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(genderSecond);

        JLabel genderSecondLabel = new JLabel(GENDER);
        genderSecondLabel.setBounds(SECOND_LABEL_X_START, 50, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(genderSecondLabel);

        JTextField firstNameSecondText = new JTextField();
        firstNameSecondText.setBounds(SECOND_TEXT_X_START, 80, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(firstNameSecondText);
        firstNameSecondText.setColumns(15);

        JLabel firstNameSecondLabel = new JLabel(FIRST_NAME);
        firstNameSecondLabel.setBounds(SECOND_LABEL_X_START, 80, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(firstNameSecondLabel);

        JTextField lastNameSecondText = new JTextField();
        lastNameSecondText.setBounds(SECOND_TEXT_X_START, 110, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(lastNameSecondText);
        lastNameSecondText.setColumns(15);

        JLabel lastNameSecondLabel = new JLabel(LAST_NAME);
        lastNameSecondLabel.setBounds(SECOND_LABEL_X_START, 110, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(lastNameSecondLabel);

        JTextField heightSecondText = new JFormattedTextField(0);
        heightSecondText.setBounds(SECOND_TEXT_X_START, 140, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(heightSecondText);
        heightSecondText.setColumns(5);

        JLabel heightSecondLabel = new JLabel(HEIGHT);
        heightSecondLabel.setBounds(SECOND_LABEL_X_START, 140, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(heightSecondLabel);

        JTextField weightSecondText = new JFormattedTextField(0);
        weightSecondText.setBounds(SECOND_TEXT_X_START, 170, TEXT_WIDTH, TEXT_HEIGHT);
        frame.getContentPane().add(weightSecondText);
        weightSecondText.setColumns(3);

        JLabel weightSecondLabel = new JLabel(WEIGHT);
        weightSecondLabel.setBounds(SECOND_LABEL_X_START, 170, LABEL_WIDTH, LABEL_HEIGHT);
        frame.getContentPane().add(weightSecondLabel);

        JLabel titleLabel = new JLabel("Human pairs compatibility tester");
        titleLabel.setBounds(180, 10, 400, 25);
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 18);
        titleLabel.setFont(f1);
        frame.getContentPane().add(titleLabel);

        JButton btnSubmit = new JButton("Compatibility");
        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(FIRST_LABEL_X_START, 240, 120, 23);
        frame.getContentPane().add(btnSubmit);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(150, 240, 100, 23);
        frame.getContentPane().add(btnClear);

        btnExecMiss = new JButton("Mission");
        btnExecMiss.setBackground(Color.BLUE);
        btnExecMiss.setForeground(Color.MAGENTA);
        btnExecMiss.setBounds(FIRST_LABEL_X_START, 80, 100, 23);

        closeFailedFrame = new JButton("Close");
        closeFailedFrame.setBackground(Color.BLUE);
        closeFailedFrame.setForeground(Color.MAGENTA);
        closeFailedFrame.setBounds(FIRST_LABEL_X_START, 80, 80, 23);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String heightString = heightText.getText();
                String weighString = weightText.getText();
                boolean genderFirst = ((String) gender.getSelectedItem()).equalsIgnoreCase(MALE) ? true : false;
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

//                    JOptionPane.showMessageDialog(null, "Nothing happened ...");
                    failedResultFrame = new JFrame();
                    failedResultFrame.setVisible(true);
                    failedResultFrame.setBounds(300, 200, 330, 160);
                    failedResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    failedResultFrame.getContentPane().setLayout(null);

                    JLabel failedLabel = new JLabel("Nothing happened ...");
                    failedLabel.setBounds(FIRST_LABEL_X_START, 40, 150, LABEL_HEIGHT);
                    failedResultFrame.getContentPane().add(failedLabel);
                    failedResultFrame.getContentPane().add(btnExecMiss);
                    return;
                }
                JFrame childNameFrame = new JFrame();
                childNameFrame.setVisible(true);
                childNameFrame.setBounds(300, 200, 330, 160);
                childNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                childNameFrame.getContentPane().setLayout(null);
                String child = humanChild.getGender() ? "son" : "daughter";
                JLabel childNameLabel = new JLabel(String.format("Name for %s:", child));
                childNameLabel.setBounds(FIRST_LABEL_X_START, 40, 150, LABEL_HEIGHT);
                childNameFrame.getContentPane().add(childNameLabel);

                JTextField childNameText = new JTextField();
                childNameText.setBounds(150, 40, TEXT_WIDTH, TEXT_HEIGHT);
                childNameFrame.getContentPane().add(childNameText);
                childNameText.setColumns(15);

                JButton childOk = new JButton("Ok");
                childOk.setBackground(Color.BLUE);
                childOk.setForeground(Color.MAGENTA);
                childOk.setBounds(FIRST_LABEL_X_START, 80, 100, 23);
                childNameFrame.getContentPane().add(childOk);

                childOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String childName = childNameText.getText();
                        humanChild.setFirstName(childName);
                        childNameFrame.dispose();
                        String resultMessage = String.format("Your %s:" +
                                        "\n First name: %s" +
                                        "\n Last name: %s" +
                                        "\n Weight: %s" +
                                        "\n Height: %s", child, humanChild.getFirstName(), humanChild.getLastName(),
                                humanChild.getWeight(), humanChild.getHeight());
//                        JOptionPane.showMessageDialog(null, resultMessage);

                        JFrame childResultFrame = new JFrame();
                        childResultFrame.setVisible(true);
                        childResultFrame.setBounds(300, 200, 550, 460);
                        childResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        childResultFrame.getContentPane().setLayout(null);

                        JLabel childResult = new JLabel(resultMessage);
                        childResult.setBounds(FIRST_LABEL_X_START, 40, 150, LABEL_HEIGHT);
                        childResultFrame.getContentPane().add(childResult);
                        childNameFrame.getContentPane().add(btnExecMiss);
                    }
                });
            }
        });

        btnExecMiss.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame missionFrame = new JFrame();
                missionFrame.setVisible(true);
                missionFrame.setBounds(300, 200, 550, 460);
                missionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                missionFrame.getContentPane().setLayout(null);
                Human son = humanService.executeMansMission(firstHuman, secondHuman);
                if (son == null) {
                    JLabel missionLabel = new JLabel("He doesn't have a son, the mission is missed");
                    missionLabel.setBounds(FIRST_LABEL_X_START, 40, 300, LABEL_HEIGHT);
                    missionFrame.getContentPane().add(missionLabel);
                }


            }
        });

        closeFailedFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                failedResultFrame.dispose();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weightSecondText.setText(null);
                heightSecondText.setText(null);
                lastNameSecondText.setText(null);
                firstNameSecondText.setText(null);
                firstNameText.setText(null);
                lastNameText.setText(null);
                heightText.setText(null);
                weightText.setText(null);
                gender.setSelectedItem(MALE);
                genderSecond.setSelectedItem(MALE);
            }
        });
    }

    private Human buildEmptyHuman(boolean gender) {
        if (gender) {
            return new Man();
        }
        return new Woman();
    }
}

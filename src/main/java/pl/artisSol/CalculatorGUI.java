package pl.artisSol;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {



    public CalculatorGUI(Mechanism mechanism) {

        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel numbersPad = new JPanel(new GridLayout(4, 3));
        JPanel operationsOnePad = new JPanel(new GridLayout(1, 4));
        JPanel operationsTwoPad = new JPanel(new GridLayout(1, 3));
        JPanel textFieldsPanel = new JPanel(new GridLayout(2, 1));


        //Creating buttons for numberPanel
        JButton but1 = new JButton("1");
        JButton but2 = new JButton("2");
        JButton but3 = new JButton("3");
        JButton but4 = new JButton("4");
        JButton but5 = new JButton("5");
        JButton but6 = new JButton("6");
        JButton but7 = new JButton("7");
        JButton but8 = new JButton("8");
        JButton but9 = new JButton("9");
        JButton but0 = new JButton("0");
        JButton butDot = new JButton(".");

        //Adding action listeners to buttons in numberPanel
        but1.addActionListener(mechanism::numberButtonClicked);
        but2.addActionListener(mechanism::numberButtonClicked);
        but3.addActionListener(mechanism::numberButtonClicked);
        but4.addActionListener(mechanism::numberButtonClicked);
        but5.addActionListener(mechanism::numberButtonClicked);
        but6.addActionListener(mechanism::numberButtonClicked);
        but7.addActionListener(mechanism::numberButtonClicked);
        but8.addActionListener(mechanism::numberButtonClicked);
        but9.addActionListener(mechanism::numberButtonClicked);
        but0.addActionListener(mechanism::numberButtonClicked);
        butDot.addActionListener(mechanism::numberButtonClicked);

        // Adding reset button to the bottom corner
        JButton butReset = new JButton("C");
        butReset.addActionListener(actionEvent -> mechanism.resetButtonClicked());



        //Adding buttons to numberPanel
        numbersPad.add(but1);
        numbersPad.add(but2);
        numbersPad.add(but3);
        numbersPad.add(but4);
        numbersPad.add(but5);
        numbersPad.add(but6);
        numbersPad.add(but7);
        numbersPad.add(but8);
        numbersPad.add(but9);
        numbersPad.add(but0);
        numbersPad.add(butDot);

        numbersPad.add(butReset);


        //Creating operation buttons for operationsPanel
        JButton butRot = new JButton("√");
        JButton butPow = new JButton("^");
        JButton butDiv = new JButton("/");
        JButton butMul = new JButton("*");
        JButton butSub = new JButton("-");
        JButton butAdd = new JButton("+");

        JButton butEqu = new JButton("=");

        //Adding actionListener to operation buttons
        butRot.addActionListener(mechanism::operationButtonClicked);
        butPow.addActionListener(mechanism::operationButtonClicked);
        butDiv.addActionListener(mechanism::operationButtonClicked);
        butMul.addActionListener(mechanism::operationButtonClicked);
        butSub.addActionListener(mechanism::operationButtonClicked);
        butAdd.addActionListener(mechanism::operationButtonClicked);
        butEqu.addActionListener(actionEvent -> mechanism.mathOperationsWithOrder());

        //Adding operation buttons to operations Panel
        operationsOnePad.add(butRot);
        operationsOnePad.add(butPow);
        operationsOnePad.add(butDiv);
        operationsOnePad.add(butMul);
        operationsTwoPad.add(butSub);
        operationsTwoPad.add(butAdd);
        operationsTwoPad.add(butEqu);

        //Adding textFields to show math operations to textField Panel
        textFieldsPanel.add(mechanism.textFieldInput);
        textFieldsPanel.add(mechanism.textFieldResult);

        //Adding panels to frame view
        getContentPane().add(textFieldsPanel);
        getContentPane().add(operationsOnePad);
        getContentPane().add(operationsTwoPad);
        getContentPane().add(numbersPad);

        getContentPane().setLayout(new GridLayout(4, 1));


        pack();
        setVisible(true);
        setResizable(true);
        setLocation(500, 500);

    }

}

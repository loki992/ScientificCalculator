package pl.artisSol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Mechanism implements ActionListener {

    String inputCache;
    String textFieldInputValue;
    JTextField textFieldInput;
    JTextField textFieldResult;
    List<Double> listOfNumbers;
    List<Character> listOfSymbols;
    String currentNumberCache;
    Boolean wasEqualsButtonAlreadyClicked;
    Double result;

    public Mechanism() {
        this.inputCache = "";
        this.textFieldInputValue = "";
        this.textFieldInput = new JTextField();
        this.textFieldResult = new JTextField();
        this.listOfNumbers = new ArrayList<>();
        this.listOfSymbols = new ArrayList<>();
        this.result = 0.0;
        this.currentNumberCache = "";
        this.wasEqualsButtonAlreadyClicked = false;
    }

    void numberButtonClicked(ActionEvent actionEvent) {
        if (wasEqualsButtonAlreadyClicked) {
            resetButtonClicked();
        }
        inputCache += actionEvent.getActionCommand();
        currentNumberCache += actionEvent.getActionCommand();
        changeFieldInputValue();
    }

    void operationButtonClicked(ActionEvent actionEvent) {
        if (wasEqualsButtonAlreadyClicked) {
            inputCache += listOfNumbers.get(0);
            textFieldResult.setText("");
            textFieldInputValue = "";
            textFieldInput.setText("");
            wasEqualsButtonAlreadyClicked = false;
        }
        inputCache += actionEvent.getActionCommand();
        if (!currentNumberCache.isEmpty()) {
            listOfNumbers.add(Double.parseDouble(currentNumberCache));
        }
        listOfSymbols.add(actionEvent.getActionCommand().charAt(0));
        currentNumberCache = "";

        changeFieldInputValue();

    }

    void changeFieldInputValue() {
        textFieldInputValue += inputCache;
        textFieldInput.setText(textFieldInputValue);
        inputCache = "";

    }

    void resetButtonClicked() {
        inputCache = "";
        textFieldInput.setText("");
        textFieldResult.setText("");
        textFieldInputValue = "";
        currentNumberCache = "";
        listOfNumbers.clear();
        listOfSymbols.clear();
        result = 0.0;
        wasEqualsButtonAlreadyClicked = false;

    }

    void mathOperationsWithOrder() {
        {
            if (!listOfSymbols.isEmpty() && !currentNumberCache.isEmpty()) {
                listOfNumbers.add(Double.parseDouble(currentNumberCache));
            }
            currentNumberCache = "";
            if (listOfSymbols.contains('^') && listOfSymbols.contains('√')) {
                if (listOfSymbols.indexOf('^') < listOfSymbols.indexOf('√')) {
                    result = Math.pow(listOfNumbers.get(listOfSymbols.indexOf('^')), listOfNumbers.get(listOfSymbols.indexOf('^') + 1));
                    variablesModAfterMathOper('^');
                    mathOperationsWithOrder();
                } else {
                    result = Math.pow(listOfNumbers.get(listOfSymbols.indexOf('√')), 1 / listOfNumbers.get(listOfSymbols.indexOf('√') + 1));
                    variablesModAfterMathOper('√');
                    mathOperationsWithOrder();
                }
            }
            if (listOfSymbols.contains('^')) {
                result = Math.pow(listOfNumbers.get(listOfSymbols.indexOf('^')), listOfNumbers.get(listOfSymbols.indexOf('^') + 1));
                variablesModAfterMathOper('^');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.contains('√')) {
                result = Math.pow(listOfNumbers.get(listOfSymbols.indexOf('√')), 1 / listOfNumbers.get(listOfSymbols.indexOf('√') + 1));
                variablesModAfterMathOper('√');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.contains('*') && listOfSymbols.contains('/')) {
                if (listOfSymbols.indexOf('*') < listOfSymbols.indexOf('/')) {
                    result = listOfNumbers.get(listOfSymbols.indexOf('*')) * listOfNumbers.get(listOfSymbols.indexOf('*') + 1);
                    variablesModAfterMathOper('*');
                    mathOperationsWithOrder();
                } else {
                    result = listOfNumbers.get(listOfSymbols.indexOf('/')) / listOfNumbers.get(listOfSymbols.indexOf('*') + 1);
                    variablesModAfterMathOper('/');
                    mathOperationsWithOrder();
                }
            }
            if (listOfSymbols.contains('*')) {
                result = listOfNumbers.get(listOfSymbols.indexOf('*')) * listOfNumbers.get(listOfSymbols.indexOf('*') + 1);
                variablesModAfterMathOper('*');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.contains('/')) {
                result = listOfNumbers.get(listOfSymbols.indexOf('/')) / listOfNumbers.get(listOfSymbols.indexOf('*') + 1);
                variablesModAfterMathOper('/');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.contains('+') && listOfSymbols.contains('-')) {
                if (listOfSymbols.indexOf('+') < listOfSymbols.indexOf('-')) {
                    result = listOfNumbers.get(listOfSymbols.indexOf('+')) + listOfNumbers.get(listOfSymbols.indexOf('+') + 1);
                    variablesModAfterMathOper('+');
                    mathOperationsWithOrder();
                } else {
                    result = listOfNumbers.get(listOfSymbols.indexOf('-')) - listOfNumbers.get(listOfSymbols.indexOf('-') + 1);
                    variablesModAfterMathOper('-');
                    mathOperationsWithOrder();
                }
            }
            if (listOfSymbols.contains('+')) {
                result = listOfNumbers.get(listOfSymbols.indexOf('+')) + listOfNumbers.get(listOfSymbols.indexOf('+') + 1);
                variablesModAfterMathOper('+');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.contains('-')) {
                result = listOfNumbers.get(listOfSymbols.indexOf('-')) - listOfNumbers.get(listOfSymbols.indexOf('-') + 1);
                variablesModAfterMathOper('-');
                mathOperationsWithOrder();
            }
            if (listOfSymbols.isEmpty()) {
                textFieldResult.setText(listOfNumbers.get(0).toString());
                wasEqualsButtonAlreadyClicked = true;
            } else {
                resetButtonClicked();
                textFieldResult.setText("ARG ERR");
            }
        }
    }

    private void variablesModAfterMathOper(Character symbol) {
        listOfNumbers.remove(listOfSymbols.indexOf(symbol));
        listOfNumbers.remove(listOfSymbols.indexOf(symbol));
        listOfNumbers.add(listOfSymbols.indexOf(symbol), result);
        listOfSymbols.remove(listOfSymbols.indexOf(symbol));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}

package pl.artisSol;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Mechanism mech = new Mechanism();
        CalculatorGUI frame = new CalculatorGUI(mech);
    }
}
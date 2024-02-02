/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package easycalculatorapp;

/**
 *
 * @author Ola
 */

//-------------------------KONTROLER-------------------------------------------

public class CalculatorController {
    public CalculatorModel model;
    public myCalculator view; //widok

    public CalculatorController(CalculatorModel model, myCalculator view) {
        this.model = model;
        this.view = view;
        initialize(); // Wywołaj inicjalizację po utworzeniu obiektu
    }

    public void initialize() {
        // Przypisz słuchaczy (listeners) do przycisków
        view.getButton0().addActionListener(e -> buttonClicked("0"));
        view.getButton1().addActionListener(e -> buttonClicked("1"));
        view.getButton2().addActionListener(e -> buttonClicked("2"));
        view.getButton3().addActionListener(e -> buttonClicked("3"));
        view.getButton4().addActionListener(e -> buttonClicked("4"));
        view.getButton5().addActionListener(e -> buttonClicked("5"));
        view.getButton6().addActionListener(e -> buttonClicked("6"));
        view.getButton7().addActionListener(e -> buttonClicked("7"));
        view.getButton8().addActionListener(e -> buttonClicked("8"));
        view.getButton9().addActionListener(e -> buttonClicked("9"));

        // Przypisz słuchaczy do operacji matematycznych
        view.getButtonAdd().addActionListener(e -> operatorClicked("+"));
        view.getButtonSub().addActionListener(e -> operatorClicked("-"));
        view.getButtonMul().addActionListener(e -> operatorClicked("x"));
        view.getButtonDiv().addActionListener(e -> operatorClicked("/"));

        // Przypisz słuchacza do przycisku =
        view.getButtonEqual().addActionListener(e -> equalClicked());
    }

    private void buttonClicked(String text) {
        appendText(text);
    }

    private void appendText(String text) {
        String currentText = view.getT1().getText();
        currentText += text;
        view.getT1().setText(currentText);
    }

    private void equalClicked() {
        if (!view.getT1().getText().isEmpty()) {
            double currentNumber = Double.parseDouble(view.getT1().getText());

            if (model.getNum1() != 0 && model.getOperator() != null) {
                model.setNum2(currentNumber);
                double result = model.calculate();
                view.getT1().setText(String.valueOf(result));
                // Ustaw num1 na obliczony wynik, num2 na 0 i wyczyść operator
                model.setNum1(result);
                model.setNum2(0);
                model.setOperator(null);
            }
        }
    }

    private void operatorClicked(String operator) {
        if (!view.getT1().getText().isEmpty()) {
            double currentNumber = Double.parseDouble(view.getT1().getText());

            if (model.getNum1() == 0) {
                model.setNum1(currentNumber);
            } else if (model.getOperator() != null) {
                
                // Jeśli num1 i operator są ustawione, oblicz wynik
                model.setNum2(currentNumber);
                double result = model.calculate();
                view.getT1().setText(String.valueOf(result));
                
                // Ustaw num1 na obliczony wynik, num2 na 0 i operator na nowy operator
                model.setNum1(result);
                model.setNum2(0);
                model.setOperator(operator);
            }
            else {
                // Jeśli num1 jest ustawione, ale operator nie, ustaw operator na nowy operator
                model.setOperator(operator);
            }
        }
    }
}
 
//------------------------------------------------------------------------------
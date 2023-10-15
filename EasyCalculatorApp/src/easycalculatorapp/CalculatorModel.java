/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package easycalculatorapp;

/**
 *
 * @author Ola
 */

//-------------------------MODEL------------------------------------------

public class CalculatorModel {
    private double num1 = 0;
    private double num2 = 0;
    private String operator = null;
    
    public double getNum1(){
        return num1;
    }
    
    public double getNum2(){
        return num2;
    }
       
    public String getOperator(){
        return operator;
    }
    
    public void setNum1(double num1) {
        this.num1 = num1;
        //System.out.println("setNum1(): num1 = " + num1);

    }
    
    public void setNum2(double num2) {
       this.num2 = num2;    
       //System.out.println("setNum2(): num2 = " + num2);

    }
    
    public void setOperator(String operator) {
        this.operator = operator;
        //System.out.println("setOperator() " + operator);
    }

    public double calculate() {
        if (operator == null) {
            // Brak operatora, zwróć bieżącą liczbę
            return num1;
        }
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    // Obsługa dzielenia przez zero lub rzucenie wyjątku
                    return Double.NaN; 
                }
            default:
                // Obsługa nieznanej operacji lub rzucenie wyjątku
                return Double.NaN; 
        }
    }
    
}

//----------------------------------------------------------------------------
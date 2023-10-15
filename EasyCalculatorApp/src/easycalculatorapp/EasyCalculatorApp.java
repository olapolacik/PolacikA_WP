/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package easycalculatorapp;

/**
 *
 * @author Ola
 */

public class EasyCalculatorApp {

    public static void main(String[] args) {
    CalculatorModel model = new CalculatorModel();
    
    myCalculator view = new myCalculator();
    
    CalculatorController controller = new CalculatorController(model, view);
    
    // Przekaż kontroler do widoku
    view.setController(controller); 
        
    } 
}
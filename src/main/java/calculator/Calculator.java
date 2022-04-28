package calculator;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

public class Calculator {
    private static final List<String> allOperation = List.of("+", "/","-","*");

    public String calculate(int first, int second , String operation) {

        double result = calculationStep(first, second, operation);

       return (new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH))).format(result);
    }

    public double calculationStep(int first, int second, String operation)  {
        if (second == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        if (first < 0) {
            throw new InputMismatchException("Negative number");
        }
        if (second < 0) {
            throw new InputMismatchException("Negative number");
        }
        if ( allOperation.contains(operation)) {

        }else{
            throw new NumberFormatException("Wrong input data!");
        }

        return performOperation( first, second, operation);
    }


    private double performOperation(int firstNumber, int secondNumber, String operation) {
        double result;
        switch (operation){
            case "+":
                result = plus(firstNumber, secondNumber);
                break;
            case "-":
                result = minus(firstNumber, secondNumber);
                break;
            case "*":
                result = multiply(firstNumber, secondNumber);
                break;
            case "/":
                result = division(firstNumber, secondNumber);
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }
    private int plus(int numberOne, int numberTwo){
        return numberOne + numberTwo;
    }
    private int minus(int numberOne, int numberTwo){
        return numberOne - numberTwo;
    }
    private double multiply(int numberOne, double numberTwo){
        return numberOne * numberTwo;
    }
    private double division(int numberOne, double numberTwo){
        return numberOne / numberTwo;
    }
}
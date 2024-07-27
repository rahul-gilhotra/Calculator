package com.example.mytempapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Stack;

public class EquationSolver {
    static String getResult(String equation,Context context){
        Double d = 0.0;
        try {
            d = evaluate(equation,context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(d);
    }
    public static double evaluate(String expression, Context context) throws Exception {

        // Step 1: Tokenize the input string
        // Create two stacks: one for numbers and one for operators
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                // Extract the number (it could be more than one digit)
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (isOperator(c)) {
                // Handle operator precedence
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    performOperation(numbers, operators);
                }
                operators.push(c);
                i++;
            } else {
                throw new Exception("Invalid character: " + c);
            }
        }

        // Perform remaining operations
        while (!operators.isEmpty()) {
            performOperation(numbers, operators);
        }

        // The final result will be the only number left in the stack
        return numbers.pop();
    }

    private static void performOperation(Stack<Double> numbers, Stack<Character> operators) throws Exception {
        if (numbers.size() < 2) {
            throw new Exception("Insufficient numbers for operation.");
        }
        double b = numbers.pop();
        double a = numbers.pop();
        char operator = operators.pop();

        switch (operator) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case 'X':
                numbers.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                numbers.push(a / b);
                break;
            default:
                throw new Exception("Unknown operator: " + operator);
        }
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case 'X':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == 'X' || c == '/';
    }
}

package CalcolatriceRpnDatabase;

import java.util.Objects;
import java.util.Stack;

public class CalcolatriceRPN {
    public CalcolatriceRPN() {}
    private static boolean isNumber(String c) {
        return c.equals("0") || c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5")
                || c.equals("6") || c.equals("7") || c.equals("8") || c.equals("9");
    }

    private static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    private static int getPriority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 1;
        }
        else if (c.equals("*") || c.equals("/")) {
            return 2;
        }
        else {
            return 0;
        }
    }

    private static boolean isNumberOrDecimal(String token) {
        try {
            // provo a convertire il token in un numero
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            // se la conversione fallisce vuol dire che non e un numero convertibile quindi sara un operatore o parentesi
            return false;
        }
    }
    private static String trasformaInPostfissa(String input) {
        Stack<String> operatori = new Stack<>();
        Stack<String> risultato = new Stack<>();
        String[] tokens = input.split("\\s+"); //splitto la stringa in base agli spazi

        for (String token : tokens) {
            if (isNumberOrDecimal(token)) {
                risultato.push(token);
            } else if (token.equals("(")) {
                operatori.push(token);
            } else if (token.equals(")")) {
                while (!Objects.equals(operatori.peek(), "(")) {
                    risultato.push(operatori.pop());
                }
                operatori.pop();
            } else if (isOperator(token)) {
                while (!operatori.isEmpty() && getPriority(token) <= getPriority(operatori.peek())) {
                    risultato.push(operatori.pop());
                }
                operatori.push(token);
            }
        }

        while (!operatori.isEmpty()) {
            risultato.push(operatori.pop());
        }

        StringBuilder rpnExpression = new StringBuilder();
        for (String token : risultato) {
            rpnExpression.append(token).append(" ");
        }

        return rpnExpression.toString().trim();
    }
    public static String calcola(boolean isPostfissa, String input) throws ArithmeticException {
        String[] tokens;
        if (!isPostfissa) {
            tokens = trasformaInPostfissa(input).split("\\s+");
        }
        else {
            tokens = input.split("\\s+");
        }
        Stack<Double> risultato = new Stack<>();
        try {
            for (String token : tokens) {
                if (isNumberOrDecimal(token)) {
                    risultato.push(Double.parseDouble(String.valueOf(token)));
                } else if (isOperator(token)) {
                    double a = risultato.pop();
                    double b = risultato.pop();
                    switch (token) {
                        case "+":
                            risultato.push(b + a);
                            break;
                        case "-":
                            risultato.push(b - a);
                            break;
                        case "*":
                            risultato.push(b * a);
                            break;
                        case "/":
                            if (a == 0) {
                                throw new ArithmeticException("Divisione per 0 non consentita");
                            }
                            risultato.push(b / a);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new ArithmeticException("Espressione non valida");
        }
        return String.valueOf(risultato.pop());
    }
}

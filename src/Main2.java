import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = sc.nextLine();
        String result = calc(input);
        System.out.println("Результат: " + result);
    }

    public static String calc(String input) {
        if (input.contains("+")) {
            String[] numbers = input.split("\\+");
            checkNumbers(numbers[0], numbers[1]);
            return performAddition(numbers[0], numbers[1]);
        } else if (input.contains("-")) {
            String[] numbers = input.split("-");
            checkNumbers(numbers[0], numbers[1]);
            return performSubtraction(numbers[0], numbers[1]);
        } else if (input.contains("*")) {
            String[] numbers = input.split("\\*");
            checkNumbers(numbers[0], numbers[1]);
            return performMultiplication(numbers[0], numbers[1]);
        } else if (input.contains("/")) {
            String[] numbers = input.split("/");
            checkNumbers(numbers[0], numbers[1]);
            return performDivision(numbers[0], numbers[1]);
        } else {
            throw new IllegalArgumentException("Некорректное арифметическое выражение");
        }
    }

    public static void checkNumbers(String num1, String num2) {
        if (!isNumberValid(num1) || !isNumberValid(num2)) {
            throw new IllegalArgumentException("Операнды должны быть целыми числами от 1 до 10");
        }
    }

    public static boolean isNumberValid(String num) {
        try {
            int number = Integer.parseInt(num);
            return number >= 1 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String performAddition(String num1, String num2) {
        int result = Integer.parseInt(num1) + Integer.parseInt(num2);
        return Integer.toString(result);
    }

    public static String performSubtraction(String num1, String num2) {
        int result = Integer.parseInt(num1) - Integer.parseInt(num2);
        return Integer.toString(result);
    }

    public static String performMultiplication(String num1, String num2) {
        int result = Integer.parseInt(num1) * Integer.parseInt(num2);
        return Integer.toString(result);
    }

    public static String performDivision(String num1, String num2) {
        int result = Integer.parseInt(num1) / Integer.parseInt(num2);
        return Integer.toString(result);
    }
}
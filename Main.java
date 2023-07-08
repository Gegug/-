import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Character, Integer> romanValues = createRomanValuesMap();

    private static Map<Character, Integer> createRomanValuesMap() {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        // Остальные значения римских чисел

        return romanValues;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = sc.nextLine();
        String result = calc(input);
        System.out.println("Результат: " + result);
    }

    public static String calc(String input) {
        String[] tokens = input.split("\\+|-|\\*|/");
        if (tokens.length > 2) {
            throw new IllegalArgumentException("Некорректное арифметическое выражение");
        }
        if (input.contains("+")) {
            String[] numbers = input.split("\\+");
            boolean isRoman = isRomanNumber(numbers[0]) && isRomanNumber(numbers[1]);
            if (isRoman) {
                return performRomanAddition(numbers[0], numbers[1]);
            } else {
                checkNumbers(numbers[0], numbers[1]);
                return performAddition(numbers[0], numbers[1]);
            }
        } else if (input.contains("-")) {
            String[] numbers = input.split("-");
            boolean isRoman = isRomanNumber(numbers[0]) && isRomanNumber(numbers[1]);
            if (isRoman) {
                return performRomanSubtraction(numbers[0], numbers[1]);
            } else {
                checkNumbers(numbers[0], numbers[1]);
                return performSubtraction(numbers[0], numbers[1]);
            }
        } else if (input.contains("*")) {
            String[] numbers = input.split("\\*");
            boolean isRoman = isRomanNumber(numbers[0]) && isRomanNumber(numbers[1]);
            if (isRoman) {
                return performRomanMultiplication(numbers[0], numbers[1]);
            } else {
                checkNumbers(numbers[0], numbers[1]);
                return performMultiplication(numbers[0], numbers[1]);
            }
        } else if (input.contains("/")) {
            String[] numbers = input.split("/");
            boolean isRoman = isRomanNumber(numbers[0]) && isRomanNumber(numbers[1]);
            if (isRoman) {
                return performRomanDivision(numbers[0], numbers[1]);
            } else {
                checkNumbers(numbers[0], numbers[1]);
                return performDivision(numbers[0], numbers[1]);
            }
        }
        throw new IllegalArgumentException("Некорректное арифметическое выражение");
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

    public static boolean isRomanNumber(String num) {
        for (char c : num.toCharArray()) {
            if (!romanValues.containsKey(c)) {
                return false;
            }
        }
        return true;
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

    public static String performRomanAddition(String num1, String num2) {
        int result = convertRomanToArabic(num1) + convertRomanToArabic(num2);
        return convertArabicToRoman(result);
    }

    public static String performRomanSubtraction(String num1, String num2) {
        int result = convertRomanToArabic(num1) - convertRomanToArabic(num2);
        return convertArabicToRoman(result);
    }

    public static String performRomanMultiplication(String num1, String num2) {
        int result = convertRomanToArabic(num1) * convertRomanToArabic(num2);
        return convertArabicToRoman(result);
    }

    public static String performRomanDivision(String num1, String num2) {
        int result = convertRomanToArabic(num1) / convertRomanToArabic(num2);
        return convertArabicToRoman(result);
    }

    public static int convertRomanToArabic(String romanNumber) {
        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char ch = romanNumber.charAt(i);
            int value = romanValues.get(ch);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
                prevValue = value;
            }
        }

        return result;
    }

    public static String convertArabicToRoman(int arabicNumber) {
        if (arabicNumber < 0) {
            throw new IllegalArgumentException("Римское число не может быть отрицательным");
        }
        if (arabicNumber == 0) {
            return "0";
        }

        StringBuilder romanNumber = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : romanValues.entrySet()) {
            char ch = entry.getKey();
            int value = entry.getValue();
            while (arabicNumber >= value) {
                romanNumber.append(ch);
                arabicNumber -= value;
            }
        }

        return romanNumber.toString();
    }
}
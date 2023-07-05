import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Character, Integer> ROMAN_VALUES = createRomanValuesMap();

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
            throw new IllegalArgumentException("Операнды должны быть целыми числами от I до X");
        }
    }

    public static boolean isNumberValid(String num) {
        try {
            if (num.length() == 1) {
                char romanDigit = num.charAt(0);
                return ROMAN_VALUES.containsKey(romanDigit);
            } else if (num.length() == 2) {
                char firstDigit = num.charAt(0);
                char secondDigit = num.charAt(1);
                return ROMAN_VALUES.containsKey(firstDigit) && ROMAN_VALUES.containsKey(secondDigit);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String performAddition(String num1, String num2) {
        int arabic1 = romanToArabic(num1);
        int arabic2 = romanToArabic(num2);
        int result = arabic1 + arabic2;
        return arabicToRoman(result);
    }

    public static String performSubtraction(String num1, String num2) {
        int arabic1 = romanToArabic(num1);
        int arabic2 = romanToArabic(num2);
        int result = arabic1 - arabic2;
        return arabicToRoman(result);
    }

    public static String performMultiplication(String num1, String num2) {
        int arabic1 = romanToArabic(num1);
        int arabic2 = romanToArabic(num2);
        int result = arabic1 * arabic2;
        return arabicToRoman(result);
    }

    public static String performDivision(String num1, String num2) {
        int arabic1 = romanToArabic(num1);
        int arabic2 = romanToArabic(num2);
        int result = arabic1 / arabic2;
        return arabicToRoman(result);
    }

    public static int romanToArabic(String romanNum) {
        int result = 0;
        int prevValue = 0;
        for (int i = romanNum.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_VALUES.get(romanNum.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        return result;
    }public static String arabicToRoman(int arabicNum) {
        if (arabicNum <= 0) {
            throw new IllegalArgumentException("Арабское число должно быть положительным");
        }
        StringBuilder romanNum = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : ROMAN_VALUES.entrySet()) {
            char romanDigit = entry.getKey();
            int arabicValue = entry.getValue();
            while (arabicNum >= arabicValue) {
                romanNum.append(romanDigit);
                arabicNum -= arabicValue;
            }
        }
        return romanNum.toString();
    }

    public static Map<Character, Integer> createRomanValuesMap() {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        return romanValues;
    }
}
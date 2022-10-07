import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что Вы хотите посчитать?");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (NumberFormatException | IOException e) {
            System.out.println(e);
        }
    }
    public static String calc (String input) throws NumberFormatException, IOException{
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] arabian = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        String[] actions = {"+", "-", "*", "/"};
        String[] input1 = input.split(" ");

        if (input1.length < 3)
            throw new NumberFormatException("Символы в выражении должны быть написаны через пробел");

        boolean a = false;
        for (int i = 0; i < actions.length; i++)
            if (input1[1].equals(actions[i])) {
                a = true;
            }
        if (!a)
            throw new NumberFormatException("Некорректное выражение");

        for (int i = 0; i < 10; i++)
            if (input1[0].equals(roman[i]))
                for (int l = 0; l < 10; l++)
                    if (input1[2].equals(arabian[l]))
                        throw new NumberFormatException("Числа в разных системах счисления");

        for (int i = 0; i < 10; i++)
            if (input1[0].equals(arabian[i]))
                for (int l = 0; l < 10; l++)
                    if (input1[2].equals(roman[l]))
                        throw new NumberFormatException("Числа в разных системах счисления");

        String result = "";

        for (int i = 0; i < 10; i++)
            if (input1[0].equals(roman[i])) {
                result = romanCalc(input1);
                return result;
            }

        for (int i = 0; i < 10; i++)
            if (input1[0].equals(arabian[i])) {
                result = Integer.toString(arabianCalc(input1));
                return result;
            }

        if (result.equals(""))
            throw new NumberFormatException("Необходимо вводить целые числа от 1 до 10 включительно");

        return result;
    }
    public static String romanCalc (String [] input1) throws IOException {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] arabian = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

        for (int i = 0; i < roman.length; i++)
            if (input1[0].equals(roman[i]))
                input1[0] = arabian[i];

        for (int i = 0; i < roman.length; i++)
            if (input1[2].equals(roman[i]))
                input1[2] = arabian[i];

        if (arabianCalc(input1) < 1) {
            throw new IOException("В римской системе счисления отсутствуют отрицательные числа");
        }

        String arabianResult = Integer.toString(arabianCalc(input1));
        String[] arr = arabianResult.split("");
        String romanResult;

        if (arr.length == 1) {
            for (int i = 0; i < roman.length; i++)
                if (arr[0].equals(arabian[i]))
                    arr[0] = roman[i];
            romanResult = arr[0];
        }
        else {
            arr[0] = arr[0] + "0";

            for (int i = 0; i < roman.length; i++)
                if (arr[0].equals(arabian[i]))
                    arr[0] = roman[i];

            if (!arr[1].equals("0")) {
                for (int i = 0; i < roman.length; i++)
                    if (arr[1].equals(arabian[i]))
                        arr[1] = roman[i];
            }
            else arr[1] = "";

            if (arr.length > 2)
                arr[0] = roman[18];

            romanResult = arr[0] + arr[1];
        }
        return romanResult;
    }
        public static int arabianCalc (String[] input1) throws NumberFormatException {
            int a = Integer.parseInt(input1[0]);
            int b = Integer.parseInt(input1[2]);

            if (a < 1 || a > 10 || b < 1 || b > 10)
                throw new NumberFormatException("Необходимо ввести числа от 1 до 10");

            int result = 0;
            switch (input1[1]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
            }
            return result;
        }
    }
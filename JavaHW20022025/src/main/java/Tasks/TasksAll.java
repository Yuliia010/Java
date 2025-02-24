package Tasks;
import java.util.*;

public class TasksAll {
    public static void main(String[] args) {
        // task1();
        // task2();
        //task3();
        // task4();
        //task5();
        //task6();
        //task7();
        //task8();
        //task9();
        //task10();
        //task11();
        task12();
    }

    // Task 1
    public static void task1() {
        System.out.println("\"Your time is limited,\n\tso don’t waste it\n\t\tliving someone else’s life\"");
        System.out.println("Steve Jobs");
    }

    // Task 2
    public static void task2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число: ");
        double number = scanner.nextDouble();

        System.out.print("Введіть відсоток: ");
        double percent = scanner.nextDouble();

        double result = (number * percent) / 100;
        System.out.println(percent + "% від " + number + " = " + result);

        scanner.close();
    }

    // Task 3
    public static void task3() {
        Scanner scanner = new Scanner(System.in);
        int result = 0;

        System.out.print("Введіть перше число: ");
        if (scanner.hasNextInt()) {
            int number1 = scanner.nextInt();
            result = number1 * 100;
        } else {
            System.out.println("Це не ціле число");
            scanner.close();
            return;
        }

        System.out.print("Введіть друге число: ");
        if (scanner.hasNextInt()) {
            int number2 = scanner.nextInt();
            result += number2 * 10;
        } else {
            System.out.println("Це не ціле число");
            scanner.close();
            return;
        }

        System.out.print("Введіть третє число: ");
        if (scanner.hasNextInt()) {
            int number3 = scanner.nextInt();
            result += number3;
        } else {
            System.out.println("Це не ціле число");
            scanner.close();
            return;
        }

        System.out.println("Результат = " + result);
        scanner.close();
    }

    // Task 4
    public static void task4() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шестизначне число: ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            String numStr = Integer.toString(number);

            if (numStr.length() == 6) {
                char[] digits = numStr.toCharArray();
                swap(digits, 0, 5);
                swap(digits, 1, 4);

                System.out.println("Результат: " + new String(digits));
            } else {
                System.out.println("Помилка: Число повинно бути шестизначним");
            }
        } else {
            System.out.println("Помилка: Введене значення не є цілим числом");
        }

        scanner.close();
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Task 5
    public static void task5() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть номер місяця (1-12): ");
        if (scanner.hasNextInt()) {
            int month = scanner.nextInt();

            if (month >= 1 && month <= 12) {
                String season;
                if (month == 12 || month == 1 || month == 2) {
                    season = "Зима";
                } else if (month >= 3 && month <= 5) {
                    season = "Весна";
                } else if (month >= 6 && month <= 8) {
                    season = "Літо";
                } else {
                    season = "Осінь";
                }

                System.out.println("Пора року: " + season);
            } else {
                System.out.println("Помилка: номер місяця повинен бути в діапазоні від 1 до 12.");
            }
        } else {
            System.out.println("Помилка: введене значення не є цілим числом.");
        }

        scanner.close();
    }

    // Task 6
    public static void task6() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість метрів: ");
        if (scanner.hasNextDouble()) {
            double meters = scanner.nextDouble();

            System.out.println("Оберіть одиницю вимірювання для конвертації:");
            System.out.println("1 - Мілі (miles)");
            System.out.println("2 - Дюйми (inches)");
            System.out.println("3 - Ярди (yards)");
            System.out.print("Ваш вибір: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                double result = 0;
                String unit = "";

                switch (choice) {
                    case 1:
                        result = meters * 0.000621371;
                        unit = "миль";
                        break;
                    case 2:
                        result = meters * 39.3701;
                        unit = "дюймів";
                        break;
                    case 3:
                        result = meters * 1.09361;
                        unit = "ярдів";
                        break;
                    default:
                        System.out.println("Помилка: Невірний вибір.");
                        scanner.close();
                        return;
                }

                System.out.println(meters + " метрів = " + result + " " + unit);
            } else {
                System.out.println("Помилка: Введене значення не є числом.");
            }
        } else {
            System.out.println("Помилка: Введене значення не є числом.");
        }

        scanner.close();
    }

    // Task 7
    public static void task7() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть перше число: ");
        if (scanner.hasNextInt()) {
            int num1 = scanner.nextInt();

            System.out.print("Введіть друге число: ");
            if (scanner.hasNextInt()) {
                int num2 = scanner.nextInt();

                int start = Math.min(num1, num2);
                int end = Math.max(num1, num2);

                System.out.println("Непарні числа в діапазоні [" + start + ", " + end + "]:");

                for (int i = start; i <= end; i++) {
                    if (i % 2 != 0) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Помилка: Друге введене значення не є цілим числом.");
            }
        } else {
            System.out.println("Помилка: Перше введене значення не є цілим числом.");
        }

        scanner.close();
    }

    // Task 8
    public static void task8() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть перше число (початок діапазону): ");
        if (scanner.hasNextInt()) {
            int start = scanner.nextInt();

            System.out.print("Введіть друге число (кінець діапазону): ");
            if (scanner.hasNextInt()) {
                int end = scanner.nextInt();

                if (start <= end) {

                    for (int i = start; i <= end; i++) {
                        System.out.println("Таблиця множення для " + i + ":");
                        for (int j = 1; j <= 10; j++) {
                            System.out.println(i + " * " + j + " = " + (i * j));
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("Помилка: Початкове число повинно бути менше або рівне кінцевому.");
                }
            } else {
                System.out.println("Помилка: Друге введене значення не є цілим числом.");
            }
        } else {
            System.out.println("Помилка: Перше введене значення не є цілим числом.");
        }

        scanner.close();
    }

    // Task 9
    public static void task9(){

        int size = 20;
        int[] array = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100; // випадкове число від -100 до 100
        }

        int min = array[0], max = array[0];
        int negativeCount = 0, positiveCount = 0, zeroCount = 0;

        for (int num : array) {
            if (num < 0) {
                negativeCount++;
            } else if (num > 0) {
                positiveCount++;
            } else {
                zeroCount++;
            }

            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        System.out.println("Масив: ");
        for (int num : array) {
            System.out.print(num + " ");
        }

        System.out.println("\nМінімальне значення: " + min);
        System.out.println("Максимальне значення: " + max);
        System.out.println("Кількість від'ємних чисел: " + negativeCount);
        System.out.println("Кількість додатних чисел: " + positiveCount);
        System.out.println("Кількість нулів: " + zeroCount);
    }

    //  Task 10
    public static void task10(){
        int size = 20;
        int[] array = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100;
        }

        int evenCount = 0, oddCount = 0, negativeCount = 0, positiveCount = 0;

        for (int num : array) {
            if (num % 2 == 0) evenCount++;
            if (num % 2 != 0) oddCount++;
            if (num < 0) negativeCount++;
            if (num > 0) positiveCount++;
        }

        int[] evenArray = new int[evenCount];
        int[] oddArray = new int[oddCount];
        int[] negativeArray = new int[negativeCount];
        int[] positiveArray = new int[positiveCount];

        int evenIndex = 0, oddIndex = 0, negativeIndex = 0, positiveIndex = 0;

        for (int num : array) {
            if (num % 2 == 0) evenArray[evenIndex++] = num;
            if (num % 2 != 0) oddArray[oddIndex++] = num;
            if (num < 0) negativeArray[negativeIndex++] = num;
            if (num > 0) positiveArray[positiveIndex++] = num;
        }

        System.out.println("Оригінальний масив: ");
        for (int num : array) {
            System.out.print(num + " ");
        }

        System.out.println("\nПарні числа: ");
        for (int num : evenArray) {
            System.out.print(num + " ");
        }

        System.out.println("\nНепарні числа: ");
        for (int num : oddArray) {
            System.out.print(num + " ");
        }

        System.out.println("\nВід'ємні числа: ");
        for (int num : negativeArray) {
            System.out.print(num + " ");
        }

        System.out.println("\nДодатні числа: ");
        for (int num : positiveArray) {
            System.out.print(num + " ");
        }
    }

    // Task 11
    public static void task11(){
        printLine(10, "horizontal", "*");
        printLine(5, "vertical", "#");
    }

    public static void printLine(int length, String direction, String symbol) {
         if ("horizontal".equalsIgnoreCase(direction)) {
            for (int i = 0; i < length; i++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
        else if ("vertical".equalsIgnoreCase(direction)) {
            for (int i = 0; i < length; i++) {
                System.out.println(symbol);
            }
        }
        else {
            System.out.println("Невідомий напрямок. Використовуйте 'horizontal' або 'vertical'.");
        }
    }

    // Task 12
    public static void task12(){
        int[] array = {15, 2, 8, 3, 19, 1, 9};

        System.out.println("Початковий масив: " + Arrays.toString(array));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть напрям сортування (1 - зростання, 2 - спадання): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            sortArray(array, true);
        } else if (choice == 2) {
            sortArray(array, false);
        } else {
            System.out.println("Невірний вибір!");
        }

        System.out.println("Відсортований масив: " + Arrays.toString(array));

        scanner.close();
    }

    public static void sortArray(int[] array, boolean ascendingSort) {
        if (ascendingSort) {
            Arrays.sort(array);
        } else {
            Arrays.sort(array);
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        }
    }
}

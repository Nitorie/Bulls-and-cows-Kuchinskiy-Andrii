import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Грати");
            System.out.println("2. Правила");
            System.out.println("3. Вихід");
            System.out.print("Оберiть пункт: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введіть число від 1 до 3");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    playGame(scanner);
                    break;
                case 2:
                    showRules();
                    break;
                case 3:
                    System.out.println("Вихід з гри...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }

    public static void showRules() {
        System.out.println("\n===== ПРАВИЛА =====");
        System.out.println("Комп'ютер загадує n-значне число.");
        System.out.println("Ваша задача — вгадати його.");
        System.out.println("Бики — цифра стоїть на правильному місці.");
        System.out.println("Корови — цифра є в числі, але не на своєму місці.");
        System.out.println("Приклад: якщо число 1234, а ви ввели 1243:");
        System.out.println("Бики: 2, Корови: 2");
    }

    public static void playGame(Scanner scanner) {
        int n;
        int p;
        int bulls = 0;
        int cows = 0;
        int attempts = 0;

        Random r = new Random();

        while (true) {
            try {
                System.out.print("Введiть довжину числа: ");
                n = scanner.nextInt();

                if (n <= 0) {
                    throw new IllegalArgumentException("Довжина повинна бути більше 0");
                }
                if (n > 9) {
                    throw new IllegalArgumentException("Від 1 до 9 цифр");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Потрібно ввести число!");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Загадано " + n + "-значне число");

        // генерація числа
        p = r.nextInt(9) + 1;
        for (int i = 1; i < n; i++) {
            p = p * 10 + r.nextInt(10);
        }

        System.out.println("----------------------------");

        while (bulls != n) {
            try {
                System.out.print("Введiть " + n + "-значне число: ");
                String input = scanner.next();

                if (input.length() != n) {
                    throw new NumberFormatException();
                }

                int b = Integer.parseInt(input);

                int[] digits = new int[n];
                for (int i = n - 1; i >= 0; i--) {
                    digits[i] = b % 10;
                    b /= 10;
                }

                int tempP = p;
                int[] secretDigits = new int[n];
                for (int i = n - 1; i >= 0; i--) {
                    secretDigits[i] = tempP % 10;
                    tempP /= 10;
                }

                for (int i = 0; i < n; i++) {
                    if (digits[i] == secretDigits[i]) {
                        bulls++;
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (digits[i] == secretDigits[i]) continue;

                    for (int j = 0; j < n; j++) {
                        if (i != j && digits[i] == secretDigits[j]) {
                            cows++;
                            break;
                        }
                    }
                }

                System.out.println("Бики: " + bulls);
                System.out.println("Корови: " + cows);

                attempts++;
                System.out.println("Спроби: " + attempts);

            } catch (NumberFormatException e) {
                System.out.println("Введіть коректне число!");
                scanner.nextLine();
            }

            if (bulls == n) {
                System.out.println("🎉 Ви вгадали число за " + attempts + " спроб!");
                break;
            }
            bulls = 0;
            cows = 0;
        }
    }
}
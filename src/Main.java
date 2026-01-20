import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int n;
    int p = 0;

    Scanner scanner = new Scanner(System.in);
    Random r = new Random();
    System.out.print("Введiть довжину числа : ");
    n = scanner.nextInt();

    if (n <= 0) {
      System.out.println("Довжина числа повинна бути більше 0");
      return;
    } else {
      System.out.println("Створилось рандомне " +n+ "-значне число");
    }

    //Фунция что-бы первая цифра не была 0 и что-бы число было n-значное
    p = r.nextInt(9) + 1;
    for (int i = 1; i < n; i++) {
      p = p * 10 + r.nextInt(10);
    }

    System.out.println("-------------------------------------------------------");
    System.out.print("Напишить будь-яке " +n+"-значне число : ");
    int b = scanner.nextInt();

    //разбиваем числа на отдельные цифры и записываем их как массив
    int[] digits = new int[n];
    for (int i = n-1; i >= 0; i--) {
      digits[i] = b % 10;
      b /= 10;
    }

    int[] secretDigits = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      secretDigits[i] = p % 10;
      p /= 10;
    }

    //Сравнение чисел и нахождение быков и коров
    int bulls = 0;
    int cows = 0;

    for (int i = 0; i < n; i++) {
      if (digits[i] == secretDigits[i]) {
        bulls++;
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i != j && digits[i] == secretDigits[j]) {
          cows++;
          break;
        }
      }
    }


    System.out.println("Быки: " + bulls);
    System.out.println("Коровы: " + cows);

    scanner.close();
  }
}
import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int n;
    int p = 0;
    int bulls = 0;
    int cows = 0;
    int attemps = 0;

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


    //Сравнение чисел и нахождение быков и коров
      while (bulls != n) {

        System.out.print("Напишiть будь-яке " +n+"-значне число : ");
        String asd = scanner.next();
        if (asd.length() != n) {
          System.out.println("Потрібно ввести рівно " + n + " цифр");
          continue;
        }
        //Преобразовуем строку в инт
        int b = Integer.parseInt(asd);

        //разбиваем числа на отдельные цифры и записываем их как массив
        int[] digits = new int[n];
        for (int i = n-1; i >= 0; i--) {
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
      attemps++;
        System.out.println("Кiлькiсть спроб: " +attemps);

        if (bulls == n) {
          System.out.println("🎉🎉Ви🎉вгадали🎉число🎉за🎉" + attemps + "🎉спроб!🎉🎉");
          break;
        }
        bulls = 0;
        cows = 0;
    }

    scanner.close();
  }
}
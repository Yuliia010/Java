import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Tasks {
    public static void main(String[] args) throws InterruptedException, IOException {
        task1();
        task2();
    }
    private static void task1() throws InterruptedException {
        final int SIZE = 10;
        int[] array = new int[SIZE];
        CountDownLatch latch = new CountDownLatch(1);

        Thread generator = new Thread(() -> {
            Random rand = new Random();
            for (int i = 0; i < SIZE; i++) {
                array[i] = rand.nextInt(100);
            }
            System.out.println("Generated array: " + Arrays.toString(array));
            latch.countDown();
        });

        Thread sumThread = new Thread(() -> {
            try {
                latch.await();
                int sum = Arrays.stream(array).sum();
                System.out.println("Sum of elements: " + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread avgThread = new Thread(() -> {
            try {
                latch.await();
                double average = Arrays.stream(array).average().orElse(0);
                System.out.println("Average value: " + average);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        generator.start();
        sumThread.start();
        avgThread.start();

        generator.join();
        sumThread.join();
        avgThread.join();
    }

    private static void task2() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        CountDownLatch latch = new CountDownLatch(1);

        Thread writer = new Thread(() -> {
            try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                Random rand = new Random();
                for (int i = 0; i < 10; i++) {
                    int num = rand.nextInt(50) + 1;
                    bw.write(num + "\n");
                }
                System.out.println("File written.");
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread primeFinder = new Thread(() -> {
            try {
                latch.await();
                List<Integer> primes = new ArrayList<>();
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    int num = Integer.parseInt(line.trim());
                    if (isPrime(num)) primes.add(num);
                }
                Files.write(Paths.get("primes.txt"), primes.toString().getBytes());
                System.out.println("Primes found: " + primes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread factorialFinder = new Thread(() -> {
            try {
                latch.await();
                List<String> lines = Files.readAllLines(path);
                List<String> results = new ArrayList<>();
                for (String line : lines) {
                    int num = Integer.parseInt(line.trim());
                    results.add(num + "! = " + factorial(num));
                }
                Files.write(Paths.get("factorials.txt"), results);
                System.out.println("Factorials calculated.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        writer.start();
        primeFinder.start();
        factorialFinder.start();

        writer.join();
        primeFinder.join();
        factorialFinder.join();
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static long factorial(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
}

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

class Matrix<T extends Number> {
    private final int rows;
    private final int cols;
    private final T[][] data;
    private final Class<T> type;

    public Matrix(Class<T> type, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.type = type;
        this.data = (T[][]) Array.newInstance(type, rows, cols);
    }

    public void fillFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = type.cast(scanner.nextInt());
            }
        }
    }

    public void fillWithRandom() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = type.cast(random.nextInt(100));
            }
        }
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8d ", data[i][j].intValue());
            }
            System.out.println();
        }
    }

    public T findMax() {
        T max = data[0][0];
        for (T[] row : data) {
            for (T num : row) {
                if (num.intValue() > max.intValue()) {
                    max = num;
                }
            }
        }
        return max;
    }

    public T findMin() {
        T min = data[0][0];
        for (T[] row : data) {
            for (T num : row) {
                if (num.intValue() < min.intValue()) {
                    min = num;
                }
            }
        }
        return min;
    }

    public double calculateAverage() {
        double sum = 0;
        int count = rows * cols;
        for (T[] row : data) {
            for (T num : row) {
                sum += num.intValue();
            }
        }
        return sum / count;
    }

    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Розміри матриць повинні бути однаковими для додавання.");
        }

        Matrix<T> result = new Matrix<>(this.type, this.rows, this.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = type.cast(this.data[i][j].intValue() + other.data[i][j].intValue());
            }
        }
        return result;
    }

    public Matrix<T> subtract(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Розміри матриць повинні бути однаковими для віднімання.");
        }

        Matrix<T> result = new Matrix<>(this.type, this.rows, this.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = type.cast(this.data[i][j].intValue() - other.data[i][j].intValue());
            }
        }
        return result;
    }

    public Matrix<T> multiply(Matrix<T> other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Кількість стовпців першої матриці повинна дорівнювати кількості рядків другої.");
        }

        Matrix<T> result = new Matrix<>(this.type, this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                int sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k].intValue() * other.data[k][j].intValue();
                }
                result.data[i][j] = type.cast(sum);
            }
        }
        return result;
    }

    public Matrix<T> divide(double scalar) {
        Matrix<T> result = new Matrix<>(this.type, this.rows, this.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = type.cast((int) (this.data[i][j].intValue() / scalar));
            }
        }
        return result;
    }
}

public class Task2 {
    public static void main(String[] args) {
        Matrix<Integer> matrix1 = new Matrix<>(Integer.class, 3, 3);
        matrix1.fillWithRandom();
        System.out.println("Згенерована матриця 1:");
        matrix1.display();

        Matrix<Integer> matrix2 = new Matrix<>(Integer.class, 3, 3);
        matrix2.fillWithRandom();
        System.out.println("Згенерована матриця 2:");
        matrix2.display();

        Matrix<Integer> addedMatrix = matrix1.add(matrix2);
        System.out.println("Матриця 1 + Матриця 2:");
        addedMatrix.display();

        Matrix<Integer> subtractedMatrix = matrix1.subtract(matrix2);
        System.out.println("Матриця 1 - Матриця 2:");
        subtractedMatrix.display();

        Matrix<Integer> multipliedMatrix = matrix1.multiply(matrix2);
        System.out.println("Матриця 1 * Матриця 2:");
        multipliedMatrix.display();

        Matrix<Integer> dividedMatrix = matrix1.divide(2);
        System.out.println("Матриця 1 / 2:");
        dividedMatrix.display();

        System.out.println("Максимальний елемент у матриці 1: " + matrix1.findMax());
        System.out.println("Мінімальний елемент у матриці 1: " + matrix1.findMin());

        System.out.println("Середнє значення матриці 1: " + matrix1.calculateAverage());
    }
}

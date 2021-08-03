import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int number = readAndCheckInput();
        int[][] array = createTwoDimensionalArray(number);
        array = fillDiagonalsOfArray(array);
        printTwoDimensionalArray(array);
    }

    static int readAndCheckInput() {
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        try {
            number = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(exception + ": Enter a valid number.");
        }

        if (number <= 0 || number > 100) {
            throw new InputMismatchException("Enter a number, not greater than 100.");
        }

        return number;
    }

    static int[][] createTwoDimensionalArray(int size) {
        int[][] array = new int[size][size];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
            }
        }

        return array;
    }

    static int[][] fillDiagonalsOfArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int n = i - j;
                array[i][j] = Math.abs(n);
            }
        }

        return array;
    }

    static void printTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }
}
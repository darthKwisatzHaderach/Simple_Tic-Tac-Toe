import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int size = readAndCheckInput();
        String[][] array = createTwoDimensionalArrayWithDots(size);
        array = fillMiddleRowOfArray(array);
        array = fillMiddleColumnOfArray(array);
        array = fillSecondaryDiagonalsOfArray(array);
        printTwoDimensionalArray(array);
    }

    static int readAndCheckInput() {
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        try {
            number = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(exception + ": Enter a valid odd number.");
        }

        if (number <= 0 || number > 15) {
            throw new InputMismatchException("Enter an odd number, not exceeding 15.");
        }

        if (number % 2 == 0) {
            throw new InputMismatchException("Enter an odd number.");
        }

        return number;
    }

    static String[][] createTwoDimensionalArrayWithDots(int size) {
        String[][] array = new String[size][size];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ".";
            }
        }

        return array;
    }

    static String[][] fillMiddleRowOfArray(String[][] array) {
        int middleRow = array.length / 2;

        for (int i = 0; i < array[middleRow].length; i++) {
            array[middleRow][i] = "*";
        }

        return array;
    }

    static String[][] fillMiddleColumnOfArray(String[][] array) {
        int middleColumn = array.length / 2;

        for (int i = 0; i < array.length; i++) {
            array[i][middleColumn] = "*";
        }

        return array;
    }

    static String[][] fillSecondaryDiagonalsOfArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = "*";
        }

        for (int i = 0; i < array.length; i++) {
            array[i][array[i].length - 1 - i] = "*";
        }

        return array;
    }

    static void printTwoDimensionalArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }
}
package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] grid = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        printGameGrid(grid);
        Result result;
        String[][] newGrid = Arrays.stream(grid).map(String[]::clone).toArray(String[][]::new);

        do {
            newGrid = Arrays.stream(newGrid).map(String[]::clone).toArray(String[][]::new);

            do {
                newGrid = getAndCheckUserInput(newGrid);
            } while (Arrays.deepEquals(newGrid, grid));

            printGameGrid(newGrid);
            result = calculateResult(newGrid);
        } while (result == Result.GAME_NOT_FINISHED);

        System.out.println(result.description);
    }

    static String[][] readGridState() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");

        String gridState = scanner.nextLine();

        if (gridState.length() != 9) {
            throw new InputMismatchException("Input string with grid state must be 9 characters long");
        }

        String stringWithWrongSymbols = gridState
                .replace("X", "")
                .replace("O", "")
                .replace("_", "");

        if (stringWithWrongSymbols.length() > 0) {
            throw new InputMismatchException("Input string with grid state contains wrong symbols.");
        }

        String[] gridStateArray = gridState.split("");
        String[][] grid = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = gridStateArray[i * 3 + j];
            }
        }

        return grid;
    }

    static void printGameGrid(String[][] grid) {
        System.out.println("---------");

        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");

            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");
    }

    static Result calculateResult(String[][] grid) {
        Result result = checkIfXWins(grid);

        if (Result.X_WINS.equals(result)) {
            if (Result.GAME_NOT_FINISHED.equals(checkIfGameNotFinished(grid))) {
                return checkForExtraMoves(grid, result);
            } else {
                return result;
            }
        }

        result = checkIfOWins(grid);

        if (Result.O_WINS.equals(result)) {
            if (Result.GAME_NOT_FINISHED.equals(checkIfGameNotFinished(grid))) {
                return checkForExtraMoves(grid, result);
            } else {
                return result;
            }
        }

        result = checkIfGameNotFinished(grid);

        if (Result.GAME_NOT_FINISHED.equals(result)) {
            return checkForExtraMoves(grid, result);
        }

        return result;
    }

    static Result checkIfGameNotFinished(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (" ".equals(grid[i][j]) || "_".equals(grid[i][j])) {
                    return Result.GAME_NOT_FINISHED;
                }
            }
        }

        return Result.DRAW;
    }

    static Result checkForExtraMoves(String[][] grid, Result previousResult) {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ("X".equals(grid[i][j])) {
                    xCount += 1;
                }

                if ("O".equals(grid[i][j])) {
                    oCount += 1;
                }
            }
        }

        if (Math.abs(xCount - oCount) > 1) {
            return Result.IMPOSSIBLE;
        }

        return previousResult;
    }

    static Result checkIfXWins(String[][] grid) {
        String[] xRow = new String[]{"X", "X", "X"};

        String[][] possibleWinRows = {
                {grid[0][0], grid[0][1], grid[0][2]},
                {grid[1][0], grid[1][1], grid[1][2]},
                {grid[2][0], grid[2][1], grid[2][2]},
                {grid[0][0], grid[1][0], grid[2][0]},
                {grid[0][1], grid[1][1], grid[2][1]},
                {grid[0][2], grid[1][2], grid[2][2]},
                {grid[0][0], grid[1][1], grid[2][2]},
                {grid[0][2], grid[1][1], grid[2][0]}
        };

        for (int i = 0; i < possibleWinRows.length; i++) {
            if (Arrays.equals(xRow, possibleWinRows[i])) {
                return Result.X_WINS;
            }
        }

        return Result.DRAW;
    }

    static Result checkIfOWins(String[][] grid) {
        String[] oRow = new String[]{"O", "O", "O"};

        String[][] possibleWinRows = {
                {grid[0][0], grid[0][1], grid[0][2]},
                {grid[1][0], grid[1][1], grid[1][2]},
                {grid[2][0], grid[2][1], grid[2][2]},
                {grid[0][0], grid[1][0], grid[2][0]},
                {grid[0][1], grid[1][1], grid[2][1]},
                {grid[0][2], grid[1][2], grid[2][2]},
                {grid[0][0], grid[1][1], grid[2][2]},
                {grid[0][2], grid[1][1], grid[2][0]}
        };

        for (int i = 0; i < possibleWinRows.length; i++) {
            if (Arrays.equals(oRow, possibleWinRows[i])) {
                return Result.O_WINS;
            }
        }

        return Result.DRAW;
    }

    static String[][] getAndCheckUserInput(String[][] grid) {
        Scanner scanner = new Scanner(System.in);

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ("X".equals(grid[i][j])) {
                    xCount += 1;
                }

                if ("O".equals(grid[i][j])) {
                    oCount += 1;
                }
            }
        }

        System.out.print("Enter the coordinates: ");

        try {
            int i = scanner.nextInt();
            int j = scanner.nextInt();

            if (i < 1 || i > 3 || j < 1 || j > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if (" ".equals(grid[i - 1][j - 1]) || "_".equals(grid[i - 1][j - 1])) {
                    if (xCount <= oCount) {
                        grid[i - 1][j - 1] = "X";
                    } else {
                        grid[i - 1][j - 1] = "O";
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("You should enter numbers!");
        }

        return grid;
    }
}

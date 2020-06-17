import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Setup
        int degrees = Integer.parseInt(input.substring(input.indexOf("(") + 1, input.indexOf(")")));
        int rotationDegrees = degrees % 360;

        //Getting information for matrix
        input = scanner.nextLine();
        List<String> words = new ArrayList<>();
        while (!input.equals("END")) {
            words.add(input);
            input = scanner.nextLine();
        }

        // Initiating an empty matrix
        char[][] matrix = new char[0][];

        //Getting measurements of matrix
        int rows = words.size();
        int cols = findBiggestWord(words);

        //Rotating matrix by degrees
        if (rotationDegrees == 0) {
            matrix = rotateZeroDegrees(rows, cols, words);

        } else if (rotationDegrees == 90) {
            matrix = rotateNinetyDegrees(rows, cols, words);

        } else if (rotationDegrees == 180) {
            matrix = rotateHundredEightyDegrees(rows, cols, words);

        } else if (rotationDegrees == 270) {
            matrix = rotateTwoHundredSeventyDegrees(rows, cols, words);
        }
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char character : chars) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    private static int findBiggestWord(List<String> words) {
        int maxWordLength = -1;
        for (String word : words) {
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
        }
        return maxWordLength;
    }

    // Rotate by zero degrees
    private static char[][] rotateZeroDegrees(int rows, int cols, List<String> words) {
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j >= words.get(i).length()) {
                    matrix[i][j] = ' ';
                } else {
                    matrix[i][j] = words.get(i).charAt(j);
                }
            }
        }
        return matrix;
    }

    // Rotate by 90 degrees
    private static char[][] rotateNinetyDegrees(int rows, int cols, List<String> words) {
        int newCols = rows;
        rows = cols;
        cols = newCols;
        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            int index = 0;
            for (int col = cols - 1; col >= 0; col--) {
                if (row >= words.get(col).length()) {
                    matrix[row][index] = ' ';
                } else {
                    matrix[row][index] = words.get(col).charAt(row);
                }
                index++;
            }

        }

        return matrix;
    }

    // Rotate by 180 degrees
    private static char[][] rotateHundredEightyDegrees(int rows, int cols, List<String> words) {
        char[][] matrix = new char[rows][cols];
        int anotherIndex = 0;
        for (int i = 0; i < rows; i++) {
            int index = 0;
            for (int j = cols - 1; j >= 0; j--) {
                if (index >= words.get(i).length()) {
                    matrix[i][j] = ' ';
                } else {
                    matrix[i][j] = words.get(i).charAt(index);
                }
                index++;
            }
        }
        int count = 0;
        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                char character = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - count][j];
                matrix[matrix.length - 1 - count][j] = character;
            }
            count++;
        }
        return matrix;
    }

    //Rotate by 270 degrees
    private static char[][] rotateTwoHundredSeventyDegrees(int rows, int cols, List<String> words) {
        int newCols = rows;
        rows = cols;
        cols = newCols;
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = 0; col < cols; col++) {
                if (index >= words.get(col).length()) {
                    matrix[row][col] = ' ';
                } else {
                    matrix[row][col] = words.get(col).charAt(index);
                }
            }
            index++;
        }
        return matrix;
    }
}
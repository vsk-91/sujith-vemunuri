import java.util.Random;

public class CandyCrush {
    static final int ROWS = 6;
    static final int COLS = 6;
    static final char[] CANDY_TYPES = {'R', 'G', 'B', 'Y', 'P'}; // R: Red, G: Green, etc.
    static char[][] board = new char[ROWS][COLS];
    static Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        while (crushCandies()) {
            dropCandies();
            refillBoard();
            System.out.println("After crushing:");
            printBoard();
        }
    }

    static void initializeBoard() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                board[i][j] = randomCandy();
    }

    static char randomCandy() {
        return CANDY_TYPES[random.nextInt(CANDY_TYPES.length)];
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean crushCandies() {
        boolean[][] toCrush = new boolean[ROWS][COLS];
        boolean crushed = false;

        // Horizontal matches
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS - 2; j++) {
                char c = board[i][j];
                if (c != ' ' && c == board[i][j + 1] && c == board[i][j + 2]) {
                    toCrush[i][j] = toCrush[i][j + 1] = toCrush[i][j + 2] = true;
                    crushed = true;
                }
            }

        // Vertical matches
        for (int j = 0; j < COLS; j++)
            for (int i = 0; i < ROWS - 2; i++) {
                char c = board[i][j];
                if (c != ' ' && c == board[i + 1][j] && c == board[i + 2][j]) {
                    toCrush[i][j] = toCrush[i + 1][j] = toCrush[i + 2][j] = true;
                    crushed = true;
                }
            }

        // Crush marked candies
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (toCrush[i][j])
                    board[i][j] = ' ';

        return crushed;
    }

    static void dropCandies() {
        for (int j = 0; j < COLS; j++) {
            int writeRow = ROWS - 1;
            for (int i = ROWS - 1; i >= 0; i--) {
                if (board[i][j] != ' ') {
                    board[writeRow--][j] = board[i][j];
                }
            }
            while (writeRow >= 0) {
                board[writeRow--][j] = ' ';
            }
        }
    }

    static void refillBoard() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (board[i][j] == ' ')
                    board[i][j] = randomCandy();
    }
}

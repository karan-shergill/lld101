package snakesandladders_v1.Utils;

public class CommonUtils {
    public static int getPositionViaRowAndCol(int row, int col, int R, int C) {
        int reverseRowIndex = R - 1 - row;
        int pos;
        if ((reverseRowIndex % 2) == 0) {
            // left to right
            pos = reverseRowIndex * C + col + 1;
        } else {
            // right to left
            pos = reverseRowIndex * C + (C - 1 - col) + 1;
        }
        return  pos;
    }

    public static int[] getRowAndColViaPosition(int pos, int R, int C) {
        int row = R - 1 - (pos - 1) / C;
        int col;
        if (((R - 1 - row) % 2) == 0) {
            // Even row from bottom → left to right
            col = (pos - 1) % C;
        } else {
            // Odd row from bottom → right to left
            col = C - 1 - (pos - 1) % C;
        }
        return new int[]{row, col};
    }
}

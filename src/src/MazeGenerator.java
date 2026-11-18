
import java.util.*;
public class MazeGenerator {
    private char[][] maze;
    private Random rand = new Random();
    public char[][] generate(int rows, int cols) {
        if (rows < 5 || cols < 5 ||
                rows % 2 == 0 || cols % 2 == 0) {
            throw new IllegalArgumentException(
                    "Dimensions must be odd and at least 5."
            );
        }
        maze = new char[rows][cols];
        for (char[] row : maze) {
            Arrays.fill(row, '*');
        }
        dfs(1, 1);
        return maze;
    }
    private void dfs(int r, int c) {
        maze[r][c] = ' ';
        int[][] dirs = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
        for (int i = dirs.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int[] temp = dirs[i];
            dirs[i] = dirs[j];
            dirs[j] = temp;
        }
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr > 0 && nr < maze.length - 1 &&
                    nc > 0 && nc < maze[0].length - 1 &&
                    maze[nr][nc] == '*') {

                maze[r + d[0] / 2][c + d[1] / 2] = ' ';
                dfs(nr, nc);
            }
        }
    }
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator();
        char[][] maze = generator.generate(51, 51);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                System.out.print(maze[r][c]);
            }
            System.out.println();
        }
    }
}

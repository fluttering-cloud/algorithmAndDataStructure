public class Solution {

    public boolean exist(char[][] board, String word) {

        char[] chars = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,chars,i,j,0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board,char[] chars, int i, int j, int s) {
        if (s == chars.length) return true;
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0) return false;

        if (board[i][j] == chars[s]) {
            board[i][j] = '\0';
            boolean flag = dfs(board, chars, i, j + 1,s + 1)
                    || dfs(board, chars, i, j - 1,s + 1)
                    || dfs(board, chars, i + 1, j,s + 1)
                    || dfs(board, chars, i - 1, j,s + 1);
            board[i][j] = chars[s];
            return flag;
        }
        return false;
    }


    public int movingCount(int m, int n, int k) {

        boolean[][] visited = new boolean[m][n];
        int count = dfs(m, n, k, visited, 0, 0,0,0);
        return count;
    }

    public int dfs(int m, int n, int k, boolean[][] visited, int i, int j, int s_m, int s_n){
        if (s_n + s_m > k || i >=  m || j >= n || visited[i][j]) return 0;
        visited[i][j] = true;
        int count = dfs(m, n, k, visited,i + 1, j, (i + 1) % 10 == 0 ? s_m - 8: s_m + 1, s_n)
                    + dfs(m, n, k, visited, i, j + 1, s_m, (j + 1) % 10 == 0 ? s_n - 8 : s_n + 1);
        return count + 1;
    }


}

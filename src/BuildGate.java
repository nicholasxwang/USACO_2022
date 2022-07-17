import java.util.*;
import java.io.*;

class DetectWall {
    boolean north;
    boolean west;
    boolean south;
    boolean east;
    public DetectWall(boolean north, boolean south, boolean east, boolean west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
    public void change(boolean north, boolean south, boolean east, boolean west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

}
public class BuildGate {

    public static int start_floodfill(int num, ArrayList<ArrayList<Integer>> indexes) {
        int[][] grid = new int[num*2][num*2];  // the grid itself
        DetectWall[][] grid2 = new DetectWall[num*2][num*2];  // the grid itself
        int rowNum = num*2;
        int colNum = num*2;  // grid dimensions, rows and columns
        boolean[][] visited = new boolean[num*2][num*2];  // keeps track of which nodes have been visited
        for (int i = 0; i<grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                grid2[i][j] = new DetectWall(false, false, false, false);
            }
        }
        for (int i =0; i<indexes.size(); i++) {
            int x = indexes.get(i).get(0);
            int y = indexes.get(i).get(1);
            grid2[x][y + 1].change(true, grid2[x + 1][y].south, grid2[x + 1][y].east, grid2[x + 1][y].west);
            grid2[x][y - 1].change(grid2[x + 1][y].north, true, grid2[x + 1][y].east, grid2[x + 1][y].west);
            grid2[x - 1][y].change(grid2[x + 1][y].north, grid2[x + 1][y].south, true, grid2[x + 1][y].west);
            grid2[x + 1][y].change(grid2[x + 1][y].north, grid2[x + 1][y].south, grid2[x + 1][y].east, true);
        }
        ArrayList<ArrayList<ArrayList<Integer>>> connected = new ArrayList<>(); // Array of Regions of Coordinates of X/Y
        int ans = 0;
        for (int r = 0; r < rowNum; r++) {
            for (int c = 0; c < colNum; c++) {
                if (!visited[r][c]) {
                    ans++;
                    floodfill(r, c, grid2, rowNum, colNum, grid, visited, connected, -1);
                }
            }
        }

        return  ans-1;


    }
    public static void floodfill(int r, int c, DetectWall[][] grid2, int rowNum, int colNum, int[][] grid, boolean[][] visited, ArrayList<ArrayList<ArrayList<Integer>>> component, int component_id) {
        int[] R_CHANGE = {0, 1, 0, -1};
        int[] C_CHANGE = {1, 0, -1, 0};
        Stack<ArrayList<Integer>> frontier = new Stack<>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(r);
        temp.add(c);
        frontier.push(temp);
        while (!frontier.isEmpty()) {
            ArrayList<Integer> curr = frontier.pop();
            int row = curr.get(0);
            int col = curr.get(1);

            if (row < 0 || row >= rowNum || col < 0 || col >= colNum
                    || visited[row][col])
                continue;

            visited[row][col] = true;
            boolean[] dw = {grid2[r][c].north, grid2[r][c].east, grid2[r][c].south, grid2[r][c].west};
            for (int i = 0; i < 4; i++) {
                if (dw[i])
                    continue;
                temp.clear();
                temp.add(row + R_CHANGE[i]);
                temp.add(col + C_CHANGE[i]);
                frontier.add(temp);
            }
            System.out.println(frontier);

        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        int num = Integer.parseInt(br.readLine());
        String s = br.readLine();
        ArrayList<ArrayList<Integer>> went = new ArrayList<>();
        int x = num;
        int y = num;

        ArrayList<Integer> t  = new ArrayList<>();
        t.add(x);
        t.add(x);
        went.add(t);
        for (int i = 0; i<s.toCharArray().length; i++){
            if (s.toCharArray()[i] == 'N'){
                y++;
            }
            if (s.toCharArray()[i] == 'S'){
                y--;
            }
            if (s.toCharArray()[i] == 'E'){
                x++;
            }
            if (s.toCharArray()[i] == 'W'){
                x--;
            }
            t  = new ArrayList<>();
            for (int j = 0; j<went.size(); j++){
                if (went.get(j).get(0) == x && went.get(j).get(1) == y){
                }
            }
            t.add(x);
            t.add(y);
            went.add(t);
        }

        PrintWriter pw = new PrintWriter("gates.out");
        pw.println(start_floodfill(num, went));
        pw.close();

    }

}




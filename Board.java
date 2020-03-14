import java.util.ArrayList;

public class Board {
    public int x, y;
    public int previousX = -1, previousY = -1;
    public int f, g = 0;
    public Board parent = null;
    public char[][] puzzle;
    public Board goal;

    public Board(int i, int j) {
        puzzle = new char[i][j];
    }

    public void setChar(int i, int j, char newChar) {
        puzzle[i][j] = newChar;
        if (newChar == '_') {
            x = i;
            y = j;
        }
    }


    // revoir pour n'import quel autre goal
    public int computeH() {
        int h = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if(puzzle[i][j] == '1') h+=(Math.abs(i-0) + Math.abs(j-0));
                if(puzzle[i][j] == '2') h+=(Math.abs(i-0) + Math.abs(j-1));
                if(puzzle[i][j] == '3') h+=(Math.abs(i-0) + Math.abs(j-2));
                if(puzzle[i][j] == '8') h+=(Math.abs(i-1) + Math.abs(j-0));
                if(puzzle[i][j] == '0') h+=(Math.abs(i-1) + Math.abs(j-1));
                if(puzzle[i][j] == '4') h+=(Math.abs(i-1) + Math.abs(j-2));
                if(puzzle[i][j] == '7') h+=(Math.abs(i-2) + Math.abs(j-0));
                if(puzzle[i][j] == '6') h+=(Math.abs(i-2) + Math.abs(j-1));
                if(puzzle[i][j] == '5') h+=(Math.abs(i-2) + Math.abs(j-2));
            }
        }

        return h;
    }

    public int f() {
        return computeH() + g;
    }


    public ArrayList<Board> getChildren() {
        ArrayList<Board> children = new ArrayList<Board>(0);
        if (x-1 >=0 && (x-1 != previousX || y != previousY)) {
            children.add(this.exchange(x-1, y));
        }

        if (x+1 < 3 && (x+1 != previousX || y != previousY)) {
            children.add(this.exchange(x+1, y));
        }

        if (y-1 >=0 && (x != previousX || y-1 != previousY)) {
            children.add(this.exchange(x, y-1));
        }

        if (y+1 < 3 && (x != previousX || y+1 != previousY)) {
            children.add(this.exchange(x, y+1));
        }

        return children;
    }

    /*public void setParent(Board parent) {
        this.parent = parent;
    }*/

    public Board exchange(int x2, int y2) {
        Board copie = new Board(3,3);
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                copie.puzzle[i][j] = puzzle[i][j];
            }
        }

        copie.puzzle[x][y] = puzzle[x2][y2];
        copie.puzzle[x2][y2] = '_';
        copie.x = x2;
        copie.y = y2;
        copie.previousX = x;
        copie.previousY = y;
        copie.g = g + 1;
        return copie;
    }


    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < puzzle.length; i++) {
            str += puzzle[i][0] + " " + puzzle[i][1] + " " + puzzle[i][2] + "\n";
        }

        return str;
    }

    public boolean isSolvable() {
        int inversions = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                inversions += inversionsCase(puzzle[i][j], 3*i + j);
            }
        }

        return (inversions % 2 == 0) ? true : false;
    }


    /**
     * Check nombre d'inversions d'une case
     */
    public int inversionsCase(char car, int pos) {

        if (car == '_') return 0;

        char[] cases = { goal.puzzle[0][0], goal.puzzle[0][1], goal.puzzle[0][2],
                         goal.puzzle[1][0], goal.puzzle[1][1], goal.puzzle[1][2],
                         goal.puzzle[2][0], goal.puzzle[2][1], goal.puzzle[2][2] };

        int inversions = 0;

        for (int i = pos; i < cases.length; i++) {
            if (cases[i] != '_' && car > cases[i]) inversions++;
        }

        return inversions;
    }
}
public class Puzzle {
    public int x, y;
    public int previousX = -1, previousY = -1;
    public char[][] puzzle;
    public int g = 0, f;
    public Puzzle parent = null;

    public Puzzle(int i, int j) {
        puzzle = new char[i][j];
    }

    public void setChar(int i, int j, char newChar) {
        puzzle[i][j] = newChar;
        if (newChar == '_') {
            x = i;
            y = j;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Puzzle exchange(int x2, int y2) {
        Puzzle copie = new Puzzle(3,3);
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
        copie.previousY = y2;
        copie.g = g + 1;
        return copie;
    }

    public int computeH(char[][] goal) {
        int h = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if(puzzle[i][j] != goal[i][j]) {
                    h++;
                }
            }
        }

        return h;
    }

    public void afficher() {
        for (int i = 0; i < puzzle.length; i++) {
            System.out.println(puzzle[i][0] + " " + puzzle[i][1] + " " + puzzle[i][2]);
        }

    }

    public boolean equals(char[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (this.puzzle[i][j] != puzzle[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void path() {
        if (this.parent.parent != null) {
            this.parent.path();
        }

        this.parent.afficher();
        System.out.println("\n  ||  \n  \\/\n");
    }
}

public class List {
    public Puzzle[] list;

    public List(int i) {
        list = new Puzzle[i];
    }

    public Puzzle depiler() {
        Puzzle[] copie = new Puzzle[list.length - 1];
        for (int i = 0; i < copie.length; i++) {
            copie[i] = list[i];
        }
        Puzzle puzzleDepilee = list[list.length - 1];
        list = copie;
        return puzzleDepilee;
    }

    public void add(Puzzle puzzle) {
        Puzzle[] copie = new Puzzle[list.length + 1];
        for (int i = 0; i < copie.length - 1; i++) {
            copie[i] = list[i];
        }
        copie[list.length] = puzzle;
        list = copie;
    }

    public boolean includes(Puzzle puzzle) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(puzzle.puzzle)) {
                return true;
            }
        }

        return false;
    }

    public void sort() {
        for (int i = list.length - 1; i >=0; i--) {
            for (int j = 1; j <= i; j++) {
                if (list[j-1].f < list[j].f) {
                    var temp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

}

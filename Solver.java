import java.util.*;
import java.util.Scanner;

public class Solver {

    public static int moves = 0;

    public static void reconstitutePath(Board board) {
        if (board.parent == null) {
            System.out.println("----------------");
            System.out.println(board);
        } else {
            moves++;
            reconstitutePath(board.parent);
            System.out.println("\n  ||  \n  \\/\n");
            System.out.println(board);
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter goal:");
        Board goal = new Board(3,3);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                goal.setChar(i, j, scan.next().charAt(0));
            }
        }

        System.out.println("Enter initial setup:");
        Board init = new Board(3,3);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                init.setChar(i, j, scan.next().charAt(0));
            }
        }

        scan.close();

        init.goal = goal;

        if (!init.isSolvable()) {
            System.out.println("ERREUR : PAS DE SOLUTION");
            System.exit(0);
        }

        PriorityQueue<Board> openList = new PriorityQueue<Board>((a,b) -> a.f() - b.f());
        ArrayList<char[][]> closedList = new ArrayList<char[][]> (0);

        openList.add(init);

        while (openList.size() != 0) {
            Board current = openList.poll();

            if (Arrays.deepEquals(current.puzzle, goal.puzzle)) {
                reconstitutePath(current);
                System.out.println("It took " + moves + " moves!");
                return;
            }

            ArrayList<Board> children = current.getChildren();

            for (int i = 0; i < children.size(); i++) {

                Board child = children.get(i);
                child.goal = goal;
                child.parent = current;

                if (!closedList.contains(child.puzzle)) {
                    child.g = current.g + 1;
                    openList.add(child);
                }
            }

            closedList.add(current.puzzle);

        }

    }

}
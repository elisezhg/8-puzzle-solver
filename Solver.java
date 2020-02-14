import java.util.Scanner;
import java.lang.*;

public class Solver {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter initial setup:");
        Puzzle init = new Puzzle(3,3);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                init.setChar(i, j, scan.next().charAt(0));
            }
        }

        System.out.println("Enter goal:");
        Puzzle goal = new Puzzle(3,3);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                goal.setChar(i, j, scan.next().charAt(0));
            }
        }

        List closedList = new List(0);
        List openList = new List(0);
        openList.add(init);

        while (openList.list.length != 0) {
            openList.sort();

            Puzzle current = openList.depiler();

            if (current.equals(goal.puzzle)) {
                current.path();
                goal.afficher();
                System.exit(0);
            }

            int x = current.getX();
            int y = current.getY();
            int prevX = current.previousX;
            int prevY = current.previousY;

            // liste des voisins
            List voisins = new List(0);
            if (x-1 >=0 && (x-1 != prevX || y != prevY)) {
                voisins.add(current.exchange(x-1, y));
            }

            if (x+1 < 3 && (x+1 != prevX || y != prevY)) {
                voisins.add(current.exchange(x+1, y));
            }

            if (y-1 >=0 && (x != prevX || y-1 != prevY)) {
                voisins.add(current.exchange(x, y-1));
            }

            if (y+1 < 3 && (x != prevX || y+1 != prevY)) {
                voisins.add(current.exchange(x, y+1));
            }


            for (int i = 0; i < voisins.list.length; i++) {
                Puzzle voisin = voisins.list[i];
                voisin.parent = current;

                voisin.g = current.g + 1;
                int h = voisin.computeH(goal.puzzle);
                voisin.f = voisin.g + h;

                boolean coutInf = true;

                if (openList.includes(voisin)) {
                    for (int j = 0; j < openList.list.length; j++) {
                        if (openList.list[j].equals(voisin.puzzle)) {
                            if(openList.list[j].f <= voisin.f) {
                                coutInf = false;
                            }
                        }
                    }
                }

                boolean coutInf2 = true;

                if (closedList.includes(voisin)) {
                    for (int j = 0; j < closedList.list.length; j++) {
                        if (closedList.list[j].equals(voisin.puzzle)) {
                            if(closedList.list[j].f <= voisin.f) {
                                coutInf2 = false;
                            }
                        }
                    }
                }
                if (coutInf2 && coutInf) {
                    openList.add(voisin);

                }
            }

            closedList.add(current);
        }

        System.out.println("erreur");
    }
}

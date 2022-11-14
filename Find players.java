import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;
import java.awt.Point;
interface IPlayersFinder {
    java.awt.Point[] findPlayers(String[] play, int certain, int hold);
}
public class PlayersFinder implements IPlayersFinder{
    
    static int row;
    static int col;
    static int count;
    static int maxX;
    static int maxY;
    static int minX;
    static int minY;
    static int center = 0;
    char[][] check = new char[100][100];


    public java.awt.Point[] sort (Point[] arraySorting){
            Point temp;
            for (int i = 0; i < center; i++) {
                for (int j = 1; j < center - i; j++) {
                    if (arraySorting[j - 1].x > arraySorting[j].x) {
                        temp = arraySorting[j - 1];
                        arraySorting[j - 1] = arraySorting[j];
                        arraySorting[j] = temp;
                    }

                    if ((arraySorting[j - 1].x == arraySorting[j].x) && (arraySorting[j - 1].y > arraySorting[j].y)) {
                        temp = arraySorting[j - 1];
                        arraySorting[j - 1] = arraySorting[j];
                        arraySorting[j] = temp;
                    }
                }
            }
            return arraySorting;
    }

    public void search(int certain, int i, int j, String[] play) {
        check[i][j] = '@';
        count++;

        maxX = Math.max(maxX, i);

        maxY = Math.max(maxY, j);

        minX = Math.min(minX, i);

        minY = Math.min(minY, j);


        int[] rowNbr = new int[] { 1, 0, -1, 0};
        int[] colNbr = new int[] { 0, -1, 0, 1};

        for (int k = 0; k < 4; ++k) {
            int newX = i + rowNbr[k];
            int newY = j + colNbr[k];

            boolean notOut = (newX >= 0) && (newX < play.length) && (newY >= 0) && (newY < play[0].length());
            char wanted = (char) ((char)certain + '0');
            if (notOut && (check[newX][newY] == wanted)) {
                search(certain, newX, newY, play);
            }
        }
    }

    public java.awt.Point[] findPlayers(String[] play, int certain, int hold) {
        Point[] arr = new Point[100];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                check[i][j] = play[i].charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = 0;
                if (check[i][j] == (char) (certain + '0')) {
                    maxX = i;
                    maxY = j;
                    minX = i;
                    minY = j;
                    search(certain, i, j, play);

                    if (count >= hold / 4.0) {
                        arr[center] = new Point(minY + maxY + 1, minX + maxX + 1);
                        center++;
                    }
                }



            }
        }
        return arr;
    }

    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty()) {
            arr = new int[]{};
        } else {
            for (int i = 0; i < s.length; i++) {
                arr[i] = Integer.parseInt(s[i]);
            }
        }

        row = arr[0];
        col = arr[1];
        String[] play = new String[row];
        for (int i = 0; i < row; i++) {
            String string = sc.nextLine();
            play[i] = string;
        }

        int certain = sc.nextInt();
        int hold = sc.nextInt();
        Point[] expand = new PlayersFinder().findPlayers(play, certain, hold);

        expand = new PlayersFinder().sort(expand);
        System.out.printf("[");
        for (int i = 0; i < center; i++) {
            System.out.printf("(%d, %d)", (int) expand[i].getX(), (int) expand[i].getY());
            if (i != center - 1) {
                System.out.printf(", ");
            }
        }
        System.out.printf("]");
    }
}

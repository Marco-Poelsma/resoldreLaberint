import java.util.ArrayList;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        char[][] laberint = generadorLaberints(10, 10);
        ArrayList<String> visitats = new ArrayList<>();
        int[] iniciFinal = iniciFinalRandom(laberint);
        int iniciX = iniciFinal[0];
        int iniciY = iniciFinal[1];
        int finalX = iniciFinal[2];
        int finalY = iniciFinal[3];
        laberint[finalX][finalY] = 'X';
        laberint[iniciX][iniciY] = 'I';
        imprimirLaberint(laberint);
        if (resolLaberint(iniciX, iniciY, laberint, visitats)) {
            System.out.println("Camí trobat!");
        } else {
            System.out.println("No hi ha solució...");
        }
    }
    private static void imprimirLaberint(char[][] laberint) { 
        System.out.print("\033[H\033[2J"); // Mostra el laberint actual a la consola
        System.out.flush();// Esborra la pantalla per animació suau
        for (char[] fila : laberint) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        //esperar(100);
        System.out.println();
    }
    // Pausa l'execució per veure la bola movent-se
    private static void esperar(int milisegons) {
        try {
        Thread.sleep(milisegons);
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
    }

    private static boolean esValid(int x, int y, char[][] laberint) {
        return (x >= 0 && y >= 0 && x < laberint.length && y < laberint[0].length && (laberint[x][y] == ' ' || laberint[x][y] == 'X') && laberint[x][y] != '#');
    }

    private static boolean esFinal(int x, int y, char[][] laberint) {
        return laberint[x][y] == 'X';
    }

    public static boolean resolLaberint(int x, int y, char[][] laberint, ArrayList<String> visitats) {
        if (esValid(x, y, laberint)) {
            if (esFinal(x, y, laberint)) {
                return true;
            }
            String pos = x + "," + y;
            if (visitats.contains(pos)) return false;
            visitats.add(pos);
            laberint[x][y] = 'O'; // Mark the cell as part of the path
            imprimirLaberint(laberint);
            if (resolLaberint(x + 1, y, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x, y + 1, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x - 1, y, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x, y - 1, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x + 1, y+1, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x-1, y + 1, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x - 1, y-1, laberint, visitats)) {
                return true;
            }
            if (resolLaberint(x+1, y - 1, laberint, visitats)) {
                return true;
            }
            laberint[x][y] = ' '; // Unmark the cell if it is not part of the solution
            imprimirLaberint(laberint);
        }
        return false;
    }

    public static char[][] generadorLaberints(int files, int columnes) {
        char[][] laberint = new char[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (Math.random() < 0.3) {
                    laberint[i][j] = '#';
                } else {
                    laberint[i][j] = ' ';
                }
            }
        }
        return laberint;
    }

    public static int[] iniciFinalRandom(char[][] laberint) {
        Random rand = new Random();
        int iniciX, iniciY, finalX, finalY;
        do {
            iniciX = rand.nextInt(laberint.length);
            iniciY = rand.nextInt(laberint[0].length);
        } while (laberint[iniciX][iniciY] == '#');
        do {
            finalX = rand.nextInt(laberint.length);
            finalY = rand.nextInt(laberint[0].length);
        } while (laberint[finalX][finalY] == '#' || (finalX == iniciX && finalY == iniciY));
        return new int[]{iniciX, iniciY, finalX, finalY};
    }
}
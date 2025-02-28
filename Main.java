public class Main {
    public static void main(String[] args) {
        char[][] laberint = generadorLaberints(5, 5);
        imprimirLaberint(laberint);
        if (resolLaberint(0, 0, laberint)) {
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
        esperar(100);
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

    public static boolean resolLaberint(int x, int y, char[][] laberint) {
        if (esValid(x, y, laberint)) {
            if (esFinal(x, y, laberint)) {
                laberint[x][y] = 'X';
                return true;
            }
            laberint[x][y] = 'O';
            esperar(150);
            imprimirLaberint(laberint);
            if (laberint[x][y] == 'X') {
                return true;
            }
            if (resolLaberint(x + 1, y, laberint) || resolLaberint(x, y + 1, laberint) || resolLaberint(x - 1, y, laberint) || resolLaberint(x, y - 1, laberint) || resolLaberint(x + 1, y + 1, laberint) || resolLaberint(x - 1, y - 1, laberint) || resolLaberint(x + 1, y - 1, laberint) || resolLaberint(x - 1, y + 1, laberint)) {
                return true;
            }
            
            laberint[x][y] = ' ';
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
        laberint[files - 1][columnes - 1] = 'X';
        laberint[0][0] = ' ';
        return laberint;
    }
}
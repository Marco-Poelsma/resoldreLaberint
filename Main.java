public class Main {

    static char[][] laberint = {{' ', '#', ' ', ' ', ' '}, {' ', '#', ' ', ' ', ' '}, {' ', ' ', ' ', '#', ' '}, {'#', '#', '#', '#', ' '}, {'X', ' ', ' ', ' ', ' '}};
    public static void main(String[] args) {
        imprimirLaberint(laberint);
        resoldreLaberint(0, 0);
    }
    private static void imprimirLaberint(char[][] laberint) { // Mostra el laberint actual a la consola
        System.out.print("\033[H\033[2J"); // Esborra la pantalla per animació suau
        System.out.flush();
        for (char[] fila : laberint) {
        for (char c : fila) {
        System.out.print(c + " ");
        }
        System.out.println();
        }
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
    private static boolean esValid(int x, int y) {
        return x >= 0 && y >= 0 && x < laberint.length && y < laberint[0].length && laberint[x][y] == ' ' && laberint[x][y] != '#';
    }
    public static boolean resoldreLaberint( int x, int y){ {
        
        if (esValid(x,y)){
            if (laberint[x][y] != 'X'){
                laberint[x][y] = '.';
                imprimirLaberint(laberint);
                esperar(150);
                if (resoldreLaberint(x+1,y) || resoldreLaberint(x-1,y) || resoldreLaberint(x,y+1) || resoldreLaberint(x,y-1)){
                    return true;
                }
                laberint[x][y] = ' ';
                imprimirLaberint(laberint);
                esperar(150);
            } else {
                return true;
            }
        }
        return false;
    }
}
}
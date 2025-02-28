public class Main {
    public static void main(String[] args) {
        
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

    private static boolean esValid(int x, int y, char[][] laberint) {
        return x >= 0 && y >= 0 && x < laberint.length && y < laberint[0].length && laberint[x][y] == ' ' && laberint[x][y] != '#';
    }
}
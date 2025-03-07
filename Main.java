import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[][] laberint = generadorLaberints(10, 10);
        int[] iniciFinal = iniciFinalRandom(laberint);
        int iniciX = iniciFinal[0];
        int iniciY = iniciFinal[1];
        int finalX = iniciFinal[2];
        int finalY = iniciFinal[3];
        laberint[finalX][finalY] = 'X';
        laberint[iniciX][iniciY] = 'I';
        imprimirLaberint(laberint);
        if (resolLaberint(iniciX, iniciY, laberint)) {
            System.out.println("Camí trobat!");
        } else {
            System.out.println("No hi ha solució...");
        }
        imprimirLaberint(laberint); // Print the final maze with the shortest path
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
        return (x >= 0 && y >= 0 && x < laberint.length && y < laberint[0].length && (laberint[x][y] == ' ' || laberint[x][y] == 'X' || laberint[x][y] == 'I') && laberint[x][y] != '#');
    }

    private static boolean esFinal(int x, int y, char[][] laberint) {
        return laberint[x][y] == 'X';
    }

    public static boolean resolLaberint(int startX, int startY, char[][] laberint) {
        int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        queue.add(new int[]{startX, startY});
        boolean[][] visited = new boolean[laberint.length][laberint[0].length];
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (esFinal(x, y, laberint)) {
                // Backtrack to mark the shortest path
                String currentPos = x + "," + y;
                while (currentPos != null) {
                    String[] parts = currentPos.split(",");
                    int cx = Integer.parseInt(parts[0]);
                    int cy = Integer.parseInt(parts[1]);
                    if (laberint[cx][cy] != 'X' && laberint[cx][cy] != 'I') {
                        laberint[cx][cy] = 'O';
                    }
                    currentPos = parentMap.get(currentPos);
                }
                // Clear all other marks
                clearMarks(laberint, parentMap);
                return true;
            }

            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (esValid(newX, newY, laberint) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                    parentMap.put(newX + "," + newY, x + "," + y);
                    if (laberint[newX][newY] != 'X') {
                        laberint[newX][newY] = '·';
                    }
                }
            }
            imprimirLaberint(laberint); // Print the maze at each iteration
        }
        return false;
    }

    private static void clearMarks(char[][] laberint, Map<String, String> parentMap) {
        for (int i = 0; i < laberint.length; i++) {
            for (int j = 0; j < laberint[i].length; j++) {
                if ((laberint[i][j] == 'O' && !parentMap.containsKey(i + "," + j)) || laberint[i][j] == '·') {
                    laberint[i][j] = '·';
                }
            }
        }
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
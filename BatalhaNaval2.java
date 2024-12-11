import java.util.Scanner;

public class BatalhaNaval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criar jogadores
        System.out.println("Informe o nome do Jogador 1:");
        Jogador jogador1 = new Jogador(scanner.nextLine());
        System.out.println();
        System.out.println("Informe o nome do Jogador 2:");
        Jogador jogador2 = new Jogador(scanner.nextLine());
        
        System.out.println();
        System.out.println("S = Submarino");
        System.out.println("C = Cruzador");
        System.out.println("P = Porta aviao");
        // Posicionar armas
        System.out.println("\n" + jogador1.getNome() + ", posicione suas armas:");
        posicionarArmas(scanner, jogador1);

        System.out.println("\n" + jogador2.getNome() + ", posicione suas armas:");
        posicionarArmas(scanner, jogador2);

        // Rodadas de tiros
        boolean jogoAtivo = true;
        while (jogoAtivo) {

            System.out.println("\n" + jogador1.getNome() + ", é sua vez!");
            jogoAtivo = realizarTiros(scanner, jogador1, jogador2);

            if (!jogoAtivo) break;

            System.out.println("\n" + jogador2.getNome() + ", é sua vez!");
            jogoAtivo = realizarTiros(scanner, jogador2, jogador1);
        }

        scanner.close();
    }

    private static void posicionarArmas(Scanner scanner, Jogador jogador) {
        char[] tipos = {'S', 'S', 'S', 'C', 'C', 'P'};
        int[] tamanhos = {1, 1, 1, 2, 2, 5};
        for (int i = 0; i < tipos.length; i++) {
            char tipo = tipos[i];
            int tamanho = tamanhos[i];
            boolean posicionado = false;
            while (!posicionado) {
                System.out.println("Posicione o " + tipo + " (linha coluna ex: 1 1):");
                int linha = scanner.nextInt();
                int coluna = scanner.nextInt();
                posicionado = jogador.posicionarArma(linha, coluna, tipo, tamanho);
                if (!posicionado) {
                    System.out.println("Posição inválida ou espaço ocupado. Tente novamente.");
                }
            }
            jogador.mostrarTabuleiro(true);
        }
    }

    private static boolean realizarTiros(Scanner scanner, Jogador atirador, Jogador alvo) {
        for (int i = 0; i < 2; i++) { 
            System.out.println("Informe a posição do tiro (linha coluna ex: 1 1):");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            boolean acertou = alvo.verificarSeAcertou(linha, coluna);
            atirador.registrarTiro(linha, coluna, acertou);

            System.out.println(acertou ? "Acertou!" : "Errou!");
            atirador.mostrarTabuleiro(false);
        }

        return !verificarFimDoJogo(alvo);
    }

    private static boolean verificarFimDoJogo(Jogador jogador) {
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                if (jogador.getMeuJogo()[i][c] == 'S' || jogador.getMeuJogo()[i][c] == 'C' || jogador.getMeuJogo()[i][c] == 'P') {
                    return false; 
                }
            }
        }
        System.out.println("Todas as armas de " + jogador.getNome() + " foram destruídas!");
        return true;
    }
}

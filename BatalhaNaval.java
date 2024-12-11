import java.util.Scanner;

public class BatalhaNaval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação dos jogadores
        System.out.println("Informe o nome do Jogador 1:");
        Jogador jogador1 = new Jogador(scanner.nextLine());

        System.out.println();
        
        System.out.println("Informe o nome do Jogador 2:");
        Jogador jogador2 = new Jogador(scanner.nextLine());

        // Posicionamento das armas
        System.out.println("\n" + jogador1.getNome() + ", posicione suas armas:");
        posicionarArmas(scanner, jogador1);

        System.out.println("\n" + jogador2.getNome() + ", posicione suas armas:");
        posicionarArmas(scanner, jogador2);

        // Rodadas de tiros
        boolean jogoAtivo = true;
        while (jogoAtivo) {
            System.out.println("\n" + jogador1.getNome() + ", é a sua vez de atirar.");
            realizarTiros(scanner, jogador1, jogador2);

            if (verificarFimDoJogo(jogador2)) {
                System.out.println("Parabéns " + jogador1.getNome() + "! Você venceu!");
                break;
            }

            System.out.println("\n" + jogador2.getNome() + ", é a sua vez de atirar.");
            realizarTiros(scanner, jogador2, jogador1);

            if (verificarFimDoJogo(jogador1)) {
                System.out.println("Parabéns " + jogador2.getNome() + "! Você venceu!");
                break;
            }
        }

        scanner.close();
    }

    private static void posicionarArmas(Scanner scanner, Jogador jogador) {
        // Posicionar submarinos
        for (int i = 1; i <= 3; i++) {
            System.out.println("Submarino " + i + " (ex.: 0 0):");
            int[] posicao = lerPosicao(scanner);
            while (!jogador.posicionarArma(posicao[0], posicao[1], 'S', 1)) {
                System.out.println("Erro: Posição inválida ou espaço ocupado. Tente novamente.");
                posicao = lerPosicao(scanner);
            }
            jogador.imprimirMeuJogo();
        }

        // Posicionar cruzadores
        for (int i = 1; i <= 2; i++) {
            System.out.println("Cruzador " + i + " (ex.: 0 0):");
            int[] posicao = lerPosicao(scanner);
            while (!jogador.posicionarArma(posicao[0], posicao[1], 'C', 2)) {
                System.out.println("Erro: Posição inválida ou espaço ocupado. Tente novamente.");
                posicao = lerPosicao(scanner);
            }
            jogador.imprimirMeuJogo();
        }

        // Posicionar porta-aviões
        System.out.println("Porta-Aviões (ex.: 0 0):");
        int[] posicao = lerPosicao(scanner);
        while (!jogador.posicionarArma(posicao[0], posicao[1], 'P', 5)) {
            System.out.println("Erro: Posição inválida ou espaço ocupado. Tente novamente.");
            posicao = lerPosicao(scanner);
        }
        jogador.imprimirMeuJogo();
    }

    private static void realizarTiros(Scanner scanner, Jogador atirador, Jogador alvo) {
        for (int i = 1; i <= 2; i++) {
            System.out.println("Informe a posição do tiro " + i + " (ex.: 0 0):");
            int[] posicao = lerPosicao(scanner);

            // Verificar se o tiro já foi disparado
            while (atirador.getJogoDoAdversario()[posicao[0]][posicao[1]] != '~') {
                System.out.println("Você já atirou nessa posição. Escolha outra.");
                posicao = lerPosicao(scanner);
            }

            boolean acertou = alvo.verificarSeAcertou(posicao[0], posicao[1]);
            atirador.registrarTiro(posicao[0], posicao[1], acertou);
            System.out.println(acertou ? "Acertou!" : "Errou!");
            atirador.imprimirJogoDoAdversario();
        }
    }

    private static boolean verificarFimDoJogo(Jogador jogador) {
        char[][] tabuleiro = jogador.getMeuJogo();
        for (char[] linha : tabuleiro) {
            for (char celula : linha) {
                if (celula == 'S' || celula == 'C' || celula == 'P') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] lerPosicao(Scanner scanner) {
        int linha = -1, coluna = -1;
        do {
            if (scanner.hasNextInt()) {
                linha = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    coluna = scanner.nextInt();
                    if (linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8) {
                        return new int[]{linha, coluna};
                    }
                }
            }
            scanner.nextLine(); 
            System.out.println("Erro: Entrada inválida. Tente novamente.");
        } while (true);
    }
}

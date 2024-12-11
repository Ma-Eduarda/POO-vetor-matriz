import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Quantas apostas você quer fazer?");
        int quantidadeApostas = teclado.nextInt();
        SorteGrande sorteGrande = new SorteGrande(quantidadeApostas);

        for (int i = 0; i < quantidadeApostas; i++) {
            System.out.printf("Quantos números para a %dª aposta?", i + 1);
            int quantidadeNumero = teclado.nextInt();
            int[] aposta = new int[quantidadeNumero];
            for (int j = 0; j < quantidadeNumero; j ++) {
                System.out.printf("Digite o %dº número %dª aposta ", j + 1,  i + 1);
                aposta[j] = teclado.nextInt();
            }
            sorteGrande.adicionarAposta(aposta);
        }
        teclado.close();

        sorteGrande.imprimirApostas();
    }
}
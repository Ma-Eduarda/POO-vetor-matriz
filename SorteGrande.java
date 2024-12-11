public class SorteGrande {
    private int[][] apostas;
    private int indice = 0;

    public SorteGrande(int quantidadeApostas){
        this.apostas = new int[quantidadeApostas][];
    }

    public void adicionarAposta(int[] aposta){
        if (indice < apostas.length) {
            apostas[indice] = aposta; 
            indice++;
        }
    }

    public void imprimirApostas(){
        for(int linha = 0; linha < apostas.length; linha++){
            for (int coluna = 0; coluna < apostas[linha].length; coluna++) {
                System.out.printf("%d ", apostas[linha][coluna]);
            }
            System.out.println();
        }
    }

}

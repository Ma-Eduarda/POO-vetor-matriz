public class Jogador {
    private String nome;
    private char[][] meuJogo = new char[8][8];
    private char[][] jogoDoAdversario = new char[8][8];

    public Jogador(String nome) {
        this.nome = nome;
        inicializarTabuleiros();
    }

    private void inicializarTabuleiros() {
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                meuJogo[i][c] = '~';
                jogoDoAdversario[i][c] = '~';
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char[][] getMeuJogo() {
        return meuJogo;
    }

    public void setMeuJogo(char[][] meuJogo) {
        this.meuJogo = meuJogo;
    }

    public char[][] getJogoDoAdversario() {
        return jogoDoAdversario;
    }

    public void setJogoDoAdversario(char[][] jogoDoAdversario) {
        this.jogoDoAdversario = jogoDoAdversario;
    }

    public boolean posicionarArma(int linha, int coluna, char tipo, int tamanho) {
        if (linha < 1 || linha > 8 || coluna < 1 || coluna + tamanho > 8) {
            return false; 
        }
        for (int i = 0; i < tamanho; i++) {
            if (meuJogo[linha - 1][coluna - 1 + i] != '~') {
                return false; 
            }
        }
        for (int i = 0; i < tamanho; i++) {
            meuJogo[linha - 1][coluna - 1 + i] = tipo;
        }
        return true;
    }

    public boolean verificarSeAcertou(int linha, int coluna) {
        if (meuJogo[linha - 1][coluna - 1] != '~' && meuJogo[linha - 1][coluna - 1] != 'X' && meuJogo[linha - 1][coluna - 1] != 'O') {
            meuJogo[linha - 1][coluna - 1] = 'O'; 
            return true;
            
        }
        meuJogo[linha - 1][coluna - 1] = 'X'; 
        return false;
    }

    public void registrarTiro(int linha, int coluna, boolean acertou) {
        jogoDoAdversario[linha - 1][coluna - 1] = acertou ? 'O' : 'X';
    }

    public void mostrarTabuleiro(boolean mostrarArmas) {
        System.out.println("Tabuleiro de " + nome + (mostrarArmas ? " (com armas):" : " (do adversário):"));

        System.out.print("  ");
        for (int i = 1; i <= 8; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        
        char[][] tabuleiro = mostrarArmas ? meuJogo : jogoDoAdversario;
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " "); 
            for (int c = 0; c < 8; c++) {
                System.out.print(tabuleiro[i][c] + " ");
            }
            System.out.println();
        }
    }

    public String toString() {
        return "Jogador: " + nome;
    }
}

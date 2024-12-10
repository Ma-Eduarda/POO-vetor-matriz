class Jogador {
    private String nome;
    private char[][] meuJogo;
    private char[][] jogoDoAdversario;

    public Jogador (String nome) {
        this.nome = nome;
        meuJogo = new char[8][8];
        jogoDoAdversario = new char[8][8];
        inicializarTabuleiros();
    }

    private void inicializarTabuleiros() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                meuJogo[i][j] = '~';
                jogoDoAdversario[i][j] = '~';
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
        if (coluna + tamanho > 8 || linha < 0 || linha >= 8) {
            return false; // Fora dos limites
        }
        for (int i = 0; i < tamanho; i++) {
            if (meuJogo[linha][coluna + i] != '~') {
                return false; // Espaço ocupado
            }
        }
        for (int i = 0; i < tamanho; i++) {
            meuJogo[linha][coluna + i] = tipo;
        }
        return true;
    }

    public boolean verificarSeAcertou(int linha, int coluna) {
        if (meuJogo[linha][coluna] == 'S' || meuJogo[linha][coluna] == 'C' || meuJogo[linha][coluna] == 'P') {
            meuJogo[linha][coluna] = 'O'; // Marca como atingido
            return true;
        }
        return false;
    }

    public void registrarTiro(int linha, int coluna, boolean acertou) {
        jogoDoAdversario[linha][coluna] = acertou ? 'O' : 'X'; // Marca o tiro no tabuleiro
    }

    public void imprimirMeuJogo() {
        System.out.println("Tabuleiro do jogador " + nome + ":");
        imprimirTabuleiro(meuJogo);
    }

    public void imprimirJogoDoAdversario() {
        System.out.println("Tiros no adversário:");
        imprimirTabuleiro(jogoDoAdversario);
    }

    private void imprimirTabuleiro(char[][] tabuleiro) {
        System.out.print("  ");
        for (int j = 0; j < 8; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Jogador: " + nome;
    }
}

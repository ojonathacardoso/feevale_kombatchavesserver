
package prog;

import java.util.Random;

/** 
 * Classe pai dos dois tipos de jogos, em dupla ou individual.
 * @author Jonatha Cardoso
 */
public class Jogo
{
    
    private Jogador jogador1;
    private Jogador jogador2;
    
    private int codigo;
    private boolean encerrado;
    
    private int altura;
    private int largura;
    
    private final int velocidade = 10;
    private final int ataque = 10;
    
    /**
     * Construtor da classe, que obtém os jogadores e as opções do jogo.
     * @param j1 Jogador - Instância do jogador 1
     * @param j2 Jogador - Instância do jogador 2
     * @param opcoes String[] - Array de opções necessárias para o jogo
     */
    public Jogo(Jogador j1, Jogador j2, String[] opcoes)
    {
        this.jogador1 = j1;
        this.jogador2 = j2;
        
        this.jogador1.setJogo(this);
        this.jogador2.setJogo(this);
        
        this.setAltura(Integer.parseInt(opcoes[3])-30);
        this.setLargura(Integer.parseInt(opcoes[4])-10);
        
        this.jogador1.setAltura(Integer.parseInt(opcoes[5]));
        this.jogador2.setAltura(Integer.parseInt(opcoes[5]));        
        this.jogador1.setLargura(Integer.parseInt(opcoes[6]));
        this.jogador2.setLargura(Integer.parseInt(opcoes[6]));
        
        this.jogador1.posicionar();
        this.jogador2.posicionar();
        
        this.jogador1.setPontos(0);
        this.jogador2.setPontos(0);
        
        this.jogador1.setLado(1);
        this.jogador2.setLado(2);
        
        this.setCodigo(new Random().nextInt(10000)+1);
        
        System.out.println("#"+this.getCodigo()+": NOVO JOGO");
    }
    
    /**
     * Verifica se os jogadores estão prontos e autorizados para iniciarem
     * @return boolean
     */
    public boolean isJogoAutorizado()
    {
        return (this.getJogador1().isAutorizado() && this.getJogador2().isAutorizado());
    }
    
    /**
     * Recebe a jogada e a instância do jogador que a fez
     * @param jogada String - Código da Tecla pressionada pelo jogadort
     * @param jogador Jogador - Instância do jogador que fez a jogada
     */
    public void processarJogada(String jogada, Jogador jogador)
    {
        int ataque1 = 0;
        int ataque2 = 0;
        
        if(jogada.equals("DIR")){
            this.moverDireita(jogador);
        }

        if(jogada.equals("ESQ")){
            this.moverEsquerda(jogador);
        }

        if(jogada.equals("BAI")){
            this.moverBaixo(jogador);
        }

        if(jogada.equals("CIM")){
            this.moverCima(jogador);
        }
        
        if(jogada.equals("ATA"))
        {
            this.atacar(jogador);
            
            if(jogador.getOpcao() == 1)
            {
                ataque1 = 1;
                ataque2 = 0;
            }
            else
            {
                ataque1 = 0;
                ataque2 = 1;
            }
        }
        
        /*
        [0] Personagem 1
        [1] X 1
        [2] Y 1
        [3] Lado 1
        [4] Ataque 1
        [5] Pontos 1
        [6] Personagem 2
        [7] X 2
        [8] Y 2
        [9] Lado 2
        [10] Ataque 2
        [11] Pontos 2
        */       
        StringBuilder sb = new StringBuilder();
        sb.append(this.getJogador1().getPersonagem());
        sb.append(";");
        sb.append(this.getJogador1().getX());
        sb.append(";");
        sb.append(this.getJogador1().getY());
        sb.append(";");
        sb.append(this.getJogador1().getLado());
        sb.append(";");
        sb.append(ataque1);
        sb.append(";");
        sb.append(this.getJogador1().getPontos());
        sb.append(";");
        sb.append(this.getJogador2().getPersonagem());
        sb.append(";");
        sb.append(this.getJogador2().getX());
        sb.append(";");
        sb.append(this.getJogador2().getY());
        sb.append(";");
        sb.append(this.getJogador2().getLado());
        sb.append(";");
        sb.append(ataque2);
        sb.append(";");
        sb.append(this.getJogador2().getPontos());
        
        System.out.println("#" + this.getCodigo() + ": @" + jogador.getCodigo() + " " + jogada);
        this.enviarMensagem(sb.toString());
    }
    
    /**
     * Move um jogador para a direita na tela cliente.
     * @param jogador Jogador - Instância do jogador que deve ser movido
     */
    public void moverDireita(Jogador jogador)
    {        
        if( (jogador.getX() + jogador.getLargura() + this.velocidade ) > this.largura) {
            jogador.setX( jogador.getX() );
        }
        else {
            jogador.setX( jogador.getX() + this.velocidade );
        }

        jogador.setLado(1);
    }
    
    /**
     * Move um jogador para a esquerda na tela cliente.
     * @param jogador Jogador - Instância do jogador que deve ser movido
     */
    public void moverEsquerda(Jogador jogador)
    {        
        if( (jogador.getX() - this.velocidade) < 0) {
            jogador.setX( 0 );
        }
        else {
            jogador.setX( jogador.getX() - this.velocidade );
        }

        jogador.setLado(2);
    }
    
    /**
     * Move um jogador para baixo na tela cliente.
     * @param jogador Jogador - Instância do jogador que deve ser movido
     */
    public void moverBaixo(Jogador jogador)
    {        
        if( (jogador.getY() + jogador.getAltura() + this.velocidade) > this.altura) {
            jogador.setY( jogador.getY() );
        }
        else {
            jogador.setY( jogador.getY() + this.velocidade );
        }
    }
    
    /**
     * Move um jogador para cima na tela cliente.
     * @param jogador Jogador - Instância do jogador que deve ser movido
     */
    public void moverCima(Jogador jogador)
    {
        if( (jogador.getY() - this.velocidade) < 0) {
            jogador.setY( 0 );
        }
        else {
            jogador.setY( jogador.getY() - this.velocidade );
        }
    }
    
    /**
     * Efetua o ataque do jogador ao seu adversário
     * @param jogador Jogador - Instância do jogador que deve ser movido
     */
    public void atacar(Jogador jogador)
    {
        Jogador atacado;
        if(jogador.getOpcao() == 1) {
            atacado = this.getJogador2();
        }
        else {
            atacado = this.getJogador1();
        }

        int distanciaX;
        if( jogador.getX() > atacado.getX()  )
        {
            distanciaX = jogador.getX() - ( atacado.getX() + atacado.getLargura() );
        }
        else
        {
            distanciaX = atacado.getX() - ( jogador.getX() + jogador.getLargura() );
        }

        int distanciaY;
        if( jogador.getY() > atacado.getY()  )
        {
            distanciaY = jogador.getY() - ( atacado.getY() + atacado.getAltura());
        }
        else
        {
            distanciaY = atacado.getY() - ( jogador.getY() + jogador.getAltura() );
        }
        
        if ( (distanciaX < 10) && (distanciaY < 10) )
        {
            jogador.setPontos(jogador.getPontos() + this.ataque);
        }
    }
    
    /**
     * Para um jogo
     */
    public void pararJogo()
    {
        getJogador1().setAtivo(false);
        getJogador2().setAtivo(false);
        
        if(! this.encerrado)
        {
            System.out.println("#"+this.getCodigo()+": JOGO ENCERRADO");
            this.encerrado = true;
        }
    }
    
    /**
     * Envia mensagem para os jogadores do jogo
     * @param mensagem String - Mensagem enviada
     */
    public void enviarMensagem(String mensagem)
    {
        // Implementado nas classes que herdam
    }
    
    /**
     * Obtém o código do jogo
     * @return int
     */
    public int getCodigo()
    {
        return codigo;
    }

    /**
     * Define o código do jogo
     * @param codigo int - Código
     */
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Obtém a altura da tela
     * @return int
     */
    public int getAltura()
    {
        return altura;
    }
    
    /**
     * Define a altura da tela
     * @param altura int - Altura em px
     */
    public void setAltura(int altura)
    {
        this.altura = altura;
    }

    /**
     * Obtém a largura da tela
     * @return int
     */
    public int getLargura()
    {
        return largura;
    }
    
    /**
     * Define a largura da tela
     * @param largura int - Largura em px
     */
    public void setLargura(int largura)
    {
        this.largura = largura;
    }

    /**
     * Obtém a instância do jogador 1
     * @return Jogador
     */
    public Jogador getJogador1() {
        return jogador1;
    }

    /**
     * Define a instância do jogador 1
     * @param jogador1 Jogador - Jogador 1
     */
    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    /**
     * Obtém a instância do jogador 2
     * @return Jogador
     */
    public Jogador getJogador2() {
        return jogador2;
    }

    /**
     * Define a instância do jogador 1
     * @param jogador2 Jogador - Jogador 1
     */    
    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }
    
}
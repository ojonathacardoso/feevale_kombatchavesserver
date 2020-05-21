package prog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/** 
 * Classe pai dos dois tipos de jogadores, o real ou robô.
 * @author Jonatha Cardoso
 */
public class Jogador implements Runnable {
    
    private boolean autorizado;
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    private Jogo jogo;
    
    private int codigo;
    private int personagem;
    private int opcao;
    private boolean ativo;
    
    private int x, y;
    private int largura;
    private int altura;
    private int lado;
    
    private int pontos;
    
    /**
     * Construtor da classe, que obtém o socket, o código do personagem e a opção.
     * @param s Socket - Socket do jogador conectado
     * @param p int - Código do jogador
     * @param o int - Opção, sendo 1 para o jogador 1 ou 2 para o jogador 2
     */
    public Jogador(Socket s, int p, int o)
    {
        try {
            this.socket   = s;
            this.in       = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
            this.out      = new PrintWriter(this.getSocket().getOutputStream(), true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        this.personagem = p;
        this.opcao = o;
        this.setCodigo(new Random().nextInt(10000)+1);
    }

    /**
     * Posiciona o jogador antes de iniciar o jogo,
     * sendo que se for o jogador 1 fica à esquerda e se for o jogador 2 fica à direita
     */
    public void posicionar()
    {
        if(this.getOpcao() == 1) {
            this.setX(0);
        }
        else {
            this.setX( this.getJogo().getLargura() - this.getLargura() ); 
        }

        this.setY(0);
    }
    
    /**
     * Executa o jogo
     */
    public void run()
    {
        // Implementado nas classes que herdam
    }
    
    /**
     * Envia mensagem para o jogador
     * @param mensagem String - Mensagem que deve ser enviada
     */
    public void enviarMensagem(String mensagem)
    {
        // Implementado nas classes que herdam
    }    
    
    /**
     * Retorna se o jogador está ativo
     * @return boolean
     */
    public boolean isAtivo()
    {
        return this.ativo;
    }

    /**
     * Define se o jogador está ativo ou não. 
     * @param ativo boolean - Ativo ou não
     */
    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
    
    /**
     * Retorna o código do jogador, usado no jogo
     * @return int
     */
    public int getCodigo()
    {
        return this.codigo;
    }

    /**
     * Retorna o código do jogador
     * @param codigo int - Código
     */
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
    
    /**
     * Retorna o número do personagem
     * @return int
     */
    public int getPersonagem()
    {
        return this.personagem;
    }

    /**
     * Obté o número do personagem
     * @param personagem int - Número do personagem
     */
    public void setPersonagem(int personagem)
    {
        this.personagem = personagem;
    }
    
    /**
     * Retorna a instância da classe Jogo a qual pertence o jogador
     * @return Jogo
     * @see Jogo
     */
    public Jogo getJogo()
    {
        return this.jogo;
    }

    /**
     * Retorna a instância da classe Jogo a qual pertence o jogador
     * @param jogo Jogo - Instância do jogo
     * @see Jogo
     */
    public void setJogo(Jogo jogo)
    {
        this.jogo = jogo;
    }
    
    /**
     * Retorna se o jogador está autorizado para jogar
     * @return boolean
     */
    public boolean isAutorizado()
    {
        return this.autorizado;
    }
    
    /**
     * Autoriza ou não um jogador
     * @param autorizado boolean - Autorização 
     */
    public void setAutorizado(boolean autorizado)
    {
        this.autorizado = autorizado;
    }

    /**
     * Retorna a altura do jogador
     * @return int
     */
    public int getAltura()
    {
        return altura;
    }
    
    /**
     * Define a altura do jogador
     * @param altura int - Altura em px
     */
    public void setAltura(int altura)
    {
        this.altura = altura;
    }

    /**
     * Retorna a largura do jogador
     * @return int
     */
    public int getLargura()
    {
        return largura;
    }
    
    /**
     * Define a largura do jogador
     * @param largura int - Largura em px
     */
    public void setLargura(int largura)
    {
        this.largura = largura;
    }

    /**
     * Retorna a posição horizontal do jogador
     * @return int
     */
    public int getX()
    {
        return x;
    }

    /**
     * Define a posição horizontal do jogador
     * @param x int - Posição
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Retorna a posição vertical do jogador
     * @return int
     */
    public int getY() 
    {
        return y;
    }

    /**
     * Define a posição vertical do jogador
     * @param y int - Posição
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Retorna o lado do jogador (direita ou esquerda)
     * @return int
     */
    public int getLado()
    {
        return lado;
    }

    /**
     * Define o lado do jogador
     * @param lado int - Lado, sendo 1 para direita ou 2 para esquerda
     */
    public void setLado(int lado)
    {
        this.lado = lado;
    }

    /**
     * Retorna a opção do jogador (1 ou 2)
     * @return int
     */
    public int getOpcao()
    {
        return opcao;
    }

    /**
     * Define a opção do jogador
     * @param opcao int - Opção, sendo 1 ou 2
     */
    public void setOpcao(int opcao)
    {
        this.opcao = opcao;
    }

    /**
     * Retorna a pontuação do jogador
     * @return int
     */
    public int getPontos()
    {
        return pontos;
    }

    /**
     * Define a pontuação do jogador
     * @param pontos int - Pontos
     */
    public void setPontos(int pontos)
    {
        this.pontos = pontos;
    }

    /**
     * Retorna o socket do jogador (cliente).
     * @return Socket
     */
    public Socket getSocket()
    {
        return this.socket;
    }

    /** 
     * Obtém a entrada de mensagens do cliente.
     * @return BufferedReader
     */
    public BufferedReader getIn()
    {
        return this.in;
    }

    /** 
     * Obtém a saída de mensagens para o cliente.
     * @return PrintWriter
     */
    public PrintWriter getOut()
    {
        return out;
    }
    
}
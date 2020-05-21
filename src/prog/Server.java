package prog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * Recebe as conexões dos novos jogadores e direciona conforme as opções.
 * @author Jonatha Cardoso
 */
public class Server
{
    
    private ServerSocket server;    
    private Socket socket;
    
    private JogadorReal jogAguardando;
    
    /** 
     * Construtor da classe, que inicia o servidor.
     */
    public static void main(String[] args)
    {
        new Server();
    }
    
    /** 
     * Construtor da classe, que permanece aguardando e direcionando as conexões.
     */
    public Server()
    {
        while(true)
        {
            aguardarConexao();
            direcionarJogador();
        }
    }
    
    /**
     * Cria o servidor na porta 8800 e ativa o recebimento de conexões Socket.
     */    
    public void aguardarConexao() 
    {
        try {
            if (this.server == null) {
                this.server = new ServerSocket(8800);
            }
            this.socket = this.server.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Aguarda a mensagem do cliente para direcionar conforme a opção de 1 ou 2 jogadores.
     */ 
    public void direcionarJogador()
    {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String mensagem = in.readLine();
            String[] opcoes = mensagem.split(";");

            if(opcoes[0].equals("1")) {
                adversarioSimples(opcoes);
            }
            else if(opcoes[0].equals("2")) {
                adversarioDupla(opcoes);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Quando há apenas um jogador, ele cria uma instância de JogadorReal e outra de JogadorRobo,
     * além de criar a instância do JogoSimples e iniciar as Threads
     * @param op String[] - Mensagem recebida do cliente, após ser explodida em um array.
     * @see JogadorReal
     * @see JogadorRobo
     * @see JogoSimples
     */ 
    public void adversarioSimples(String[] op)
    {
        JogadorReal jog1 = new JogadorReal(this.socket, Integer.parseInt(op[1]), 1);
        JogadorRobo jog2 = new JogadorRobo(this.socket, Integer.parseInt(op[2]));
        
        JogoSimples jogo = new JogoSimples(jog1, jog2, op);

        Thread threadJog1 = new Thread(jog1);
        Thread threadJog2 = new Thread(jog2);
        
        threadJog1.start();
        threadJog2.start();
    }
    
    /**
     * Quando há dois jogadores, ele verifica se há um jogador aguardando e se ele está ativo.
     * Se não há ou não está, ele cria um novo JogadorReal e o deixa aguardando.
     * Se há, ele cria outra instância de JogadorReal, além de criar a instância do JogoDupla e iniciar as Threads
     * @param op String[] - Mensagem recebida do cliente, após ser explodida em um array.
     * @see JogadorReal
     * @see JogoDupla
     */ 
    public void adversarioDupla(String[] op)
    {
        if(this.jogAguardando == null)
        {
            this.jogAguardando = new JogadorReal(this.socket, Integer.parseInt(op[1]), 1);
            Thread threadJog1 = new Thread(this.jogAguardando);
            threadJog1.start();
        }
        else
        {
            if(this.jogAguardando.isAtivo())
            {
                JogadorReal jogPronto = new JogadorReal(this.socket, Integer.parseInt(op[1]), 2);
                JogoDupla jogo = new JogoDupla(this.jogAguardando, jogPronto, op);
                
                Thread threadJogo = new Thread(jogo);
                threadJogo.start();
                
                this.jogAguardando = null;
            }
            else
            {
                this.jogAguardando = new JogadorReal(this.socket, Integer.parseInt(op[1]), 1);
                
                Thread threadJog1 = new Thread(this.jogAguardando);
                threadJog1.start();
            }
        }
    }

}
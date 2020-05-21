package prog;

import java.net.Socket;

/** 
 * Jogador real, que possui conexão ao servidor, recebe e envia mensagens
 * @author Jonatha Cardoso
 */
public class JogadorReal extends Jogador {  
    
    /**
     * Construtor da classe, que obtém o socket, o código do personagem e a opção.
     * @param s Socket - Socket do jogador conectado
     * @param p int - Código do jogador
     * @param ordem int - Opção, sendo 1 para o jogador 1 ou 2 para o jogador 2
     */
    public JogadorReal(Socket s, int p, int ordem)
    {
        super(s, p, ordem);
        this.setAtivo(true);
        System.out.println("@"+this.getCodigo()+": NOVO JOGADOR");      
    }
    
    /**
     * Executa o jogo enquanto estiver ativo, recebendo e direcionando os comandos recebidos do jogador cliente.
     */
    public void run()
    {
        try {
            while(this.isAtivo())
            {
                String mensagem = this.getIn().readLine();
                
                if(mensagem.equals("FIM"))
                {
                    System.out.println("@"+this.getCodigo()+": JOGADOR DESCONECTADO");
                    
                    this.setAtivo(false);
                    
                    if(this.getJogo() != null)
                    {
                        this.getJogo().enviarMensagem("FIM");
                        this.getJogo().pararJogo();
                    }
                    
                    Thread.sleep(30);
                    
                    this.getSocket().close();
                }
                else if(mensagem.equals("OK"))
                {
                    this.setAutorizado(true);
                    
                    if(this.getJogo().isJogoAutorizado())
                    {
                        this.getJogo().enviarMensagem("OK");
                    }   
                }
                else
                {
                    this.getJogo().processarJogada(mensagem, this);
                }
            }            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Envia uma mensagem ao jogador.
     * @param mensagem String - Mensagem a ser enviada.
     */
    public void enviarMensagem(String mensagem)
    {
        this.getOut().println(mensagem);
    }
    
}
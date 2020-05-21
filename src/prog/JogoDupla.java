
package prog;

/** 
 * Jogo em dupla, entre dois jogadores reais
 * @author Jonatha Cardoso
 */
public class JogoDupla extends Jogo implements Runnable
{
    
    /**
     * Construtor da classe, que obtém os jogadores e as opções do jogo.
     * @param j1 Jogador - Instância do jogador real 1
     * @param j2 Jogador - Instância do jogador real 2
     * @param opcoes String[] - Array de opções necessárias para o jogo
     */
    public JogoDupla(JogadorReal j1, JogadorReal j2, String[] opcoes)
    {
        super(j1, j2, opcoes);
    }
    
    /**
     * Testa se os personagens são diferentes, iniciando o jogo após a confirmação
     */
    public void run()
    {
        if (this.getJogador1().getPersonagem() == this.getJogador2().getPersonagem())
        {
            int personagem = -1;
            do{
                try{
                    this.getJogador2().enviarMensagem("-1");
                    String mensagem = this.getJogador2().getIn().readLine();
                    String[] opcao = mensagem.split(";");
                    personagem = Integer.parseInt(opcao[1]);
                } catch(Exception e) {
                    e.printStackTrace();
                }      
            } while(personagem == getJogador1().getPersonagem());

            getJogador2().setPersonagem(personagem); 
        }
        
        Thread threadJog2 = new Thread(getJogador2());
        threadJog2.start();

        this.getJogador1().enviarMensagem( Integer.toString( getJogador2().getPersonagem() ) );
        this.getJogador2().enviarMensagem( Integer.toString( getJogador1().getPersonagem() ) );
    }
    
    /**
     * Envia mensagem para os jogadores do jogo
     * @param mensagem String - Mensagem enviada
     */
    public void enviarMensagem(String mensagem)
    {
        this.getJogador1().enviarMensagem(mensagem);
        this.getJogador2().enviarMensagem(mensagem);
    }

}

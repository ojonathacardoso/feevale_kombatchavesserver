
package prog;

/** 
 * Jogo individual, entre um jogador real e um jogador robô
 * @author Jonatha Cardoso
 */
public class JogoSimples extends Jogo
{

    /**
     * Construtor da classe, que obtém os jogadores e as opções do jogo.
     * @param j1 Jogador - Instância do jogador real
     * @param j2 Jogador - Instância do jogador robô
     * @param opcoes String[] - Array de opções necessárias para o jogo
     */
    public JogoSimples(JogadorReal j1, JogadorRobo j2, String[] opcoes)
    {
        super(j1, j2, opcoes); 
    }
    
    /**
     * Envia mensagem para o jogador real do jogo
     * @param mensagem String - Mensagem enviada
     */
    public void enviarMensagem(String mensagem)
    {
        this.getJogador1().enviarMensagem(mensagem);
    }
    
}

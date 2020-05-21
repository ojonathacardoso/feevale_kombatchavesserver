package prog;

import java.net.Socket;
import java.util.Random;

/** 
 * Jogador fictício, que sorteia jogadas aleatórias durante o jogo.
 * @author Jonatha Cardoso
 */
public class JogadorRobo extends Jogador {
    
    /**
     * Construtor da classe, que obtém o socket, o código do personagem e a opção.
     * @param s Socket - Socket do jogador conectado
     * @param p int - Código do jogador
     * Não há parâmetro de opção, pois o robô sempre será a opção 2.
     */
    public JogadorRobo(Socket s, int p)
    {
        super(s, p, 2);
        this.setAtivo(true);
        System.out.println("@"+this.getCodigo()+": NOVO ROBÔ");
    }
    
    /**
     * Enquanto o jogo estiver ativo, pede pra sortear uma jogada a cada 0,2 segundos.
     */
    public void run()
    {
        while(this.isAtivo())
        {
            try {
                this.sortearJogada();
                Thread.sleep(200);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }  
    }
    
    /**
     * Sorteia uma jogada, conforme um número de 1 a 7 sorteado.
     */
    public void sortearJogada()
    {
        int sorteio = new Random().nextInt(7)+1;
        String jogada = "";
        
        switch(sorteio)
        {
            case 1:
                jogada = "DIR";
                break;
            case 2:
                jogada = "ESQ";
                break;
            case 3:
                jogada = "CIM";
                break;
            case 4:
                jogada = "BAI";
                break;
            case 5:
                jogada = "ATA";
                break;
            case 6:
                jogada = "DIR";
                break;
            case 7:
                jogada = "ESQ";
                break;
        }

        this.getJogo().processarJogada(jogada, this);

    }
    
}
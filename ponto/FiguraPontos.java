package ponto;
import java.awt.Color;
import java.awt.Graphics;
import permanencia.Armazem;
/**
 * Contem metodos para desenhar figuras com ponto
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class FiguraPontos {

    /**
     * Desenha um ponto na tela
     * @param g biblioteca grafica para desenhar elementos gr�ficos
     * @param x coordena x do ponto
     * @param y coordenada y do ponto
     * @param nome nome do ponto
     * @param diametro diametro do ponto
     * @param cor cor do ponto
     */
    public static void desenharPonto(Graphics g, int x, int y, String nome, int diametro, Color cor, Armazem gaps){
        // Color cor = new Color((int) (Math.random() * 256),  
        // (int) (Math.random() * 256),  
        // (int) (Math.random() * 256));
        //System.out.println("222 Desenhando ponto em (" + x + "," + y + ") diam=" + diametro);
        PontoGr p = new PontoGr(x, y, cor, nome, diametro);
        gaps.adicionar(p);
        p.desenharPonto(g);
    }
    public static void desenharPonto(Graphics g, int x, int y, String nome, int diametro, Color cor){
        //System.out.println("Desenhando ponto em (" + x + "," + y + ") diam=" + diametro);
        PontoGr p = new PontoGr(x, y, cor, nome, diametro);
        p.desenharPonto(g);

 
    }
    /**
     * Desenha variso pontos na tela com cores diferentes
     * @param g biblioteca grafica para desenhar elementos gr�ficos
     * @param qtde quantidade de pontos
     * @param diametro diametro do pontos
     */
    public static void desenharPontos(Graphics g, int qtde, int diametro){

        for(int i=0; i < qtde; i++) {
            int x = (int) (Math.random() * 801);
            int y = (int) (Math.random() * 801);

            // R, G e B aleatorio
            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            PontoGr p = new PontoGr(x, y, cor, diametro);
            p.desenharPonto(g);
        }
    }
}

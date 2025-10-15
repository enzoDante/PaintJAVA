package circulo;
import java.awt.Color;
import java.awt.Graphics;
import permanencia.Armazem;
import ponto.Ponto;

/**
 * Desenhar figuras com circulo.
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class FiguraCirculos {
    

    /**
     * Desenha circulo utilizando equacoes parametricas
     * 
     * @param g Biblioteca gr�fica
     * @param xc coordenada x do centro
     * @param yc coordenada y do centro
     * @param raio raio do circulo
     * @param nome nome do circulo
     * @param esp espessura da borda
     * @param cor cor do circulo
    */
    public static void desenharCirculo(Graphics g, int xc, int yc, int raio, String nome, int esp, Color cor, Armazem galp){
        CirculoGr c = new CirculoGr(xc, yc, raio, cor, nome, esp);
        galp.adicionar(c);
        c.desenharCirculo(g);
    }
    public static void desenharCirculo(Graphics g, int xc, int yc, Ponto raio, String nome, int esp, Color cor, Armazem galp){
        
        CirculoGr c = new CirculoGr(xc, yc, (int)raio.getX(), (int)raio.getY(), cor, nome, esp);
        galp.adicionar(c);
        c.desenharCirculo(g);
    }
    public static void desenharCirculo(Graphics g, int xc, int yc, Ponto raio, String nome, int esp, Color cor){
        CirculoGr c = new CirculoGr(xc, yc, (int)raio.getX(), (int)raio.getY(), cor, nome, esp);
        c.desenharCirculo(g);
    }
    public static void desenharCirculo(Graphics g, int xc, int yc, int raio, String nome, int esp, Color cor){
        CirculoGr c = new CirculoGr(xc, yc, raio, cor, nome, esp);
        c.desenharCirculo(g);
    }
    

     
    /**
     * Desenha varios circulos
     * 
     * @param g Biblioteca gr�fica
     * @param qtde quantidade de circulos
     * @param esp espessura da borda do circulo
     */
    public static void desenharCirculos(Graphics g, int qtde, int esp){

        for(int i=0; i < qtde; i++) {
            int xc = (int) (Math.random() * 801);
            int yc = (int) (Math.random() * 801);
            int raio = (int) (Math.random() * 801);

            // R, G e B aleatorio
            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            CirculoGr c = new CirculoGr(xc, yc, raio, cor, "", esp);
            c.desenharCirculo(g);
        }
    }
}

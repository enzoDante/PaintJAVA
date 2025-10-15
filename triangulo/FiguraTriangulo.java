package triangulo;

import java.awt.*;
import permanencia.Armazem;
/**
 * Desenha o triângulo
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class FiguraTriangulo
{

    /**
     * Construtor para objetos da classe FiguraTriangulo
     */
    public static void desenharTriangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3,  Color cor, String nome, int esp){
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, cor, nome, esp);
        r.desenharTriangulo(g);
    }

    public static void desenharTriangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, Color cor, String nome, int esp, Armazem gaps){
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, cor, nome, esp);
        gaps.adicionar(r);
        r.desenharTriangulo(g);
    }
    

    
}

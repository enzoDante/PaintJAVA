package Retangulo;

import java.awt.Color;
import java.awt.Graphics;
import permanencia.Armazem;

/**
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */

public class FiguraRetangulo {
    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2,  Color cor, String nome, int esp){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
        r.desenharRetangulo(g);
    }

    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2,  Color cor, String nome, int esp, Armazem gaps){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
        gaps.adicionar(r);
        r.desenharRetangulo(g);
    }
}

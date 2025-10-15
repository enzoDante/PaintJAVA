package triangulo;

import ponto.Ponto;

/**
 * Estrutura do triângulo
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class Triangulo
{
    
    private Ponto p1, p2, p3;
    /**
     * Construtor para objetos da classe Triangulo
     */

    public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    


    public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.setP1(new Ponto(x1, y1));
        this.setP2(new Ponto(x2, y2));
        this.setP3(new Ponto(x3, y3));
    }

    public Ponto getP1() {
        return p1;
    }
    public void setP1(Ponto p1) {
        this.p1 = p1;
    }
    public Ponto getP2() {
        return p2;
    }
    public void setP2(Ponto p2) {
        this.p2 = p2;
    }
    public Ponto getP3() {
        return p3;
    }
    public void setP3(Ponto p3) {
        this.p3 = p3;
    }
    

    public String toString(){
        return "Triangulo: [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
    }

}

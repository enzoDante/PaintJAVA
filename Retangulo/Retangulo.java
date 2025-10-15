package Retangulo;
import ponto.Ponto;

/**
 * Escreva uma descri��o da classe Retangulo aqui.
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class Retangulo
{
    private Ponto p1, p2, p3, p4;
    
    public Retangulo(int x1, int y1, int x2, int y2){
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        calcularP3P4();
    }

    public Retangulo(Ponto p1, Ponto p2){
        setP1(p1);
        setP2(p2);
        calcularP3P4();
    }

    public Retangulo(Retangulo r){
        setP1(r.getP1());
        setP2(r.getP2());
        calcularP3P4();

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

    public Ponto getP4() {
        return p4;
    }
    
    public void calcularP3P4(){
        p3 = new Ponto(p2.getX(), p1.getY());
        p4 = new Ponto(p1.getX(), p2.getY());
    }

    @Override
    public String toString() {
        return "Retangulo: [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + "]";
    }
}

package circulo;
import ponto.Ponto;

/**
 * Teste de circulos.
 *
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class TestaCirculo {
    /**
     * @param args parametros externos
     */
    public static void main(String args[]) {
        Ponto p = new Ponto(23.5, 45.7);
        Circulo c = new Circulo(10, 10, 20);
        Circulo c1 = new Circulo(c);
        Circulo c2 = new Circulo(p, 22);
        System.out.println(c);
        System.out.println(c1);
        System.out.println(c2);
    }
}
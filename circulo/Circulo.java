package circulo;
import ponto.Ponto;

/**
 * Implementa circulo matematico.
 * 
 * @author Julio Arakaki
 * @version 24/08/2021
 */
public class Circulo {
    // centro do circulo
    private Ponto centro;
    private double raio; // raio do circulo
    private Ponto raioA;

    /**
     * Contrutor da classe circulo
     * @param centro Ponto centro do circulo
     * @param raio double raio do circulo
     */
    public Circulo(Ponto centro, double raio) {
        setCentro(centro);
        setRaio(raio);
    }

    /**
     * Contrutor da classe circulo
     * @param x double coordenada x do centro do circulo
     * @param y double coordenada y do centro do circulo
     * @param raio double raio do circulo
     */
    public Circulo(double x, double y, double raio) {
        setCentro(new Ponto(x, y));
        setRaio(raio);
    }
    public Circulo(double x, double y, double xraio, double yraio) {
        setCentro(new Ponto(x, y));
        // raioA = new Ponto(xraio, yraio);
        setRaioA(new Ponto(xraio, yraio));
        double distancia = Math.sqrt(((Math.pow(x-xraio, 2)-Math.pow(y-yraio, 2))));
        setRaio(distancia);
    }

    /**
     * Contrutor da classe circulo. Cria uma copia
     * @param c Circulo circulo a ser copiado
     */
    public Circulo(Circulo c) {
        setCentro(new Ponto(c.getCentro()));
        setRaio(c.getRaio());
    }
    public double calcularDistancia(Ponto centro, Ponto raio){
        double distancia = Math.sqrt(((Math.pow(centro.getX()-raio.getX(), 2)-Math.pow(centro.getY()-raio.getY(), 2))));
        return distancia;
    }
    /**
     * @return the centro
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    /**
     * @return the raio
     */
    public double getRaio() {
        return raio;
    }
    /**
     * @return the raio
     */
    public Ponto getRaioA() {
        return raioA;
    }
    /**
     * @param raio the raio to set
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }
    public void setRaioA(Ponto praio){
        this.raioA = praio;
    }

    /**
     * Method toString
     *
     * @return The return value
     */
    public String toString(){
        String s = "Circulo: \n Centro: " + getCentro().toString() + " Raio: " + getRaio();
        return s;
    }
}

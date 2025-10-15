package circulo;
import java.awt.Color;
import java.awt.Graphics;

import org.json.JSONObject;

import estatico.DimensionView;
import permanencia.IElemento;
import ponto.Ponto;
import ponto.PontoGr;

/**
 * Implementa circulo grafico.
 * 
 * @author Julio Arakaki
 * @version 11/09/2021
 */

public class CirculoGr extends Circulo implements IElemento {
    // Atributos do circulo grafico
    Color corCirculo = Color.BLACK;   // cor da Circulo
    String nomeCirculo = ""; // nome da Circulo
    Color corNomeCirculo  = Color.BLACK;
    int espCirculo = 1; // espessura da Circulo

    /**
     * @param x coordenada x do centro
     * @param y coordenada y do centro
     * @param raio raio do circulo
     */
    public CirculoGr(double x, double y, double raio) {
        super(x, y, raio);
        // cor, nome e espessura sao defaults
    }

    /**
     * @param xc coordenada x do centro
     * @param yc coordenada y do centro
     * @param raio raio do circulo
     * @param cor Cor do circulo
     * @param nome Nome do circulo
     * @param esp espessura da borda do circulo
     */
    public CirculoGr(int xc, int yc, int raio, Color cor, String nome, int esp) {
        super((double) xc, (double) yc, (double) raio);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
    }
    /**
     * @param xc coordenada x do centro
     * @param yc coordenada y do centro
     * @param raio raio do circulo
     * @param cor Cor do circulo
     * @param nome Nome do circulo
     * @param esp espessura da borda do circulo
     */
    public CirculoGr(int xc, int yc, int xraio, int yraio, Color cor, String nome, int esp) {
        super((double) xc, (double) yc, (double) xraio, (double) yraio);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
    }
    /**
     * @return the corCirculo
     */
    public Color getCorCirculo() {
        return corCirculo;
    }

    /**
     * @param corCirculo the corCirculo to set
     */
    public void setCorCirculo(Color corCirculo) {
        this.corCirculo = corCirculo;
    }

    /**
     * @return the nomeCirculo
     */
    public String getNomeCirculo() {
        return nomeCirculo;
    }

    /**
     * @param nomeCirculo the nomeCirculo to set
     */
    public void setNomeCirculo(String nomeCirculo) {
        this.nomeCirculo = nomeCirculo;
    }

    /**
     * @return the corNomeCirculo
     */
    public Color getCorNomeCirculo() {
        return corNomeCirculo;
    }

    /**
     * @param corNomeCirculo the corNomeCirculo to set
     */
    public void setCorNomeCirculo(Color corNomeCirculo) {
        this.corNomeCirculo = corNomeCirculo;
    }

    /**
     * @return the espCirculo
     */
    public int getEspCirculo() {
        return espCirculo;
    }

    /**
     * @param espCirculo the espCirculo to set
     */
    public void setEspCirculo(int espCirculo) {
        this.espCirculo = espCirculo;
    }

 
    /**
     * Desenha circulo grafico utilizando equacao parametrica (angulo de 0 a 360)
     *
     * @param g Graphics. Classe com os metodos graficos do Java
     */
    public void desenharCirculo(Graphics g){
        // Variaveis auxiliares
        PontoGr ponto = new PontoGr(); 
        double x, y;

        double angIni = 0;
        double angFim = 45;
        double incr = 0.1;
        double alfa = 0;

        // percorre de 0  ate 45. 
        // x e� calculado pela equacao: x = xc + R*seno(alfa)
        // y e� calculado pela equacao: y = yc + R*cos(alfa)
        for(alfa = angIni; alfa <= angFim; alfa = alfa + incr){ 
            // Calculo de x e y (por trigonometria)
            x = getRaio() * Math.sin((alfa*Math.PI)/180.);
            y = getRaio() * Math.cos((alfa*Math.PI)/180.);

            // desenhar 8 pontos (um em cada octante) por simetria
            desenharPontosSimetricos(g, (int)x, (int)y, ponto);
        }
    }

    /**
     * Desenha os pontos simetricos do circulo. Um em cada octante
     * @param g - componente para acessar modo gr�fico
     * @param x - coordenada x de um ponto do primeiro octante do circulo
     * @param y - coordenada y de um ponto do primeiro octante do circulo
     * @param ponto - objeto utilizado para "acender" (desenhar) um ponto
     */
    private void desenharPontosSimetricos(Graphics g, int x, int y, PontoGr ponto){
        // pega o centro do circulo
        int cx = (int)getCentro().getX();
        int cy = (int)getCentro().getY();

        // seta cor e espessura
        ponto.setCorPto(getCorCirculo());
        ponto.setDiametro(getEspCirculo());

        // desenha nome do circulo
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), cx, cy);

        // desenha os 8 pontos simetricos. Inclui o centro do circulo
        // (1) (cx+x, cy+y)
        desenharPontoSimetrico(g, cx + x, cy + y, ponto);
        // (2) (cx+y, cy+x)
        desenharPontoSimetrico(g, cx + y, cy + x, ponto);
        // (3) (cx-y, cy+x)
        desenharPontoSimetrico(g, cx - y, cy + x, ponto);
        // (4) (cx-x, cy+y)
        desenharPontoSimetrico(g, cx - x, cy + y, ponto);
        // (5) (cx-x, cy-y)
        desenharPontoSimetrico(g, cx - x, cy - y, ponto);
        // (6) (cx-y, cy-x)
        desenharPontoSimetrico(g, cx - y, cy - x, ponto);
        // (7) (cx+y, cy-x)
        desenharPontoSimetrico(g, cx + y, cy - x, ponto);
        // (8) (cx+x, cy-y)
        desenharPontoSimetrico(g, cx + x, cy - y, ponto);
    }


    /**
     * M�todo desenharPontoSimetrico
     *
     * @param x coordenada x
     * @param y coordenda y
     * @param ponto Ponto utilizado para desenhar o ponto
     * @param g Biblioteca grafica
     */
    private void desenharPontoSimetrico(Graphics g, int x, int y, PontoGr ponto){
        ponto.setX(x);
        ponto.setY(y);
        ponto.desenharPonto(g);
    }

    public void desenho(Graphics g){
        desenharCirculo(g);
    }
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("centro", this.pontoParaJSON(this.getCentro()));
        obj.put("raio", this.pontoParaJSON(this.getRaioA()));
        obj.put("id", this.getNomeCirculo());
        obj.put("esp", this.getEspCirculo());

        JSONObject cor = new JSONObject();
        cor.put("r", this.getCorCirculo().getRed());
        cor.put("g", this.getCorCirculo().getGreen());
        cor.put("b", this.getCorCirculo().getBlue());
        obj.put("cor", cor);
        
        return obj;
    }

    private JSONObject pontoParaJSON(Ponto p) {
        JSONObject ponto = new JSONObject();
        ponto.put("x", ((double) p.getX() / DimensionView.LARGURA));
        ponto.put("y", ((double) p.getY() / DimensionView.ALTURA));
        return ponto;
    }

    @Override
    public boolean contem (int x, int y){
        int dx = x - (int)getCentro().getX();
        int dy = y - (int)getCentro().getY();
        double distancia = Math.sqrt(dx*dx + dy*dy);
        return Math.abs(distancia - getRaio()) <= 5;
    }

    public void mover(int dx, int dy){
        getCentro().setX(getCentro().getX() + dx);
        getCentro().setY(getCentro().getY() + dy);
    }
    public void escalar(double fator){
        double vx = getRaioA().getX() - getCentro().getX();
        double vy = getRaioA().getY() - getCentro().getY();

        setRaioA( new Ponto(
            Math.round(getCentro().getX()+vx*fator),
            Math.round(getCentro().getY() + vy * fator) 
        ));

        double distancia = Math.sqrt(Math.pow(getCentro().getX()-getRaioA().getX(), 2) - Math.pow(getCentro().getY()-getRaioA().getY(), 2));
        setRaio(distancia);
    }
}

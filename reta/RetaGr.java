package reta;
import java.awt.Color;
import java.awt.Graphics;

import org.json.JSONObject;

import estatico.DimensionView;
import permanencia.IElemento;
import ponto.Ponto;
import ponto.PontoGr;
/**
 * Implementacao da classe reta grafica.
 *
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class RetaGr extends Reta implements IElemento{
    // Atributos da reta grafica
    Color corReta = Color.BLACK;   // cor da reta
    String nomeReta = ""; // nome da reta
    Color corNomeReta  = Color.BLACK;
    int espReta = 1; // espessura da reta

    // Construtores
    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor da reta
     * @param nome String. Nome da reta
     * @param esp int. Espessura da reta
     */
    public RetaGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta(nome);
        setEspReta(esp);
    }    

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor da reta
     */
    public RetaGr(int x1, int y1, int x2, int y2, Color cor){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta("");
    }   

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor da reta
     * @param esp int. Espessura da reta
     */
    public RetaGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta("");
        setEspReta(esp);
    }   

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     */
    public RetaGr(int x1, int y1, int x2, int y2){
        super (x1, y1, x2, y2);
        setCorReta(Color.black);
        setNomeReta("");
    }   

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     */
    public RetaGr(PontoGr p1, PontoGr p2){
        super(p1, p2);
        setCorReta(Color.black);
        setNomeReta("");
    }    

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     * @param cor Color. Cor da reta
     */
    public RetaGr(PontoGr p1, PontoGr p2, Color cor){
        super(p1, p2);
        setCorReta(cor);
        setNomeReta("");
    }    

    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     * @param cor Color. Cor da reta
     * @param nome String. Nome da reta
     */
    public RetaGr(PontoGr p1, PontoGr p2, Color cor, String str){
        super(p1, p2);
        setCorReta(cor);
        setNomeReta(str);
    }    

    /**
     * Altera a cor da reta.
     *
     * @param cor Color. Cor da reta.
     */
    public void setCorReta(Color cor) {
        this.corReta = cor;
    }

    /**
     * Altera o nome da reta.
     *
     * @param str String. Nome da reta.
     */
    public void setNomeReta(String str) {
        this.nomeReta = str;
    }

    /**
     * Altera a espessura da reta.
     *
     * @param esp int. Espessura da reta.
     */
    public void setEspReta(int esp) {
        this.espReta = esp;
    }

    /**
     * Retorna a espessura da reta.
     *
     * @return int. Espessura da reta.
     */
    public int getEspReta() {
        return(this.espReta);
    }

    /**
     * Retorna a cor da reta.
     *
     * @return Color. Cor da reta.
     */
    public Color getCorReta() {
        return corReta;
    }

    /**
     * Retorna o nome da reta.
     *
     * @return String. Nome da reta.
     */
    public String getNomeReta() {
        return nomeReta;
    }

    /**
     * @return the corNomeReta
     */
    public Color getCorNomeReta() {
        return corNomeReta;
    }

    /**
     * @param corNomeReta the corNomeReta to set
     */
    public void setCorNomeReta(Color corNomeReta) {
        this.corNomeReta = corNomeReta;
    }

    /**
     * Desenha reta grafica utilizando a equacao da reta: y = mx + b
     *
     * @param g Graphics. Classe com os metodos graficos do Java
     */
    public void desenharReta(Graphics g){
        // calcula m e b da equacao da reta y = mx + b
        double m = calcularM();
        double b = calcularB();

        // Variaveis auxiliares
        PontoGr ponto; 
        double x, y;

        double cIni; // Coordenada de inicio
        double cFim; // Coordenada de fim 

        // desenha nome da Reta
        g.setColor(getCorNomeReta());
        g.drawString(getNomeReta(), (int)getP1().getX() + getEspReta(), (int)getP1().getY());

        if(p1.getX() == p2.getX()) { // reta vertical
            if (p1.getY() < p2.getY()){ // Caso 1: y1 < y2
                cIni = p1.getY();
                cFim = p2.getY();
            } else { // Caso 2: y1 > y2
                cIni = p2.getY();
                cFim = p1.getY();            
            }
            // percorre de y1 ate y2. 
            // x e� calculado pela equacao: x = (y - b) / m
            for(y = cIni; y <= cFim; ++y){ // percorre de y1 ate y2
                // x1 e x2 s�o iguais
                x = p1.getX(); // ou x = p2.getX()

                // Define ponto grafico
                ponto = new PontoGr((int)x, (int)y, getCorReta(), getEspReta());

                // Desenha ponto grafico
                ponto.desenharPonto(g);
            }
        } else { // Outras retas (diferentes de vertical)
            double deltaX = Math.abs(p1.getX() - p2.getX());
            double deltaY = Math.abs(p1.getY() - p2.getY());

            if (deltaX > deltaY){ // percorre pelo x
                if (p1.getX() < p2.getX()){ // Caso 1: x1 < x2
                    cIni = p1.getX();
                    cFim = p2.getX();
                } else { // Caso 2: x1 > x2
                    cIni = p2.getX();
                    cFim = p1.getX();            
                }

                // percorre de x1 ate x2. 
                // y e� calculado pela equacao: y = mx + b
                for(x = cIni; x <= cFim; ++x){ 
                    // Calculo de y pela equacao da reta
                    y = (m*x + b);

                    // Define ponto grafico
                    ponto = new PontoGr((int)x, (int)y, getCorReta(), getEspReta());

                    // Desenha ponto grafico
                    ponto.desenharPonto(g);
                }
            } else { //deltaY > deltaX, percorre pelo y
                if (p1.getY() < p2.getY()){ // Caso 1: y1 < y2
                    cIni = p1.getY();
                    cFim = p2.getY();
                } else { // Caso 2: y1 > y2
                    cIni = p2.getY();
                    cFim = p1.getY();            
                }
                // percorre de y1 ate y2. 
                // x e� calculado pela equacao: x = (y - b) / m
                for(y = cIni; y <= cFim; ++y){ // percorre de y1 ate y2
                    // Calculo de x pela equacao da reta
                    x = ((y - b)/m);

                    // Define ponto grafico
                    ponto = new PontoGr((int)x, (int)y, getCorReta(), getEspReta());

                    // Desenha ponto grafico
                    ponto.desenharPonto(g);
                }
            }
        }
    }
    public void desenho(Graphics g){
        desenharReta(g);
        //FiguraPontos.desenharPonto(g, (int)getX(), (int)getY(), "", getDiametro(), getCorPto());
    }
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("p1", pontoParaJSON(this.getP1()));
        obj.put("p2", pontoParaJSON(this.getP2()));
        obj.put("id", this.getNomeReta());
        obj.put("esp", this.getEspReta());
        JSONObject cor = new JSONObject();
        cor.put("r", this.getCorReta().getRed());
        cor.put("g", this.getCorReta().getGreen());
        cor.put("b", this.getCorReta().getBlue());
        obj.put("cor", cor);
        

        return obj;
    }

    private JSONObject pontoParaJSON(Ponto p) {
        JSONObject ponto = new JSONObject();
        ponto.put("x", (p.getX() / DimensionView.LARGURA));
        ponto.put("y", (p.getY() / DimensionView.ALTURA));
        return ponto;
    }
    @Override
    public boolean contem(int x, int y){
        int tolerancia = 5;
        double distancia = distanciaPontoparaReta(
            x, y, (int)getP1().getX(), (int)getP1().getY(), (int)getP2().getX(), (int)getP2().getY()
            );
        return distancia <= tolerancia;
    }
    private double distanciaPontoparaReta(int px, int py, int x1, int y1, int x2, int y2){
        double A = px - x1;
        double B = py - y1;
        double C = x2 - x1;
        double D = y2 - y1;

        double dot = A * C + B * D;
        double len_sq = C * C + D * D;
        double param = -1;
        if(len_sq != 0) param = dot / len_sq;

        double xx, yy;
        if(param < 0){
            xx = x1;
            yy = y1;
        }else if(param > 1){
            xx = x2;
            yy = y2;
        }else{
            xx = x1 + param * C;
            yy = y1 + param * D;
        }

        double dx = px - xx;
        double dy = py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void mover (int dx, int dy){
        p1.setX(p1.getX() + dx);
        p1.setY(p1.getY() + dy);
        p2.setX(p2.getX() + dx);
        p2.setY(p2.getY() + dy);
    }

    public void rotacionarReta(double graus){
        double rad = Math.toRadians(graus);
        double cx = (getP1().getX() + getP2().getX()) / 2;
        double cy = (getP1().getY() + getP2().getY()) / 2;

        int[] p1r = rotacionarPonto((int)getP1().getX(), (int) getP1().getY(), cx, cy, rad);
        int[] p2r = rotacionarPonto((int)getP2().getX(), (int) getP2().getY(), cx, cy, rad);
        getP1().setX(p1r[0]); getP1().setY(p1r[1]);
        getP2().setX(p2r[0]); getP2().setY(p2r[1]);
    }
    private int[] rotacionarPonto (int x, int y, double cx, double cy, double rad){
        double xr = cx + (x - cx) * Math.cos(rad) - (y - cy) * Math.sin(rad);
        double yr = cy + (x - cx) * Math.sin(rad) + (y - cy) * Math.cos(rad);
        return new int[] { (int)Math.round(xr), (int)Math.round(yr) };
    }
    public void escalar(double fator){
        double cx = (getP1().getX() + getP2().getX()) / 2.0;
        double cy = (getP1().getY() + getP2().getY()) / 2.0;

        int[] p1s = escalarPonto((int)getP1().getX(), (int) getP1().getY(), cx, cy, fator);
        int[] p2s = escalarPonto((int) getP2().getX(), (int) getP2().getY(), cx, cy, fator);
        getP1().setX(p1s[0]); getP1().setY(p1s[1]);
        getP2().setX(p2s[0]); getP2().setY(p2s[1]);

    }
    private int[] escalarPonto(int x, int y, double cx, double cy, double fator){
        double novoX = cx + (x-cx) * fator;
        double novoY = cy + (y-cy) * fator;
        return new int[] {(int)Math.round(novoX), (int)Math.round(novoY)};
    }
}


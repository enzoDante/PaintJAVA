package triangulo;

import java.awt.Color;
import java.awt.Graphics;

import org.json.JSONObject;

import estatico.DimensionView;
import permanencia.IElemento;
import ponto.Ponto;
import reta.RetaGr;

/**
 * Desnha o triângulo
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class TrianguloGr extends Triangulo implements IElemento
{
    // vari�veis de inst�ncia - substitua o exemplo abaixo pelo seu pr�prio
    private RetaGr lado1, lado2, lado3;
    Color corT = Color.BLACK; 
    String nomeT = "";
    Color corNomeT = Color.BLACK;
    int espT = 1;

    private void atualizar(){
        this.lado1 = new RetaGr(
            (int)getP1().getX(), (int)getP1().getY(), (int)getP2().getX(), (int)getP2().getY(),
            getCorT(), getEspT());

        this.lado2 = new RetaGr(
        (int)getP2().getX(), (int)getP2().getY(), (int)getP3().getX(), (int)getP3().getY(),
        getCorT(), getEspT());

        this.lado3 = new RetaGr(
        (int)getP1().getX(), (int)getP1().getY(), (int)getP3().getX(), (int)getP3().getY(),
        getCorT(), getEspT());
    }
    /**
     * Construtor para objetos da classe TrianguloGr
     */

    public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color corT, String nomeT, int espT) {
        super(p1, p2, p3);
        this.setCorT(corT);
        this.setNomeT(nomeT);
        this.setEspT(espT);
        atualizar();
    }
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color corT, String nomeT, int espT) {
        super(x1,y1,x2,y2,x3,y3);
        this.setCorT(corT);
        this.setNomeT(nomeT);
        this.setEspT(espT);
        atualizar();
    }
    

    public Color getCorT() {
        return corT;
    }

    public void setCorT(Color corT) {
        this.corT = corT;
    }

    public String getNomeT() {
        return nomeT;
    }

    public void setNomeT(String nomeT) {
        this.nomeT = nomeT;
    }

    public Color getCorNomeT() {
        return corNomeT;
    }

    public void setCorNomeT(Color corNomeT) {
        this.corNomeT = corNomeT;
    }

    public int getEspT() {
        return espT;
    }

    public void setEspT(int espT) {
        this.espT = espT;
    }

    public void desenharTriangulo(Graphics g){
        this.lado1.desenharReta(g);
        this.lado2.desenharReta(g);
        this.lado3.desenharReta(g);
    }
    @Override
    public void desenho(Graphics g){
        desenharTriangulo(g);
    }
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("p1", pontoParaJSON(this.getP1()));
        obj.put("p2", pontoParaJSON(this.getP2()));
        obj.put("p3", pontoParaJSON(this.getP3()));
        obj.put("id", this.getNomeT());
        obj.put("esp", this.getEspT());
        JSONObject cor = new JSONObject();
        cor.put("r", this.getCorT().getRed());
        cor.put("g", this.getCorT().getGreen());
        cor.put("b", this.getCorT().getBlue());
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
        atualizar();
        if(lado1.contem(x, y) ||
            lado2.contem(x, y) ||
            lado3.contem(x, y)) return true;
        return false;
    }

    public void mover(int dx, int dy){
        getP1().setX(getP1().getX() + dx);
        getP1().setY(getP1().getY() + dy);

        getP2().setX(getP2().getX() + dx);
        getP2().setY(getP2().getY() + dy);

        getP3().setX(getP3().getX() + dx);
        getP3().setY(getP3().getY() + dy);
        atualizar();
    }

    public void rotacionar(double anguloGraus){
        double angulo = Math.toRadians(anguloGraus);

        double cx = (getP1().getX() + getP2().getX() + getP3().getX()) / 3.0;
        double cy = (getP1().getY() + getP2().getY() + getP3().getY()) / 3.0;

        // Rotaciona cada ponto
        rotacionarPonto(getP1(), cx, cy, angulo);
        rotacionarPonto(getP2(), cx, cy, angulo);
        rotacionarPonto(getP3(), cx, cy, angulo);

        atualizar(); // atualiza os lados
    }
    private void rotacionarPonto(Ponto p, double cx, double cy, double angulo){
        double x = p.getX();
        double y = p.getY();

        double novoX = cx + (x - cx) * Math.cos(angulo) - (y - cy) * Math.sin(angulo);
        double novoY = cy + (x - cx) * Math.sin(angulo) + (y - cy) * Math.cos(angulo);

        p.setX(novoX);
        p.setY(novoY);
    }

    public void escalar(double fator){
        double cx = (getP1().getX() + getP2().getX() + getP3().getX()) / 3.0;
        double cy = (getP1().getY() + getP2().getY() + getP3().getY()) / 3.0;
        escalarPonto(getP1(), cx, cy, fator);
        escalarPonto(getP2(), cx, cy, fator);
        escalarPonto(getP3(), cx, cy, fator);
        atualizar();
    }
    private void escalarPonto(Ponto p, double cx, double cy, double fator){
        double x = p.getX();
        double y = p.getY();

        double novoX = cx + (x-cx) * fator;
        double novoY = cy + (y-cy) * fator;

        p.setX(novoX);
        p.setY(novoY);
    }
}

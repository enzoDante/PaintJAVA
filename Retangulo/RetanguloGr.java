package Retangulo;

import java.awt.Color;
import java.awt.Graphics;

import org.json.JSONObject;

import estatico.DimensionView;
import permanencia.IElemento;
import ponto.Ponto;
import ponto.PontoGr;
import reta.RetaGr;

/**
 * Classe para desenhar o retângulo
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */

public class RetanguloGr extends Retangulo implements IElemento{
    
    private RetaGr ladoSuperior, ladoDireito, ladoInferior, ladoEsquerdo;
    Color corRetangulo = Color.BLACK; 
    String nomeRetangulo = "";
    Color corNomeRetangulo = Color.BLACK;
    int espRetangulo = 1;

    private void atualizarLados() {
        ladoSuperior = new RetaGr((int)getP1().getX(), (int)getP1().getY(),
                                  (int)getP3().getX(), (int)getP3().getY(),
                                  corRetangulo, espRetangulo);
        ladoDireito  = new RetaGr((int)getP3().getX(), (int)getP3().getY(),
                                  (int)getP2().getX(), (int)getP2().getY(),
                                  corRetangulo, espRetangulo);
        ladoInferior = new RetaGr((int)getP2().getX(), (int)getP2().getY(),
                                  (int)getP4().getX(), (int)getP4().getY(),
                                  corRetangulo, espRetangulo);
        ladoEsquerdo = new RetaGr((int)getP4().getX(), (int)getP4().getY(),
                                  (int)getP1().getX(), (int)getP1().getY(),
                                  corRetangulo, espRetangulo);
    }

    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo(nome);
        setEspRetangulo(esp);
        atualizarLados();
    }
    
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor){
        super(x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
        atualizarLados();
    }

    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super(x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
        setEspRetangulo(esp);
        atualizarLados();
    }

    public RetanguloGr(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
        setCorRetangulo(Color.BLACK);
        setNomeRetangulo("");
        atualizarLados();
    }


    public Color getCorRetangulo() {
        return corRetangulo;
    }

    public void setCorRetangulo(Color corRetangulo) {
        this.corRetangulo = corRetangulo;
    }

    public String getNomeRetangulo() {
        return nomeRetangulo;
    }

    public void setNomeRetangulo(String nomeRetangulo) {
        this.nomeRetangulo = nomeRetangulo;
    }

    public Color getCorNomeRetangulo() {
        return corNomeRetangulo;
    }

    public void setCorNomeRetangulo(Color corNomeRetangulo) {
        this.corNomeRetangulo = corNomeRetangulo;
    }

    public int getEspRetangulo() {
        return espRetangulo;
    }

    public void setEspRetangulo(int espRetangulo) {
        this.espRetangulo = espRetangulo;
    }

    public void desenharRetangulo(Graphics g) {
        // Desenha as 4 bordas
        ladoSuperior.desenharReta(g);
        ladoDireito.desenharReta(g);
        ladoInferior.desenharReta(g);
        ladoEsquerdo.desenharReta(g);

    }

    public void desenho(Graphics g){
        desenharRetangulo(g);
    }
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("p1", pontoParaJSON(this.getP1()));
        obj.put("p2", pontoParaJSON(this.getP2()));
        obj.put("id", this.getNomeRetangulo());
        obj.put("esp", this.getEspRetangulo());
        JSONObject cor = new JSONObject();
        cor.put("r", this.getCorRetangulo().getRed());
        cor.put("g", this.getCorRetangulo().getGreen());
        cor.put("b", this.getCorRetangulo().getBlue());
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
        atualizarLados();
        if(ladoSuperior.contem(x, y) ||
            ladoDireito.contem(x, y) ||
            ladoInferior.contem(x, y) ||
            ladoEsquerdo.contem(x, y)        
        ) return true;

        return false;
        // posso verificar se está dentro da área do retângulo, mas não vou fazer isso
        // int minX = (int) Math.min(getP1().getX(), getP2().getX());
        // int maxX = (int) Math.max(getP1().getX(), getP2().getX());
        // int minY = (int) Math.min(getP1().getY(), getP2().getY());
        // int maxY = (int) Math.max(getP1().getY(), getP2().getY());

        // return (x >= minX - tolerancia && x <= maxX + tolerancia &&
        //         y >= minY - tolerancia && y <= maxY + tolerancia);
    }

    public void mover(int dx, int dy){
        getP1().setX(getP1().getX() + dx);
        getP1().setY(getP1().getY() + dy);

        getP2().setX(getP2().getX() + dx);
        getP2().setY(getP2().getY() + dy);

        getP3().setX(getP3().getX() + dx);
        getP3().setY(getP3().getY() + dy);

        getP4().setX(getP4().getX() + dx);
        getP4().setY(getP4().getY() + dy);

        atualizarLados(); // mantém as retas sincronizadas
    }
    public void rotacionar(double anguloGraus){
        double angulo = Math.toRadians(anguloGraus);
        // centro do retângulo
        double cx = (getP1().getX() + getP2().getX() + getP3().getX() + getP4().getX()) / 4.0;
        double cy = (getP1().getY() + getP2().getY() + getP3().getY() + getP4().getY()) / 4.0;

        // rotaciona cada ponto
        rotacionarPont(getP1(), cx, cy, angulo);
        rotacionarPont(getP2(), cx, cy, angulo);
        rotacionarPont(getP3(), cx, cy, angulo);
        rotacionarPont(getP4(), cx, cy, angulo);

        atualizarLados();
    }
    private void rotacionarPont(Ponto p, double cx, double cy, double angulo){
        double x = p.getX();
        double y = p.getY();

        double novoX = cx + (x-cx) * Math.cos(angulo) - (y-cy) * Math.sin(angulo);
        double novoY = cy + (x-cx) * Math.sin(angulo) + (y-cy) * Math.cos(angulo);
        p.setX(novoX);
        p.setY(novoY);
    }

    public void escalar(double fator){
        // Centro do retângulo
        double cx = (getP1().getX() + getP2().getX() + getP3().getX() + getP4().getX()) / 4.0;
        double cy = (getP1().getY() + getP2().getY() + getP3().getY() + getP4().getY()) / 4.0;
        escalarPonto(getP1(), cx, cy, fator);
        escalarPonto(getP2(), cx, cy, fator);
        escalarPonto(getP3(), cx, cy, fator);
        escalarPonto(getP4(), cx, cy, fator);

        atualizarLados();
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

import Retangulo.FiguraRetangulo;
import Retangulo.RetanguloGr;
import circulo.CirculoGr;
import circulo.FiguraCirculos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import permanencia.*;
import ponto.FiguraPontos;
import ponto.Ponto;
import ponto.PontoGr;
import reta.FiguraRetas;
import reta.RetaGr;
import triangulo.FiguraTriangulo;
import triangulo.TrianguloGr;

/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {

    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    Color corAtual;       // Cor atual do primitivo
    int esp;              // Diametro do ponto
    int raio;
    Ponto raioA;
    
    //Armazenar desenho
    // Armazem galpao = new Armazem();
    Armazem galps;
    boolean mouseMove = false;
    
    // Para ponto
    int x, y;

    // Para reta
    int x1, y1, x2, y2, x3, y3;
    // selecionar primeiro click do mouse
    boolean primeiraVez = true;
    boolean segundaVez = false;
    
    int contClickTriang = 0;

    // elemento selecionado
    IElemento elementoSelect;
    double anguloAnterior = 0.0;
    boolean modoRotacao = false;
    double distanciaAnterior = 0.0;
    boolean modoEscala = false;
    /**
     * Constroi o painel de desenho
     *
     * @param msg mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param corAtual cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, Color corAtual, int esp, Armazem galpao){
        setTipo(tipo);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);
        galps = galpao;
        // elementoSelect = new PontoGr();

        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);

    }

    /**
     * Altera o tipo atual do primitivo
     *
     * @param tipo tipo do primitivo
     */
    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
        
        primeiraVez = true;
        segundaVez = false;
        mouseMove = false;
    
        // Zerar coordenadas
        x1 = y1 = x2 = y2 = x3 = y3 = 0;
        contClickTriang = 0;
    }

    /**
     * Retorna o tipo do primitivo
     *
     * @return tipo do primitivo
     */
    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    /**
     * Altera a espessura do primitivo
     *
     * @param esp espessura do primitivo
     */
    public void setEsp(int esp){
        this.esp = esp;
    }

    /**
     * Retorna a espessura do primitivo
     *
     * @return espessura do primitivo
     */
    public int getEsp(){
        return this.esp;
    }

    /**
     * Altera o raio atual do primitivo
     *
     * @param rai raio atual do primitivo
     */
    public void setRaio(int rai){
        this.raio = rai;
    }
    public void setRaioA(Ponto rai){
        this.raioA = rai;
    }

    /**
     * retorna o raio atual do primitivo
     *
     * @return raio atual do primitivo
     */
    public int getRaio(){
        return this.raio;
    }
     public Ponto getRaioA(){
        return this.raioA;
    }

    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAtual(Color corAtual){
        this.corAtual = corAtual;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAtual(){
        return this.corAtual;
    }

    /**
     * Altera a msg a ser apresentada no rodape
     *
     * @param msg mensagem a ser apresentada
     */
    public void setMsg(JLabel msg){
        this.msg = msg;
    }

    /**
     * Retorna a mensagem
     *
     * @return mensagem as ser apresentada no rodape
     */
    public JLabel getMsg(){
        return this.msg;
    }

    /**
     * Metodo chamado quando o paint eh acionado
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        
        //desenhar (armazem)
        for(IElemento el : galps.getElementos()){
            // System.out.println("Desenhando classe: " + el.getClass().getName());
            // desenharPrimitivos(g, el); // , el
            el.desenho(g);
        }
        desenharPrimitivos(g); 
        
        if (tipo == TipoPrimitivo.TRIANGULO) {
            if (contClickTriang == 1) {
                // dois pontos definidos, apenas a linha de um lado
                FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", esp, corAtual);
            } else if (contClickTriang == 2) {
                // três pontos definidos
                FiguraTriangulo.desenharTriangulo(g, x1, y1, x2, y2, x3, y3, corAtual, "", esp);
            }
        }else
        if(tipo == TipoPrimitivo.RETANGULO && mouseMove) {
            FiguraRetangulo.desenharRetangulo(g, x1, y1, x2, y2, getCorAtual(), "", getEsp());
        }
        
            
        
    }
    @Override

    public void mousePressed(MouseEvent e) {

        if (tipo == TipoPrimitivo.PONTO) {

            x = e.getX();

            y = e.getY();

            galps.adicionar(new PontoGr(x, y, corAtual, "", getEsp()));

            repaint();

            return;

        }else
    
        if (tipo == TipoPrimitivo.RETA || tipo == TipoPrimitivo.RETANGULO || tipo == TipoPrimitivo.CIRCULO) {

            if (primeiraVez) {
                x1 = e.getX();
                y1 = e.getY();
                primeiraVez = false;
                mouseMove = true;
            } else {
                x2 = e.getX();
                y2 = e.getY();
                primeiraVez = true;
                mouseMove = false;
                if (tipo == TipoPrimitivo.RETANGULO) {

                    RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, getCorAtual(), "", getEsp());
                    galps.adicionar(r);
                    repaint();
                } else if (tipo == TipoPrimitivo.CIRCULO) {

                    raio = (int) Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
                    setRaio(raio);
                    setRaioA(new Ponto(x2, y2));

                    // Se o círculo precisa ser armazenado ao finalizar:
                    CirculoGr circl = new CirculoGr(x1, y1, x2, y2, getCorAtual(), "", getEsp());
                    galps.adicionar(circl);
                    //FiguraCirculos.desenharCirculo(getGraphics(), x1, y1, getRaioA(), "", esp, corAtual, galps);
                    repaint();

                } else if (tipo == TipoPrimitivo.RETA) {
                    // Armazene a reta quando finalizar
                    FiguraRetas.desenharReta(getGraphics(), x1, y1, x2, y2, "", getEsp(), getCorAtual(), galps);
                    repaint();
                }
            }
        } else if (tipo == TipoPrimitivo.TRIANGULO) {
            if (contClickTriang == 0) {

                x1 = e.getX(); y1 = e.getY();
                mouseMove = true;
                contClickTriang++;

            } else if (contClickTriang == 1) {
                x2 = e.getX(); y2 = e.getY();
                contClickTriang++;

            } else if (contClickTriang == 2) {

                x3 = e.getX(); y3 = e.getY();
                mouseMove = false;
                // Armazene o triângulo ao finalizar
                FiguraTriangulo.desenharTriangulo(getGraphics(), x1, y1, x2, y2, x3, y3, corAtual, "", esp, galps);
                contClickTriang = 0;
                repaint();
            }
        }else if(tipo == TipoPrimitivo.SELECTION){
            x = e.getX();
            y = e.getY();
            for(IElemento el : galps.getElementos()){
                if(el.contem(x, y)){
                    elementoSelect = el;
                    // System.out.println("Elemento selecionado: " + el);

                    if(e.isShiftDown()){
                        if(elementoSelect instanceof RetanguloGr){
                            RetanguloGr rect = (RetanguloGr) elementoSelect;
                            double cx = (rect.getP1().getX() + rect.getP2().getX() + rect.getP3().getX() + rect.getP4().getX()) / 4.0;
                            double cy = (rect.getP1().getY() + rect.getP2().getY() + rect.getP3().getY() + rect.getP4().getY()) / 4.0;
                            anguloAnterior = Math.atan2(e.getY()-cy, e.getX()-cx);
                        }else if(elementoSelect instanceof RetaGr){
                            RetaGr r = (RetaGr) elementoSelect;
                            double cx = (r.getP1().getX() + r.getP2().getX()) / 2.0;
                            double cy = (r.getP1().getY() + r.getP2().getY()) / 2.0;
                            anguloAnterior = Math.atan2(e.getY() - cy, e.getX() - cx);
                        }else if(elementoSelect instanceof TrianguloGr){
                            TrianguloGr t = (TrianguloGr) elementoSelect;
                            double cx = (t.getP1().getX() + t.getP2().getX() + t.getP3().getX()) / 3.0;
                            double cy = (t.getP1().getY() + t.getP2().getY() + t.getP3().getY()) / 3.0;

                            anguloAnterior = Math.atan2(e.getY() - cy, e.getX() - cx);
                        }
                        modoRotacao = true;
                    }else if(e.isControlDown()){
                        if(elementoSelect instanceof RetanguloGr){
                            RetanguloGr rect = (RetanguloGr) elementoSelect;
                            distanciaAnterior = distanciaDoCentro(rect, e.getX(), e.getY());
                        }else if(elementoSelect instanceof TrianguloGr){
                            TrianguloGr t = (TrianguloGr) elementoSelect;
                            double cx = (t.getP1().getX() + t.getP2().getX() + t.getP3().getX()) / 3.0;
                            double cy = (t.getP1().getY() + t.getP2().getY() + t.getP3().getY()) / 3.0;
                            distanciaAnterior = Math.sqrt(Math.pow(e.getX() - cx, 2) + Math.pow(e.getY() - cy, 2));
                        }else if(elementoSelect instanceof RetaGr){
                            RetaGr r = (RetaGr) elementoSelect;
                            double cx = (r.getP1().getX() + r.getP2().getX()) / 2.0;
                            double cy = (r.getP1().getY() + r.getP2().getY()) / 2.0;
                            distanciaAnterior = Math.sqrt(Math.pow(e.getX() - cx, 2) + Math.pow(e.getY() - cy, 2));
                        }else if(elementoSelect instanceof CirculoGr){
                            CirculoGr c = (CirculoGr) elementoSelect;
                            double cx = c.getCentro().getX();
                            double cy = c.getCentro().getY();
                            distanciaAnterior = Math.sqrt(Math.pow(e.getX() - cx, 2) + Math.pow(e.getY() - cy, 2));
                        }
                        modoEscala = true;
                    }
                    break;
                }
            }
        }
    }

    // Função auxiliar
    private double distanciaDoCentro(RetanguloGr rect, int mx, int my) {
        double cx = (rect.getP1().getX() + rect.getP2().getX() + rect.getP3().getX() + rect.getP4().getX()) / 4.0;
        double cy = (rect.getP1().getY() + rect.getP2().getY() + rect.getP3().getY() + rect.getP4().getY()) / 4.0;
        return Math.sqrt(Math.pow(mx - cx, 2) + Math.pow(my - cy, 2));
    }

    public void mouseReleased(MouseEvent e) { 
        elementoSelect = null;
        modoRotacao = false;
        modoEscala = false;
    }           

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        // selecionou um elemento, isso acontece
        if(tipo == TipoPrimitivo.SELECTION){
            int dx = e.getX() - x;
            int dy = e.getY() - y;
            if(elementoSelect instanceof PontoGr){
                PontoGr p = (PontoGr) elementoSelect;
                p.setX(p.getX() + dx);
                p.setY(p.getY() + dy);
            }else if(elementoSelect instanceof RetaGr){
                RetaGr r = (RetaGr) elementoSelect;
                if(modoRotacao){
                    double cx = (r.getP1().getX() + r.getP2().getX()) / 2.0;
                    double cy = (r.getP1().getY() + r.getP2().getY()) / 2.0;

                    double anguloAtual = Math.atan2(e.getY() - cy, e.getX() - cx);
                    double deltaGraus = Math.toDegrees(anguloAtual - anguloAnterior);
                    r.rotacionarReta(deltaGraus);
                    anguloAnterior = anguloAtual;
                }else if(modoEscala){
                    double cx = (r.getP1().getX() + r.getP2().getX()) / 2.0;
                    double cy = (r.getP1().getY() + r.getP2().getY()) / 2.0;
                    double distanciaAtual = Math.sqrt(Math.pow(e.getX()-cx, 2) + Math.pow(e.getY()-cy, 2));
                    double fator = distanciaAtual / distanciaAnterior;
                    r.escalar(fator);
                    distanciaAnterior = distanciaAtual;
                }else{
                    r.mover(dx, dy);
                }
            }else if(elementoSelect instanceof CirculoGr){
                CirculoGr c = (CirculoGr) elementoSelect;
                if(modoEscala){
                    double cx = c.getCentro().getX();
                    double cy = c.getCentro().getY();
                    double distanciaAtual = Math.sqrt(Math.pow(e.getX()-cx, 2) + Math.pow(e.getY() - cy, 2));
                    double fator = distanciaAtual / distanciaAnterior;
                    c.escalar(fator);
                    distanciaAnterior = distanciaAtual;
                }else
                    c.mover(dx, dy);
            }else if(elementoSelect instanceof RetanguloGr){
                RetanguloGr ret = (RetanguloGr) elementoSelect;
                if(modoRotacao){
                    // Calcula centro
                    double cx = (ret.getP1().getX() + ret.getP2().getX() + ret.getP3().getX() + ret.getP4().getX()) / 4.0;
                    double cy = (ret.getP1().getY() + ret.getP2().getY() + ret.getP3().getY() + ret.getP4().getY()) / 4.0;
                    // Ângulo atual do mouse
                    double anguloAtual = Math.atan2(e.getY() - cy, e.getX() - cx);
                    // Diferença de ângulo
                    double delta = Math.toDegrees(anguloAtual - anguloAnterior);
                    ret.rotacionar(delta);
                    anguloAnterior = anguloAtual;
                }else if(modoEscala){
                    double distanciaAtual = distanciaDoCentro(ret, e.getX(), e.getY());
                    double fator = distanciaAtual / distanciaAnterior;
                    ret.escalar(fator);
                    distanciaAnterior = distanciaAtual;
                }else{
                    ret.mover(dx, dy);
                }
            }else if(elementoSelect instanceof TrianguloGr){
                TrianguloGr t = (TrianguloGr) elementoSelect;
                if(modoRotacao){
                    double cx = (t.getP1().getX() + t.getP2().getX() + t.getP3().getX()) / 3.0;
                    double cy = (t.getP1().getY() + t.getP2().getY() + t.getP3().getY()) / 3.0;

                    double anguloAtual = Math.atan2(e.getY() - cy, e.getX() - cx);
                    double deltaGraus = Math.toDegrees(anguloAtual - anguloAnterior);

                    t.rotacionar(deltaGraus);
                    anguloAnterior = anguloAtual;
                }else if(modoEscala){
                    double cx = (t.getP1().getX() + t.getP2().getX() + t.getP3().getX()) / 3.0;
                    double cy = (t.getP1().getY() + t.getP2().getY() + t.getP3().getY()) / 3.0;
                    double distanciaAtual = Math.sqrt(Math.pow(e.getX() - cx, 2) + Math.pow(e.getY() - cy, 2));
                    double fator = distanciaAtual / distanciaAnterior;
                    t.escalar(fator);
                    distanciaAnterior = distanciaAtual;
                }else{
                    t.mover(dx, dy);
                }
            }
            modoRotacao = e.isShiftDown() ? true : false;
            modoEscala = e.isControlDown() && !e.isShiftDown() ? true : false;
            x = e.getX();
            y = e.getY();
            repaint();
        }
    }

    /**
     * Evento mouseMoved: escreve mensagem no rodape (x, y) do mouse
     *
     * @param e dados do evento do mouse
     */
    public void mouseMoved(MouseEvent e) {
        if(tipo == TipoPrimitivo.TRIANGULO){
            if(contClickTriang == 1){
                x2 = e.getX();
                y2 = e.getY();
            }else if(contClickTriang == 2){
                x3 = e.getX();
                y3 = e.getY();    
            }
            repaint();
        }
        else if(tipo != TipoPrimitivo.PONTO){
            if(!primeiraVez){
                x2 = e.getX();
                y2 = e.getY();
                repaint();
            }
        }
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    
    public void desenharPrimitivos(Graphics g, IElemento el){
        el.desenho(g);
    }

    public void desenharPrimitivos(Graphics g) {
        if (tipo == TipoPrimitivo.PONTO) {
            // Preview do ponto sob o mouse (opcional)
            FiguraPontos.desenharPonto(g, x, y, "", getEsp(), getCorAtual());
        } else if (tipo == TipoPrimitivo.RETA) {
            if (mouseMove) {
                FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
            }
        } else if (tipo == TipoPrimitivo.CIRCULO) {
            if (mouseMove) {
                int r = (int) Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
                setRaio(r);
                FiguraCirculos.desenharCirculo(g, x1, y1, getRaio(), "", esp, corAtual);
            }
        } else if (tipo == TipoPrimitivo.RETANGULO) {
            if (mouseMove) {
                FiguraRetangulo.desenharRetangulo(g, x1, y1, x2, y2, getCorAtual(), "", getEsp());
            }
        } else if (tipo == TipoPrimitivo.TRIANGULO) {
            if (!primeiraVez || !segundaVez) {
                if (mouseMove) {
                    if (contClickTriang == 1) {
                        FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
                    } else if (contClickTriang == 2) {
                        FiguraTriangulo.desenharTriangulo(g, x1, y1, x2, y2, x3, y3, corAtual, "", esp);
                    }
                }
            }
        }
    }
}

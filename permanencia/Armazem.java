package permanencia;
import java.util.ArrayList;
import estatico.*;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Retangulo.RetanguloGr;
import circulo.CirculoGr;

import java.awt.Color;
import ponto.PontoGr;
import reta.RetaGr;
import triangulo.TrianguloGr;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Escreva uma descri��o da classe Armazem aqui.
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */
public class Armazem
{
    ArrayList<IElemento> elementos = new ArrayList<IElemento>();

    public void adicionar(IElemento r){
        elementos.add(r);
    }
    public ArrayList<IElemento> getElementos(){
        return this.elementos;
    }   
    
    public void ClearArmazen(){
        this.elementos.clear();
    }

    public void salvarJSON(File caminho){
        JSONObject resultado = new JSONObject();
        JSONObject figuras = new JSONObject();

        for (IElemento item : this.elementos) {
            
            String tipo = item.getClass().getSimpleName().toLowerCase().replace("gr", "");

            if(!figuras.has(tipo))
                figuras.put(tipo, new JSONArray());

            figuras.getJSONArray(tipo).put(item.toJSON());
        }
        resultado.put("figura", figuras);


        try (FileWriter file = new FileWriter(caminho)) { // "Desenho.json"
            file.write(resultado.toString(4));
            System.out.println("Arquivo JSON criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso em:\n" + caminho.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public void carregarJSON(File arquivo){
        try (FileReader reader = new FileReader(arquivo)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject json = new JSONObject(tokener);
            
            
            // Aqui você pode usar o objeto json carregado
            //JOptionPane.showMessageDialog(null, "Arquivo JSON carregado com sucesso!\nConteúdo:\n" + json.toString(4));

            jsonToIElement(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo: " + ex.getMessage());
        }
    }

    public void jsonToIElement(JSONObject json){
        JSONObject desenho = json.getJSONObject("figura");

        // Para acessar as chaves dentro de "desenho" 
        JSONArray retaArray = desenho.has("reta") ? desenho.getJSONArray("reta") : null;
        JSONArray circuloArray = desenho.has("circulo") ? desenho.getJSONArray("circulo") : null;
        JSONArray trianguloArray = desenho.has("triangulo") ? desenho.getJSONArray("triangulo") : null;
        JSONArray retanguloArray = desenho.has("retangulo") ? desenho.getJSONArray("retangulo") : null;

        // Processar os PONTOS
        if(desenho.has("ponto")) //pontoArray != null
            for (Object o: desenho.getJSONArray("ponto")) { // int i = 0; i < pontoArray.length(); i++
                // JSONObject ponto = pontoArray.getJSONObject(i);
                JSONObject ponto = (JSONObject) o;
                int x = (int) (ponto.getDouble("x") * DimensionView.LARGURA);
                int y = (int) (ponto.getDouble("y") * DimensionView.ALTURA);
                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = ponto.getJSONObject("cor");
                int r = cor.getInt("r");
                int g = cor.getInt("g");
                int b = cor.getInt("b");
                int esp = ponto.getInt("esp");
                String id = ponto.getString("id");

                Color corPonto = new Color(r, g, b); // 1

                // PontoGr pontoGr = new PontoGr(x, y, corPonto, id, esp);
                // ✅ Cria o ponto manualmente com setters
                PontoGr pontoGr = new PontoGr();
                pontoGr.setX(x);
                pontoGr.setY(y);
                pontoGr.setCorPto(corPonto);
                pontoGr.setDiametro(esp);
                pontoGr.setNomePto(id);
                adicionar(pontoGr);

                // Mostrar os valores lidos
                System.out.println("Ponto " + id + ": Espessura=" + esp + ", X=" + x + ", Y=" + y + ", Cor (R, G, B)=" + r + ", " + g + ", " + b);
            }

        // Processar as RETAS
        if(retaArray != null)
            for (int i = 0; i < retaArray.length(); i++) {
                JSONObject reta = retaArray.getJSONObject(i);

                // Acessar os pontos p1 e p2
                JSONObject p1 = reta.getJSONObject("p1");
                JSONObject p2 = reta.getJSONObject("p2");

                double p1x = (p1.getDouble("x") * DimensionView.LARGURA);
                double p1y = (p1.getDouble("y") * DimensionView.ALTURA);
                double p2x = (p2.getDouble("x") * DimensionView.LARGURA);
                double p2y = (p2.getDouble("y") * DimensionView.ALTURA);

                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = reta.getJSONObject("cor");
                int r = cor.getInt("r");
                int g = cor.getInt("g");
                int b = cor.getInt("b");
                double esp = reta.getDouble("esp");
                String id = reta.getString("id");
                Color corReta = new Color(r, g, b);

                RetaGr RetaGr = new RetaGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, corReta, id, (int)esp);
                adicionar(RetaGr);

                // Mostrar os valores lidos
                System.out.println("Reta " + id + ": Espessura=" + esp);
                System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
            }

        // Processar os CIRCULOS
        if(circuloArray != null)
            for (int i = 0; i < circuloArray.length(); i++) {
                JSONObject circulo = circuloArray.getJSONObject(i);

                // Acessar informacoes do circulo
                JSONObject centro = circulo.getJSONObject("centro");
                double centroX = (centro.getDouble("x") * DimensionView.LARGURA);
                double centroY = (centro.getDouble("y") * DimensionView.ALTURA);
                JSONObject raio = circulo.getJSONObject("raio");
                double raioX = (raio.getDouble("x") * DimensionView.LARGURA);
                double raioY = (raio.getDouble("y") * DimensionView.ALTURA);

                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = circulo.getJSONObject("cor");
                int r = cor.getInt("r");
                int g = cor.getInt("g");
                int b = cor.getInt("b");
                String id = circulo.getString("id");
                double esp = circulo.getDouble("esp");

                Color corCirculo = new Color(r, g, b);

                CirculoGr circuloGR = new CirculoGr((int)centroX, (int)centroY, (int)raioX, (int)raioY, corCirculo, id, (int)esp);
                adicionar(circuloGR);

                // Mostrar os valores lidos
                System.out.println("Circulo " + id + ": Espessura=" + esp);
                System.out.println("Centro (X, Y): " + centroX + ", " + centroY);
                System.out.println("Raio (X, Y): " + raioX + ", " + raioY);
                System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
            }

        // Processar os TRIANGULOS
        if(trianguloArray != null)
            for (int i = 0; i < trianguloArray.length(); i++) {
                JSONObject triangulo = trianguloArray.getJSONObject(i);

                // Acessar informacoes dos tres pontos do triangulo
                JSONObject p1 = triangulo.getJSONObject("p1");
                JSONObject p2 = triangulo.getJSONObject("p2");
                JSONObject p3 = triangulo.getJSONObject("p3");

                double p1x = (p1.getDouble("x") * DimensionView.LARGURA);
                double p1y = (p1.getDouble("y") * DimensionView.ALTURA);
                double p2x = (p2.getDouble("x") * DimensionView.LARGURA);
                double p2y = (p2.getDouble("y") * DimensionView.ALTURA);
                double p3x = (p3.getDouble("x") * DimensionView.LARGURA);
                double p3y = (p3.getDouble("y") * DimensionView.ALTURA);

                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = triangulo.getJSONObject("cor");
                int r = cor.getInt("r");
                int g = cor.getInt("g");
                int b = cor.getInt("b");
                double esp = triangulo.getDouble("esp");
                String id = triangulo.getString("id");

                Color corTri = new Color(r,g,b);
                TrianguloGr trianguloGr = new TrianguloGr(
                    (int) p1x, (int) p1y, (int) p2x, (int) p2y, (int) p3x, (int) p3y,
                    corTri, "", (int) esp                    
                    );
                adicionar(trianguloGr);

                // Mostrar os valores lidos
                System.out.println("Triangulo " + id + ": Espessura=" + esp);
                System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                System.out.println("Ponto 3 (X, Y): " + p3x + ", " + p3y);
                System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
            }

        // Processar RETANGULOS
        if(retanguloArray != null)
            for (int i = 0; i < retanguloArray.length(); i++) {
                JSONObject retangulo = retanguloArray.getJSONObject(i);

                // Acessar informacoes dos dois pontos do retangulo
                JSONObject p1 = retangulo.getJSONObject("p1");
                JSONObject p2 = retangulo.getJSONObject("p2");

                double p1x = (p1.getDouble("x") * DimensionView.LARGURA);
                double p1y = (p1.getDouble("y") * DimensionView.ALTURA);
                double p2x = (p2.getDouble("x") * DimensionView.LARGURA);
                double p2y = (p2.getDouble("y") * DimensionView.ALTURA);

                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = retangulo.getJSONObject("cor");
                int r = cor.getInt("r");
                int g = cor.getInt("g");
                int b = cor.getInt("b");
                double esp = retangulo.getDouble("esp");
                String id = retangulo.getString("id");

                Color corForm = new Color(r, g, b);
                RetanguloGr retanguloGr = new RetanguloGr((int) p1x, (int) p1y, (int) p2x, (int) p2y, corForm, id, (int) esp);
                adicionar(retanguloGr);

                // Mostrar os valores lidos
                System.out.println("Retangulo " + id + ": Espessura=" + esp);
                System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
            }


    }
}

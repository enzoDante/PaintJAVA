import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import Retangulo.RetanguloGr;
import permanencia.Armazem;
import permanencia.IElemento;

@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 1;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();
    private JToolBar barraComandos2 = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Persistencia de dados
    Armazem galpao = new Armazem();
    IElemento elementoSelected;

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10, galpao);

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbCor = new JButton("Cor");
    private JButton jbAngulo = new JButton("Rotacionar / escala");
    private JButton jbSelect = new JButton("Selecionar");
    private JButton jbSalvar = new JButton("Salvar");
    private JButton jbCarregar = new JButton("Carregar");
    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 1));
    private JSlider jsEsp = new JSlider(1, 50, 1);

    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos2.add(jbCor); // Botao de Cores
        barraComandos2.add(jbSelect);
        
        barraComandos2.add(jlEsp); // Label para espessura
        barraComandos2.add(jsEsp);    // Slider para espacamento
        barraComandos2.add(jbAngulo);
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        barraComandos.add(jbSalvar);
        barraComandos.add(jbCarregar);
        barraComandos.add(jbSair); // Botao de Cores

        // adicionar o menu em um grid 2x1
        JPanel painelBarras = new JPanel(new GridLayout(2, 1));
        painelBarras.add(barraComandos);
        painelBarras.add(barraComandos2);
        // adiciona os componentes com os respectivos layouts
        add(painelBarras, BorderLayout.NORTH);
        add(areaDesenho, BorderLayout.CENTER);
        add(msg, BorderLayout.SOUTH);

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.PONTO;
            areaDesenho.setTipo(tipoAtual);
        });        
        jbReta.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETA;
            areaDesenho.setTipo(tipoAtual);
        }); 
        jbRetangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETANGULO;
            areaDesenho.setTipo(tipoAtual);
        });
        jbTriangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.TRIANGULO;
            areaDesenho.setTipo(tipoAtual);
        });       
        jbCirculo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.CIRCULO;
            areaDesenho.setTipo(tipoAtual);
        });        
        jbLimpar.addActionListener(e -> {
            galpao.ClearArmazen();
            areaDesenho.removeAll();
            jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivoda tela) 
            areaDesenho.repaint();        
        });
        jbAngulo.addActionListener(e -> {
            // para rodar o objeto, basta segurar shift e o botão do mouse e move, a opção SELECTION deve estar ativada
            String msg = "Para rotacionar um objeto, basta segurar shift e o botão do mouse em cima do objeto, \né necessário ativar a opção SELECIONAR\n\nPara a escala do objeto, segure CTRL e botão do mouse, mova o mouse para mudar a escala";
            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

        });
        jbCor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
            if (c != null){ 
                corAtual = c; // pega do chooserColor 
            }
            areaDesenho.setCorAtual(corAtual); // cor atual
        });
        jbSelect.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.SELECTION;
            areaDesenho.setTipo(tipoAtual);
        });
        jsEsp.addChangeListener(e -> {
            espAtual = jsEsp.getValue();
            jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
            areaDesenho.setEsp(espAtual);        
        });        

        jbSair.addActionListener(e -> {
            System.exit(0);
        });

        jbSalvar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar arquivo JSON");
            
            // Filtro para arquivos JSON
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos JSON", "json"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                // Garante que o arquivo termine com .json
                String path = fileToSave.getAbsolutePath();
                if (!path.toLowerCase().endsWith(".json")) {
                    fileToSave = new File(path + ".json");
                }

                galpao.salvarJSON(fileToSave);
            }

        });

        jbCarregar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Carregar arquivo JSON");
            
            // Filtro para arquivos JSON
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos JSON", "json"));

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                galpao.carregarJSON(fileToSave);
                repaint();
                
            }
        });
    }
}

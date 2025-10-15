package permanencia;
import java.awt.Graphics;

import org.json.JSONObject;

/**
 * Escreva a descri��o da interface IArmazem aqui.
 * 
 * @author Enzo Dante Mícoli, Nicolas Okamoto Celestrino, Pedro Bizzari
 * @version 08/09/2024
 */

public interface IElemento
{
    /**
     * Exemplo de um cabe�alho de m�todo - substitua este coment�rio pelo seu
     * 
     * @param  y    exemplo de um par�metro de m�todo
     * @return        o resultado produzido pelo sampleMethod 
     */
    void desenho(Graphics g);
    public JSONObject toJSON();
    boolean contem(int x, int y); // verifica se usuário clicou no objeto
}

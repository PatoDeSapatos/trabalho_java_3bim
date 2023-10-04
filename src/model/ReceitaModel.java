package model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */
public class ReceitaModel {
    private ArrayList<ReceitaVO> receitas = new ArrayList<>();

    /**
     * Inicia a classe com cinco Receitas.
     */
    public ReceitaModel() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        receitas.add( new ReceitaVO(1, "Bolo"            , "Ovo, chocolate e leite", strDate) );
        receitas.add( new ReceitaVO(2, "Torta"           , "Ovo, leite e limão"    , strDate) );
        receitas.add( new ReceitaVO(3, "Café com Leite"  , "Café, açúcar e leite"  , strDate) );
        receitas.add( new ReceitaVO(4, "Pão com manteiga", "Pão e manteiga"        , strDate) );
        receitas.add( new ReceitaVO(5, "Suco Tang"       , "Suco, Tang e água"     , strDate) );
    }

    /**
     * Cria um objeto Receita e adiciona ao banco de dados
     * @param id
     * @param nome 
     * @param ingredientes
     * @param data
     */
    public void addReceita(int id, String nome, String ingredientes, String data) {
        ReceitaVO receita = new ReceitaVO( id, nome, ingredientes, data );
        this.receitas.add( receita );
    }

    /**
     * Remove uma receita do banco de dados
     * @param codigo
     * @return true caso a remoção seja um sucesso, false caso a remoção falhe
     */
    public boolean removerReceita( int codigo ) {
        for (int i = 0; i < this.receitas.size(); i++) {
            if ( this.receitas.get(i).getCodigo() == codigo ) {
                this.receitas.remove( this.receitas.get(i) );
                return true;
            }
        }

        return false;
    }

    /**
     * Edita uma receita no banco de dados, alterando o nome e os ingredientes
     * @param id
     * @param novoNome
     * @param novosIngredientes
     * @return true caso a edição seja um sucesso, false caso falhe
     */
    public boolean editarReceita(int id, String novoNome, String novosIngredientes) {
        try {
            for (int i = 0; i < receitas.size(); i++) {
                if ( receitas.get(i).getCodigo() == id ) {
                    receitas.get(i).setNome(novoNome);
                    receitas.get(i).setIngredientes(novosIngredientes);
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na edição de receita: " + e.getMessage());
        }

        return false;
    }

    /**
     * Pesquisa uma receita no banco de dados a partir do código
     * @param int codigo o código da receita a ser pesquisada
     * @return um array de strings com o nome e os ingrefientes da receita encontrada
     */
    public String[] pesquisarCodigo( int codigo ) {
        for (ReceitaVO receitaVO : receitas) {
            if ( receitaVO.getCodigo() == codigo ) {
                String[] retorno = { receitaVO.getNome(), receitaVO.getIngredientes() };
                return retorno;
            }
        }

        String[] retorno = {};
        return retorno;
    }

    /**
     * Procura uma Receita com o id fornecido dentro do banco de dados
     * @param id O código a ser procurado
     * @return true caso seja encontrado uma Receita com o id fornecido, false caso não encontre nada.
     */
    public boolean idExiste( int id ) {
        for (ReceitaVO receitaVO : receitas) {
            if (receitaVO.getCodigo() == id) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna todas as receitas guardadas no banco de dados em um array 2d
     * @return Um array 2d com todas as receitas armazenadas. 
     */
    public Object[][] getTodasReceitas() {
        Object[][] lista = new Object[ getSize() ][ 4 ]; 

        for( int i = 0; i < lista.length; i++ ) {
            ReceitaVO receita = getReceita(i);

            if ( receita != null ) {
                String[] linhaReceita = {
                    Integer.toString( receita.getCodigo() ),
                    receita.getNome(),
                    receita.getIngredientes(),
                    receita.getData() 
                };

                lista[i] = linhaReceita;
            }
        }

        return lista;
    }
    
    /**
     * Retorna todas as receitas que compartilham o mesmo nome.
     * @param name O nome a ser pesquisado
     * @return Um array 2d com todas as receitas que compartilham o mesmo nome
     */
    public Object[][] getReceitasPorNome( String name ) {
        ArrayList<ReceitaVO> receitasPesquisadas = new ArrayList<>();

        for (ReceitaVO receita : this.receitas) {
            if ( receita.getNome().toLowerCase().trim().contains(name) ) {
                receitasPesquisadas.add( receita );
            }
        }

        Object[][] lista = new Object[ receitasPesquisadas.size() ][ 4 ];

        for( int i = 0; i < lista.length; i++ ) {
            ReceitaVO receita = receitasPesquisadas.get(i);

            if ( receita != null ) {
                String[] linhaReceita = {
                    Integer.toString( receita.getCodigo() ),
                    receita.getNome(),
                    receita.getIngredientes(),
                    receita.getData() 
                };

                lista[i] = linhaReceita;
            }
        }

        return lista;
    }

    public ReceitaVO getReceita( int index ) {
        return this.receitas.get( index );
    }

    public int getSize() {
        return this.receitas.size();
    }
}

package model;

import java.util.ArrayList;

public class ReceitaModel {
    private ArrayList<ReceitaVO> receitas = new ArrayList<>();

    public ReceitaModel() {
        receitas.add( new ReceitaVO(1, "Bolo", "Ovo, chocolate e leite", "xx-xx") );
        receitas.add( new ReceitaVO(2, "Torta", "Ovo, leite e limão", "xx-xx") );
        receitas.add( new ReceitaVO(3, "Café com Leite", "Café, açúcar e leite", "xx-xx") );
        receitas.add( new ReceitaVO(4, "Pão com manteiga", "Pão e manteiga", "xx-xx") );
        receitas.add( new ReceitaVO(5, "Suco Tang", "Suco, Tang e água", "xx-xx") );
    }

    public void addReceita(int id, String nome, String ingredientes, String data) {
        ReceitaVO receita = new ReceitaVO( id, nome, ingredientes, data );
        this.receitas.add( receita );
    }

    public boolean removerReceita( int codigo ) {
        for (int i = 0; i < this.receitas.size(); i++) {
            if ( this.receitas.get(i).getCodigo() == codigo ) {
                this.receitas.remove( this.receitas.get(i) );
                return true;
            }
        }

        return false;
    }

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

    public boolean idExiste( int id ) {
        for (ReceitaVO receitaVO : receitas) {
            if (receitaVO.getCodigo() == id) {
                return true;
            }
        }

        return false;
    }

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

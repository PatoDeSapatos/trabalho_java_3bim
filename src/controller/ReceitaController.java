package controller;

import java.util.Date;

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.ReceitaModel;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */
public class ReceitaController {
    private ReceitaModel model = new ReceitaModel();
    
    /**
     * Trata os dados recebidos e aciona o método do ReceitaModel para adicionar uma nova receita no banco de dados
     * @param id O código da receita a ser adicionada
     * @param nome O nome da receuta a ser adicionada
     * @param ingredientes Os ingredientes da receita a ser adicionada
     */
    public void addReceita( String id, String nome, String ingredientes ) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        int idInt;

        try {
            idInt = Integer.parseInt( id );
            if ( !model.idExiste( idInt ) ) {
                model.addReceita(idInt, nome, ingredientes, strDate);
                JOptionPane.showMessageDialog(null, "Receita adicionada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "O ID Selecionado já exite, por favor altere.");
            }
        } catch (Exception e) {
            System.err.println("Erro: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar a receita.");
        }

    }

    /**
     * Trata os dados recebidos e aciona o ReceitaModel para remover uma receita do banco de dados
     * @param codigo
     * @return true caso a receita seja removida, false caso a remoção falhe
     */
    public boolean removerReceita( String codigo ) {
        int codigoInt;

        try {
            codigoInt = Integer.parseInt(codigo);
            return model.removerReceita( codigoInt );
        } catch( Exception e ) {
            System.err.println("Campo vazio!");
            return false;
        }
    }

    /**
     * Trata os dados recebidos e aciona o ReceitaModel para editar as informações de uma receita do banco de dados
     * @param codigo O código da receita
     * @param nome O nome da receita
     * @param ingredientes Os ingredientes da receita
     */
    public void editarReceita(int codigo, String nome, String ingredientes) {
        if ( nome != "" && ingredientes != "" ) {
            if ( model.editarReceita(codigo, nome, ingredientes) ) {
                JOptionPane.showMessageDialog(null, "Receita editada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro na edição de receita.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não deixe campos vazios.");
        }
    }

    /**
     * Trata os dados recebidos e aciona o ReceitaModel para pesquisar uma receita com base em um código
     * @param codigo O código do item a ser pesquisado
     * @return String[] - O item encontrado
     */
    public String[] pesquisarCodigo( String codigo ) {
        try {
            int codigoInt = Integer.parseInt(codigo);
            return model.pesquisarCodigo( codigoInt );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Codigo inválido.");
            System.err.println("Erro: " + e.getMessage());
            return new String[0];
        }
    }

    /**
     * Aciona o ReceitaModel para pesquisar receitas pelo nome
     * @param nome O nome das receitas a serem pesquisadas
     * @return Um array 2d com as receitas encontradas
     */
    public Object[][] pesquisarReceitas( String nome ) {
        return model.getReceitasPorNome(nome);
    }
    
    /**
     * Aciona o ReceitaModel para listar todas as receitas do banco de dadoss
     * @return Um array 2d com todas as receitas do banco de dados
     */
    public Object[][] listarReceitas() {
        return model.getTodasReceitas();
    }

    public int getDbSize() {
        return model.getSize();
    }
}

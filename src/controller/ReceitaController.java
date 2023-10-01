package controller;

import java.util.Date;

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.ReceitaModel;

public class ReceitaController {
    private ReceitaModel model = new ReceitaModel();
    
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

    public Object[][] pesquisarReceitas( String nome ) {
        return model.getReceitasPorNome(nome);
    }
    
    public Object[][] listarReceitas() {
        return model.getTodasReceitas();
    }

    public int getDbSize() {
        return model.getSize();
    }
}

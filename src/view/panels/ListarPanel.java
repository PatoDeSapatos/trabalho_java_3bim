package view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.ReceitaController;
import view.ReceitaView;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */

public class ListarPanel extends JPanel {
    private ReceitaController controller;
    private ReceitaView parent;
    private Dimension dimension = new Dimension(1080, 720);

    // Components
    private JTable table;
    private JButton fecharButton = new JButton( "Fechar" );
    private JButton listarTodosButton = new JButton( "Listar Todos" );
    private JButton limparButton = new JButton( "Limpar" );
    private JButton pesquisarButton = new JButton("Pesquisar por nome");
    private JLabel pesquisarLabel = new JLabel("Pesquisar: ");
    private JTextField pesquisarField = new JTextField(10);

    public ListarPanel( ReceitaController controller, ReceitaView parent ) {
        setLayout(new BorderLayout());
        setSize( dimension );
        this.controller = controller;
        this.parent = parent;

        String[] columnNames = {"Id", "Nome", "Ingredientes", "Data de Registro"};
        this.table = new JTable();
        this.table.setModel( new DefaultTableModel(columnNames, 0) );
        this.table.setEnabled(false);
        
        ButtonHandler handler = new ButtonHandler();
        fecharButton.addActionListener( handler );
        listarTodosButton.addActionListener( handler );
        limparButton.addActionListener( handler );
        pesquisarButton.addActionListener( handler );

        JPanel pesquisaPanel = new JPanel( new BorderLayout() );
            pesquisaPanel.add(pesquisarLabel, BorderLayout.NORTH);
            pesquisaPanel.add(pesquisarField, BorderLayout.CENTER);
            pesquisaPanel.add(pesquisarButton, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel( new FlowLayout() );
            buttonsPanel.add(pesquisaPanel);
            buttonsPanel.add(listarTodosButton);
            buttonsPanel.add(limparButton);
            buttonsPanel.add(fecharButton);
        add( buttonsPanel, BorderLayout.PAGE_START );

        JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize( dimension );
        add( scrollPane, BorderLayout.CENTER );

        atualizarLista();
    }

    /**
     * Atualiza a lista de receitas a serem mostradas na tela com todas as receitas guardadas no banco de dados
     */
    public void atualizarLista() {
        String[] rowNames = { "Id", "Nome", "Ingredientes", "Data de Registro" };
        Object[][] tableContent = controller.listarReceitas();
        
        this.table.setModel( new DefaultTableModel(tableContent, rowNames) );
    }

    /**
     * Atualiza a lista de receitas a serem mostradas na tela com todas as receitas que compartilham um nome
     * @param nome nome a ser pesquisado no banco de dados
     */
    public void atualizarLista( String nome ) {
        String[] rowNames = { "Id", "Nome", "Ingredientes", "Data de Registro" };
        Object[][] tableContent = controller.pesquisarReceitas( nome );
    
        this.table.setModel( new DefaultTableModel(tableContent, rowNames) );
    }

    public Dimension getDimension() {
        return dimension;
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == listarTodosButton ) {
                atualizarLista();
            } else if ( e.getSource() == limparButton ) {
                String[] columnNames = {"Id", "Nome", "Ingredientes", "Data de Registro"};
                table.setModel(new DefaultTableModel(columnNames, 0));
                pesquisarField.setText("");
            } else if ( e.getSource() == fecharButton ) {
                pesquisarField.setText("");
                parent.onClose();
            } else if ( e.getSource() == pesquisarButton ) {
                atualizarLista( pesquisarField.getText() );
            } else {
                System.err.println("Erro: botao sem funcao clicado em listar");
            }
        }
        
    }
}

package view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ReceitaController;
import view.ReceitaView;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */
public class EditarPanel extends JPanel {
    private Dimension dimension = new Dimension(380, 220);
    private ReceitaController controller;
    private ReceitaView parent;

    // Components
    private JPanel pesquisarCodigo;
    private JPanel editarReceita;

    private JLabel codigoLabel = new JLabel("Código: ");
    private JLabel nomeLabel = new JLabel("            Nome: ");
    private JLabel ingredientesLabel = new JLabel("Ingredientes: ");

    private JTextField codigoField = new JTextField(15);
    private JTextField nomeField = new JTextField(15);
    private JTextField ingredientesField = new JTextField(15);

    private JButton pesquisarButton = new JButton("Pesquisar");
    private JButton editarButton = new JButton("Editar");
    private JButton fecharButton = new JButton("Fechar");
    private JButton cancelarButton = new JButton("Cancelar");
    private JButton limparButton = new JButton("Limpar");

    public EditarPanel(ReceitaController controller, ReceitaView parent) {
        setSize( this.dimension );
        setLayout(new FlowLayout());

        this.controller = controller;
        this.parent = parent;
        ButtonHandler handler = new ButtonHandler();

        pesquisarButton.addActionListener(handler);
        editarButton.addActionListener(handler);
        fecharButton.addActionListener(handler);
        limparButton.addActionListener(handler);
        cancelarButton.addActionListener(handler);
        
        this.pesquisarCodigo = new JPanel( new BorderLayout() );
            pesquisarCodigo.add(codigoLabel, BorderLayout.NORTH);
            pesquisarCodigo.add(codigoField, BorderLayout.CENTER);
            pesquisarCodigo.add(pesquisarButton, BorderLayout.SOUTH);
        add(pesquisarCodigo);

        this.editarReceita = new JPanel( new BorderLayout() );
            JPanel nomePanel = new JPanel();
                nomePanel.add(nomeLabel);
                nomePanel.add(nomeField);
            JPanel ingredientesPanel = new JPanel();
                ingredientesPanel.add(ingredientesLabel);
                ingredientesPanel.add(ingredientesField);
            JPanel buttonsPanel = new JPanel();
                buttonsPanel.add(editarButton);
                buttonsPanel.add(cancelarButton);
                buttonsPanel.add(limparButton);
                buttonsPanel.add(fecharButton);
            editarReceita.add(nomePanel, BorderLayout.NORTH);
            editarReceita.add(ingredientesPanel, BorderLayout.CENTER);
            editarReceita.add(buttonsPanel, BorderLayout.SOUTH);
        add(editarReceita);

        pesquisarButton.setVisible(true);
        editarReceita.setVisible(false);
    }

    public Dimension getDimension() {
        return dimension;
    }

    private class ButtonHandler implements ActionListener {

        private int codigo;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pesquisarButton ) {
                String[] receita = controller.pesquisarCodigo( codigoField.getText() );

                if ( receita.length > 0 ) {
                    this.codigo = Integer.parseInt(codigoField.getText());
                    pesquisarCodigo.setVisible(false);
                    nomeField.setText(receita[0]);
                    ingredientesField.setText(receita[1]);
                    editarReceita.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foram encontradas Receitas com este código.");
                    codigoField.setText("");
                }
            } else if (e.getSource() == editarButton ) {
                controller.editarReceita( this.codigo, nomeField.getText(), ingredientesField.getText() );
                nomeField.setText("");
                ingredientesField.setText("");
                pesquisarCodigo.setVisible(true);
                editarReceita.setVisible(false);
            } else if (e.getSource() == fecharButton ) {
                codigoField.setText("");
                nomeField.setText("");
                ingredientesField.setText("");
                pesquisarCodigo.setVisible(true);
                editarReceita.setVisible(false);
                parent.onClose();
            } else if (e.getSource() == limparButton ) {
                codigoField.setText("");
                nomeField.setText("");
                ingredientesField.setText("");
            } else if (e.getSource() == cancelarButton) {
                codigoField.setText("");
                nomeField.setText("");
                ingredientesField.setText("");
                pesquisarCodigo.setVisible(true);
                editarReceita.setVisible(false);
            }
        }
        
    }

}

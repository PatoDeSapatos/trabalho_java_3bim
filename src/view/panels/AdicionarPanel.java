package view.panels;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ReceitaController;
import view.ReceitaView;

import javax.swing.*;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */
public class AdicionarPanel extends JPanel {
    private ReceitaController controller;
    private ReceitaView parent;
    private Dimension dimension = new Dimension(320, 180);

    // Components
    private JLabel idLabel = new JLabel("           Código: ");
    private JLabel nomeLabel = new JLabel("             Nome: ");
    private JLabel ingredientesLabel = new JLabel("Ingredientes: ");
    private JTextField idField = new JTextField(15);
    private JTextField nomeField = new JTextField(15);
    private JTextField ingredientesField = new JTextField(15);
    private JButton adicionarButton = new JButton("Adicionar");
    private JButton limparButton = new JButton("Limpar");
    private JButton fecharButton = new JButton("Fechar");

    public AdicionarPanel( ReceitaController controller, ReceitaView parent ) {
        setLayout( new FlowLayout() );
        setSize( dimension );
        this.controller = controller;
        this.parent = parent;

        add(idLabel);
        add(idField);

        add(nomeLabel);
        add(nomeField);
        
        add(ingredientesLabel);
        add(ingredientesField);
        
        ButtonHandler handler = new ButtonHandler();
        adicionarButton.addActionListener(handler);
        limparButton.addActionListener(handler);
        fecharButton.addActionListener(handler);

        add(adicionarButton);
        add(limparButton);
        add(fecharButton);
    }
    
    public Dimension getDimension() {
        return dimension;
    }

    private class ButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == adicionarButton ) {
                String id = idField.getText();
                String nome = nomeField.getText();
                String ingredientes = ingredientesField.getText();

                controller.addReceita( id, nome, ingredientes );
            } else if ( e.getSource() == limparButton ) {
                idField.requestFocus(true);
                idField.setText("");
                nomeField.setText("");
                ingredientesField.setText("");
            } else if ( e.getSource() == fecharButton) {
                parent.onClose();
                idField.setText("");
                nomeField.setText("");
                ingredientesField.setText("");
            }
        }
        
    }

}

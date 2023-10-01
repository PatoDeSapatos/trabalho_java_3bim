package view.panels;

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

public class RemoverPanel extends JPanel {
    private ReceitaController controller;
    private ReceitaView parent;
    private Dimension dimension = new Dimension(320, 180);
    // Components
    private JLabel codigoLabel = new JLabel("Digite o código da receita: ");
    private JTextField codigoField = new JTextField(22);

    private JButton removerButton = new JButton("Remover");
    private JButton limparButton = new JButton("Limpar");
    private JButton fecharButton = new JButton("Fechar");
    
    public RemoverPanel( ReceitaController controller, ReceitaView parent ) {
        this.parent = parent;
        this.controller = controller;
        setSize(this.dimension);
        setLayout(new FlowLayout());

        add(codigoLabel);
        add(codigoField);

        ButtonHandler handler = new ButtonHandler();
        removerButton.addActionListener(handler);
        limparButton.addActionListener(handler);
        fecharButton.addActionListener(handler);

        add(removerButton);
        add(limparButton);
        add(fecharButton);
    }

    public Dimension getDimension() {
        return dimension;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == removerButton ) {
                if ( controller.removerReceita(codigoField.getText()) ) {
                    JOptionPane.showMessageDialog( null, "Receita Removida com sucesso!" );
                } else {
                    JOptionPane.showMessageDialog( null, "Receita não encontrada." );
                }
                codigoField.setText("");
                codigoField.requestFocus( true );
            } else if ( e.getSource() == fecharButton ) {
                parent.onClose();
            } else if ( e.getSource() == limparButton ) {
                codigoField.setText("");
                codigoField.requestFocus( true );
            }
        }
    }
}

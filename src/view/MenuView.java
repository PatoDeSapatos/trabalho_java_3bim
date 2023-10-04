package view;

import javax.swing.BorderFactory;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ReceitaView.Panels;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    // Project classes
    private ReceitaView receitaView = new ReceitaView( this );

    // JFrame components
    private JLabel tituloLabel = new JLabel("Livro de Receitas!");
    private JButton adicionarButton = new JButton( "Adicionar Receitas" );
    private JButton removerButton = new JButton( "Remover Receitas" );
    private JButton listarButton = new JButton( "Listar Receitas" );
    private JButton editarButton = new JButton( "Editar Receitas" );

    public MenuView() {
        super("Menu");

        ButtonHandler handler = new ButtonHandler();
        FlowLayout layout = new FlowLayout();

        setSize( 700, 150 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        setLayout( new BorderLayout() );
        setResizable( false );

        adicionarButton.addActionListener( handler );
        removerButton.addActionListener( handler );
        editarButton.addActionListener( handler );
        listarButton.addActionListener( handler );
        tituloLabel.setFont( new Font("Bold", 0, 30) );

        JPanel tituloPanel = new JPanel(new GridBagLayout());
            tituloPanel.add(tituloLabel);
            tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add( tituloPanel, BorderLayout.NORTH );

        JPanel buttonsPanel = new JPanel( layout );
            buttonsPanel.add( adicionarButton );
            buttonsPanel.add( removerButton );
            buttonsPanel.add( editarButton );
            buttonsPanel.add( listarButton );
        add( buttonsPanel, BorderLayout.CENTER );
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            if ( e.getSource() == adicionarButton ) {
                receitaView.changePanel( Panels.ADICIONAR );
            } else if ( e.getSource() == removerButton ) {
                receitaView.changePanel( Panels.REMOVER );
            } else if ( e.getSource() == editarButton ) {
                receitaView.changePanel( Panels.EDITAR );
            } else if ( e.getSource() == listarButton ) {
                receitaView.changePanel( Panels.LISTAR );
            } else {
                System.out.println("Erro: Botao sem evento clicado");
                return;
            }

            receitaView.setLocationRelativeTo( removerButton );
            receitaView.setVisible();
            setVisible(false);
        }
    }
}

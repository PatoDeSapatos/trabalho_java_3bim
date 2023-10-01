package view;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.ReceitaController;
import view.panels.*;

public class ReceitaView extends JFrame {
    private JPanel panel;
    private JFrame parent;
    private ReceitaController controller = new ReceitaController();
    private AdicionarPanel adicionarPanel = new AdicionarPanel(controller, this);
    private RemoverPanel removerPanel = new RemoverPanel(controller, this);
    private EditarPanel editarPanel = new EditarPanel(controller, this);
    private ListarPanel listarPanel = new ListarPanel(controller, this);


    /**
     * Enum com todos os painéis do projeto
     */
    public enum Panels {
        ADICIONAR(0),
        REMOVER(1),       
        EDITAR(2),
        LISTAR(3),
        PESQUISAR(4);

        public final int panel;

        Panels( int panel ) {
            this.panel = panel;
        }
    }

    public ReceitaView( JFrame parent ) {
        super( "Receitas" );
        this.parent = parent;
        setSize( 1080, 720 );
        setDefaultCloseOperation( HIDE_ON_CLOSE );
        setLocationRelativeTo( parent );
        setLayout( new FlowLayout() );
        setResizable( false );

        addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose();
            }
            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    public void setVisible() {
        setVisible( true );
    }

    /**
     * Substitui o painel atual por um novo
     * @param newPanel Novo painel a ser aberto
     */
    public void changePanel( Panels newPanel ) {
        if ( this.panel != null ) {
            remove( this.panel );
        } 

        switch ( newPanel ) {
            case ADICIONAR:
                this.panel = adicionarPanel;
                setSize( adicionarPanel.getDimension() );
                break;
            case REMOVER:
                this.panel = removerPanel;
                setSize( removerPanel.getDimension() );
                break;
            case EDITAR:
                this.panel = editarPanel;
                setSize( editarPanel.getDimension() );
                break;
            case LISTAR:
                this.panel = listarPanel;
                setSize( listarPanel.getDimension() );
                break;
            default:
                System.out.print("Erro: Tentando abrir jpanel inexistente");
                break;
        }

        setLayout(null);
        add( this.panel );
    }



    public void onClose() {
        this.parent.setVisible(true);
        setVisible(false);
    }

}

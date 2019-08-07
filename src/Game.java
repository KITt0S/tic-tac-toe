import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements MouseListener {

    private List<JLabel> labels = new ArrayList<>();
    private int step = 0;

    {

        for (int i = 0; i < 9; i++) {

            JLabel label = new JLabel();
            label.setBackground( Color.WHITE );
            label.setOpaque( true );
            labels.add( label );
        }
    }

    Game() {

        setTitle( "Крестики-нолики" );
        setResizable( false );
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( screenSize.width / 2 - 300 / 2, screenSize.height / 2 - 300 / 2 );
        JPanel panel = new JPanel();
        panel.setPreferredSize( new Dimension( 300, 300 ) );

        add( panel );
        pack();
        panel.setLayout( new GridLayout(3, 3 ) );
        for ( JLabel i :
             labels ) {

            i.addMouseListener( this );
            panel.add( i );
        }
        setVisible( true );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Game();
            }
        });
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        try {

            step += 1;
            JLabel cell = ( JLabel )e.getComponent();
            if( step % 2 == 1 ) {

                cell.setIcon( new ImageIcon( "cross.gif" ) );
            } else {

                cell.setIcon( new ImageIcon( "circle.gif" ) );
            }
            cell.removeMouseListener( this );
            check();
        } catch ( NullPointerException exc ) {

            System.out.println( "Элемент не найден" );
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void check() {

        List<String> iconNames = new ArrayList<>();
        for ( JLabel i :
             labels ) {

            if( i.getIcon() != null ) {

                iconNames.add( i.getIcon().toString() );
            } else {

                iconNames.add( "" );
            }
        }
        String winner = "";
        if( ( iconNames.get( 0 ).equals( iconNames.get( 1 ) )
                && iconNames.get( 0 ).equals( iconNames.get( 2 ) ) ) ) {

            winner = iconNames.get( 0 );
        } else if ( ( iconNames.get( 3 ).equals( iconNames.get( 4 ) )
                && iconNames.get( 3 ).equals( iconNames.get( 5 ) ) ) ) {

            winner = iconNames.get( 3 );
        } else if ( ( iconNames.get( 6 ).equals( iconNames.get( 7 ) )
                && iconNames.get( 6 ).equals( iconNames.get( 8 ) ) ) ) {

            winner = iconNames.get( 6 );
        } else if ( ( iconNames.get( 0 ).equals( iconNames.get( 3 ) )
                && iconNames.get( 0 ).equals( iconNames.get( 6 ) ) ) ) {

            winner = iconNames.get( 0 );
        } else if ( ( iconNames.get( 1 ).equals( iconNames.get( 4 ) )
                && iconNames.get( 1 ).equals( iconNames.get( 7 ) ) ) ) {

            winner = iconNames.get( 1 );
        } else if ( ( iconNames.get( 2 ).equals( iconNames.get( 5 ) )
                && iconNames.get( 2 ).equals( iconNames.get( 8 ) ) ) ) {

            winner = iconNames.get( 2 );
        } else if ( ( iconNames.get( 0 ).equals( iconNames.get( 4 ) )
                && iconNames.get( 0 ).equals( iconNames.get( 8 ) ) ) ) {

            winner = iconNames.get( 0 );
        } else if ( ( iconNames.get( 2 ).equals( iconNames.get( 4 ) )
                && iconNames.get( 2 ).equals( iconNames.get( 6 ) ) ) ) {

            winner = iconNames.get( 2 );
        }

        if( !winner.equals( "" ) ) {

            if( winner.equals( "cross.gif" ) ) {

                JOptionPane.showMessageDialog( null,"Победа X", "Результат",
                        JOptionPane.INFORMATION_MESSAGE );
            } else {

                JOptionPane.showMessageDialog( null,"Победа O", "Результат",
                        JOptionPane.INFORMATION_MESSAGE );
            }
            setVisible( false );
            dispose();
        } else {

            for (int i = 0; i < iconNames.size(); i++) {

                if( iconNames.get( i ).equals( "" ) ) {

                    break;
                }

                if( i == iconNames.size() - 1 ) {

                    JOptionPane.showMessageDialog( null,"Ничья", "Результат",
                            JOptionPane.INFORMATION_MESSAGE );
                }
            }
        }
    }
}

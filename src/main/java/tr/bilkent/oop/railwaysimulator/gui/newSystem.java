package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */

        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import javax.swing.JButton;
        import java.awt.BorderLayout;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;
        import javax.swing.JSplitPane;
        import net.miginfocom.swing.MigLayout;
        import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;
        import tr.bilkent.oop.railwaysimulator.model.user.User;

public class newSystem {

    private JFrame frame;
    static newSystem window = new newSystem();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public newSystem() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][][][]", "[][][][][][]"));

        JButton btnNewButton = new JButton("Open Railway System");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        frame.getContentPane().add(btnNewButton, "cell 3 3");

        JButton btnNewButton_1 = new JButton("New  Railway System");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                RailwaySystemFacade.getInstance().initilizeNewSystemFor( getCurrentUser());
                window.frame.setVisible(false);
                getRailwaySystemCreate();

            }
        });
        frame.getContentPane().add(btnNewButton_1, "cell 3 5");
    }

}

package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */

        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import javax.swing.JTextPane;
        import java.awt.BorderLayout;

public class simulate {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    simulate window = new simulate();
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
    public simulate() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //result panel
        JTextPane textPane = new JTextPane();
        frame.getContentPane().add(textPane, BorderLayout.CENTER);
    }

}

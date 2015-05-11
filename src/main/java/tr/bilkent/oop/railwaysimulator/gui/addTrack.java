package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */

        import java.awt.EventQueue;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JFrame;
        import net.miginfocom.swing.MigLayout;
        import tr.bilkent.oop.railwaysimulator.model.railwaysystem.RailwaySystemFacade;

        import javax.swing.JLabel;
        import javax.swing.JTextField;
        import javax.swing.JButton;

public class addTrack {

    private JFrame frame;
    private JTextField textField;
    private JButton btnDone;
    static  addTrack window = new addTrack();

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
    public addTrack() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][][][][][grow][][]", "[][][][][][][][][]"));

        JLabel lblTrackIsAdded = new JLabel("Track is added to system!");
        frame.getContentPane().add(lblTrackIsAdded, "cell 5 4");

        textField = new JTextField("Enter the Name of First Station...");
        frame.getContentPane().add(textField, "cell 5 5,growx");
        textField.setColumns(10);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RailwaySystemFacade.getInstance().addNewTrackToCurrentSystem();

            }
        });

        btnDone = new JButton("Done");
        frame.getContentPane().add(btnDone, "cell 6 8");
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.frame.setVisible(false);
                try{
                    RailwaySystemCreate window = new RailwaySystemCreate();
                    window.frame.setVisible(true);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }


            }
        });
    }

}

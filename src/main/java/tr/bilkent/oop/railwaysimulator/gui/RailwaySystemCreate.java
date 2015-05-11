package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */
        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import net.miginfocom.swing.MigLayout;
        import javax.swing.JButton;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;

public class RailwaySystemCreate {

    private JFrame frame;
    static RailwaySystemCreate window = new RailwaySystemCreate();

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
    public RailwaySystemCreate() {
        initialize();
    }

    private void getRailwaySystemCreate(){
        RailwaySystemCreate rw = new RailwaySystemCreate();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][292.00][225.00][3.00]", "[][][][][][][][][]"));

        JButton btnAddTrack = new JButton("Add  Track  ");
        btnAddTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.frame.setVisible(false);
                try {
                    addTrack window = new addTrack();
                    window.frame.setVisible(true);
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnAddTrack, "cell 1 1 6 1,growx");

        JButton btnAddStation = new JButton("Add Station");
        btnAddStation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.frame.setVisible(false);

                try {
                    addStation window = new addStation();
                    window.frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnAddStation, "cell 1 3 6 1,growx");

        JButton btnAddTrain = new JButton("Add  Train   ");
        btnAddTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.frame.setVisible(false);

                try {
                    addTrain window = new addTrain();
                    window.frame.setVisible(true);
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnAddTrain, "cell 1 5 6 1,growx");

        JButton btnSimulate = new JButton("Simulate");
        btnSimulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                window.frame.setVisible(false);

                try {
                    simulate window = new simulate();
                    window.frame.setVisible(true);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }

            }
        });
        frame.getContentPane().add(btnSimulate, "cell 7 8,growx");
    }

}

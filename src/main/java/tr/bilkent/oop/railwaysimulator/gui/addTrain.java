package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */

        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import net.miginfocom.swing.MigLayout;
        import javax.swing.JTextField;
        import javax.swing.JButton;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;

public class addTrain {

    private JFrame frame;
    private JTextField txtEnterTheName;
    private JTextField txtEnterTheName_1;
    private JTextField txtEnterTheName_2;
    private JTextField textField;
    private JTextField txtEnterTheSpeed;
    private JTextField txtEnterTheNumber;
    private JTextField textField_1;
    private JButton btnShowTracksList;
    private JButton btnShowStatitonsList;
    private JButton btnShowStation;
    private JButton btnSetTheCapacity;
    private JButton btnDone;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    addTrain window = new addTrain();
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
    public addTrain() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][][]"));

        txtEnterTheName = new JTextField();
        txtEnterTheName.setText("Enter the Name of Desired Track");
        frame.getContentPane().add(txtEnterTheName, "cell 1 1,growx");
        txtEnterTheName.setColumns(10);

        btnShowTracksList = new JButton("Show Tracks List");
        frame.getContentPane().add(btnShowTracksList, "cell 2 1,growx");

        txtEnterTheName_1 = new JTextField();
        txtEnterTheName_1.setText("Enter the Name of Departure Station");
        frame.getContentPane().add(txtEnterTheName_1, "cell 1 2,growx");
        txtEnterTheName_1.setColumns(10);

        btnShowStatitonsList = new JButton("Show Stations List");
        frame.getContentPane().add(btnShowStatitonsList, "cell 2 2");

        txtEnterTheName_2 = new JTextField();
        txtEnterTheName_2.setText("Enter the Name of Destination Station");
        frame.getContentPane().add(txtEnterTheName_2, "cell 1 3,growx");
        txtEnterTheName_2.setColumns(10);

        btnShowStation = new JButton("Show Stations List");
        btnShowStation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        frame.getContentPane().add(btnShowStation, "cell 2 3");

        textField = new JTextField("Enter the Name of Train");
        frame.getContentPane().add(textField, "cell 1 4,growx");
        textField.setColumns(10);

        txtEnterTheSpeed = new JTextField();
        txtEnterTheSpeed.setText("Enter the Speed of Train");
        frame.getContentPane().add(txtEnterTheSpeed, "cell 1 5,growx");
        txtEnterTheSpeed.setColumns(10);

        txtEnterTheNumber = new JTextField();
        txtEnterTheNumber.setText("Enter the Number of the Waggon");
        frame.getContentPane().add(txtEnterTheNumber, "cell 1 6,growx");
        txtEnterTheNumber.setColumns(10);

        btnSetTheCapacity = new JButton("Set the Capacity ");
        frame.getContentPane().add(btnSetTheCapacity, "cell 2 6,growx");

        textField_1 = new JTextField("Enter the Tabletime of the Train");
        frame.getContentPane().add(textField_1, "cell 1 7,growx");
        textField_1.setColumns(10);

        btnDone = new JButton("Done");
        frame.getContentPane().add(btnDone, "cell 2 9,growx");
    }

}

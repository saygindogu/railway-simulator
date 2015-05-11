package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */


        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import net.miginfocom.swing.MigLayout;
        import javax.swing.JButton;
        import javax.swing.JTextField;
        import javax.swing.JLabel;
        import javax.swing.JComboBox;
        import javax.swing.JTextPane;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;

public class addStation {

    private JFrame frame;
    private JTextField textField;
    private JTextField txtEnterTheName;
    private JButton btnNewButton;
    private JButton btnShowStationsList;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    addStation window = new addStation();
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
    public addStation() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[grow][][][grow][][][grow][][][][][][][][][][][][][][]", "[][][][][][][][][grow][][][]"));

        txtEnterTheName = new JTextField();
        txtEnterTheName.setText("Enter the Name of Track to Add Station");
        frame.getContentPane().add(txtEnterTheName, "cell 0 1 18 1,growx");
        txtEnterTheName.setColumns(10);

        btnNewButton = new JButton("Show Tracks List  ");
        frame.getContentPane().add(btnNewButton, "cell 18 1");

        textField = new JTextField("Enter the Name of Station...");
        frame.getContentPane().add(textField, "cell 0 2 18 1,growx");
        textField.setColumns(10);

        btnShowStationsList = new JButton("Show Stations List");
        frame.getContentPane().add(btnShowStationsList, "cell 18 2");

        JButton btnAdd = new JButton("    Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        textField_1 = new JTextField("Enter the Distance From Previous Station");
        frame.getContentPane().add(textField_1, "cell 0 3 18 1,growx");
        textField_1.setColumns(10);
        frame.getContentPane().add(btnAdd, "cell 18 11,growx");

        JButton btnComplate = new JButton("Done");
        btnComplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        frame.getContentPane().add(btnComplate, "cell 20 11");
    }

}

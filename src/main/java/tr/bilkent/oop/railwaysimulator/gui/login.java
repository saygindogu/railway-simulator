package tr.bilkent.oop.railwaysimulator.gui;

/**
 * Created by Elif on 11.05.2015.
 */

        import java.awt.EventQueue;

        import javax.swing.JFrame;

        import net.miginfocom.swing.MigLayout;

        import javax.swing.JTextField;
        import javax.swing.JButton;

        import tr.bilkent.oop.railwaysimulator.model.user.User;

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.KeyAdapter;
        import java.awt.event.KeyEvent;

public class login {

    private JFrame frame;
    private JTextField txtEnterTheName;
    private JTextField txtEnterThePassword;
    static     login window = new login();

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
    public login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][][]"));

        txtEnterTheName = new JTextField();
        txtEnterTheName.setText("Enter the Name");
        frame.getContentPane().add(txtEnterTheName, "cell 2 2,growx");
        txtEnterTheName.setColumns(10);

        txtEnterThePassword = new JTextField();
        txtEnterThePassword.setText("Enter the Password");
        frame.getContentPane().add(txtEnterThePassword, "cell 2 3,growx");
        txtEnterThePassword.setColumns(10);

        txtEnterTheName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent arg0) {
                User currentUser = new User((txtEnterTheName.getText()) , "" );
            }
        });

        JButton btnDone = new JButton("Done");
        frame.getContentPane().add(btnDone, "cell 2 4");
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                window.frame.setVisible(false);
                try {
                    RailwaySystemCreate window = new RailwaySystemCreate();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

})
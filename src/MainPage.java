import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainPage extends JFrame implements ActionListener {

    //JPanel and Container for the CreateAccountPage
    Container content = getContentPane();
    JPanel panel = new JPanel();

    //The buttons that allow you to create an account or cancel
    JButton createAccount;
    JButton cancel;

    //This array holds the information that is asked to create an account
    String[] fields = {"Username: ", "Name: ", "Email: ", "Password: "};

    //This arraylist holds the JTextFields that will be created for each field of information being asked for
    ArrayList<JTextField> jTextFields = new ArrayList<>();



    //The JPanel that contains the information that is needed to create an account
    JPanel rowPanel;

    //The JPanel that contains the two buttons, createAccount, and cancel
    JPanel buttonPanel;

    ObjectOutputStream oos;
    ObjectInputStream ois;
    Try client;

    JButton convertButton = new JButton("Convert");

    MainPage (Try client) {

        this.client = client;
        this.oos = client.getOos();
        this.ois = client.getOis();

        content.setLayout(new BorderLayout());
        panel.setBounds(61, 11, 81, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        content.add(panel, BorderLayout.CENTER);

    }


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == convertButton) {
                // TODO
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            // TODO
        }
    }
}


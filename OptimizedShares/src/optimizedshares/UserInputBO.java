package optimizedshares;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserInputPanel extends JPanel implements ActionListener {

    private JButton SubmitButton;
    private JTextField NumberOfMemberField;
    private String NumberOfMembers;

    public UserInputPanel() {
        SubmitButton = new JButton("Submit");

        NumberOfMemberField = new JTextField(15);

        JLabel NumberOfMemberLabel = new JLabel("Number Of Members that particpated in CSCTF: ");
        JPanel NumberOfMemberPanel = new JPanel();

        NumberOfMemberPanel.add(NumberOfMemberLabel);
        NumberOfMemberPanel.add(NumberOfMemberField);

        SubmitButton.addActionListener(this); //event listener registration

        JPanel CenterPanel = new JPanel();
        CenterPanel.add(NumberOfMemberPanel);
        CenterPanel.add(SubmitButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) {
            try {
                NumberOfMembers = NumberOfMemberField.getText();
                SharePanel share = new SharePanel(NumberOfMembers);
                removeAll();
                revalidate();
                repaint();
                add(share,BorderLayout.CENTER);
             /* ShareBO share = new ShareBO(NumberOfMembers);
                JComponent component = (JComponent) evt.getSource();
                Window win = SwingUtilities.getWindowAncestor(component);
                win.dispose(); */
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter how many members came to csctf noob!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

public class UserInputBO extends JFrame {

    private UserInputPanel CP_Panel;

    public UserInputBO() {
        setTitle("CSCTF Share");
        setSize(720, 720);

        //get screen size and set the location of the frame
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setLocation(screenWidth / 3, screenHeight / 4);

        addWindowListener(new WindowAdapter() //handle window event
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
         
        Container contentPane = getContentPane(); //add a panel to a frame
        CP_Panel = new UserInputPanel();

        contentPane.add(CP_Panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new UserInputBO();
    }
}

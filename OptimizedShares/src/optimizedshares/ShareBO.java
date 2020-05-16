package optimizedshares;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SharePanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField NameField[]; //array of names
    private JTextField PointField[]; //array of member csctf points
    private JTextField TotalPointField;
    private JTextField TotalCPField;
    private JTextField CPShareField[]; //array of shares
    private int NumberOfMembers;

    public SharePanel(String Num_Members) {
        OpenButton = new JButton("Calculate Shares");
        OpenButton.addActionListener(this);
        NumberOfMembers = Integer.parseInt(Num_Members); //convert string to int
        
        NameField = new JTextField[NumberOfMembers];
        PointField = new JTextField[NumberOfMembers];
        CPShareField = new JTextField[NumberOfMembers];
        
        for(int i = 0; i < NumberOfMembers; i++){
            NameField[i] = new JTextField(15);
            PointField[i] = new JTextField(15);
            CPShareField[i] = new JTextField(15);
            CPShareField[i].setEditable(false); //do not allow people to alter cp shares of members
        }
        
        Box Names = Box.createVerticalBox();
        Box Points = Box.createVerticalBox();
        Box CPs = Box.createVerticalBox();
        Names.add(new JLabel("Member Names"));
        Points.add(new JLabel("CSCTF Points"));
        CPs.add(new JLabel("CP Share"));
        for(int i = 0; i < NumberOfMembers; i++){
            Names.add(NameField[i]);
            Points.add(PointField[i]);
            CPs.add(CPShareField[i]);
        }
        
        Box member_container = Box.createHorizontalBox();
        member_container.add(Names);
        member_container.add(Points);
        member_container.add(CPs);
        
        
        
        
        
        
        JLabel TotalPointLabel = new JLabel("Total CSCTF Points(Make sure it's correct): ");
        JLabel TotalCPsLabel = new JLabel("Enter amount of CPs for share: ");
        
        JPanel TotalPointPanel = new JPanel();
        TotalPointField = new JTextField(15);
        TotalPointField.setEditable(false);
        TotalPointPanel.add(TotalPointLabel);
        TotalPointPanel.add(TotalPointField);
        
        JPanel TotalCPsPanel = new JPanel();
        TotalCPField = new JTextField(15);
        TotalCPsPanel.add(TotalCPsLabel);
        TotalCPsPanel.add(TotalCPField);
        
        Box info = Box.createVerticalBox();
        info.add(TotalCPsPanel);
        info.add(TotalPointPanel);
        info.add(OpenButton);
        
        JScrollPane pane = new JScrollPane(member_container,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension(700, 570));
        
        add(info,BorderLayout.NORTH);
        add(pane,BorderLayout.CENTER);
        
        
        
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Calculate Shares")) {
            try {
                double total_cps = Double.parseDouble(TotalCPField.getText());
                double total_point = 0;
                //get total points from CSCTF
                for (int i = 0; i < NumberOfMembers; i++) {
                    total_point += Double.parseDouble(PointField[i].getText());
                }
                TotalPointField.setText(String.valueOf(total_point));
                
                //calculate shares
                for (int i = 0; i < NumberOfMembers; i++) {
                    double member_csctf_point = Double.parseDouble(PointField[i].getText());
                    double member_share = (member_csctf_point / total_point) * total_cps;
                    CPShareField[i].setText(String.valueOf(member_share));
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Make sure nothing is empty.\n"
                        + "Make sure you filled out amount of cps for share.\n"
                        + "Make sure  you fill out CSCTF Points as Numbers and NOT LETTERS\n",
                        "Confirmation",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}

public class ShareBO extends JFrame {

    private SharePanel CP_Panel;

    public ShareBO(String Num_Members) {
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
        CP_Panel = new SharePanel(Num_Members);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

   
}

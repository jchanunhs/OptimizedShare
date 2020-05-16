package optimizedshares;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

class SharePanel extends JPanel implements ActionListener {

    private JButton CalculateShareButton, AddMemberButton;
    //private JTextField NameField[]; //array of names
   // private JTextField PointField[]; //array of member csctf points
    private JTextField TotalPointField;
    private JTextField TotalCPField;
    //private JTextField CPShareField[]; //array of shares
    ArrayList<JTextField> NameField = new ArrayList<JTextField>();
    ArrayList<JTextField> PointField = new ArrayList<JTextField>();
    ArrayList<JTextField> CPShareField = new ArrayList<JTextField>();
    Box Names;
    Box Points;
    Box CPs;
    
    private int NumberOfMembers;
    JScrollPane scroll;
    
    public SharePanel(String Num_Members) {
        CalculateShareButton = new JButton("Calculate Shares");
        CalculateShareButton.addActionListener(this);
        
        //add new row in case user forgets total members
        AddMemberButton = new JButton("Add Member");
        AddMemberButton.addActionListener(this);
        
        NumberOfMembers = Integer.parseInt(Num_Members); //convert string to int
        
        //create jtextfield and add to array list
        for(int i = 0; i < NumberOfMembers; i++){
            JTextField NF = new JTextField(15);
            JTextField PF = new JTextField(15);
            JTextField CPS = new JTextField(15);
            CPS.setEditable(false); //do not allow people to alter cp shares of members
            NameField.add(NF);
            PointField.add(PF);
            CPShareField.add(CPS);
        }
        
        //vertical box
        Names = Box.createVerticalBox(); //member name
        Points = Box.createVerticalBox(); // their ctf points
        CPs = Box.createVerticalBox(); //share 
        //first item in box is labels for each column
        Names.add(new JLabel("Member Names"));
        Points.add(new JLabel("CSCTF Points"));
        CPs.add(new JLabel("CP Share"));
        for(int i = 0; i < NumberOfMembers; i++){
            Names.add(NameField.get(i));
            Points.add(PointField.get(i));
            CPs.add(CPShareField.get(i));
        }
        
        //add boxes into 1 horizontal box
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
        TotalPointPanel.add(CalculateShareButton); // calculate button next to total points
        TotalPointPanel.add(AddMemberButton); //add member button next to calculate share
        
        JPanel TotalCPsPanel = new JPanel();
        TotalCPField = new JTextField(15);
        TotalCPsPanel.add(TotalCPsLabel);
        TotalCPsPanel.add(TotalCPField);
        
        //Total cps and display total ctf poitns
        Box info = Box.createVerticalBox();
        info.add(TotalCPsPanel);
        info.add(TotalPointPanel);
       
        //scrollbar for member information which will be in center of layout
        scroll = new JScrollPane(member_container,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(700, 570));       
        
        add(info,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        
        
        
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Calculate Shares")) {
            try {
                double total_cps = Double.parseDouble(TotalCPField.getText());
                double total_point = 0;
                //get total points from CSCTF
                for (int i = 0; i < NameField.size(); i++) {
                    total_point += Double.parseDouble(PointField.get(i).getText());
                }
                TotalPointField.setText(String.valueOf(total_point));
                
                //calculate shares
                for (int i = 0; i < NameField.size(); i++) {
                    double member_csctf_point = Double.parseDouble(PointField.get(i).getText());
                    double member_share = (member_csctf_point / total_point) * total_cps;
                    CPShareField.get(i).setText(String.valueOf(member_share));
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Make sure nothing is empty.\n"
                        + "Make sure you filled out amount of cps for share.\n"
                        + "Make sure  you fill out CSCTF Points as Numbers and NOT LETTERS\n",
                        "Confirmation",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(arg.equals("Add Member")){
            //new textfield
            JTextField NF = new JTextField(15);
            JTextField PF = new JTextField(15);
            JTextField CPS = new JTextField(15);
            CPS.setEditable(false); //do not allow people to alter cp shares of members
            //add to array list
            NameField.add(NF);
            PointField.add(PF);
            CPShareField.add(CPS);
            //get last element of array list and add to box
            Names.add(NameField.get(NameField.size()-1));
            Points.add(PointField.get(PointField.size()-1));
            CPs.add(CPShareField.get(CPShareField.size()-1));
            scroll.revalidate();
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

        Container contentPane = getContentPane(); //add a scrolll to a frame
        CP_Panel = new SharePanel(Num_Members);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

   
}

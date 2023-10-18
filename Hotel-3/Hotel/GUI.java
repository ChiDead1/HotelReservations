
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.*;


public class GUI extends JFrame implements ActionListener
{
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    //set the item within the menu
     private JMenuItem User = new JMenuItem("User"); //*************************************
     private JMenuItem Reserv = new JMenuItem("Reservations");
     private JMenuItem Change = new JMenuItem("Change");
     private JMenuItem Review = new JMenuItem("Review");
     private JMenuItem Reports = new JMenuItem("Reports");
     private JMenuItem CancelRe = new JMenuItem("Cancel");
     private JMenuItem Exit = new JMenuItem("Exit");
     
     //seting the frame with the loan box to let use enter
     FlowLayout flow = new FlowLayout();
     
     //creating the frame
     JFrame frame = new JFrame(" ");
     
     //creating the menuBar
      JMenuBar menuBar = new JMenuBar();
        
      /*
      JLabel temp = new JLabel("bar");
     JTextField fh = new JTextField(10);
         */       
     //creating the text area
     JTextArea myTextArea;
     
     //creating blank label 
     JLabel  sum = new JLabel("");  
     JLabel  sum2 = new JLabel("");  

    User U1 = new User(); //********************************************
    Reservations R1 = new Reservations(); 
    Change C1 = new Change();
    Cancel C2 = new Cancel();
    Review R2 = new Review(); 
    Reports R3 = new Reports(); 
    Button_cl b1 = new Button_cl(); 

    public GUI()
    {
       
        //creating the menu for the menubar
        JMenu menu1 = new JMenu("Options");
        JMenu menu2 = new JMenu("Exit");
         
        //adding all the tools
        frame.setLayout(flow);
       // frame.add(temp);
      //  frame.add(fh);
        frame.add(sum);
        frame.add(sum2);
        frame.add(menuBar);
        
        myTextArea = new JTextArea (10,75);
        add(myTextArea);
        
     
        
       JScrollPane scrollPane = new JScrollPane (myTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
       add (scrollPane );
       
       //set menu item for actionlistenere so it can do something
        User.addActionListener(this); //*******************************
        Reserv.addActionListener(this);
        Change.addActionListener(this);
        Review.addActionListener(this);
        Reports.addActionListener(this);
        Exit.addActionListener(this);
        CancelRe.addActionListener(this);
        
        //adding all the item
        menu1.add(User); //************************
        menu1.add(Reserv);
        menu1.add(Change); 
        menu1.add(Review);
        menu1.add(Reports);
        menu1.add(CancelRe);
        menu2.add(Exit);
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        
        frame.setJMenuBar(menuBar);
      
        
        frame.setSize(850,500);
        frame.setVisible(true);
    }
    
    //the actionEvent that funtion all the menu items
    public void actionPerformed(ActionEvent E)
    {
        
         /*     
            String text = myTextArea.getText(); 
     
                String temp = fh.getText(); 
           
                loan = Double.parseDouble(temp);
                
                DecimalFormat df = new DecimalFormat("0.00");
          
        }
            */
        if(E.getSource() == User)
        {
            //demo1.AWTFlowLayoutDemo();
            U1.user_re();
            sum.setText("Users");
        }
        if(E.getSource() == Reserv)
        {
            R1.Booking();
        }
        
         if(E.getSource() == Change)
        {
            try {
                C1.change_re();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sum.setText("Change");
        }
        
         if(E.getSource() == Review)
        {
          sum.setText("Review");
          R2.review_re();
        }
        
         if(E.getSource() == Reports)
        {
           sum.setText("Reports");
           R3.reports_re(); 
        }
        
        if(E.getSource() == CancelRe)
        {
            C2.cancel_re();
           //sum.setText("Cancel");
           //b1.Butoon_mt();
        }
        
        //close the program
        if(E.getSource() == Exit)
        {
            System.exit(0);
        }
    }
    
}



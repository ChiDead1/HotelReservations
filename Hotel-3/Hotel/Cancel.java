import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/***
 *
 */
public class Cancel
{
    /***
     *
     */
    String confirmationNumToCancel;

    JOptionPane pane1, pane2;
    JPanel panel;
    JFrame fr;
    JTextField tf;
    JLabel lbl;
    JButton btn;

    /***
     *
     */
    public void cancel_re()
    {

        //System.out.println(Database.pullFromFile("59"));  // Here I tested the pullFromFile method by using confirmation number that exists in the text file.

        lbl = new JLabel("Enter Confirmation Number:");
        //lbl.setVisible(true);
        lbl.setBounds(0,50,200,30);

        btn = new JButton("Submit");
        btn.setBounds(200, 199, 100,50);
        btn.addActionListener(new ActionListener() {

            /***
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationNumToCancel = tf.getText();
                         //if confirmation # exists cancellation successful pops up
                if(Database.isInFile(confirmationNumToCancel)){
                    String str = Database.pullFromFile(confirmationNumToCancel);
                 //   String[] arr = str.split(" ");
                    Reservations.roomTypeIncrement(str);

//                    System.out.println(Reservations.getSingleRoomsAvailable());
//                    System.out.println(Reservations.getDoubleRoomsAvailable());
//                    System.out.println(Reservations.getTripleRoomsAvailable());
//                    System.out.println(Reservations.getQuadRoomsAvailable());

//                    if(Database.whichRmType(confirmationNumToCancel).equalsIgnoreCase("Single")){
//                    //    int singles = Reservations.getSingleRoomsAvailable();
//                        Reservations.setSingleRoomAvailable(Reservations.getSingleRoomsAvailable()+ 1);
//                        System.out.println(Reservations.getSingleRoomsAvailable());
//                    }else if(Database.whichRmType(confirmationNumToCancel).equalsIgnoreCase("Double")){
//                        int doubles = Reservations.getDoubleRoomsAvailable();
//                        Reservations.setDoubleRoomAvailable(doubles + 1);
//                        System.out.println(Reservations.getDoubleRoomsAvailable());
//                    }else if(Database.whichRmType(confirmationNumToCancel).equalsIgnoreCase("Triple")){
//                        int triples = Reservations.getTripleRoomsAvailable();
//                        Reservations.setTripleRoomsAvailable(triples + 1);
//                        System.out.println(Reservations.getTripleRoomsAvailable());
//                    }else{
//                        int quads = Reservations.getQuadRoomsAvailable();
//                        Reservations.setQuadRoomAvailable(quads + 1);
//                        System.out.println(Reservations.getQuadRoomsAvailable());
//                    }

                    Database.cancel_re(confirmationNumToCancel);
                    pane2.showMessageDialog(null,"Cancellation Successful!", "Cancellation Status", JOptionPane.PLAIN_MESSAGE);
                    fr.dispose();
                }
                                //if user enters the wrong confirmation # then an error message pops up
                else
                {
                    pane1 = new JOptionPane();
                    pane1.showMessageDialog(null,"Re-check confirmation number or call to speak with a front desk associate.", "Confirmation Number not found", JOptionPane.ERROR_MESSAGE);

                }

            }
        } );

        tf = new JTextField();
        tf.setPreferredSize(new Dimension(100, 40));
        tf.setBounds(200, 349, 50, 30);

        panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(500, 500));
        panel.add(tf);
        panel.add(lbl);
        panel.add(btn);

        fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Reservation Cancellation");
        fr.setSize(500, 500);
        fr.add(panel);
        fr.setVisible(true);



    }
}

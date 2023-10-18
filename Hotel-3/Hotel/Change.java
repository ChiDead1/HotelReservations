import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Change implements ActionListener {
    private JFrame frame;
    private JButton button, button1, button2, button3, button4;

    private JLabel welcomelabel, label1, label2, label3;
    int Single, Double, Triple, Quad;

    String confirmationnumber, fullname, age, roomselected, roomtyped, occupants, daystyped, cardtyped, creditordebit = "";

    public void change_re() throws IOException {

        frame = new JFrame(); //Open Window


        label1 = new JLabel("Reservation Found!  ");
        welcomelabel = new JLabel("What would you like to modify in your reservation? ");
        button = new JButton("Modify Room Type");
        button.addActionListener(this);
        button1 = new JButton("Modify Days of Stay");
        button1.addActionListener(this);
        button2 = new JButton("Modify Card Type");
        button2.addActionListener(this);
        button3 = new JButton("Modify Credit or Debit");
        button3.addActionListener(this);
        button4 = new JButton("Submit");
        button4.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        panel.add(label1);
        panel.add(welcomelabel);
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);


        String ConfirmText = JOptionPane.showInputDialog(null, "Enter Confirmation Number");

        int k = 0;

        Path path = Paths.get("database.txt");
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                if (ConfirmText.equals(values[0])) {

                    //JOptionPane.showMessageDialog(null,"Reservation Found!", "Reservation Status", JOptionPane.PLAIN_MESSAGE);

                    confirmationnumber = values[0];
                    fullname = values[1];
                    age = values[2];
                    roomtyped = values[3];
                    occupants = values[4];
                    daystyped = values[5];
                    cardtyped = values[6];
                    creditordebit = values[7];

                    if (values[3] == "Single") {
                        Single++;

                    }
                    if (values[3] == "Double") {
                        Double++;

                    }
                    if (values[3] == "Triple") {
                        Triple++;

                    }
                    if (values[3] == "Quad") {
                        Quad++;

                    }

                    System.out.println("here1");

                    frame.add(panel, BorderLayout.CENTER);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setTitle("Change Reservation");
                    frame.pack();
                    frame.setVisible(true);

                }
                System.out.println("Confirmedtext: " + ConfirmText);
                System.out.println(values[0]);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("here printing");
        System.out.println(confirmationnumber + "," + fullname + "," + age + "," + roomtyped + "," + occupants + "," + daystyped + "," + cardtyped + "," + creditordebit + "\n");


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("hereroomselect");
            roomselected = JOptionPane.showInputDialog(null, "Room Type: ");
            System.out.println("Confirmed: " + roomtyped);
            System.out.println("here room selected: "+ roomselected);
            System.out.println("room capacity: "+ Single);

            if (roomselected.equals("Single")||roomselected.equals("single")) {
                Single++;
                if (Single < 10) {
                    roomtyped = roomselected;
                    System.out.println("not suppose to be here: "+ roomtyped);
                } else {
                    JOptionPane.showMessageDialog(null, "Reservation cannot be changed " + "\n" + "Room type: " + roomtyped + " full", "Reservation Status", JOptionPane.PLAIN_MESSAGE);
                }

            }
            if (roomselected.equals("Double") || roomselected.equals("double")) {
                Double++;
                if (Double < 10) {
                    roomtyped = roomselected;
                    System.out.println("here: "+ roomtyped);
                }
                /*
                else {
                    JOptionPane.showMessageDialog(null, "Reservation cannot be changed " + "\n" + "Room type: " + roomtyped + " full", "Reservation Status", JOptionPane.PLAIN_MESSAGE);
                }

                 */

            }
            if (roomselected.equals("Triple") || roomselected.equals("triple")) {
                Triple++;
                if (Triple < 10) {
                    roomtyped = roomselected;
                    System.out.println("here: "+ roomtyped);
                }
                /*else {
                    JOptionPane.showMessageDialog(null, "Reservation cannot be changed " + "\n" + "Room type: " + roomtyped + " full", "Reservation Status", JOptionPane.PLAIN_MESSAGE);
                }

                 */

            }
            if (roomselected.equals("Quad") || roomselected.equals("quad")) {
                Quad++;
                if (Quad < 10) {
                    roomtyped = roomselected;
                    System.out.println("here: "+ roomtyped);
                } else {
                    //JOptionPane.showMessageDialog(null, "Reservation cannot be changed " + "\n" + "Room type: " + roomtyped + " full", "Reservation Status", JOptionPane.PLAIN_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Room type is not a valid option" + "\n" + "Room type: " + roomtyped + " full", "Reservation Status", JOptionPane.PLAIN_MESSAGE);

            }
        }

            if (e.getSource() == button1) {
                System.out.println("here4");
                daystyped = JOptionPane.showInputDialog(null, "Days of Stay");
                System.out.println("Confirmed: " + daystyped);

            }
            if (e.getSource() == button2) {
                cardtyped = JOptionPane.showInputDialog(null, "Card Type", "Modifying Card Type", JOptionPane.PLAIN_MESSAGE);
                System.out.println("Confirmed: " + cardtyped);

            }
            if (e.getSource() == button3) {
                creditordebit = JOptionPane.showInputDialog(null, "Credit or Debit");
                System.out.println("Confirmed: " + creditordebit);

            }
            if (e.getSource() == button4) {
                try {
                    System.out.println("herewritingtofile");
                    Database.cancel_re(confirmationnumber);
                    FileWriter writer = new FileWriter("Database.txt",true);
                    writer.append(confirmationnumber + "," + fullname + "," + age + "," + roomtyped + "," + occupants + "," + daystyped + "," + cardtyped + "," + creditordebit + "\n");
                    writer.close();
                } catch (FileNotFoundException d) {
                    d.printStackTrace();
                } catch (IOException d) {
                    d.printStackTrace();

                }


                JOptionPane.showMessageDialog(null, "Reservation Change Successful" + "\n" + "Confirmation Number: " + confirmationnumber + "\n" + "Name: " + fullname + "\n" + "Age: " + age + "\n" + "Room Type: " + roomtyped + "\n" + "Guest Number: " + occupants + "\n" + "Total Days of Stay: " + daystyped + "\n" + "Card Type: " + cardtyped + "\n" + "Payment Type: " + creditordebit, "Reservation Status", JOptionPane.PLAIN_MESSAGE);

                label2 = new JLabel("Confirmation: " + confirmationnumber + "\n" + "Name: " + fullname + "\n" + "Age: " + age + "\n" + "Room Type: " + roomtyped + "\n" + "Guest Number: " + occupants + "\n" + "Total Days of Stay: " + daystyped + "\n" + "Card Type: " + cardtyped + "\n" + "Payment Type" + creditordebit);

                frame.dispose();

            }


    }
}


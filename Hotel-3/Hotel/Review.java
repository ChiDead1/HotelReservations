import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Review implements ActionListener
{
    JFrame frame;
    JButton button;

    public void review_re()
    {

       frame = new JFrame(); //Open Window

        JLabel  label0;


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));

        button = new JButton("Exit");
        button.addActionListener(this);

        System.out.println("Print out a reports or recipt for Coustomer or Manager");
        Path path = Paths.get("database.txt");
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));

            while((line = br.readLine()) != null){

                String[] values = line.split(",");



                label0 = new JLabel("<html><i>Confirmation Number:  " + values[0] + "<br> Full Name:  " + values[1]+ "\n"+"<br>Age:  " + values[2]+ "\n"+"<br>Room Type:  " + values[3] + "\n"+"<br>Occupants: " + values[4] + "\n"+"<br>Days reserved: " + values[5] + "\n"+"<br>Card Type:  " + values[6] + "\n"+"<br>Payment Type:  " + values[7] + "\n</i><html>");
                //label0 = new JLabel("Confirmation Number:  " + values[0]+ "\n" );
                label0.setOpaque(true);
                label0.setBackground(Color.WHITE);
                label0.setForeground(Color.blue);

                panel.add(label0);


                panel.add(button);


                frame.add(panel, BorderLayout.CENTER);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Manager Report");
                frame.pack();
                frame.setVisible(true);


            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
        
}

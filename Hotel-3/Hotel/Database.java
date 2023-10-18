import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/***
 *
 */
public class Database {

    /***
     *
     * @param Reservation
     */
    public static void addToFile(Reservations Reservation){
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("Database.txt", true));

            //below i append to the file. Each line in file = one reservation
            writer.append(String.valueOf(Reservation.getConfirmationNumber() + ", " ));
            writer.append(Reservation.getName() + ", ");
            writer.append(Reservation.getAge() + ", ");
            writer.append(Reservation.getRoomType() + ", ");
            writer.append(String.valueOf(Reservation.getNumOfOccupants() + ", "));
            writer.append(Reservation.getNumberOfDaysStay() + ", ");
            writer.append(Reservation.getPaymentCardType() + ", ");
            writer.append(Reservation.getPaymentCardCompany() + "\n");

            writer.close();

        }     catch (IOException e) {
            e.printStackTrace();
        }

    }

   /* public static void change_re(Reservations Reservation){

    }*/


    /***
     *
     * @param confirmationNum
     */
    public static void cancel_re(String confirmationNum){
        String textFileName = "Database.txt";
       // String splitter = ",";
        int pos = 0; //delete based on confirmation num which is at index 0
        String tmp = "tmp.txt";
        File oldFile = new File("Database.txt");
        File newFile = new File(tmp);
        String currentLine;
        String[] lineElements;
        try{
            FileWriter fw = new FileWriter(tmp,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(textFileName);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null)
            {
                lineElements = currentLine.split(",");
                if (!(lineElements[pos].equalsIgnoreCase(confirmationNum)))
                {
                    pw.append(currentLine);
                    pw.append("\n");
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            fw.close();
            bw.close();


            oldFile.delete();
            File updatedFile = new File("Database.txt");
            newFile.renameTo(updatedFile);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    /***
     * @return
     *
     * @param confirmationNumber
     */
    public static boolean isInFile(String confirmationNumber){
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Database.txt"));
            String currentLine;
            String[] lineElements;
            int pos = 0;
            while((currentLine = reader.readLine()) != null)
            {
                lineElements = currentLine.split(",");
                if (lineElements[pos].equalsIgnoreCase(confirmationNumber))
                {
                    //reader.close();
                    return true;
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /***
     *
     * @param confirmationNumber
     * @return
     */
    public static String pullFromFile(String confirmationNumber) {
        StringBuilder reservation = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Database.txt"));
            String currentLine;
            String[] lineElements;
            int pos = 0;
            while((currentLine = reader.readLine()) != null)
            {
                lineElements = currentLine.split(",");
                if (lineElements[pos].equalsIgnoreCase(confirmationNumber))
                {
                    for(String i:lineElements) {
                        reservation.append(i);
                    }
                    return reservation.toString();
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       return null;
    }

    public static String whichRmType(String confirmationNumber){
        StringBuilder reservation = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Database.txt"));
            String currentLine;
            String[] lineElements;
            int pos = 0;
            while((currentLine = reader.readLine()) != null)
            {

                lineElements = currentLine.split(",");
                if (lineElements[pos].equalsIgnoreCase(confirmationNumber))
                {
                    for(String i:lineElements) {
                        reservation.append(i);
                    }
                    return lineElements[3];
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

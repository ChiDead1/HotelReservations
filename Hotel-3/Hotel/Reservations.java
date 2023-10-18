import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.Random;

/***
 *
 */
public class Reservations
{
    public String name;
    private String age;
    private String roomType;
    private int numOfOccupants;
    private String numberOfDaysStay;
    private String paymentCardType;
    private String paymentCardCompany;
    private int confirmationNumber;
    private static int singleRoomsAvailable = 10;   //did 10 rooms of each type, increment accordingly if canceling/changing reservation
    private static int doubleRoomsAvailable = 10;
    private static int tripleRoomsAvailable = 10;
    private static int quadRoomsAvailable = 10;


    private JTextField fullNameTextField, ageTextField;
    private JPanel fullNamePanel, agePanel, roomTypePanel, numOfOccupantsPanel;      //Panels
    private JButton btn1, btn2, btn3, btn4;
    private JLabel fullNameLabel, ageLabel, roomTypeLabel, numOfOccupantsLabel, daysOfStayLabel, paymentTypeLabel, paymentCardTypeLabel, lbl1, lbl2, lbl3, lbl4, lbl5;
    private JComboBox roomTypeComboBox, numOfOccupantsComboBox, daysOfStayComboBox, paymentTypeComboBox, paymentCardTypeComboBox;

    static ArrayList<Integer> confirmationNumbersList = new ArrayList<>();

    public static ArrayList confirmationNumbersList() {
        return confirmationNumbersList;

    }
    /***
     *
     */
    public void Booking()
    {
        //---------------jframe------------------------------------
        JFrame jframe = new JFrame();
        jframe.setTitle("Reservations");
        jframe.setSize(1500, 1000);
        jframe.setLayout(new FlowLayout());
        //----------------comboBox-------------------------------------------
        String[] roomTypeOptions = {"Single", "Double", "Triple", "Quad"};
        roomTypeComboBox = new JComboBox(roomTypeOptions);
        roomTypeComboBox.setBounds(250, 125, 175, 30);

        Integer[] numOfOccupantOptions = {1};
        numOfOccupantsComboBox = new JComboBox(numOfOccupantOptions);
        numOfOccupantsComboBox.setBounds(250, 170, 175, 30);

        String[] daysOfStayOptions = {"1", "2", "3", "4", "5", "6", "7", "indefinitely"};
        daysOfStayComboBox = new JComboBox(daysOfStayOptions);
        daysOfStayComboBox.setBounds(250,210,175, 30);

        String[] paymentCardTypeOptions = {"Visa", "MasterCard", "Discover"};
        paymentCardTypeComboBox = new JComboBox(paymentCardTypeOptions);
        paymentCardTypeComboBox.setBounds(250, 250, 175, 30);

        String[] paymentTypeOptions = {"Debit", "Credit"};
        paymentTypeComboBox = new JComboBox(paymentTypeOptions);
        paymentTypeComboBox.setBounds(250, 290, 175, 30);

        //----------------textfields------------------------------------------
        fullNameTextField = new JTextField();
        fullNameTextField.setPreferredSize(new Dimension(200, 40));
        fullNameTextField.setBounds(250, 49, 175, 30);

        ageTextField = new JTextField();
        ageTextField. setPreferredSize(new Dimension(500, 40));
        ageTextField.setBounds(250, 90, 175, 30);

        //----------------buttons 1 and 2 + actionlisteners for each-------------------------------------------
        btn1 = new JButton("Submit");
        btn1.setBounds(200, 550, 100, 30);
        btn1.addActionListener(new ActionListener() {

            //below is the code for submit btn action: reservation obj fields are initialized & obj sent to database class for file writing
            //joptionpane window pops up showing successful reservation, when clicking ok on option pane the jframe is closed as well
            //Specified room type availability is decremented here as well

            /***
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn1){
                    Reservations reservation = new Reservations();
                    reservation.confirmationNumber = (int)(Math.random() * 101);      //Random # generated for each reservation
                    ArrayList<Integer> confirmationNumbersList = new ArrayList<>();
                    while(confirmationNumbersList.contains(reservation.confirmationNumber)){ //making sure confirmation # is unique else reassign new confirmation #
                        reservation.confirmationNumber = (int)(Math.random() * 101);
                    }
                    confirmationNumbersList.add(reservation.confirmationNumber);
                    reservation.name = fullNameTextField.getText();
                    reservation.age = ageTextField.getText();
                    reservation.roomType = (String)roomTypeComboBox.getSelectedItem();
                    reservation.numOfOccupants = numOfOccupantsComboBox.getItemCount();
                    reservation.numberOfDaysStay = (String)daysOfStayComboBox.getSelectedItem();
                    reservation.paymentCardType = (String)paymentTypeComboBox.getSelectedItem();
                    reservation.paymentCardCompany = (String)paymentCardTypeComboBox.getSelectedItem();
                    Reservations.roomTypeDecrement(reservation.roomType);

                    //passing obj to file
                    Database.addToFile(reservation);

                    //popup window receipt
                    JOptionPane pane1 = new JOptionPane();
                  //  JOptionPane.showMessageDialog(null,"Reservation Successful", "Reservation Status", JOptionPane.PLAIN_MESSAGE);
                    pane1.showMessageDialog(null,"Reservation Successful!\n" + "See reservation details below:\n\n" + "Name: " + reservation.getName() + "\nAge: " + reservation.getAge() + "\nRoomType: " + reservation.getRoomType() + "\n" +
                            "Number of Days Staying in: " + reservation.numberOfDaysStay + "\nOccupants:" + Integer.toString(reservation.getNumOfOccupants()) + "\nConfirmation Number: " + Integer.toString(reservation.getConfirmationNumber()) +
                            " \n\n" + "Please save your confirmation number to be able to cancel, modify, or retrieve reservation details in the future. ",
                            "Receipt" , JOptionPane.PLAIN_MESSAGE);
                    jframe.dispose();
                }
            }
        });

        //btn2 just goes back to main window
        btn2 = new JButton("Main Menu");
        btn2.setBounds(200, 625, 100, 30);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });
        //---------------labels----------------------------------------------
        fullNameLabel = new JLabel();
        fullNameLabel.setText("Full Name: ");
        fullNameLabel.setHorizontalAlignment(JLabel.LEFT);
        fullNameLabel.setBounds(50, 10, 100, 100);

        ageLabel = new JLabel();
        ageLabel.setText("Age: ");
        ageLabel.setHorizontalAlignment(JLabel.LEFT);
        ageLabel.setBounds(50, 50, 100, 100);

        roomTypeLabel = new JLabel();
        roomTypeLabel.setText("Room Type: ");
        roomTypeLabel.setHorizontalAlignment(JLabel.LEFT);
        roomTypeLabel.setBounds(50, 90, 100, 100);

        numOfOccupantsLabel = new JLabel();
        numOfOccupantsLabel.setText("Number of Occupants: ");
        numOfOccupantsLabel.setHorizontalAlignment(JLabel.LEFT);
        numOfOccupantsLabel.setBounds(50, 130, 150, 100);

        daysOfStayLabel = new JLabel();
        daysOfStayLabel.setText("How many days will you stay: ");
        daysOfStayLabel.setHorizontalAlignment(JLabel.LEFT);
        daysOfStayLabel.setBounds(50,170,200,100);

        paymentCardTypeLabel = new JLabel();
        paymentCardTypeLabel.setText("Card Type: ");
        paymentCardTypeLabel.setHorizontalAlignment(JLabel.LEFT);
        paymentCardTypeLabel.setBounds(50,210,200,100);

        paymentCardTypeLabel = new JLabel();
        paymentCardTypeLabel.setText("Card Type: ");
        paymentCardTypeLabel.setHorizontalAlignment(JLabel.LEFT);
        paymentCardTypeLabel.setBounds(50,210,200,100);

        paymentTypeLabel = new JLabel();
        paymentTypeLabel.setText("Credit or Debit: ");
        paymentTypeLabel.setHorizontalAlignment(JLabel.LEFT);
        paymentTypeLabel.setBounds(50,250,200,100);

        lbl1 = new JLabel();
        lbl1.setText("Single Rooms unavailable");
        lbl1.setForeground(new Color(0x8B0000));
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbl1.setBounds(130,400,300,100);
        lbl1.setVisible(false);

        lbl2 = new JLabel();
        lbl2.setText("Double Rooms unavailable");
        lbl2.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbl2.setForeground(new Color(0x8B0000));
        lbl2.setBounds(130,400,300,100);
        lbl2.setVisible(false);

        lbl3 = new JLabel();
        lbl3.setText("Triple Rooms unavailable");
        lbl3.setForeground(new Color(0x8B0000));
        lbl3.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbl3.setBounds(130,400,300,100);
        lbl3.setVisible(false);

        lbl4 = new JLabel();
        lbl4.setText("Quad Rooms unavailable");
        lbl4.setForeground(new Color(0x8B0000));
        lbl4.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbl4.setBounds(130,400,300,100);
        lbl4.setVisible(false);

        lbl5 = new JLabel();
        lbl5.setText("No rooms available, check back later");
        lbl5.setForeground(new Color(0x8B0000));
        lbl5.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lbl5.setBounds(105,400,300,100);
        lbl5.setVisible(false);

        //---------------panels----------------------------------------------
        fullNamePanel = new JPanel(null);//Panel creation //****************************
        fullNamePanel.setPreferredSize(new Dimension(500, 750));
        fullNamePanel.setBackground(Color.GRAY);
        fullNamePanel.add(fullNameLabel);
        fullNamePanel.add(ageLabel);
        fullNamePanel.add(roomTypeLabel);
        fullNamePanel.add(numOfOccupantsLabel);
        fullNamePanel.add(daysOfStayLabel);
        fullNamePanel.add(fullNameTextField);
        fullNamePanel.add(ageTextField);
        fullNamePanel.add(roomTypeComboBox);
        fullNamePanel.add(numOfOccupantsComboBox);
        fullNamePanel.add(daysOfStayComboBox);
        fullNamePanel.add(btn1);
        fullNamePanel.add(btn2);
        fullNamePanel.add(paymentTypeLabel);
        fullNamePanel.add(paymentCardTypeLabel);
        fullNamePanel.add(paymentCardTypeComboBox);
        fullNamePanel.add(paymentTypeComboBox);
        fullNamePanel.add(lbl1);
        fullNamePanel.add(lbl2);
        fullNamePanel.add(lbl3);
        fullNamePanel.add(lbl4);
        fullNamePanel.add(lbl5);
        //---------------jframe-----------------------------------------
        jframe.add(fullNamePanel);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    /*Code below works with the availability of the rooms and the combobox selections
    labels popup if there are no room type available*/
    //------------------------Corner case handling of room availability/ Combo box selection-------------------------
        if(singleRoomsAvailable == 0 && doubleRoomsAvailable == 0 && tripleRoomsAvailable == 0 && quadRoomsAvailable == 0){
            lbl1.setVisible(false);
            lbl2.setVisible(false);
            lbl3.setVisible(false);
            lbl4.setVisible(false);
            lbl5.setVisible(true);
            btn1.setVisible(false);
        } else if (singleRoomsAvailable == 0 && (doubleRoomsAvailable > 0 || tripleRoomsAvailable > 0 || quadRoomsAvailable > 0)){
        lbl1.setVisible(true);
        btn1.setVisible(false);

    }
        //item listener for roomTypeComboBox so options in numOfOccupantsComboBox change based on which room is selected
        roomTypeComboBox.addItemListener(new ItemListener() {
            /***
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (roomTypeComboBox.getSelectedItem().toString().equals("Double")){
                    if(doubleRoomsAvailable > 0) {
                        numOfOccupantsComboBox.removeAllItems();
                        numOfOccupantsComboBox.addItem(1);
                        numOfOccupantsComboBox.addItem(2);
                        lbl1.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        lbl2.setVisible(false);
                        btn1.setVisible(true);
                    } else if (doubleRoomsAvailable == 0 &&(singleRoomsAvailable > 0 || tripleRoomsAvailable > 0 || quadRoomsAvailable > 0)){
                        lbl1.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        lbl2.setVisible(true);
                        btn1.setVisible(false);
                    }
                } else if (roomTypeComboBox.getSelectedItem().toString().equals("Triple")){
                    if(tripleRoomsAvailable > 0) {
                        numOfOccupantsComboBox.removeAllItems();
                        numOfOccupantsComboBox.addItem(1);
                        numOfOccupantsComboBox.addItem(2);
                        numOfOccupantsComboBox.addItem(3);
                        lbl1.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        lbl2.setVisible(false);
                        btn1.setVisible(true);

                    }else if(tripleRoomsAvailable == 0 && (singleRoomsAvailable > 0 || doubleRoomsAvailable > 0 || quadRoomsAvailable > 0)){
                        lbl1.setVisible(false);
                        lbl2.setVisible(false);
                        lbl4.setVisible(false);
                        lbl3.setVisible(true);
                        btn1.setVisible(false);
                    }
                } else if (roomTypeComboBox.getSelectedItem().toString().equals("Quad")) {
                    if(quadRoomsAvailable > 0) {
                        numOfOccupantsComboBox.removeAllItems();
                        numOfOccupantsComboBox.addItem(1);
                        numOfOccupantsComboBox.addItem(2);
                        numOfOccupantsComboBox.addItem(3);
                        numOfOccupantsComboBox.addItem(4);
                        lbl1.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        lbl2.setVisible(false);
                        btn1.setVisible(true);
                    }else if(quadRoomsAvailable == 0 &&(singleRoomsAvailable > 0 || tripleRoomsAvailable > 0 || doubleRoomsAvailable > 0)){
                        lbl1.setVisible(false);
                        lbl2.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(true);
                        btn1.setVisible(false);
                    }
                } else{
                    if(singleRoomsAvailable > 0) {
                        numOfOccupantsComboBox.removeAllItems();
                        numOfOccupantsComboBox.addItem(1);
                        lbl1.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        lbl2.setVisible(false);
                        btn1.setVisible(true);
                    }else if(singleRoomsAvailable == 0 &&(doubleRoomsAvailable > 0 || tripleRoomsAvailable > 0 || quadRoomsAvailable > 0)){
                        lbl1.setVisible(true);
                        lbl2.setVisible(false);
                        lbl3.setVisible(false);
                        lbl4.setVisible(false);
                        btn1.setVisible(false);
                    }
                }
            }
        });
    }
    public static void roomTypeIncrement(String x){
        if(x.contains("Single")) {
            singleRoomsAvailable++;
        } else if(x.contains("Double")) {
            doubleRoomsAvailable++;
        } else if(x.contains("Triple")) {
            tripleRoomsAvailable++;
        } else{
            quadRoomsAvailable++;
        }
        System.out.println(singleRoomsAvailable);
        System.out.println(doubleRoomsAvailable);
        System.out.println(tripleRoomsAvailable);
        System.out.println(quadRoomsAvailable);
    }

    public static void roomTypeDecrement(String x){
        if(x.contains("Single")) {
            singleRoomsAvailable--;
        } else if(x.contains("Double")) {
            doubleRoomsAvailable--;
        } else if(x.contains("Triple")) {
            tripleRoomsAvailable--;
        } else{
            quadRoomsAvailable--;
        }
        System.out.println(singleRoomsAvailable);
        System.out.println(doubleRoomsAvailable);
        System.out.println(tripleRoomsAvailable);
        System.out.println(quadRoomsAvailable);
    }
    //-------------------toString, getters, and setters below-----------------------------------------

    /***
     *
     * @return the selected room type
     */
    public String toString(){
        return (String)roomTypeComboBox.getSelectedItem();
    }

    /***
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /***
     * the room type
     * @return
     */
    public String getRoomType() {
        return roomType;
    }

    /***
     *
     * @return the number of occupants
     */
    public int getNumOfOccupants() {
        return numOfOccupants;
    }

    /***
     *
     * @return the number of days of stay
     */
    public String getNumberOfDaysStay() {
        return numberOfDaysStay;
    }

    /***
     *
     * @return the confirmation number
     */
    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    /***
     *
     * @return the number of single rooms available
     */
    public static int getSingleRoomsAvailable() {
        return singleRoomsAvailable;
    }

    /***
     *
     * @return the number of double rooms available
     */
    public static int getDoubleRoomsAvailable() {
        return doubleRoomsAvailable;
    }

    /***
     *
     * @return the number of triple rooms available
     */
    public static int getTripleRoomsAvailable() {
        return tripleRoomsAvailable;
    }

    /***
     *
     * @return the number of quad rooms available
     */
    public static int getQuadRoomsAvailable() {
        return quadRoomsAvailable;
    }

    /***
     *
     * @return the type of card transaction
     */
    public String getPaymentCardType() {
        return paymentCardType;
    }

    /***
     *
     * @return the card company
     */
    public String getPaymentCardCompany() {
        return paymentCardCompany;
    }

    public static void setSingleRoomAvailable(int x){ //-------------------------------
        singleRoomsAvailable = x;
    }
    public static void setDoubleRoomAvailable(int x){
        doubleRoomsAvailable = x;
    }
    public static void setTripleRoomsAvailable(int x){
        tripleRoomsAvailable=x;
    }
    public static void setQuadRoomAvailable(int x){
        quadRoomsAvailable=x;
    }
    /***
     *
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /***
     *
     * @param roomType
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /***
     *
     * @param numOfOccupants
     */
    public void setNumOfOccupants(int numOfOccupants) {
        this.numOfOccupants = numOfOccupants;
    }

    /***
     *
     * @param numberOfDaysStay
     */
    public void setNumberOfDaysStay(String numberOfDaysStay) {
        this.numberOfDaysStay = numberOfDaysStay;
    }

    /***
     *
     * @param paymentCardType
     */
    public void setPaymentCardType(String paymentCardType) {
        this.paymentCardType = paymentCardType;
    }

    /***
     *
     * @param paymentCardCompany
     */
    public void setPaymentCardCompany(String paymentCardCompany) {
        this.paymentCardCompany = paymentCardCompany;
    }

    /***
     *
     * @param confirmationNumber
     */
    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    static void display(ArrayList confirmationNumbersList){
        for(int i = 0; i < confirmationNumbersList.size(); ++i){
            System.out.println(confirmationNumbersList.get(i)+" ");
            System.out.println("Size of array: " + confirmationNumbersList.size());
        }
        System.out.println();

    }

}

package eStoreSearch;

//import all pavkages
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
*@author Lauren lobo 1087364
*Program: EStoreSearch.Java
*Date: 29/11/2020
*program description: adds and searches classes of book and electronics using GUI
*/

public class EStoreSearch extends JFrame{ //define class to search objects

  // arrayLists hold values of each object added to the book and electronic classes
  private ArrayList<Product> products = new ArrayList<Product>();
  private HashMap<String,ArrayList<Integer>> index = new HashMap<String,ArrayList<Integer>>();
  Scanner scannerObj = new Scanner(System.in);
  //set constant variables fo width and height of window
  public static final int WIDTH = 900; //for main window
  public static final int HEIGHT = 700;//for main window
  public static final int SMALL_WIDTH = 200; //for confirm window
  public static final int SMALL_HEIGHT = 100;//for confirm window
  //JPanel variables to set up all panels in GUI
  private JPanel welcome_panel;
  private JPanel add_panel;
  private JPanel search_panel;
  private JPanel add_panel2;
  private JPanel root_add_panel;
  private JPanel root_search_panel;
  private JPanel search_panel2;
  //textfield variables for all user input feilds
  JTextField p_id;
  JTextField p_description;
  JTextField p_price;
  JTextField p_year;
  JTextField p_author;
  JTextField p_publisher;
  JTextField p_maker;
  JTextField s_id_text;
  JTextField s_description_text;
  JTextField s_year_start_text;
  JTextField s_year_end_text;
  //all buttons available to user
  JButton search_reset_button;
  JButton search_button;
  JButton add_button;
  JButton add_button2;
  JButton reset_button;
  //text areas for scrollable boxes
  JTextArea display;
  JTextArea search_display;
  //ever changing labels in GUI depending on adding book or electronic
  JLabel maker_label;
  JLabel author_label;
  JLabel publisher_label;
  //filename that is given from main and command line arguments
  private String filenamefinal;
  JComboBox cb; //combo box for book or electronic in add panel


  private class resetButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      p_id.setText("");
      p_description.setText("");
       p_price.setText("");
       p_year.setText("");
       p_author.setText("");
       p_publisher.setText("");
       p_maker.setText("");
       s_id_text.setText("");
       s_description_text.setText("");
       s_year_start_text.setText("");
       s_year_end_text.setText("");
    }
  } //resets all text fields
  private class addElectronicFieldsListener implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      int flag=1;
      int flag2=1;
      int flag3 =1;
      boolean valid;
      //get textField information
      String electronic_id = p_id.getText();
      String electronic_description = p_description.getText();
      String electronic_price = p_price.getText();
      String electronic_year = p_year.getText();
      String electronic_maker = p_maker.getText();
      do{
        valid =true;
        try{
          String info =null;
          Double final_price = setProductPrice(electronic_price);
          String prod_id;
          int f =0;
          //for loop checks if another product already has that ID
          if(products.size() >= 1){
           for(f=0;f<products.size();f++){ // check book class
             prod_id = products.get(f).getID();
             if (prod_id.equals(electronic_id)){
              display.append("\n");
              display.append("This product ID already exists. please try again.");
              display.append("\n");
              throw new Exception("This product ID already exists. please try again.\n ");
             }
           }
         }

          Electronics electronics = new Electronics(electronic_id,electronic_description,final_price,electronic_year,electronic_maker); //set electronics using constrcutor
          products.add(electronics); //add products to arraylist product
          display.append("\n");
          display.append("Electronic has been added");
          display.append("\n");
          display.append(electronics.toString());
          display.append("\n");
          String arrayDes[] = tolkDescription(electronics.getDescription()); //tolk the description to add hash index kay values
          int d =0;
          while(d < arrayDes.length){
            checkHash(arrayDes[d],electronics); //for the length of the description words create key values
            d++;
          }
      }catch(Exception e){
        display.append("\n");
        display.append("invalid inputs- electronic not added ");
        display.append("\n");
        valid =true;
      }
    }while(!valid);
    /*  int i =0;
        for(i=0;i<products.size();i++){
          System.out.println("\nProduct " + (i+1) +"\n");
          System.out.println(products.get(i).toString());
          System.out.println("\n");
        }
    }*/
  }
}//ActionListener adds electronic products
  private class addBookFieldsListener implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      int flag=1;
      int flag2=1;
      int flag3 =1;
      String info =null;
      boolean valid;
      //get values of information from textFields
      String book_id = p_id.getText();
      String book_description = p_description.getText();
      String book_price = p_price.getText();
      String book_year = p_year.getText();
      String book_author = p_author.getText();
      String book_publisher = p_publisher.getText();
      do{
        valid =true;
        try{
          int validlity=0;
          int f=0;
          String prod_id;
          Double final_price = setProductPrice(book_price);
          //for loop checks validity of product ID and compares to all in list
          if(products.size() >= 1){
           for(f=0;f<products.size();f++){ // check book class
             prod_id = products.get(f).getID();
             if (prod_id.equals(book_id)){
               validlity =1;
               display.append("\n");
              display.append("This product ID already exists. please try again. ");
              display.append("\n");
              throw new Exception("This product ID already exists. please try again.\n ");
             }
           }
         }
          Book book = new Book(book_id,book_description,final_price,book_year,book_author,book_publisher); //set new book using constructor
          products.add(book);  //add object to array list products
          display.append("\n");
          display.append("Book has been added");
          display.append("\n");
          display.append(book.toString());
          display.append("\n");
          String arrayDes[] = tolkDescription(book.getDescription());  //tolk the description to add hash index kay values
          int d =0;
          while(d < arrayDes.length){
              checkHash(arrayDes[d],book);  //function call to add keys to index
              d++;
          }
      }catch(Exception e){
        display.append("\n");
        display.append("invalid inputs- book not added");
        display.append("\n");
        valid =true;
      }
    }while(!valid);
    }
  }//ActionListener adds book products
  private class productComboBoxListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String choice = (String)cb.getSelectedItem();
        if(choice.equalsIgnoreCase("book")){
          //sets proper text fields,labels, and buttons for book
          publisher_label.setVisible(true);
          p_publisher.setVisible(true);
          author_label.setVisible(true);
          p_author.setVisible(true);
          maker_label.setVisible(false);
          p_maker.setVisible(false);
          add_button.setVisible(true);
          add_button2.setVisible(false);
        }else{
          //sets proper text fields,labels, and buttons for electronics
          maker_label.setVisible(true);
          p_maker.setVisible(true);
          publisher_label.setVisible(false);
          p_publisher.setVisible(false);
          author_label.setVisible(false);
          add_button.setVisible(false);
          p_author.setVisible(false);
          add_button2.setVisible(true);
        }
    }
  }//ActionListener sets up panel for add functionality
  private class addChoiceListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
      {
          welcome_panel.setVisible(false);
          root_add_panel.setVisible(true);
          root_search_panel.setVisible(false);

      }
  } //End of RedListener inner class
  private class searchChoiceListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
      {
          root_search_panel.setVisible(true);
          root_add_panel.setVisible(false);
          welcome_panel.setVisible(false);
      }
  } //End of WhiteListener inner class
  private class quitChoiceListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
      {
                writeToFile(filenamefinal);
                System.exit(0);
      }
  }//actionlistener writes to file and then exits
  private class searchButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
      {
        String ID_input = s_id_text.getText();
        String search_year_end = s_year_end_text.getText();
        String search_year_start = s_year_start_text.getText();
        String description_input = s_description_text.getText();
        String delimiters = "[ ]+";
        String[] des_value = new String[100];

        Set <Integer> arraylist = new HashSet<Integer>();
        int flag_big = 0;

        //String ID_input = getSearchId();
      //  System.out.println("Product Description (if not hit enter)\n");
      //  String description_input = scannerObj.nextLine();
        String year_Input = connectYears(search_year_start,search_year_end);
        //search_display.append("connct:"+year_Input);
        if(ID_input.equals("")){
          ID_input =null;
        }

        if (description_input.equals("")){//error checking for cases
          description_input = null;
          //display.append("des null");
        }else{//error checking for cases
          description_input = description_input.toLowerCase();
        //  display.append("des not null");
          des_value = description_input.split(delimiters);
          arraylist = checkHashDescription(des_value);
          int g= 0;

        }
        Integer strArray[] = arraylist.toArray(new Integer[arraylist.size()]);

        int i = 0;
        int empty =0;
      //  while(flag_big ==0){
      search_display.append("\n");
      search_display.append("\n");
      search_display.append("Products Found: ");
      search_display.append("\n");
      for(i=0;i<products.size() && (flag_big ==0);i++){
      //  System.out.println("\nthorugh big loop\n");
        String stringID = products.get(i).getID();
        if(ID_input != null && description_input == null && year_Input ==null){  //if only id
          if(ID_input.equals(stringID)){ //check if the input and class object are the same
            search_display.append("\n");
            printBookItems(i);
            search_display.append("\n");
          }
        }else if(ID_input == null && description_input != null && year_Input == null){  //if only description
          int ds =0;
          while(ds<arraylist.size()){
          //  int value = Integer.parseInt(strArray[ds]);
            search_display.append("\n");
            search_display.append(products.get(strArray[ds]).toString());
            search_display.append("\n");
            ds++;
          }
          flag_big =1;

        }else if(ID_input == null && description_input == null && year_Input !=null){  //if only year
          int checker = checkYearValid(year_Input);
          if (checkYearValid(year_Input) == 1 ){
                String int_val = year_Input.substring(1);
                int check = findYearsLess(int_val,i);
                if (check ==2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }

            }else if (checkYearValid(year_Input) == 2){
              String int_val2 = year_Input.substring(0,4);
              int check = findYearsMore(int_val2,i);
              if (check == 3){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
             }
           }else if ( checkYearValid(year_Input) == 3){
               String delimiters2 = "[- ]+";
               String[] int_val = new String[200]; //I CHANNGED THIS RFOM 2 TO 200
               int_val = year_Input.split(delimiters2);
               int check = findYearsBetween(int_val[0],int_val[1],i);
                if (check == 2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }
            }else if(checkYearValid(year_Input) == 0 && year_Input.matches("^[0-9]{4}$")){
              if (checkOnlyYear(year_Input,i) == 2){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
               }
             }
        }else if(ID_input != null && description_input != null && year_Input == null){  //if only id and description
          int check =0;
          check = checkDescription(des_value,i);
          int ds =0;
          while(ds<arraylist.size()){
          //  int value = Integer.parseInt(strArray[ds]);
            if(ID_input.equals(products.get(strArray[ds]).getID())){
            //  System.out.println("\nin iffffff\n");
              search_display.append(products.get(strArray[ds]).toString());
            }
            ds++;
          }
          flag_big =1;

        }else if(ID_input != null && description_input == null && year_Input !=null){  //if only id and year

          int checker = checkYearValid(year_Input);
          if ((checkYearValid(year_Input) == 1 ) && ID_input.equals(stringID)){
                String int_val = year_Input.substring(1);
                int check = findYearsLess(int_val,i);
                if (check ==2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }
            }else if ((checkYearValid(year_Input) == 2) && ID_input.equals(stringID)){
             String int_val2 = year_Input.substring(0,4);
              int check = findYearsMore(int_val2,i);
              if (check == 3){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
             }
           }else if ((checkYearValid(year_Input) == 3) && ID_input.equals(stringID)){
               String delimiters2 = "[- ]+";
               String[] int_val = new String[200]; //I CHANNGED THIS RFOM 2 TO 200
               int_val = year_Input.split(delimiters2);
               int check = findYearsBetween(int_val[0],int_val[1],i);
                if (check == 2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }
            }else if((checkYearValid(year_Input) == 0 )&& (year_Input.matches("^[0-9]{4}$")) && ID_input.equals(stringID)){
              if (checkOnlyYear(year_Input,i) == 2){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
               }
             }

        }else if(ID_input == null && description_input != null && year_Input !=null){  //if only description and year
          int check_des =0;
          check_des = checkDescription(des_value,i);
          int checker = checkYearValid(year_Input);
          if ((checkYearValid(year_Input) == 1) && (check_des==1) ){
                String int_val = year_Input.substring(1);
                int check = findYearsLess(int_val,i);
                if (check ==2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }

            }else if ((checkYearValid(year_Input) == 2) && (check_des==1)){
             String int_val2 = year_Input.substring(0,4);
              int check = findYearsMore(int_val2,i);
              if (check == 3){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
             }
           }else if ((checkYearValid(year_Input) == 3) && (check_des==1)){
               String delimiters2 = "[- ]+";
               String[] int_val = new String[200]; //I CHANNGED THIS RFOM 2 TO 200
               int_val = year_Input.split(delimiters2);
               int check = findYearsBetween(int_val[0],int_val[1],i);
                if (check == 2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }
            }else if((checkYearValid(year_Input) == 0 && year_Input.matches("^[0-9]{4}$")) && (check_des==1)){
              if (checkOnlyYear(year_Input,i) == 2){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
               }
             }

        }else if(ID_input != null && description_input != null && year_Input !=null){  //if all three feilds are valid

          int check_des =0;
          check_des = checkDescription(des_value,i);
          int checker = checkYearValid(year_Input);
          if ((checkYearValid(year_Input) == 1) && (check_des==1)&& ID_input.equals(stringID) ){
                String int_val = year_Input.substring(1);
                int check = findYearsLess(int_val,i);
                if (check ==2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }

            }else if ((checkYearValid(year_Input) == 2) && (check_des==1)&& ID_input.equals(stringID)){
             String int_val2 = year_Input.substring(0,4);
              int check = findYearsMore(int_val2,i);
              if (check == 3){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
             }
           }else if ((checkYearValid(year_Input) == 3) && (check_des==1)&& ID_input.equals(stringID)){
               String delimiters2 = "[- ]+";
               String[] int_val = new String[200]; //I CHANNGED THIS RFOM 2 TO 200
               int_val = year_Input.split(delimiters2);
               int check = findYearsBetween(int_val[0],int_val[1],i);
                if (check == 2){
                  search_display.append("\n");
                  printBookItems(i);
                  search_display.append("\n");
              }
            }else if((checkYearValid(year_Input) == 0 && year_Input.matches("^[0-9]{4}$")) && (check_des==1)&& ID_input.equals(stringID)){
              if (checkOnlyYear(year_Input,i) == 2){
                search_display.append("\n");
                printBookItems(i);
                search_display.append("\n");
               }
             }

        }else if(ID_input == null && description_input == null && year_Input ==null){  //if all fields are empty
            empty =1;
        }
      }
      if(empty == 1){  //if there are no searches input print out all objects in all classes
          int u =0;
          int f=0;
          for(u=0;u<products.size();u++){
            search_display.append("\n");
            search_display.append("Product " + (u+1) +"\n");
            search_display.append("\n");
            search_display.append(products.get(u).toString());
            search_display.append("\n");

        }
      }

      }
  }//ActionListener checks user input and performs search operations
  private class CheckOnExit extends WindowAdapter{
      public void windowClosing(WindowEvent e)
      {
          ConfirmWindow checkers = new ConfirmWindow( );
          checkers.setVisible(true);
      }
  } //End of inner class CheckOnExit
  private class ConfirmWindow extends JFrame implements ActionListener{
      public ConfirmWindow( )
      {
          setSize(SMALL_WIDTH, SMALL_HEIGHT);
          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          getContentPane( ).setBackground(Color.LIGHT_GRAY);
          setLayout(new BorderLayout( ));

          JLabel confirmLabel = new JLabel(
                         "Are you sure you want to exit?");
          add(confirmLabel, BorderLayout.CENTER);

          JPanel buttonPanel = new JPanel( );
          buttonPanel.setBackground(Color.WHITE);
          buttonPanel.setLayout(new FlowLayout( ));

          JButton exitButton = new JButton("Yes");
          exitButton.addActionListener(this);
          buttonPanel.add(exitButton);

          JButton cancelButton = new JButton("No");
          cancelButton.addActionListener(this);
          buttonPanel.add(cancelButton);

          add(buttonPanel, BorderLayout.SOUTH);
      }

      public void actionPerformed(ActionEvent e)
      {
          String actionCommand = e.getActionCommand( );

          if (actionCommand.equals("Yes")){
              writeToFile(filenamefinal);
              System.exit(0);
          }else if (actionCommand.equals("No")){
              dispose( );//Destroys only the ConfirmWindow.
          }else{
              display.append("\n");
              search_display.append("\n");
              display.append("Unexpected Error in Confirm Window.");
              search_display.append("Unexpected Error in Confirm Window.");
              display.append("\n");
              search_display.append("\n");
            }
      }
  } //End of inner class ConfirmWindow

  public EStoreSearch(String filen){
    super("eStoreSearch");
    //set filenames
    this.filenamefinal = filen;
    readFile(filenamefinal); //readfile contents

    //set up windows
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new CheckOnExit( ));
    getContentPane( ).setBackground(Color.LIGHT_GRAY);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

    //set up welcome panel
    welcome_panel = new JPanel();
    welcome_panel.setBackground(Color.LIGHT_GRAY);
    add(welcome_panel);
    welcome_panel.setLayout(new GridLayout(4,1));
    JLabel spacing = new JLabel("");
    JLabel spacing2 = new JLabel("");
    JLabel welcome_message = new JLabel("        Welcome to eStoreSearch");
    JLabel welcome_message2 = new JLabel("        Choose a command from the 'Commands' menu above for adding a product, searching products, or quitting the program.");
    welcome_message2.setFont(welcome_message2.getFont().deriveFont(Font.BOLD));
    welcome_message.setFont(welcome_message.getFont().deriveFont(Font.BOLD));
    welcome_panel.add(spacing);
    welcome_panel.add(welcome_message);
    welcome_panel.add(welcome_message2);
    //welcome_panel.add(spacing);
    welcome_panel.setVisible(true);

    //setup add panel when user clicks add in commands menu
    root_add_panel = new JPanel();
    add(root_add_panel);
    add_panel = new JPanel( );
    root_add_panel.setLayout(new GridLayout(2,1));
    root_add_panel.setVisible(false);
    JLabel product_message = new JLabel("Adding a Product");
    add_panel.setLayout(new GridLayout(0,2,10,5));
    add_panel.setBackground(Color.LIGHT_GRAY);
    add_panel.add(product_message);
    add_panel.add(spacing2);

  //set up combo box to choose product
    String[] b_e_combo_list = {"Book","Electronic"};
    JComboBox b_e_combo = new JComboBox(b_e_combo_list);
    JLabel type = new JLabel("Type:");
    add_panel.add(type);
    b_e_combo.setSelectedIndex(1);
    b_e_combo.addActionListener(new productComboBoxListener());
    add_panel.add(b_e_combo);

    //set up labels and textfields for user input to be added to products,book,electronics
    JLabel id_label = new JLabel("ProductID:");
    add_panel.add(id_label);
    p_id = new JTextField(6);
    p_id.setBounds(100,20,165,25);
    add_panel.add(p_id);

    JLabel description_label = new JLabel("Description:");
    add_panel.add(description_label);
    p_description = new JTextField(40);
    p_description.setBounds(100,20,165,25);
    add_panel.add(p_description);

    JLabel price_label = new JLabel("Price (optional):");
    add_panel.add(price_label);
    p_price = new JTextField(40);
    p_price.setBounds(100,20,165,25);
    add_panel.add(p_price);

    JLabel year_label = new JLabel("Year:");
    add_panel.add(year_label);
    p_year = new JTextField(40);
    p_year.setBounds(100,20,165,25);
    add_panel.add(p_year);

    maker_label = new JLabel("Maker (optional):");
    add_panel.add(maker_label);
    p_maker = new JTextField(40);
    p_maker.setBounds(100,20,165,25);
    add_panel.add(p_maker);

    author_label = new JLabel("Author (optional):");
    add_panel.add(author_label);
    p_author = new JTextField(40);
    p_author.setBounds(100,20,165,25);
    add_panel.add(p_author);
    author_label.setVisible(false);
    p_author.setVisible(false);
    publisher_label = new JLabel("Publisher (optional):");
    add_panel.add(publisher_label);
    p_publisher = new JTextField(40);
    p_publisher.setBounds(100,20,165,25);
    add_panel.add(p_publisher);
    publisher_label.setVisible(false);
    p_publisher.setVisible(false);

    //set up reset and add buttons for user to send in information
    //add(redPanel, BorderLayout.CENTER);
    reset_button = new JButton("Reset");
    add_panel.add(reset_button);
    reset_button.addActionListener(new resetButtonListener());
    add_button = new JButton("Add");
    add_panel.add(add_button);
    add_button.addActionListener(new addBookFieldsListener());
    add_button.setVisible(false);
    add_button2 = new JButton("Add");
    add_panel.add(add_button2);
    add_button2.addActionListener(new addElectronicFieldsListener());
    add_button2.setVisible(true);
    JLabel spaces3 = new JLabel("");
    JLabel spaces4 = new JLabel("");
    add_panel.add(spaces3);
    add_panel.add(spaces4);
    JLabel messagesInfo = new JLabel("Messages");
    add_panel.add(messagesInfo);
    root_add_panel.add(add_panel);


    //set scrollable box for displaying messages and errors to user
    add_panel2 = new JPanel( );
    root_add_panel.add(add_panel2);
    add_panel2.setLayout(new GridLayout(1,1));
    display = new JTextArea(500,500);
    display.setBackground(Color.WHITE);
    display.setEditable(false);
    JScrollPane scrolledText = new JScrollPane(display);
    scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add_panel2.add(scrolledText);

    //set up search panel for when user selects search in commands menu
    root_search_panel = new JPanel();
    add(root_search_panel);
    search_panel = new JPanel( );
    root_search_panel.setLayout(new GridLayout(2,1));
    root_search_panel.setVisible(false);
    JLabel search_message = new JLabel("Searching Products");
    search_panel.setLayout(new GridLayout(0,2,10,5));
    search_panel.setBackground(Color.LIGHT_GRAY);
    search_panel.add(search_message);
    search_panel.add(spacing);

//set up labels and textfields for user input to be searched in products,book,electronics
    JLabel id_search = new JLabel("ProductID:");
    search_panel.add(id_search);
    s_id_text = new JTextField(6);
    s_id_text.setBounds(100,20,165,25);
    search_panel.add(s_id_text);

    JLabel description_search = new JLabel("Description Keywords:");
    search_panel.add(description_search);
    s_description_text = new JTextField(40);
    s_description_text.setBounds(100,20,165,25);
    search_panel.add(s_description_text);

    JLabel start_year_search_text = new JLabel("Start Year:");
    search_panel.add(start_year_search_text);
    s_year_start_text = new JTextField(40);
    s_year_start_text.setBounds(100,20,165,25);
    search_panel.add(s_year_start_text);

    JLabel end_year_search_text = new JLabel("End Year:");
    search_panel.add(end_year_search_text);
    s_year_end_text = new JTextField(40);
    s_year_end_text.setBounds(100,20,165,25);
    search_panel.add(s_year_end_text);
    //add(redPanel, BorderLayout.CENTER);

        //set up reset and add buttons for user to send in information
    search_reset_button = new JButton("Reset");
    search_panel.add(search_reset_button);
    search_reset_button.addActionListener(new resetButtonListener());
    search_button = new JButton("Search");
    search_panel.add(search_button);
    search_button.addActionListener(new searchButtonListener());
    JLabel spaces5 = new JLabel("");
    JLabel spaces6 = new JLabel("");
    search_panel.add(spaces5);
    search_panel.add(spaces6);
    JLabel resultsInfo = new JLabel("Search Results");
    search_panel.add(resultsInfo);
    root_search_panel.add(search_panel);

    //set scrollable box and new panel for displaying messages and errors to user
    search_panel2 = new JPanel( );
    root_search_panel.add(search_panel2);
    search_panel2.setLayout(new GridLayout(1,1));
    search_display = new JTextArea(500,500);
    search_display.setBackground(Color.WHITE);
    search_display.setEditable(false);
    JScrollPane scrolledText_search = new JScrollPane(search_display);
    scrolledText_search.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrolledText_search.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    search_display.append("\n");
    search_display.append("Enter Search (fill in 1-4 of the fields above)\n");
    search_display.append("\n");
    search_panel2.add(scrolledText_search);

    //set up menu items for commands so user can choose where to go in program
    JMenu commandsMenu = new JMenu("Commands");
    JMenuItem addChoice = new JMenuItem("add");
    addChoice.addActionListener(new addChoiceListener( ));
    commandsMenu.add(addChoice);
    JMenuItem searchChoice = new JMenuItem("search");
    searchChoice.addActionListener(new searchChoiceListener( ));
    commandsMenu.add(searchChoice);
    JMenuItem quitChoice = new JMenuItem("quit");
    quitChoice.addActionListener(new quitChoiceListener( ));
    commandsMenu.add(quitChoice);
    JMenuBar bar = new JMenuBar( );
    bar.add(commandsMenu);
    setJMenuBar(bar);
  }


  /**
  method connectYears makes the concatenation of the year inputs from user
  @param start which are the inputs given by user for time frames
  @param end which are the inputs given by user for time frames
  @return the concationation of year ranges
  */

public String connectYears(String start,String end){
  String dash = "-";
  String result=null;
  if(start.equals("") && end.equals("")!=true){
    result = dash.concat(end);
  }else if(start.equals("")!=true && end.equals("")){
    result = start.concat(dash);
  }else if(start.equals("")!=true && end.equals("")!=true){
    String firstconnect = start.concat(dash);
    result=firstconnect.concat(end);
  }else{
    result=null;
  }
  return result;
}

/**
method setProductPrice checks the price input for correctness
@param inffo4 is the user input of price for product
@return the double vlaue of price
*/
  public double setProductPrice(String info4){ // method to set the price
    double prices=0;
    if (info4.isEmpty()){//error checking for cases
      prices = 0;
    }else{ //error checking for cases
      prices =Double.parseDouble(info4); // cast to double to display as a prices
    }
    return prices;
  }

/**
method tolkDescription returns a string array of the description input
@param search should be the description sentence of product
@return is the value of the array with the newly tolkenized words
*/
  public String[] tolkDescription(String search){
    String delimiters = "[ ]+";
    search = search.toLowerCase();  //make all words lowercase for easier search
    String[] key = search.split(delimiters);  //split description where there is spaces
    return key;
  }
  /**
  method checkHash adds the tolkenized search words as keys in a hashMap for index
  @param search should be each description word of product
  @param book is the object value of book or electronic
  @return is the value of j
  */
   public int checkHash(String search, Object book){ // mmethod to check for same values in description of object
     int j = 0;
     int i = products.indexOf(book);
     //search = toLowerCase(search);
     if (!index.containsKey(search)){
       ArrayList<Integer>value = new ArrayList<Integer>();  //add values to a new array list
       value.add(i); //add value of index
       index.put(search,value);
    }else if (index.containsKey(search)){ //if key already exists update it

       ArrayList<Integer> newList = new ArrayList<Integer>(); //replace with new list
       newList= index.get(search);
       newList.add(i);
       index.put(search,newList);
     }
     //System.out.println(index);
       return j;
   }


 /**
 method readFile reads an input file and adds the objects in file to products list
 @param filename should be the name of the file you want to load from

 */
 public void readFile(String filename){
    BufferedReader reader = null;  //buffered reader to read file line by line
    try{
       File f = new File(filename);
        //reader = new BufferedReader(new FileReader(filename));
        if (f.createNewFile()) {
        System.out.println("File created: " + f.getName());
      } else {
        System.out.println("File already exists.");
      }
        if (f.length() == 0) {  //check if contents of file is empty
          System.out.println("File is empty\n");
        }
          reader = new BufferedReader(new FileReader(filename));  //buffered rea to read one line at a time
        String contentLine = reader.readLine();
        int i=0;
        while (contentLine != null){
          //GraduateStudent gradstudent = new GraduateStudent();
           String words[] = contentLine.split("= ");
        //   System.out.println("line"+i+ "; "+contentLine);
           String contents = words[1].replace("\"", "");
           String category = words[0].replace(" ", "");

           if(category.equalsIgnoreCase("type")&& contents.equalsIgnoreCase("book")){
             int j=0;
             String array[] = new String[6];
             while(j<6){
               contentLine = reader.readLine(); //increment line
               String words2[] = contentLine.split("= ");
              //System.out.println("\nThis sentence;"+contentLine +"end\n");
               String line2 = words2[1].replace("\"", "");
           //    System.out.println("\nThis sentence;"+line2 +"end\n");
               array[j] = line2;
               j++;
             }
             double price = Double.parseDouble(array[2]);
             Book book = new Book(array[0],array[1],price,array[3],array[4],array[5]);
             products.add(book);
            // ArrayList<Integer>value = new ArrayList<Integer>();
             String arrayDes[] = tolkDescription(book.getDescription());
             int d =0;
             while(d < arrayDes.length){
               checkHash(arrayDes[d],book);
               d++;
             }

           }if(category.equalsIgnoreCase("type")&& contents.equalsIgnoreCase("electronics")){
             int h=0;
             String array[] = new String[5];
             while(h<5){
               contentLine = reader.readLine(); //increment line
               String words2[] = contentLine.split("= ");
             //  System.out.println("\nThis sentence;"+contentLine +"end\n");
               String line2 = words2[1].replace("\"", "");
           //    System.out.println("\nThis sentence;"+line2 +"end\n");
               array[h] = line2;
               h++;
             }
             double price = Double.parseDouble(array[2]);
             Electronics elec = new Electronics(array[0],array[1],price,array[3],array[4]);
             products.add(elec);
             String arrayDes[] = tolkDescription(elec.getDescription());
             int d =0;
             while(d < arrayDes.length){
               checkHash(arrayDes[d],elec);
               d++;
             }
           }
           contentLine = reader.readLine(); //increment line
           i++;
        }
         //System.out.println(index);
   }catch(Exception e){
     System.out.println("File doesnt exist/Error opening the file "+ filename +"\n");
     System.exit(0);
   }
 }



  /**
  method writeToFile writes to an output file and saves the objectsfrom products ArrayList to teh file
  @param filename should be the name of the file you want to load from

  */

public void writeToFile(String filename_output){

  try{
    //System.out.println(" Error writing to the file "+ filename_output +"\n");
    PrintWriter outputStream=new PrintWriter(filename_output, "UTF-8");
    int sizelist = products.size();
    int i = 0;
    Book book = new Book();
    Electronics elecs = new Electronics();
    for( i = 0; i < sizelist; i++){
        if(products.get(i).getClass() == book.getClass()){
          outputStream.println("type = \"book\"");
          outputStream.println(products.get(i).toString());
        }if(products.get(i).getClass() == elecs.getClass()){
          outputStream.println("type = \"electronics\"");
          outputStream.println(products.get(i).toString());
        }

    }
       outputStream.close();
  }catch(Exception e){
    display.setText(" Error writing to the file "+ filename_output +"\n");
    System.exit(0);
  }
}

/**
method printBookItems prints the values of the products
@param i which tells what to print from product list

*/
public void printBookItems(int i){  //method to print class object
    search_display.append("\n");
    search_display.append("\n");
    search_display.append("Product "+(i+1));
    search_display.append("\n");
    search_display.append(products.get(i).toString());
    search_display.append("\n");
    search_display.append("\n");
}



 /**
 method checkDescription checks for a match in description words of products and search words
 @param search should be the list of description words that the user input
 @param a is the index of product being checked in array list or products
 @return is the index of the found word
 */
public int checkDescription(String[] search,int a){ // mmethod to check for same values in description of object
  int i =0;
  int j=0;
  int index=0;
  String delimiters = "[ ]+";
  String str_des = products.get(a).getDescription();
  str_des = str_des.toLowerCase();
  String[] book_val = str_des.split(delimiters);
  for(i=0;i<book_val.length;i++){
    for(j=0;j<search.length;j++){
      if(search[j].equalsIgnoreCase(book_val[i])){
        index=1;
      }
    }
  }
    return index;
}



 /**
 method checkHashDescription firstfinds the values of all the description words if there is a key for import junit.framework.TestCase;
 it then checks for the intersection of words in all the products and returns the intersection set
 @param search should be the list of description words that the user input
 @return is the list of intersected values of words in products
 */

public Set checkHashDescription(String[] search){ // mmethod to check for same values in description of object
  Set<HashMap.Entry<String,ArrayList<Integer>>> entries = index.entrySet();
  Iterator<HashMap.Entry<String,ArrayList<Integer>>> iter = entries.iterator();  //set iterator lists to iterate through hashmap
  ArrayList<ArrayList<Integer>> intList = new ArrayList<ArrayList<Integer>>(); //new arrayList to replace old lists in hashmap
  Set<Integer> intersection = new HashSet<Integer>();  //set that will hold the intersection values
  int i=0;
  while(i<search.length){
    while( iter.hasNext()){  //gets the values (the intger lists for all key values)
      HashMap.Entry<String,ArrayList<Integer>> entry = iter.next();
      if(search[i].equals(entry.getKey())){
        intList.add(entry.getValue());
      }
    }
    i++;
     iter = entries.iterator();
  }
  int k=0;
  if((intList.size()-1)==0){
    intersection.addAll(intList.get(k));
  }
  while(k<(intList.size()-1)){ //only got til size-1 to avoid outofbounds error
    intList.get(k).retainAll(intList.get(k+1));  //retain all same values
    //System.out.println("Intersection Lists:" +intList.get(k));
    intersection.addAll(intList.get(k));
  //  System.out.println("Intersection Lists:" +intersection);
    k++;
  }
  //System.out.println("Intersection Lists:" +intersection);
  return intersection;
}

/**
method findYearsLess finds the products that fit the correct time range
@param input is the value to be compared with product year
@param i is the index in arrays list products
@return the index of arraylist
*/
public int findYearsLess(String input,int i){ //method to compare values of year for book class
  String string_year = products.get(i).getYear();
  int num_year =Integer.parseInt(string_year);
  int num_input =Integer.parseInt(input);
  int count =0;
  if(num_year <= num_input){
    count = 2;
  }
  return count;
}
/**
method findYearsMore finds the products that fit the correct time range
@param input is the value to be compared with product year
@param i is the index in arrays list products
@return the index of arraylist
*/

public int findYearsMore(String input, int i){ //method to compare values of year for book class
  String string_year = products.get(i).getYear();
  int num_year =Integer.parseInt(string_year);
  int num_input =Integer.parseInt(input);
  int count =0;
  if(num_year >= num_input){
    count = 3;
  }
  return count;
}


/**
method findYearsBetween finds the products that fit the correct time range
@param input1 is the value to be compared with product year
@param input2 is the value to be compared with product year
@param i is the index in arrays list products
@return the index of arraylist
*/

public int findYearsBetween(String input1, String input2,int i){ //method to compare values of year for book class
  String string_year = products.get(i).getYear();
  int num_year =Integer.parseInt(string_year);
  int num_input1 =Integer.parseInt(input1);
  int num_input2 =Integer.parseInt(input2);
  int count =0;
  if(num_year >= num_input1 && num_year <= num_input2){
    count = 2;

  }
  return count;
}

/**
method checkYearValid that checks the string to find ranges
@param input is the value of the string of years
@return the index of arraylist
*/

public int checkYearValid(String input){
  int check=0;
  if(input.length() > 4){
      if(input.charAt(0) == '-'){  // ex -2005
        check = 1;

      }if((input.charAt(4)== '-')&& (input.length() == 9) ){ // ex 2005-2010
        check =3;
      } if(input.charAt(4)== '-' && input.length() == 5){ // ex 2005-
        check =2;
      }
    }
  return check;
}

/**
method checkOnlyYear finds the products that fit the correct time range
@param input is the value to be compared with product year when its only one year
@param i is the index in arrays list products
@return the index of arraylist
*/
public int checkOnlyYear(String input,int i){ //method to check a single year input search for book class
  String string_year = products.get(i).getYear();
  int num_year =Integer.parseInt(string_year);
  int num_input =Integer.parseInt(input);
  int count=1;
  if(num_year==num_input){ //ex 2010
    count=2;
  }else{
    count=0;
  }
  return count;
}


}

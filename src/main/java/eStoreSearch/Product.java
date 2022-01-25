package eStoreSearch;
import java.util.Scanner;
import java.util.ArrayList;

/**
*@author Lauren lobo 1087364
*Program: Product.Java
*Date: Date: 29/11/2020
*program description: controls Electronics class and objects
*/

/*set attribute variables of Product class*/
abstract class Product{
  private String id_num;
  private String description;
  private double price;
  private String year;
  private String author;
  private final int END_YEAR= 9999;
  private final int START_YEAR= 1000;


  public Product(){ //empty constructor to override next one

  }
  public Product(String id, String desc, double p, String y) throws Exception{ //constructor that takes values to build object
    int yearVal= Integer.parseInt(y);
    if((yearVal <= START_YEAR || yearVal >= END_YEAR) || y.matches("^[0-9]{4}$")!= true){
      throw new Exception("That is an invalid year please try again.\n");
    }
    if(desc.equals("")){
      throw new Exception("No Description added\n ");
    }
    if(id.matches("^[0-9]{6}$")!=true||id.equals("")) {//error checking for cases if all are numbers and 6 digits
      throw new Exception("That is an invalid ID please try again.\n ");
    }
    this.id_num = id;
    this.description = desc;
    this.price = p;
    this.year = y;
  }


  public String toString(){

    return (("productId = \""+id_num+"\"\n")+("description = \""+description+"\"\n") + ("price = \""+price+"\"\n")+("year = \""+year+"\"\n"));
  }

  public void setID(String id_num){
    if(id_num.matches("^[0-9]{6}$")!=true||id_num.isEmpty()==true) {
      System.out.println("invalid id number\n");
    }else{
        this.id_num = id_num;
    }
  }

  public void setYear(String year){
    int yearVal= Integer.parseInt(year);
    if((yearVal <= START_YEAR || yearVal >= END_YEAR) || year.matches("^[0-9]{4}$")!= true){
      System.out.println("That is an invalid year please try again.\n");
    }else{
    this.year = year;
  }
  }

  //getters for products
  public String getDescription(){
    return description;
  }
  public String getID(){
    return id_num;
  }

  public double getPrice(){
    return price;
  }

  public String getYear(){
    return year;
  }

  public void getAuthor(String author){
    this.author = author;
  }
  public String getThisAuthor(){
    return author;
  }


//setters for products

  public void setDescription(String description){
    this.description = description;
  }

  public void setPrice(double price){
    this.price = price;
  }



}

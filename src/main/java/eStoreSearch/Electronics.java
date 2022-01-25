package eStoreSearch;
import java.util.Scanner;
import java.util.ArrayList;

/**
*@author Lauren lobo 1087364
*Program: Electronics.Java
*Date: Date: 29/11/2020
*program description: controls Electronics class and objects
*/




/*set attribute variables of electronics class*/
public class Electronics extends Product{
  private String maker;

  public Electronics(){

  }

  public Electronics (Electronics elec){
    if (elec == null) {
        System.out.println("Fatal Error.");
        System.exit(0);
        }
  //  super(id,desc,price,year);
    maker = elec.maker;

  }
  public Electronics(String id,String desc,double price, String year,String m) throws Exception{
    super(id,desc,price,year);
    if(m.equals("")){
      this.maker = "no maker available";
    }else{
      this.maker = m;
    }
  }
  public Electronics(String id,String desc,double price, String year) throws Exception{
    super(id,desc,price,year);
  }
/*
  public String toString(){
    return (("productId = \""+getID()+"\"\n")+("description = \""+getDescription()+"\"\n") + ("price = \""+getPrice()+"\"\n")+("year = \""+getYear()+"\"\n")+("maker = \""+maker+"\""));
  }*/
  public String toString(){
    return (super.toString() +("maker = \""+maker+"\""));
  }
  // getters for electronics class
  public String getDescription(){
    return super.getDescription();
  }
  public String getID(){
    return super.getID();
  }

  public double getPrice(){
    return super.getPrice();
  }

  public String getYear(){
    return super.getYear();
  }

  public String getMaker(){
    return maker;
  }

//setters for attributes found only in electronics class
  public void setMaker(String maker){
    this.maker = maker;
  }


}

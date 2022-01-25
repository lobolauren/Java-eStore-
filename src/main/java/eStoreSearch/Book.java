package eStoreSearch;
import java.util.Scanner;
import java.util.ArrayList;

/**
*@author Lauren lobo 1087364
*Program: Book.Java
*Date: Date: 29/11/2020
*program description: controls book class and objects
*/


public class Book extends Product{

  private String author;
  private String publisher;

  public Book(){

  }
  public Book (Book book){
    if (book == null) {
        System.out.println("Fatal Error.");
        System.exit(0);
        }
  //  super(id,desc,price,year);
    author = book.author;
    publisher = book.publisher;
  }
  public Book(String id,String desc,double price, String year,String a, String p) throws Exception{
    super(id,desc,price,year);
    if(a.equals("")){
      this.author = "no author available";
    }else{
      this.author = a;
    }
    if(p.equals("")){
      this.publisher = "no publisher available";
    }else{
      this.publisher = p;
    }

  }
  public Book(String id,String desc,double price, String year) throws Exception{
    super(id,desc,price,year);
  }
  public String toString(){
    return (super.toString()+("authors = \""+author+"\"\n")+("publisher = \""+publisher+"\""));
  }
// getters for book class
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

  public String getAuthor(){
    //return new Book(author);
    return author;
  }

  public String getPublisher(){
  //  return new Book(publisher);
  return publisher;
  }

//setters for attributes found only in books class
  public void setAuthor(String author){
    this.author = author; //passes the value given to the super class to set object
    super.getAuthor(this.author);
  }

  public void setPublisher(String publisher){
    this.publisher = publisher;
  }



}

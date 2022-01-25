Author: Lauren Lobo
Date: 30/11/2020
Programs: in package "eStoreSearch"
  EStoreSearch.java (class)
  Product.java(class)
  Book.java(class)
  Electronics.java(Class)
  App.java (main)

***************IMPORTANT********************
    -when the code first compiles a warning will appear "java uses unchecked or unsafe operations."
    the code works fine and runs as it should however due to time constrains I could not find the
    root of this warning.

files for JUnit testing included: no files as there was no JUnit testing required for this assignment
-however the file 'productfile.txt' is available to load in some products for easier use

Program Description: This program should take user input to add objects of
  books and electronics to the Product Class. The user can then search
  through these products in their classes to obtain items that meet their
  request. Each case of search must meet the requirments of product ID,
  description, and year range.This is now done through a GUI swing/awt java inferface.
  It is a user friendly program designed to allow products to be added and searched.

Assumptions:

  -text file has all attributes for book and product
  -**IMPORTANT**: if text file is missing attributes code will fail. When it gets
  saved to the file the products with no author for example are still listed but
   listed as "no author available"

How to run: run using a gradle build method
  -on windows:
    -locate the lobol_A3 directory
    -then use command: gradle build
    -run the command: gradlew run --args='<filename>'
    -this should compile and execute program

How the program was tested for correctness: TEST PLAN
  - there are 8 cases for search test: ID only, Description only, year range
  only, ID and description, ID and year, description and year, ID and description
  and year, and if the user inputs nothing for any search requirments
 -main command loop takes into account user input of words add,a,search,s,quit,q
  ignoring the case.
 -for book or electronic input it takes into account input of book,b,books,electronic
 electronics,e
 -for product ID takes into a count if the id matches 6 numbers between 0-9 and year
 for matches of 4 numbers between 0-9
  -to search an element on the list: the item is not on teh list, item is at start or
  end of list, intem is somewhere in the middle of list
  -to search description HashSet in method checks if the description search is only
  one word or multiple to avoid out of bound errors. as well as checks that if it
  has more than one search word it only goes until the second last list to avoid out of
  bounds errors.
  -test Book Class for validity of ID, Year, Description, price, author, and
  publisher using JUnit
  -test Electronics Class for validity of ID, Year, Description, price, and maker
  using JUnit
  -tests EStoreSearch for the year validity method
  -tests EStoreSearch for description, author, publisher, maker validilty
  -tests adding new products by using setters and getters in book and electronic
   classes

Improvements:
  -program does load,add,update index HashMap and finds the intersection of words however
        -code uses HashMaps (new A2 implementation) for search when a user enters a product ID and description
         and just description
        -if user enters anything with a year and description the program uses the sequential
        search method (from A1)
        -if given more time I could have figured out how to make it work with year inputs as well
  -some error checking could have been improved upon

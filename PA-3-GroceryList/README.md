# CS 212 - Programming Assignment 3  
Design Due: 4/2 
Final Due: 4/8


## Summary
For this assignment you will use your choice of data structure in a class. You will use try and catch to handle exceptions. You will also practice writing to a file.

## Description
Your friends are preparing for a party and need to go grocery shopping. Because keeping a grocery list on paper is not ideal, they ask you to develop an application to manage the grocery list. To simplify the problem, they would only put the name of the grocery item and the needed quantities in the list.

## Requirements
For this assignment, you will create a class that encapsulates the concept of a grocery list as specified in the description. It is up to you how to design and implement the **GrocceryList** class. However, it should sopport the the following operations:

1. The user can add item/quantity to the list.
2. The user can print the list with (item and quantity)
3. The user can print the list with just items.
4. The user can increase/decrease the quantity of an item.
5. The user can delete an item from the list.
6. The user can delete everything in the list and start over.
7. The user can save the list to a file for printing.
8. The user can load a saved list, erasing the current list
9. The user can load a saved list, appending to to the current list. 


You will also write a client class (Main) to use the **GrocceryList** class. The main() program should keep presenting the user with a menu of choices about what they can do with the list. One of the options from the menu should be to exit the application.

Your Main should be able to handle bad user input gracefully.


## Submission

### Design (15 points)
Due: 4/2  
(no need to write any code)

To GitHub:  
* *design.txt* giving a design of your **GrocceryList** class, including:  
    * data type of the instance variables
    * return type and list of parameters(if any) for the constructor(s) and methods.
    * the **algorithm** for at least **3 methods of your choosing**

### Final (45 points)
Due: 4/8  
To GitHub:
	* Updated *design.txt*  
	* *testGroceries.txt* with a GroceryList that you have saved/loaded to test your program  
	* All .java files  
To Moodle
* To Moodle
    * A Link to your GitHub Repo
    * A individual reflection (no word limit) typed inside the moodle submission box using the following format and addressing the following questions:

        * 1. Objective: What were you supposed to learn/accomplish?
        * 2. Procedure: What steps were followed and what techniques did you use to solve the problem? What were the Key concepts explored?
        * 3. Results: What was the program's outcome? Was it unexpected? 
        * 4. Reflection:
          	5. How did you feel about your choice of data structure?
          	6. How did that impact your design?
          	7. What challenges did you encounter?
          	8. Did you overcome them, and how?
          	9. Any key takeaways?
          	10. Do you think you learned what you were supposed to learn for this PA?
          	11. What would you do differently, given what you know now?


Reminders: 
* You should **NOT** commit any extranious files (the out directory, the .idea directory, etc.). 
* You **SHOULD** use proper comments and white space including JavaDoc and header comments
* You should commit your work often and use meaningful commit messages 







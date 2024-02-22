# CS 212 Java Cardio

##### Feb 20, 2024 

*****
## 1. Array Of Grades
Create a program that takes in:

 - The name of 1 student
 - Name of 1 class student is taking
 - 5 grades for one of his/her classes, **stored in an array**

and prints out the following:
 - Student Name
 - Name of Class
 - average grade of student in the class
 - letter grade for the student - A, B, C, D, F.
 - Include error checking to be sure that the grades entered are within 0-100
 - Remember to store the grades in an Array.  


***


## 2. Mortgage Calculator

Create a program that calculates Mortgages for homebuyers. This program takes in the following:

 - Principal
 - Annual Interest rate in %
 - Years to own the home.

and prints out the following:
 - the monthly payment (mortgage) for the homebuyer.  

**Hint:**
 - The Interest rate used in the calculation `(r)` should be gotten by first converting the Annual Interest rate to a monthly rate by dividing by 12, then to a fraction by dividing by 100.
 - The number of payments `(n)` can be gotten by multiplying the years to own the home by number of months in a year.  


Formula to calculate the mortgage – SCROLL DOWN to Method 2: <https://wikihow.com/Calculate-Mortgage-Payments/>  
***
![Formula for Mortgage](https://i.ibb.co/VjPQVsC/Formula-for-Mortgage.png)


***­


## CLASSES

## 3. 

### Part 1: Car Class 

Define a Java class called Car with the following attributes:

- brand (String): The brand of the car.
- model (String): The model of the car.
- fuelLevel (double): The current fuel level in the car.

Include the following methods:

1. A constructor to initialize the car with a brand, model, and starting fuel level.
2. A drive method that simulates driving the car, decreasing the fuel level.
3. A refuel method that simulates refueling the car, increasing the fuel level.
4. A displayInfo method to display the car's information (brand, model, current fuel level).

### Part 2: Garage Class 
Create a Java class called Garage with the following:

### In the main method:

1. Instantiate two Car objects with different brands, models, and starting fuel levels.
2. Display the information of both cars.
3. Simulate driving each car.
4. Simulate refueling one of the cars.
5. Display the updated information of both cars.

### Instructions:

Write the code for the Car and Garage classes. Demonstrate the functionality by:

- 1. Creating instances of cars
  2. Displaying the initial information
  3. Driving
  4. Refueling
  5. Displaying the **updated** information.


***


## 4.  

### Part 1: MusicPlayer Class
Define a Java class called MusicPlayer with the following attributes:

- brand (String): The brand of the music player.
- currentSong (String): The currently playing song.
- volume (int): The current volume level of the music player.

Include the following methods:

1. A constructor to initialize the music player with a brand and set the default volume to 50.
2. A playSong method that simulates playing a song, updating the current song.
3. A adjustVolume method that simulates adjusting the volume, allowing for both increase and decrease.
4. A displayInfo method to display the music player's information (brand, current song, volume).

### Part 2: MusicStore Class**  
Create a Java class called MusicStore with the following:

In the main method:

- Instantiate two MusicPlayer objects with different brands.
- Display the information of both music players.
- Simulate playing a song and adjusting the volume for each player.
- Display the updated information of both music players.

### Instructions:

Write the code for the MusicPlayer and MusicStore classes.

Demonstrate the functionality by:

1. creating instances of music players
2. displaying their information
3. playing songs
4. adjusting volume
5. displaying the updated information.

***

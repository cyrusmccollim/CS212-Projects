# Lab3: Ski Jump

	
## Problem: 
Winter is coming! One winter sport is the ski jump, where the score is determined by the distance traveled after skiing down a ramp and into the air. What type of speed does a ski jumper need to achieve on the ramp to make a good distance on their jump?  Let’s make a program to calculate the distance traveled based on speed and determine how many points they’d receive if they went that distance.
[See an image of ski jump](https://i0.wp.com/i.ytimg.com/vi/nIH01DgMOnI/maxresdefault.jpg) and [up coming competitions](https://www.fis-ski.com/DB/alpine-skiing/calendar-results.html?categorycode=WC&disciplinecode=&eventselection=&gendercode=&nationcode=&place=&racecodex=&racedate=&saveselection=-1&seasoncode=2022&seasonmonth=X-2022&seasonselection=&sectorcode=AL).

## Purpose: 
This lab gives you practice with:
* Using the math module  
* Using decision making in your code  
* Follow usability rules for your input and output  
* Drawing a flowchart & finding control paths  

## Details:
Given the type of ski jump (normal vs. large) and the jumper’s speed at the end of the ramp, predict how far they will jump using the simplification in the table below. Then output their number of points earned.

|Hill Type	| Height	| Points per meter	| Par (distance)|
|-----------|-----------|-------------------|---------------|
| Normal	| 46	    | 2	                | 90            |
| Large	    | 70	    | 1.8	            | 120           |

To simplify the calculation, the time in the air is calculated as **sqrt((2*height)/9.8)** . The distance traveled is the `jumper’s speed * time in the air`.

After determining their distance, calculate how many points they would get on the chosen hill. Their points depend on whether or not their distance is above par (good) or below or equal to par (bad). Calculate the points earned as `60 + (distance - par)*points_per_meter`.

Tell the jumper "Great job for doing better than par!" when their points are at least 61, "What happened??" if their points are less than 10, or “Sorry you didn’t go very far” otherwise. Don’t forget to output their distance and points so they know how well they did! 

## Steps:
1. Understand the problem you are trying to solve. Do you understand the input, desired output, and when a decision needs to be made?
2. Write your algorithm in algorithm.txt to solve this entire problem. Remember to write decisions correctly – refer to your notes as necessary. This is likely the hardest step of the lab -- be careful about the order of steps so that you aren't doing the same thing more times than necessary. The lab instructor must approve your algorithm before you code. 
3. Write your code in main.py. Recall that you can compare strings like "normal" and "large" for equality in the same way you can compare numerical types.
4. Make sure you follow the guidelines for well-formatted and readable code with comments.
5. Make sure you add the intro comments.
6. Draw a flowchart of your code on paper.
7. Label the control paths in your flowchart.
8. List the test cases based on your control paths in testcases.xlsx, using boundary values.
9. Use the test cases to make sure your program works correctly, and fix it if it doesn’t. Don’t just assume you did it right, there are many things that could have gone wrong. Test EVERY control path. You may find it helpful to test with additional inputs as well if you didn’t pick various special cases for your input values.

## Reminder of Intro Comments Style

  ```
  # Programmers:  
  # Course:  
  # Due Date: 
  # Lab Assignment: 
  # Problem Statement:  
  # Data In:
  # Data Out:  
  # Credits: 
  ```



import java.util.*;
public class StudentGrade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = input.next();

        double[] grades = new double[5];

        for (int i = 0; i < grades.length; i++){
            System.out.println("Grade #" + (i + 1) + "?" );
            String gradeInput = input.next();
            while (!isValidGrade(gradeInput)){
                System.out.println("Error. Try again: ");
                gradeInput = input.next();
            }
            grades[i] = Double.parseDouble(gradeInput);
        }

        double finalGrade = 0.0;
        for (double grade : grades)
            finalGrade += grade / grades.length;

        String letterGrade;
        if (finalGrade < 60) letterGrade = "F";
        else if (finalGrade < 70) letterGrade = "D";
        else if (finalGrade < 80) letterGrade = "C";
        else if (finalGrade < 90) letterGrade = "B";
        else letterGrade = "A";

        System.out.println("Name: " + name + "Grade: " + finalGrade + " | Letter Grade: " + letterGrade);
    }
    
    public static boolean isValidGrade(String str){
        try {
            double grade = Double.parseDouble(str);
            return grade >= 0 && grade <= 100;
        } catch (Exception e){
            return false;
        }
    }
}

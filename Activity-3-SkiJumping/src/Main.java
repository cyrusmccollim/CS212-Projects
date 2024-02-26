public class Main {
    public static void main(String[] args) {
        String hillTypeInput;
        Hill hillType;
        double jumperSpeed;

        new Hill("normal", 46.0, 2.0, 90.0);
        new Hill("large", 70.0, 1.8, 120.0);
        new Hill("epic", 129.0, 1.4, 180.0);

        System.out.println("\nAvailable hill types: " + Hill.getHillTypes());

        // Inputs the type of hill, includes error checking.
        System.out.println("\nWhat's the type of hill?");
        hillTypeInput = Hill.inputValidHillType();
        hillType = Hill.HILLS.get(hillTypeInput);

        // Inputs the jumper's speed, includes crash prevention.
        System.out.println("\nWhat's the jumper's speed?");
        jumperSpeed = Utilities.inputDouble();

        // Creates object that holds the type of hill and jumper's speed.
        SkiJump jumper = new SkiJump(hillType, jumperSpeed);

        // Calls the getResult method to calculate and return the jumper's score.
        System.out.println(jumper.result());
    }
}

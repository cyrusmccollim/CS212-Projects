import java.util.*;
public class PopulationCalculator {
    public static void main(String[] args){

        final int SECONDS_IN_YEAR = 60 * 60 * 24 * 365;

        Scanner input = new Scanner(System.in);

        System.out.println("How many seconds between births?");
        int births = SECONDS_IN_YEAR / input.nextInt();

        System.out.println("How many seconds between deaths?");
        int deaths = SECONDS_IN_YEAR / input.nextInt();

        System.out.println("How many seconds between immigrations?");
        int immigrations = SECONDS_IN_YEAR / input.nextInt();

        System.out.println("Current population?");
        int currentPopulation = input.nextInt();

        System.out.println("How many years in the future?");
        int yearsElapsed = input.nextInt();

        int finalPopulation = currentPopulation + (yearsElapsed * (births + immigrations - deaths));

        String populationChange;
        if (finalPopulation > currentPopulation)
            populationChange = "increased to";
        else if (finalPopulation == currentPopulation)
            populationChange = "remained as";
        else
            populationChange = "decreased to";

        System.out.println("The population in " + yearsElapsed + " years " + populationChange + " " + finalPopulation + "!");
    }
}

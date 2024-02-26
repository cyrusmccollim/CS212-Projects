import java.util.*;

public class Hill {
  public static HashMap<String, Hill> HILLS = new HashMap<>();
  private final double height, pointsPerMeter, par;

  public static String inputValidHillType(){
    Scanner input = new Scanner(System.in);

    System.out.print("\tEnter response: ");
    String hillType = input.nextLine().strip().toLowerCase();

    while (!HILLS.containsKey(hillType)){
      System.out.print("\tInvalid entry. Try again: ");
      hillType = input.nextLine().strip().toLowerCase();
    }

    return hillType;
  }

  public static List<String> getHillTypes(){
    List<String> hillTypes = new ArrayList<>();
    for (Map.Entry<String, Hill> hill : HILLS.entrySet()){
      hillTypes.add(hill.getKey());
    }
    return hillTypes;
  }

  public Hill(String name, double height, double pointsPerMeter, double par) {
    this.height = height;
    this.pointsPerMeter = pointsPerMeter;
    this.par = par;
    HILLS.put(name, this);
  }

  public double getHeight(){
    return height;
  }

  public double getPointsPerMeter(){
    return pointsPerMeter;
  }

  public double getPar(){
    return par;
  }
}

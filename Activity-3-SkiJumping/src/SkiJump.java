public class SkiJump {
  private final Hill hill;
  private final double jumpSpeed;

  public SkiJump(Hill hill, double jumpSpeed){
    this.hill = hill;
    this.jumpSpeed = jumpSpeed;
  }
  
  public double calculatePoints(){
    return 60 + ((jumpSpeed * Math.sqrt((2 * hill.getHeight()) / 9.8) - hill.getPar()) * hill.getPointsPerMeter());
  }

  public String result(){
    String result = "\nThe results are in. ";

    double points = calculatePoints();
    if (points >= 61)      result += "Great job for doing better than par!";
    else if (points < 10)  result += "What happened?";
    else                   result += "Sorry, you did not go very far.";

    return result;
  }
}

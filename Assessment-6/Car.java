public class Car {
    private final String brand, model;
    private double fuelLevel;

    public Car(String brand, String model, double fuelLevel){
        this.brand = brand;
        this.model = model;
        this.fuelLevel = fuelLevel;
    }

    public void drive(int milesDriven){
        double fuelLost = (double) milesDriven / 30;
        System.out.println(brand + " " + model + " drove " + milesDriven + " miles, losing " + Math.round(fuelLost) + " gallons.");
        fuelLevel -= (fuelLevel - fuelLost >= 0)?
                     fuelLost: fuelLevel;
    }

    public void refuel(double gallonsAdded){
        System.out.println(brand + " " + model + " refueled " + Math.round(gallonsAdded) + " gallons.");
        fuelLevel += (fuelLevel + gallonsAdded <= 12)?
                     gallonsAdded: 0;
    }

    public void displayInfo() {
        System.out.println("Car: " + brand + " " + model + " | Current Fuel: " + Math.round(fuelLevel));
    }
}

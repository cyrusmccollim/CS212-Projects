import java.util.*;

public class Garage {
    public static ArrayList<Car> cars = new ArrayList<>();

    public static void main(String[] args){
        cars.add(new Car("Nissan", "Altima", 12));
        cars.add(new Car("Ford", "Mustang", 11));
        cars.add(new Car("Ferrari", "Roma", 7));

        for (Car car : cars){
            car.displayInfo();
        }

        for (Car car : cars) {
            int randomMileage = (int) (Math.random() * 360);
            double randomRefuel = Math.random() * 12;
            car.drive(randomMileage);
            car.refuel(randomRefuel);
        }

        for (Car car : cars){
            car.displayInfo();
        }
    }

}

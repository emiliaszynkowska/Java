/* Main class
 */
public class Main {

    public static void main(String[] args) {
        
/* Create a new Garage, this stores an ArrayList of all the transport and information
 * Create train1, plane1, car1, bike1 and tricycle1
 * Add values and actions for the Transport
 * Add Transport to the Garage
 * Print out information for each Transport
 */
        Garage garage = new Garage();
        Train train1 = new Train("Train 1", 50, 8, 40, 2, "District", "Brian", "TFL");
        JetPlane plane1 = new JetPlane("Plane 1", 500, 3, 200, 2000, "Daniela", "Boeing");
        Car car1 = new Car("Car 1", "Automatic", "Motorway", 60, 4, 5, "Andrew", "Audi");
        Bicycle bike1 = new Bicycle("Bike 1", "Small Road", 10, 2, 1, "Emilia", "Argos");
        Tricycle tricycle1 = new Tricycle("Tricycle 1", "Path", 5, 3, 1, "Sally", "Homebase");
        
        train1.initialiseFuel();
        train1.setFuelType("train fuel");
        plane1.initialiseFuel();
        plane1.setFuelType("kerosene");
        car1.initialiseFuel();
        car1.setFuelType("diesel");
        car1.go(19);
        car1.reFuel(300);
        train1.go(5);
        plane1.go(50);
        bike1.setSpokes();
        bike1.setSpokesColour("green");
        
        garage.addToGarage(train1);
        garage.addToGarage(plane1);
        garage.addToGarage(car1);
        garage.addToGarage(bike1);
        garage.addToGarage(tricycle1);
        
        garage.printOut();
        garage.printOutPlus();
    }

}
package ma.sqli.vehicles;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/************
 The aim of the exercise is to pass all the test cases bellow.
 It's about vehicles and their consumption. Each vehicle has a different consumption according to its Fuel type :
  - Diesel: 5%
  - Gasoline: 6%
  - Hybrid: 3%

 A vehicle is displayed as bellow. Please respect the door's numbers as in the schema : 
         _
 door 1 | | door 2
 door 3 |_| door 4

*************/

public class VehiclesTest {

    private Vehicles vehicles;

    @Before
    public void initVehicles() {
        /**
        VEHICLE_ID:FUEL_TYPE:NUMBER_OF_DOORS
        **/
        vehicles = new Vehicles("CAR:Hybrid:4, TRUCK:Diesel:2, MOTORCYCLE:Gasoline:0");
    }

    @Test
    public void testCar() {
        String report = vehicles.move("CAR", "1 2 3 4", "200 KM");
        Assert.assertEquals("DOORS OK, MOVING. The CAR will consume 6.00 L", report);
    }

    @Test
    public void testTruck() {
        String report = vehicles.move("TRUCK", "1 2", "1000 KM");
        Assert.assertEquals("DOORS OK, MOVING. The TRUCK will consume 50.00 L", report);
    }

    @Test
    public void testMotorCycle() {
        String report = vehicles.move("MOTORCYCLE", "", "50 KM");
        Assert.assertEquals("DOORS OK, MOVING. The MOTORCYCLE will consume 3.00 L", report);
    }

    @Test
    public void testCarFrontRightDoorNotClosed() {
        /***********
         The car should be displayed as below :
           _        
          | \ 
          |_|
          
         ************/
        String report = vehicles.move("CAR", "1 3 4", "200 KM");
        Assert.assertEquals("DOORS KO, BLOCKED \n"+
                            "  _\n"+
                            " | \\\n"+
                            " |_|", report);
    }

    @Test
    public void testCarBackLeftDoorNotClosed() {
        /***********
         The car should be displayed as below :
           _
          | | 
          /_|
          
        ************/
        String report = vehicles.move("CAR", "1 2 4", "200 KM");
        Assert.assertEquals("DOORS KO, BLOCKED \n"+
                            "  _\n"+
                            " | |\n"+
                            " /_|", report);
    }
}

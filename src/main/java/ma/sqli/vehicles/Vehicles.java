package ma.sqli.vehicles;

import java.util.Formatter;
import java.util.Hashtable;
import java.util.Map;

import ma.sqli.exception.VehicleException;
import ma.sqli.fuels.FuelFabrique;
import ma.sqli.fuels.FuelStrategy;
import ma.sqli.tools.VehicleTools;
import ma.sqli.vehicle.Vehicle;

import org.apache.commons.lang3.StringUtils;

/**
 * Vehicles.java : 
 * 
 * 
 *  Spécification Crée Par SQLi :
 *  *****************************
 *  The aim of the exercise is to pass all the test cases bellow.
 *  It's about vehicles and their consumption. Each vehicle has a different consumption according to its Fuel type :
 *  - Diesel: 5%
 *  - Gasoline: 6%
 *  - Hybrid: 3%
 *  
 *  A vehicle is displayed as bellow. Please respect the door's numbers as in the schema : 
 *          _
 *  door 1 | | door 2
 *  door 3 |_| door 4
 *  
 *  ******************************
 *  
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 *
 */
public class Vehicles {
	
	/**
	 * La Liste des véhicules identifiés par Clé
	 */
	private Map<String, Vehicle> vehicleListe = new Hashtable<String, Vehicle>();
	
	/**
	 * Constructeur pour instancier des objet Vehicle
	 * 
	 * @param vehicles VEHICLE_ID:FUEL_TYPE:NUMBER_OF_DOORS, VEHICLE_ID:FUEL_TYPE:NUMBER_OF_DOORS, ....
	 */
    public Vehicles(String vehicles) {
    	try {
	    	if(StringUtils.isNotBlank(vehicles)){
	        	String[] vehicleInstances = vehicles.split(VehicleTools.VEHICLES_SEPARATOR);
	        	for (String vehicle : vehicleInstances) {
	        		String[] vehicleDetails = vehicle.split(VehicleTools.VEHICLES_DETAILS_SEPARATOR);
	        		
	        		/**Les valeurs par défauls*/
	        		String vehicleId = "";
	        		FuelStrategy fuel = null;
	        		int numberOfDoors = 0;
	        		
	        		/**Identifiant du véhicule*/
	        		if( vehicleDetails.length > 0 && StringUtils.isNotBlank(vehicleDetails[0]))
	        			vehicleId = vehicleDetails[0]; 
	        		
	        		/**Récupuration de l'objet Fuel àpartir de nomtre Fabrique FuelFabrique*/
	        		if(vehicleDetails.length > 1 && StringUtils.isNotBlank(vehicleDetails[1]))
						fuel = FuelFabrique.getFuel(vehicleDetails[1]);
	        		
	        		/**Le Nombre des ports*/
	        		if( vehicleDetails.length > 0 && 
	        				StringUtils.isNotBlank(vehicleDetails[2]) && 
	        				StringUtils.isNumeric(vehicleDetails[2]))
	    				numberOfDoors = Integer.valueOf(vehicleDetails[2]);
	        		
	        		/** Vérifier si la liste contienne déja un véhicule identifier par la variable : vehicleId*/
	        		if(vehicleListe.containsKey(vehicleId.trim()))
	        			vehicleListe.remove(vehicleId.trim());
	        		
	        		/**Enregistrer le véhicule dans vehicleListe*/
	        		vehicleListe.put(vehicleId.toUpperCase().trim() , new Vehicle(fuel, vehicleId, numberOfDoors));
				}
	        }
    	} catch (VehicleException e) {
			e.printStackTrace();
		}
    }

    /**
     * Comportement de la méthode Move
     * @param id
     * @param closedDoors
     * @param distance
     * @return
     */
    public String move(String id, String closedDoors, String distance) {
    	StringBuilder mouvementMessage = new StringBuilder(""); 
    	try {
			Vehicle vehicle = vehicleListe.get(id);
			if(vehicle != null){
				/**Transformer la chaine "200 KM" en "200"*/
				float km = Float.valueOf(distance.substring(0, distance.length() - 2).trim());
				/**Récupuration de la cosomation*/
				float consumption = vehicle.consumption(km);
				if(vehicle.allDoorsAreClosed(closedDoors)){
					mouvementMessage.append("DOORS OK, MOVING. The ")
					.append(id)
					.append(" will consume ")
					.append(formatFloat(consumption) + " L");
				}else{
					mouvementMessage.append("DOORS KO, BLOCKED \n")
					.append(vehicle.getVehiculeDesign(closedDoors));
				}
			}else{
				throw new VehicleException("Pas de véhicule avec l'identifiant " + id);
			}
    	} catch (VehicleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return mouvementMessage.toString();
    }
    
    @SuppressWarnings("resource")
	private String formatFloat(float floatNumber) {
		return new Formatter().format("%.2f", floatNumber).toString().replace(",", ".");
    }
    
}

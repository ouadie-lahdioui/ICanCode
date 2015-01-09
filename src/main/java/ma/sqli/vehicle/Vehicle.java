package ma.sqli.vehicle;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import ma.sqli.exception.VehicleException;
import ma.sqli.fuels.FuelStrategy;

/**
 * Vehicle.java
 * 
 * NB 1 : Dans cette classe j'ai essayé le max de ne pas exposer rien que les service nécessaires
 * pour bien respecter le principe de l'encapsulation
 *  
 * NB 2 : J'ai pas spécifier les getters et les setters pour respecter le principe de l'encapsulation.
 * et de faire voir l’objet Vehicule à l’extérieur comme une boîte noire. mais cela dit pas que j'aurai pas besoin des getters/setters au futur.
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 *
 */
public class Vehicle {
	
	/**
	 * Une Composition avec l'objet FuelStrategy pour hériter le comportement au mement de l'éxecution
	 */
	private FuelStrategy fuel;
	
	/**
	 * L'identifiant de la véhicule
	 */
	private String vehicleId;
	
	/**
	 * Le Nombre des portes
	 */
	private int numberOfDoors;
	
	/**
	 * Constructeur
	 * 
	 * @param fuel
	 * @param vehicleId
	 * @param numberOfDoors
	 */
	public Vehicle(FuelStrategy fuel, String vehicleId, int numberOfDoors) {
		this.fuel = fuel;
		this.vehicleId = vehicleId;
		this.numberOfDoors = numberOfDoors;
	}

	/**
	 * Calculer la consomation de la véhucule en se basnt sur le type du carburent et le kilométrage
	 * 
	 * @param km
	 * @return
	 */
	public float consumption(float km) {
		return this.fuel.consumption(km);
	}
	
	/**
	 * Une Méthode pour vérifier si tous les portes sont fermés ou pas
	 * 
	 * @param closedDoors
	 * @return
	 * @throws VehicleException 
	 */
	public boolean allDoorsAreClosed(String closedDoors){
		closedDoors = closedDoors.replaceAll(" ", "").trim();
		for (int i = 1; i <= this.numberOfDoors ; i++) {
			if(closedDoors.indexOf(String.valueOf(i)) == -1) {
				return false;
			}
		}
		return true; 	
    }

	/**
	 * Illustrer le véhicule avec ses ports ouverts ou celle fermés 
	 * 
	 * @param numberOfDoors
	 * @param closedDoors
	 * @return
	 * @throws VehicleException 
	 */
    public String getVehiculeDesign(String closedDoors) throws VehicleException {
    	/**Illustration final*/
    	StringBuilder vehiculeDesign = new StringBuilder("");
    	if(numberOfDoors > 0 && StringUtils.isNotBlank(closedDoors)) {
    		/**Une collection sans doublant qui contienet le nombre des ports*/
			Set<Integer> VehiculeDoors = new HashSet<Integer>();
			/**Une collection sans doublant pour les ports fermés*/
			Set<Integer> VehiculeClosedDoors = new HashSet<Integer>();
			closedDoors = closedDoors.replaceAll(" ", "").trim();
			for (int i = 1; i <= numberOfDoors ; i++) {
				VehiculeDoors.add(i);
			}
			for (int i = 0; i < closedDoors.length() ; i++) {
				VehiculeClosedDoors.add(Character.getNumericValue(closedDoors.charAt(i)));
			}
			vehiculeDesign.append("  _\n");
			for (int i = 1; i <= numberOfDoors ; i++) {
				boolean pair = (i%2==0);
				if(!VehiculeClosedDoors.contains(i) && pair){
					vehiculeDesign.append("\\");
					if(i != numberOfDoors){
						vehiculeDesign.append("\n");	
					}
				}
				else if(!VehiculeClosedDoors.contains(i) && !pair){
					if(i == (numberOfDoors-1)){
						vehiculeDesign.append(" /_");
					}else{
						vehiculeDesign.append(" / ");
					}
				}else if(VehiculeClosedDoors.contains(i) && pair){
					vehiculeDesign.append("|");
					if(i != numberOfDoors){
						vehiculeDesign.append("\n");	
					}
				}else if(VehiculeClosedDoors.contains(i) && !pair){
					if(i == (numberOfDoors-1)){
						vehiculeDesign.append(" |_");
					}else{
						vehiculeDesign.append(" | ");
					}
				}
			}
    	}else{
    		throw new VehicleException("le véhicule " + vehicleId + " soit ne déspose pas de ports soit le paramétres qui indique les portes fermés et null ou vide :(");
    	}
 
    	return vehiculeDesign.toString();		
    }
    
	/**
	 * Récupurer le nombre des portes
	 * 
	 * @return
	 */
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	
	@Override
	public String toString() {
		return "ID : " + vehicleId + ", Carburent : " + fuel.getClass().getName() + ", Nomre de ports : " + numberOfDoors;
	}

}

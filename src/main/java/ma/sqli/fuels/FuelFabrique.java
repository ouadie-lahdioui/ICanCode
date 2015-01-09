package ma.sqli.fuels;

import ma.sqli.exception.VehicleException;
import ma.sqli.tools.VehicleTools;

import org.apache.commons.lang3.StringUtils;

/**
 * FuelFactoryFabrique.java : Une implémentation du pattern de conception Factory (Fabrique)
 * Elle permet d'instancier les  objets (DIESEL, GASOLINE, HYBRID) dont le type est dérivé de l'interface FuelStrategy
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 *
 */
public class FuelFabrique {
	
	/**
	 * Instancier Un Objet dont le type est dérivé de FuelStrategy
	 * 
	 * @param fuelType
	 * @return
	 * @throws VehicleException
	 */
	public static FuelStrategy getFuel(String fuelType) throws VehicleException {
		FuelStrategy fuel;
		if(StringUtils.isNotBlank(fuelType)) {
			if(VehicleTools.FUEL_DIESEL.equals(fuelType.toLowerCase())){
				fuel = Diesel.getInstance();
			}else if(VehicleTools.FUEL_GASOLINE.equals(fuelType.toLowerCase())){
				fuel = Gasoline.getInstance();
			}else if(VehicleTools.FUEL_HYBRID.equals(fuelType.toLowerCase())){
				fuel = Hybrid.getInstance();
			}else{
				throw new VehicleException("Impossible de créer un carburant de type " + fuelType + " :(");
			}
		}else{
			throw new VehicleException("Le type du carburant ne peux pas être vide ou null :(");
		}
		return fuel;
	}

}

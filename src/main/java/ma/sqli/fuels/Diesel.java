package ma.sqli.fuels;

import ma.sqli.tools.VehicleTools;

/**
 * Diesel.java : Une implémentation du pattern de conception Stratégie (Strategy) et Singleton
 * Elle permet de garantit que la Diesel n’a qu’une seule instance. et elle fournit également un point d’accès global à cette instance.
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 * 
 */
public class Diesel implements FuelStrategy {
	
	/**
	 * L'instance Unique
	 */
	private static volatile Diesel uniqueInstance;
	
	
	/**
	 * Constructeur privé pour ne pas pouvoir instancier la classe Diesel
	 */
	private Diesel(){}
	
	/**
	 * Psuedo Sonstructeur 
	 * @return
	 */
	public static Diesel getInstance() {
		if(uniqueInstance == null) {
			synchronized (Diesel.class) {
				if(uniqueInstance == null) {
					uniqueInstance = new Diesel();
				}
			}
		}
		return uniqueInstance;
	}
	
	/**
	 * Calculer la consomation du carburant par KM
	 * 
	 * @param km
	 * @return
	 */
	public float consumption(float km) {
		return (km * VehicleTools.FUEL_DIESEL_CONSUMPTION) / 100;
	}
}

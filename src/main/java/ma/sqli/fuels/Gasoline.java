package ma.sqli.fuels;

import ma.sqli.tools.VehicleTools;

/**
 * Gasoline.java : Une implémentation du pattern de conception Stratégie (Strategy) et Singleton
 * Elle permet de garantit que la Gasoline n’a qu’une seule instance. et elle fournit également un point d’accès global à cette instance.
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 * 
 */
public class Gasoline implements FuelStrategy {
	/**
	 * L'instance Unique
	 */
	private static volatile Gasoline uniqueInstance;
	
	/**
	 * Constructeur privé pour ne pas pouvoir instancier la classe Gasoline
	 */
	private Gasoline(){}
	
	/**
	 * Psuedo Sonstructeur 
	 * @return
	 */
	public static Gasoline getInstance() {
		if(uniqueInstance == null) {
			synchronized (Gasoline.class) {
				if(uniqueInstance == null) {
					uniqueInstance = new Gasoline();
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
		return  (km * VehicleTools.FUEL_GASOLINE_CONSUMPTION) / 100;
	}
	

}

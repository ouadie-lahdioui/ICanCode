package ma.sqli.fuels;

import ma.sqli.tools.VehicleTools;

/**
 * Hybrid.java : Une implémentation du pattern de conception Stratégie (Strategy) et Singleton
 * Elle permet de garantit que la Hybrid n’a qu’une seule instance. et elle fournit également un point d’accès global à cette instance.
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 * 
 */
public class Hybrid implements FuelStrategy {
	
	/**
	 * L'instance Unique
	 */
	private static volatile Hybrid uniqueInstance;
	
	/**
	 * Constructeur privé pour ne pas pouvoir instancier la classe Hybrid
	 */
	private Hybrid(){}
	
	/**
	 * Psuedo Sonstructeur 
	 * @return
	 */
	public static Hybrid getInstance() {
		if(uniqueInstance == null) {
			synchronized (Hybrid.class) {
				if(uniqueInstance == null) {
					uniqueInstance = new Hybrid();
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
		return (km * VehicleTools.FUEL_HYBRID_CONSUMPTION) / 100;
	}

}

package ma.sqli.fuels;

/**
 * FuelStrategy.java : Une implémentation du pattern de conception Stratégie (Strategy)
 * Elle permet de sélectionner l'algorithme de calcule de la consomation à la volée au cours du temps d'exécution. 
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 *
 */
public interface FuelStrategy {
	
	/**
	 * Calculer la consomation du carburant par KM
	 * 
	 * @param km
	 * @return
	 */
	public float consumption(float km);
}

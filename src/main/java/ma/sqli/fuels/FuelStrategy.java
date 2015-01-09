package ma.sqli.fuels;

/**
 * FuelStrategy.java : Une impl�mentation du pattern de conception Strat�gie (Strategy)
 * Elle permet de s�lectionner l'algorithme de calcule de la consomation � la vol�e au cours du temps d'ex�cution. 
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

package ma.sqli.exception;

/**
 * VehicleException.java : Une Exception personalis�e
 * 
 * @author Ouadie LAHDIOUI :) <o.lahdioui@gmail.com>
 * @version 1.0
 * @date 10/10/2014
 *
 */
public class VehicleException extends Exception {

	private static final long serialVersionUID = -930681469436932689L;

	/**
	 * Constructeur Par d�fault 
	 * @param msg
	 */
	public VehicleException(String msg) {
		super(msg);
	}

}

package asteroids.model;
import asteroids.util.ModelException;
/**
 * A class of ships in a universe of Asteroids.	
 * 
 * @author joachim & stef
 * @version pre-alpha
 * 
 * 
 */

public class Ship {
	
	
	private static final double Radius = 0;


	public void createShip() throws ModelException{
		
	}
	
	
	// region getters
	/**
	 * 
	 * @param ship
	 * @return	the value of the Radius
	 * @throws ModelException
	 */
	public double getShipRadius(Ship ship) throws ModelException{
		return this.Radius;
	}
	
}

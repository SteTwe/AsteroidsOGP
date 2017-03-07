package asteroids.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of a ship involving a position, a velocity, an angle and a radius
 * 
 * @author Joachim & Stef
 * @version pre-alpha
 *
 * @invar       The radius of a ship must be higher than or equal to the minimum radius for all ships.
 *              | isValidRadius(getRadius))
 *
 */

public class Ship {

    /**
     * Initialize a ship with
     *
     *
     * @param x
     * @param y
     * @param velocityX
     * @param velocityY
     * @param angle
     * @param radius
     */
    public Ship(double x, double y,double velocityX, double velocityY, double angle, double radius){
    }

    ///Region regarding Position///
    /**
     * 
     * @return positionX
     */
    public double getPositionX(){
        return this.positionX;
    }
    
    private double positionX;
	
    /**
     * 
     * @return positionY
     */
    public double getPositionY(){
        return this.PositionY;
    }

    private double PositionY;

    ///End Region regarding Position///

    ///Region regarding Velocity///

    /**
     *  Initialize the new velocity with the given velocity in direction x and the given velocity in direction y.
     * @param velocityX
     *          | The value of the velocity in the x-direction.
     * @param velocityY
     *          | The value of the velocity in the y-direction.
     * @param maxVelocity
     *
     * @post 
     */
    public Velocity(double velocityX, double velocityY, double maxVelocity){

        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     *
     * @param velocity
     *          | The velocity that is being checked.
     * @return  True if the velocity is not greater than the maximum velocity
     *              and not less than zero.
     *              |result ==
     *              |       velocity <= maxVelocity
     *              |       velocity >= 0
     */
    public boolean isValidVelocity(double velocity){
        if ((this.velocity >= 0) && (this.velocity <= getMaxVelocity())) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * constant containing speed of light
     */
    public static double SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT = 300000;

    /**
     *
     * @return  the maximum Velocity
     */
    ///temporary
    @Basic
    public double getMaxVelocity(){
        return SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT;
    }

    /**
     *
     * @return  the velocity in the x-direction.
     */
    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    private double velocityX;

    /**
     *
     * @return  the velocity in the y-direction.
     */
    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }

    private double velocityY;


    /**
     * Return the final velocity of this ship based on the velocity in direction X and the velocity in direction Y.
     *
     * @param velocityX
     *          | The velocity in direction X.
     * @param velocityY
     *          | The velocity in direction Y.
     *
     * @return  The total velocity of the ship in the direction x y.
     *
     *
     */
    public double calcVelocity(double velocityX, double velocityY){
        if (isValidVelocity(this.velocity)){
            this.velocity= Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
            return this.velocity;
        }
        else{
        	return this.velocity = SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT;
        }
    }

    private double velocity;


    ///End Region regarding Velocity///

    ///Region regarding Orientation///

    /**
     *  Returns the orientation of this Ship.
     *
     *  The orientation expresses a direction the Ship is facing in, as an angle in radians.
     */
    @Basic
    public double getAngle(){
        return this.angle;
    }

    private double angle;

    /**
     * Expresses the minimum value the orientation of this Ship has to be.
     */
    private double minAngle = Math.toRadians(0);

    /**
     * Expresses the maximum value the orientation of this Ship can be.
     */
    private double maxAngle = Math.toRadians(360);

    /**
     * Set the angle of the ship to the given angle.
     *
     * @param   angle
     *          The new angle for this ship.
     * @pre     The given angle must be a valid angle for the ship.
     *          | isValidAngle(angle)
     *
     * @post    If the given given angle is between the minimum and maximum anlge
     *              then angle of the ship is equal to the given angle.
     *              If that condition is not met the angle of the ship is equal to the given angle % maxAngle
     *          | if (angle  > minAngle) && (angle < maxAngle)
     *          |       this.angle = angle
     *          | else
     *          |       this.angle = angle ‰ maxAngle
     *
     */
    public void setAngle(double angle){
        assert isValidAngle(angle);
        if ((angle > minAngle) && (angle < maxAngle))
                this.angle = angle;
        else
            this.angle = angle % maxAngle;

    }

    /**
     * Return true if the given angle is a valid angle for this ship (for all ships?)
     * @param angle
     *          | The given angle for this ship.
     * @return
     *          | True if the angle is valid.
     */
    public boolean isValidAngle(double angle){
        if ((this.angle >= minAngle) && (this.angle <= maxAngle));
        return true;
    }


    ///End Region regarding Orientation///


    ///Region regarding Radius///

    /**
     * Expressing the minimum value of the radius of this ship expressed in kilometers.
     * May change in the future.
     */
    public double minRadius = 10;

	private double radius;

	public double getRadius(){
        return this.radius;
    }


    public void setRadius(){
        this.radius = radius;
    }

    ///End Region regarding Radius///

    /**
     *
     * @param velocity
     * @param angle
     */
    public void thrust (Ship ship, double amount){
    	if (amount<0){
    		amount = 0;
    		double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
            double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
    		
    	}
    	double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
        double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
        
        
    }


    /**
     * Move the ship in the current direction for a given amount of time defined by the duration.
     * @param       duration
     *              | The duration for how long the ship moves in its current direction.
     * @return
     *
     * @throws      Exception
     *
     *              | (duration < 0)
     */
    public void move(double duration) throws Exception{
    	if (duration < 0){
    	}
    	if (this.velocity == 0){
    	}
    }
    
    /**
     * Turn the ship by adding a given angle to the current orientation. Angle has to be in radians and must be between 0 and 2π.
     * @param angle
     *              The given angle that has to be added to the current angle.
     *
     * @post
     *              Set the angle to the current angle plus the given angle.
     *                      | setAngle(getAngle() + angle)
     */
    public void turn(double angle){
        //I think this code is redundant if we use setAngle, that method wil make of for overflow
    	if((this.angle + angle) >= maxAngle){
            double calculatedAngle = this.angle + angle;
    		double newAngle = calculatedAngle - maxAngle;
    		setAngle(newAngle);
    	}
    	else{
            setAngle(getAngle() + angle);
    	}
    }
    
    /**
     * Calculate the distance between two spaceships. If the two compared ships are the same, distance is 0.
     * @param 		ship1
     * 				| First spaceship
     * @param		ship2
     * 				| Second spaceship
     * @return
     * 				| Calculated distance
     */
    
    
    public double getDistanceBetween(Ship ship1, Ship ship2){
    	double x1 = ship1.getPositionX();
    	double y1 = ship1.getPositionY();
    	double x2 = ship2.getPositionX();
    	double y2 = ship2.getPositionY();
    	//not certain of this comparison
    	if (ship1 == ship2){
    		double distance = 0;
    		return distance;
    	}
    	else{
    		double distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 + y2), 2));
    		return distance;
    	}
    	
    }
    
    /**
     * Check if two ships overlap.
     * @param 	ship1
     * 			| First spaceship
     * @param 	ship2
     * 			| Second spaceship
     * @return
     * 			| True if the spaceships overlap
     */
    
    public boolean overlap(Ship ship1, Ship ship2){
    	if (ship1 == ship2){
    		return true;
    	}
    	else{
    		double distance = getDistanceBetween(ship1, ship2);
    		if (ship1.radius > distance){
    			return true;
    		}
    		else if (ship2.radius > distance){
    			return true;
    		}
    		else {
    			return false;    			
    		}
    	}
    
    }
    
    public double getTimeToCollision(Ship ship1, Ship ship2){

    	if(/*never collision*/){
    		return Double.POSITIVE_INFINITY;
    	}
    }

    public double[] getCollisionPosition(Ship ship1, Ship ship2){
        
    }

}

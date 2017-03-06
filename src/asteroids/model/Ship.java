package asteroids.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of ships involving a position, a velocity, an angle and a radius
 * 
 * @author Joachim & Stef
 * @version pre-alpha
 * 
 */

public class Ship {

    /**
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
    public double getOrientation(){
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
     *
     */
    public void setOrientation(){

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

    ///End Region regarding Radius///

    /**
     *
     * @param velocity
     * @param angle
     */
    public void thrust (Ship ship, double amount){
    	if (amount<0){
    		amount = 0;
    		double velocityX = getVelocityX() + (amount * Math.cos(getOrientation()));
            double velocityY = getVelocityY() + (amount * Math.sin(getOrientation()));
    		
    	}
    	double velocityX = getVelocityX() + (amount * Math.cos(getOrientation()));
        double velocityY = getVelocityY() + (amount * Math.sin(getOrientation()));
        
        
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
     * Turn by adding a given angle to the current orientation. Angle has to be in radians and must be between 0 and 2π.
     * @param angle
     */
    public void turn(double angle){
    	if((this.angle + angle) >= maxAngle){
    		//add calculation angle
    		this.angle += angle;
    		this.angle = this.angle - maxAngle;
    	}
    	else{
    		this.angle += angle;
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

}

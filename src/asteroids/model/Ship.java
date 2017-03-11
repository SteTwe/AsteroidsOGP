package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

import java.util.DoubleSummaryStatistics;


/**
 * A class of a ship involving a position, a velocity, an angle and a radius
 * 
 * @author Joachim & Stef
 * @version pre-alpha
 *
 * @invar       The radius of a ship must be higher than or equal to the minimum radius for all ships.
 *              | isValidRadius(getRadius))
 */

public class Ship {

    /**
     * Initialize a ship with x-position, y-position, velocity in x-direction, velocity in y-direction, angle, radius.
     *
     * @param x
     *              The x-coordinate of this new ship.
     * @param y
     *              The y-coordinate of this new ship.
     * @param velocityX
     *              The velocity in the x-direction of this new ship.
     * @param velocityY
     *              The velocity in the y-direction of this new ship.
     * @param angle
     *              The angle (orientation) which this new ship is facing in.
     * @invar radius
     *              The radius of this new shipof this new ship.
     */
    public Ship(double positionX, double positionY,double velocityX, double velocityY, double angle, double radius) throws IllegalArgumentException{
        this.setPosition(new double[] {positionX,positionY});
        this.setVelocity(new double[] {velocityX, velocityY}); //method needs fixing.
        this.angle = angle;
        if (!isValidRadius(radius)) throw new IllegalArgumentException();
        this.radius = radius;
    }

    /**
     * @return positionX
     */
    public double getPositionX(){
        return this.positionX;
    }

    /**
     * Variable holding the y-coordinate.
     */
    private double positionX;

    private boolean isValidPositionX(double positionX){
        if (Double.isNaN(positionX)) return false;
        return true;
    }

    /**
     * @return positionY
     */
    public double getPositionY(){
        return this.positionY;
    }

    /**
     * Variable holding the x-coordinate.
     */
    private double positionY;

    private boolean isValidPositionY(double positionY){
        if (Double.isNaN(positionY)) return false;
        return true;
    }

    /**
     * @return      The position of this ship as an array of positionX and positionY.
     */
    public double[] getPosition(){
        return this.position;
    }

    private double[] position = new double[2];


    private boolean isValidPosition(double[] position){
        if ((!isValidPositionX(positionX)) || (!isValidPositionY(positionY)) || (position.length != 2))
            return false;
        return true;
    }

    public void setPosition(double[] position){
        this.position = getPosition();
    }

    /**
     * @param velocityX
     */
    public void setVelocityX(double velocityX){

        this.velocityX = velocityX;
    }

    /**
     * @param velocityY
     */
    public void setVelocityY(double velocityY){

        this.velocityY = velocityY;
    }

    /**
     * @param velocity
     *          | The velocity that is being checked.
     * @return  True if the velocity is not greater than the maximum velocity
     *              and not less than zero.
     *              |result ==
     *              |       velocity <= maxVelocity
     *              |       velocity >= 0
     *              |       velocity is a number
     */
    public boolean isValidVelocity(double velocity){
        if ((this.velocity >= 0) && (this.velocity <= getMaxVelocity()) && Double.isNaN(velocity)) {
            return true;
        }else{
            return false;
        }
    }

    public void setVelocity(double[] velocity){

    }

    /**
     * constant containing speed of light
     */	
    public static double SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT = 300000;

    /**
     * @return  the maximum Velocity
     */
    ///temporary
    @Basic
    public double getMaxVelocity(){
        return SPEED_OF_LIGHT_AS_DEFINED_BY_THE_ASSIGNMENT;
    }

    /**
     * @return  the velocity in the x-direction.
     */
    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    /**
     * Variable holding the velocity in the x-direction of this ship.
     */
    private double velocityX;

    /**
     * @return  the velocity in the y-direction.
     */
    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }

    /**
     * Variable holding the velocity in the y-direction of this ship..
     */
    private double velocityY;

    /**
     * Return the final velocity of this ship based on the velocity in direction X and the velocity in direction Y.
     *
     * @param velocityX
     *          | The velocity in direction X.
     * @param velocityY
     *          | The velocity in direction Y.
     *
     * @return  The total velocity of the ship in the direction x y. If the total velocity exceeds the speed of light
     *              the total velocity is set to the speed of light.
     */
    public double calcVelocity(double velocityX, double velocityY){
        return Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
    }

    /**
     * Variable containing the total velocity of the ship.
     */
    private double velocity;

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
     *          | True if the angle is between min and max boundaries and is a number.
     */
    public boolean isValidAngle(double angle){
        if ((this.angle >= minAngle) && (this.angle <= maxAngle) && (Double.isNaN(angle)));
        return true;
    }

    /**
     * Expressing the minimum value of the radius of this ship expressed in kilometers.
     * May change in the future.
     * Final because it can't change during the lifetime of this ship.
     */
    public final double minRadius = 10;

    /**
     * Variable holding the radius of this ship.
     */
	private double radius;

    /**
     *
     * @param minRadius
     *              The minimum radius for this ship.
     * @return
     *              True if the minimum radius is a valid number (see section 2 in the project assignment part 1) and
     *                  larger than or equal to zero.
     */
	public boolean isValidMinRadius(double minRadius){

	    return (!Double.isNaN(minRadius) && minRadius >= 0);
    }

    /**
     * Return the radius of this ship.
     */
	public double getRadius(){
        return this.radius;
    }


    public void setRadius(double radius){
	    if(isValidRadius(radius)){
            this.radius = radius;
        }
    }

    /**
     * @param radius
     *              The given radius for this ship.
     * @return
     *              True if the radius is a valid number (see section 2 in the project assignment part 1) and larger as
     *                  or equal to the minimum Radius.
     */
    public boolean isValidRadius(double radius){

        return (!Double.isNaN(radius) && radius >= minRadius);
    }

    ///End Region regarding Radius///

    ///Region regarding thrust///
    /**
     *
     * @param ship
     *
     * @param amount
     *
     *              The amount of velocity that needs to be added to the current velocity.
     *
     * @post        If the given amount is higher than zero, the amount is added to the current velocity. If this results
     *                  in the velocity being higher than the speed of light, the velocity is set to the speed of light.
     */
    public void thrust (Ship ship, double amount){
    	if (amount < 0 || Double.isNaN(amount)){
    		amount = 0;
    	}
    		double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
            double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
            while (calcVelocity(velocityX, velocityY)>300000){
            	amount -= 0.1;
                double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
                double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
            }
            ship.velocityX = velocityX;
            ship.velocityY = velocityY;
            return;

    	double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
        double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
        while (calcVelocity(velocityX, velocityY)>300000){
        	amount -= 0.1;
        	double velocityX = getVelocityX() + (amount * Math.cos(getAngle()));
            double velocityY = getVelocityY() + (amount * Math.sin(getAngle()));
        }
        ship.velocityX = velocityX;
        ship.velocityY = velocityY;
        
    }

    /**
     * Move the ship in the current direction for a given amount of time defined by the duration.
     * @param       duration
     *              | The duration for how long the ship moves in its current direction.
     * @return
     *
     * @throws      Exception
     *              The given duration is not valid.
     *              | (duration < 0)
     */
    public void move(Ship ship, double duration){
    	if (duration < 0){
    		//throw exception
    	}
    	else if (ship.velocity == 0 || duration ==0){
    		return;
    	}
    	else{
    		
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
    public void turn(Ship ship, double angle){
        //I think this code is redundant if we use setAngle, that method wil make of for overflow
    	if((ship.angle + angle) >= maxAngle){
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
    	else {
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

    }

    /**
     * @param ship1
     *
     * @param ship2
     *
     * @return
     *
     */
    public double[] getCollisionPosition(Ship ship1, Ship ship2){
        if ((ship1 == ship2) || (getTimeToCollision(ship1, ship2) == Double.POSITIVE_INFINITY))
            return null;
        else{
            return;
        }

    }
}

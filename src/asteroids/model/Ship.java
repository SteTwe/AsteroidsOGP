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
     * @param positionX The x-coordinate of this new ship.
     * @param positionY The y-coordinate of this new ship.
     * @param velocityX The velocity in the x-direction of this new ship.
     * @param velocityY The velocity in the y-direction of this new ship.
     * @param angle     The angle (orientation) which this new ship is facing in.
     * @invar radius
     * The radius of this new shipof this new ship.
     */
    public Ship(double positionX, double positionY, double velocityX, double velocityY, double angle, double radius) throws IllegalArgumentException {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
        this.setAngle(angle);
        if (!isValidRadius(radius)) throw new IllegalArgumentException();
        this.radius = radius;
    }
}
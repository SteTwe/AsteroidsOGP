package asteroids.model;

/**
 * A class representing a MinorPlanet of the type Asteroid, involving a position, a velocity and a radius.
 * @author Stef & Joachim
 * @version 1.0
 *
 */
public class Asteroid extends MinorPlanet {

    /**
     * Create a new Asteroid.
     * @param positionX The x-coordinate of the asteroid.
     * @param positionY The y-coordinate of the asteroid.
     * @param velocityX The x-velocity of the asteroid.
     * @param velocityY The y-velocity of the asteroid.
     * @param radius    The radius of the asteroid.
     * @throws IllegalArgumentException
     */
    public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    /**
     * Method to resolve collisions between an Asteroid and another entity.
     * @see implementation
     */
    @Override
    public void collide(Entity other) {
        if (other instanceof Ship){
            other.terminate();
        }
        else super.collide(other);
    }
}


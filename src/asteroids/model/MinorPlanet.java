package asteroids.model;

/**
 * A class representing an Entity of the type MinorPlanet, involving a position, a velocity and a radius.
 * @author Stef & Joachim
 * @version 1.0
 *
 */
public abstract class MinorPlanet extends Entity {

    /**
     * Create a new minor planet.
     * @param positionX The x-coordinate of the planet.
     * @param positionY The y-coordinate of the planet.
     * @param velocityX The x-velocity of the planet.
     * @param velocityY The y-velocity of the planet.
     * @param radius    The radius of the planet.
     * @throws IllegalArgumentException
     */
    public MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    /**
     * Resolve the collision of a MinorPlanet with another entity.
     * @see implementation
     */
    @Override
    public void collide(Entity other) {
        if (other instanceof MinorPlanet) this.bounceOffEntity(other);
        else if (other instanceof Bullet){
            this.terminate();
            other.terminate();
        }
    }

    /**
     * Resolve the collision of a MinorPlanet with a boundary.
     * @see superclass
     */
    @Override
    public void collideWithBoundary() {
        super.collideWithBoundary();
    }
}
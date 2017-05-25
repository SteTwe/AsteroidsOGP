package asteroids.model;

/**
 * Created by joachim on 18/05/2017.
 */
public class Asteroid extends MinorPlanet {

    public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }


    @Override
    public void collide(Entity other) {
        if (other instanceof Ship){
            other.terminate();
        }
        else super.collide(other);
    }
}


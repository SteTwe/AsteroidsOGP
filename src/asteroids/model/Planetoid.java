package asteroids.model;

/**
 * Created by joachim on 18/05/2017.
 */
public class Planetoid extends MinorPlanet {

    public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException{
        super(positionX, positionY, velocityX, velocityY, radius);
    }
}

package asteroids.model;

//import be.kuleuven.cs.som.annotate.*;

/**
 * Created by joachim on 18/05/2017.
 */
public abstract class MinorPlanet extends Entity {

    public MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }


    @Override
    public void collideWith(Bullet bullet) {
        bullet.terminate();
        this.terminate();
    }

    @Override
    public void collideWith(Collideable other) {
        if (other instanceof MinorPlanet){
            this.bounceOffEntity((MinorPlanet) other);
        }
    }

    @Override
    public abstract void collideWith(Ship ship);
}
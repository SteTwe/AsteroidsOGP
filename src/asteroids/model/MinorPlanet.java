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
    public void collide(Entity other) {
        if (other instanceof MinorPlanet) this.bounceOffEntity(other);
        else if (other instanceof Bullet){
            this.terminate();
            other.terminate();
        }
    }

}
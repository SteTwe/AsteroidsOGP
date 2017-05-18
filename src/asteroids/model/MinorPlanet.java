package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * Created by joachim on 18/05/2017.
 */
public abstract class MinorPlanet extends Entity {

    public MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }


    public World world;

    public void setWorld(World world) {
        this.world = world;
    }

}
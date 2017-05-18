package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * Created by joachim on 18/05/2017.
 */
public class MinorPlanet extends Entity{

    public MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius){
        super(positionX, positionY, velocityX, velocityY, radius);
    }


    public World world;

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void collide(Entity entity) {

    }
}

package asteroids.model;

/**
 * Created by stef on 13-7-17.
 */
public class BlackHole extends Entity {

    public BlackHole(double positionX, double positionY, double radius){
        super(positionX, positionY, 0,0, radius);
    }

    

    @Override
    public void collide(Entity other) {

    }
}

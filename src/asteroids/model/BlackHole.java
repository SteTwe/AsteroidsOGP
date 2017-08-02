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
        if (other instanceof Bullet){
            return;
        }
        if (other instanceof Ship){
            other.terminate();
        }
        if (other instanceof MinorPlanet){
            other.terminate();
        }
        // destroy both, replace with new position = collisionpoint, radius = sum radii
        if (other instanceof BlackHole){
            double newRadius = this.getRadius() + other.getRadius();
            double position[] = this.getPositionCollisionWithEntity(other);
            BlackHole newBlackHole = new BlackHole(position[0], position[1], newRadius);
            this.terminate();
            other.terminate();

        }
        else other.collide(this);

    }

    public void grow(double amount){
        if(getRadius() + amount > 100) {
            this.radius = getRadius() + amount;
        }
        else {
            this.radius = minRadiusBlackHole;
        }
    }

    public void shrink(double amount){
        if(getRadius() - amount > 100){
            this.radius = getRadius() - amount;
        }
        else {
            this.radius = minRadiusBlackHole;
        }
    }


}

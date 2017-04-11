package asteroids.facade;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

import java.util.Set;

public class Facade implements IFacade {

	/**************
	 * SHIP: Basic methods
	 *************/
	@Override
	public Ship createShip() throws ModelException {
		return new Ship(0, 0, 0, 0, 0, 10);
	}

	@Override
	public Ship createShip(double positionX, double positionY, double velocityX, double velocityY, double radius, double angle)
			throws ModelException {
		if(Double.isNaN(positionX)|| Double.isNaN(positionY) 
				|| Double.isNaN(velocityX) || Double.isNaN(velocityY) || Double.isNaN(radius) 
				|| Double.isNaN(angle)) throw new ModelException("Invalid argument");
		if (radius < 0) throw new ModelException("Radius below zero");
		return new Ship(positionX, positionY, velocityX, velocityY, radius, angle);
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
						   double mass) throws ModelException{
		if(Double.isNaN(x)|| Double.isNaN(y)
				|| Double.isNaN(xVelocity) || Double.isNaN(yVelocity) || Double.isNaN(radius)
				|| Double.isNaN(direction) || Double.isNaN(mass)) throw new ModelException("Invalid argument");
		if (radius < 0) throw new ModelException("Radius below zero");
		return new Ship(x,y, xVelocity, yVelocity, radius, direction, mass);
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return false;
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getActiveThruster();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		ship.thrustOn();
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		try{
			ship.move(dt);
		}catch (Exception e){
			throw new ModelException(e);
		}
	}


	/**************
	 * BULLET: Basic methods
	 *************/

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		return null;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {

	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return false;
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return new double[0];
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return new double[0];
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return 0;
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return 0;
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return null;
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return null;
	}





	/**************
	 * WORLD: Basic methods
	 *************/
	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width, height);
	}



	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminateWorld();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		if (world.getBulletSet().isEmpty() && world.getShipSet().isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[] {world.getWidth(), world.getHeight()};
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShipSet();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBulletSet();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		world.addShip(ship);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShip(ship);
	}


	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBullet(bullet);
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBullet(bullet);
	}



	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getPositionX(), ship.getPositionY()};
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return new double[]{ship.getVelocityX(), ship.getVelocityY()};
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getAngle();
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		try{
			ship.thrust(amount);
		}catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		try{
			ship.turn(angle);
		}catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getDistanceBetween(ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getTimeToCollision(ship2);
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getCollisionPosition(ship2);
	}
}

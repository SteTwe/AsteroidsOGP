package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Ship createShip() throws ModelException {
		return new Ship(0, 0, 0, 0, 0, 10);
	}

	@Override
	public Ship createShip(double positionX, double positionY, double velocityX, double velocityY, double radius, double angle)
			throws ModelException {
		return new Ship(positionX, positionY, velocityX, velocityY, radius, angle);
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getPositionX(), ship.getVelocityY()};
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
	public void move(Ship ship, double dt) throws ModelException {
		try{
			ship.move(dt);
		}catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
		// TODO Auto-generated method stub

		
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return ship1.getDistanceBetween(ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return getTimeToCollision(ship1, ship2);
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}

package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Ship createShip() throws ModelException {
		return new Ship(0,0,0,0,0,10);
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		return new Ship(x, y, xVelocity, yVelocity, orientation, radius);
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity();
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
		move(ship, dt);
		// TODO Auto-generated method stub
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		thrust(ship, amount);
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		turn(ship, angle);
		// TODO Auto-generated method stub

	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return getDistanceBetween(ship1, ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return overlap(ship1, ship2);
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

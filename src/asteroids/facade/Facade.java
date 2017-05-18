package asteroids.facade;

import asteroids.model.*;
import asteroids.part2.CollisionListener;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Facade implements IFacade {

	/**************
	 * SHIP: Basic methods
	 *************/
	@Override
	public Ship createShip() throws ModelException {
		return new Ship(0, 0, 0, 0, 0, 10, 0);
	}

	@Override
	public Ship createShip(double positionX, double positionY, double velocityX, double velocityY, double radius, double angle)
			throws ModelException {
		if (Double.isNaN(positionX) || Double.isNaN(positionY)
				|| Double.isNaN(velocityX) || Double.isNaN(velocityY) || Double.isNaN(radius)
				|| Double.isNaN(angle)) throw new ModelException("Invalid argument");
		if (radius < 0) throw new ModelException("Radius below zero");
		return new Ship(positionX, positionY, velocityX, velocityY, radius, angle, 0);
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
						   double mass) throws ModelException {
		if (Double.isNaN(x) || Double.isNaN(y)
				|| Double.isNaN(xVelocity) || Double.isNaN(yVelocity) || Double.isNaN(radius)
				|| Double.isNaN(direction) || Double.isNaN(mass)) throw new ModelException("Invalid argument");
		if (radius < 0) throw new ModelException("Radius below zero");
		return new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getShipMass();
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
		try {
			ship.move(dt);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}


	/**************
	 * BULLET: Basic methods
	 *************/

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		if (Double.isNaN(x) || Double.isNaN(y)
				|| Double.isNaN(xVelocity) || Double.isNaN(yVelocity)
				|| Double.isNaN(radius)) throw new ModelException("Invalid argument");
		if (radius < 0) throw new ModelException("Radius below zero");
		return new Bullet(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return new double[]{bullet.getPositionX(), bullet.getPositionY()};
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return new double[]{bullet.getVelocityX(), bullet.getVelocityY()};
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getBulletMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getBulletSource();
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
		return (world.getBulletSet().isEmpty() && world.getShipSet().isEmpty());
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[]{world.getWidth(), world.getHeight()};
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
		world.removeBulletWorld(bullet);
	}


	/**************
	 * SHIP: Methods related to loaded bullets
	 *************/

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets().size();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		ship.loadBullet(bullet);
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.loadSetOfBullets(bullets);
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBulletShip(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getPositionX(), ship.getPositionY()};
	}


	/******************
	 * COLLISIONS
	 **************/
	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return 0;
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return new double[0];
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return 0;
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return new double[0];
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return 0;
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return new double[0];
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {

	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAtPosition(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntitySet();
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		try {
			ship.thrust(amount);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}


	/******************
	 * METHODS PART 1
	 **************/

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
	public void turn(Ship ship, double angle) throws ModelException {
		try {
			ship.turn(angle);
		} catch (Exception e) {
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

	/******************
	 * PART 3
	 **************/

	/******************
	 * ADMINISTRATIVE STUFF
	 **************/
	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	/**************
	 * WORLD: Asteroids and planetoids
	 *************/
	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return null;
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {

	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {

	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return null;
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {

	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {

	}


	/**************
	 * ASTEROID: Basic methods
	 *************/
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		return null;
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {

	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return false;
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return new double[0];
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return new double[0];
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return 0;
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return 0;
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return null;
	}

	/**************
	 * PLANETOID: Basic methods
	 *************/

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws ModelException {
		return null;
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {

	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return false;
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return new double[0];
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return new double[0];
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return 0;
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return 0;
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return 0;
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return null;
	}

	/**********
	 * PROGRAMS
	 **********/

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return null;
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {

	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		return null;
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return null;
	}
}
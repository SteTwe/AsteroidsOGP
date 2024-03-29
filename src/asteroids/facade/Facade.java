package asteroids.facade;

import asteroids.model.*;
import asteroids.model.Programs.ProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;


import java.util.*;

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
		try{
			return new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
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
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.isThrusterEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active) ship.thrustOn();
		else ship.thrustOff();
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
		try{
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
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
		return bullet.getMass();
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
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[]{world.getWidth(), world.getHeight()};
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		Set<Ship> shipSet = new HashSet<>();
		for (Entity entity : world.getEntitySet()){
			if (entity instanceof Ship){
				shipSet.add((Ship) entity);
			}
		}
		return shipSet;
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		Set<Bullet> bulletSet = new HashSet<>();
		for (Entity entity : world.getEntitySet()){
			if (entity instanceof Bullet){
				bulletSet.add((Bullet) entity);
			}
		}
		return bulletSet;
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try{
 			world.addEntity(ship);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try{
			world.removeEntity(ship);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try{
			world.addEntity(bullet);
		}
		catch (IllegalArgumentException e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeEntity(bullet);
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
		try{
		ship.loadBullet(bullet);
		}
		catch (Exception e){
			throw new ModelException(e);
		}

	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try{
			ship.loadSetOfBullets(bullets);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBulletShip(bullet);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
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
		return ((Entity) object).getTimeToCollisionWithBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity)object).getCollisionPositionWithBoundary();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		Entity first = (Entity) entity1;
		Entity second = (Entity) entity2;
		return first.getTimeCollisionWithEntity(second);
	}
	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		Entity first = (Entity) entity1;
		Entity second = (Entity) entity2;
		return first.getPositionCollisionWithEntity(second);
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getNextCollisionPosition();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try{
			world.evolve(dt, collisionListener);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
		//world.evolve(dt, collisionListener);
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
		try {
			return ship1.getDistanceBetween(ship2);
		} catch (Exception e){
			throw new ModelException(e);
		}

	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try{
			return ship1.overlap(ship2);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeCollisionWithShip(ship2);
		} catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getPositionCollisionWithShip(ship2);
		} catch (Exception e){
			throw new ModelException(e);
		}
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
		Set<Asteroid> asteroidSet = new HashSet<>();
		for (Entity entity : world.getEntitySet()){
			if (entity instanceof Asteroid){
				asteroidSet.add((Asteroid) entity);
			}
		}
		return asteroidSet;
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.addEntity(asteroid);
		} catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeEntity(asteroid);
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		Set<Planetoid> planetoidSet = new HashSet<>();
		for (Entity entity : world.getEntitySet()){
			if (entity instanceof Planetoid){
				planetoidSet.add((Planetoid) entity);
			}
		}
		return planetoidSet;
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.addEntity(planetoid);
		} catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removeEntity(planetoid);
	}


	/**************
	 * ASTEROID: Basic methods
	 *************/
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		try {
			return new Asteroid(x, y, xVelocity, yVelocity, radius);
		}
		catch (Exception e){
			throw  new ModelException(e);
		}
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return new double[]{asteroid.getPositionX(), asteroid.getPositionY()};
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return new double[]{asteroid.getVelocityX(), asteroid.getVelocityY()};
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	/**************
	 * PLANETOID: Basic methods
	 *************/

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws ModelException {
		try{
			return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
		}
		catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return new double[]{planetoid.getPositionX(), planetoid.getPositionY()};
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return new double[]{planetoid.getVelocityX(), planetoid.getVelocityY()};
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTotalTravelledDistance();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	/**********
	 * PROGRAMS
	 **********/

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.setProgram(program);
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		try {
			return ship.executeProgram(dt);
		} catch (Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		try {
			return new ProgramFactory();
		} catch (Exception e){
			throw new ModelException(e);
		}
	}
}
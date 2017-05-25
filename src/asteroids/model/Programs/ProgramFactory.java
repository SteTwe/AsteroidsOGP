package asteroids.model.Programs;


import asteroids.model.*;
import asteroids.model.Programs.Expressions.*;
import asteroids.model.Programs.Statements.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program>{

    @Override
    public Program createProgram(List<Function> functions, Statement main) {
        return new Program(functions,main);
    }

    @Override
    public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
        return new Function(functionName, body, sourceLocation);
    }

    @Override
    public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
        return new assignmentStatement(value, variableName, sourceLocation);
    }

    @Override
    public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
        return new whileStatement(condition, body, sourceLocation);
    }

    @Override
    public Statement createBreakStatement(SourceLocation sourceLocation) {
        return new breakStatement(sourceLocation);
    }

    @Override
    public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
        return new returnStatement(value, sourceLocation);
    }

    @Override
    public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
        return new ifStatement(condition, ifBody, elseBody, sourceLocation);
    }

    @Override
    public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
        return new printStatement(value, sourceLocation);
    }

    @Override
    public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
        return new sequenceOfStatements(statements, sourceLocation);
    }


    @Override
    public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
        return new readVariable(variableName, sourceLocation);
    }

    @Override
    public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
        return new readParameter(parameterName, sourceLocation);
    }

    @Override
    public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
        return new callFunction(functionName, actualArgs, sourceLocation);
    }

    @Override
    public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
        return new changeSign(expression, sourceLocation);
    }

    @Override
    public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
        return new logicalNegation(expression, sourceLocation);
    }

    @Override
    public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
        return new constantDouble(value, location);
    }

    @Override
    public Expression createNullExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Entity evaluate() {
                return null;
            }
        };
    }

    @Override
    public Expression createSelfExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Ship evaluate() {
                return getProgram().getShip();
            }
        };
    }

    @Override
    public Expression createShipExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Ship evaluate() {
                Ship thisShip = getProgram().getShip();

                Set<Ship> ships = new HashSet<>();
                for (Entity entity :thisShip.getWorld().getEntitySet()){
                    if (entity instanceof Ship) ships.add((Ship) entity);
                }

                Optional<Ship> closest = ships.stream().filter(ship -> !ship.equals(thisShip)).
                        reduce((ship1, ship2) -> (thisShip.getDistanceBetween(ship1) < thisShip.getDistanceBetween(ship2) ? ship1 : ship2));
                if (closest.isPresent()) return closest.get();
                return null;
            }
        };
    }

    @Override
    public Expression createAsteroidExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Asteroid evaluate(){
                    Ship thisShip = getProgram().getShip();
                    Set<Asteroid> asteroids = new HashSet<>();
                for (Entity entity :thisShip.getWorld().getEntitySet()){
                    if (entity instanceof Asteroid) asteroids.add((Asteroid) entity);
                }

                    Optional<Asteroid> closest = asteroids.stream().
                        reduce((asteroid1, asteroid2) -> (thisShip.getDistanceBetween(asteroid1) < thisShip.getDistanceBetween(asteroid2) ? asteroid1 : asteroid2));
                if (closest.isPresent()) return closest.get();
                return null;
            }
        };
    }

    @Override
    public Expression createPlanetoidExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Planetoid evaluate() {
                Ship thisShip = getProgram().getShip();
                Set<Planetoid> planetoids = new HashSet<>();
                for (Entity entity :thisShip.getWorld().getEntitySet()){
                    if (entity instanceof Planetoid) planetoids.add((Planetoid) entity);
                }

                Optional<Planetoid> closest = planetoids.stream().
                        reduce((planetoid1, planetoid2) -> (thisShip.getDistanceBetween(planetoid1) < thisShip.getDistanceBetween(planetoid2) ? planetoid1 : planetoid2));
                if (closest.isPresent()) return closest.get();
                return null;
            }
        };
    }

    @Override
    public Expression createBulletExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Bullet evaluate()  {
                Ship thisShip = getProgram().getShip();
                Set<Bullet> bullets = new HashSet<>();
                for (Entity entity :thisShip.getWorld().getEntitySet()){
                    if (entity instanceof Bullet) bullets.add((Bullet) entity);
                }

                Optional<Bullet> closest = bullets.stream().
                        reduce((bullet1, bullet2) -> (thisShip.getDistanceBetween(bullet1) < thisShip.getDistanceBetween(bullet2) ? bullet1 : bullet2));
                if (closest.isPresent()) return closest.get();
                return null;
            }
        };
    }

    @Override
    public Expression createPlanetExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public MinorPlanet evaluate() throws IllegalArgumentException {
                Ship thisShip = getProgram().getShip();
                Set<MinorPlanet> planets = new HashSet<>();
                for (Entity entity :thisShip.getWorld().getEntitySet()){
                    if (entity instanceof MinorPlanet) planets.add((MinorPlanet) entity);
                }

                Optional<MinorPlanet> closest = planets.stream().
                        reduce((planet1, planet2) -> (thisShip.getDistanceBetween(planet1) < thisShip.getDistanceBetween(planet2) ? planet1 : planet2));
                if (closest.isPresent()) return closest.get();
                return null;
            }
        };
    }

    @Override
    public Expression createAnyExpression(SourceLocation location) {
        return new entity(location) {
            @Override
            public Entity evaluate(){
                Ship thisShip = getProgram().getShip();
                Optional<Entity> anyEntity = thisShip.getWorld().getEntitySet().stream().findAny();
                if (anyEntity.isPresent()) return anyEntity.get();
                return null;
            }
        };
    }

    @Override
    public Expression createGetXExpression(Expression expression, SourceLocation location) {
        return new getX(expression, location);
    }

    @Override
    public Expression createGetYExpression(Expression expression, SourceLocation location) {
        return new getY(expression, location);
    }

    @Override
    public Expression createGetVXExpression(Expression expression, SourceLocation location) {
        return new getVelocityX(expression, location);
    }

    @Override
    public Expression createGetVYExpression(Expression expression, SourceLocation location) {
        return new getVelocityY(expression, location);
    }

    @Override
    public Expression createGetDirectionExpression(SourceLocation location) {
        return new getDirection(location);
    }

    @Override
    public Expression createGetRadiusExpression(Expression expression, SourceLocation location) {
        return new getRadius(expression, location);
    }

    @Override
    public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
        return new lessThan(e1, e2, location);
    }

    @Override
    public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
        return new equalTo(e1, e2, location);
    }

    @Override
    public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
        return new addition(e1, e2, location);
    }

    @Override
    public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
        return new multiplication(e1, e2, location);
    }

    @Override
    public Expression createSqrtExpression(Expression expression, SourceLocation location) {
        return new sqrt(expression, location);
    }

    @Override
    public Statement createThrustOnStatement(SourceLocation location) {
        return new actionStatement(location) {
            @Override
            public void execute() {
                setFailedToAdvance(false);
                getProgram().setLocation(getSourceLocation());
                if (getProgram().getExecutionTimeLeft() < 0.2){
                    setFailedToAdvance(true);
                    return;
                }
                getProgram().getShip().thrustOn();
                getProgram().advanceTime();
            }
        };
    }

    @Override
    public Statement createThrustOffStatement(SourceLocation location) {
        return new actionStatement(location) {
            @Override
            public void execute() {
                setFailedToAdvance(false);
                getProgram().setLocation(getSourceLocation());
                if (getProgram().getExecutionTimeLeft() < 0.2){
                    setFailedToAdvance(true);
                    return;
                }
                getProgram().getShip().thrustOff();
                getProgram().advanceTime();
            }
        };
    }

    @Override
    public Statement createFireStatement(SourceLocation location) {
        return new actionStatement(location) {
            @Override
            public void execute() {
                setFailedToAdvance(false);
                getProgram().setLocation(getSourceLocation());
                if (getProgram().getExecutionTimeLeft() <0.2){
                    setFailedToAdvance(true);
                    return;
                }
                getProgram().getShip().fireBullet();
                getProgram().advanceTime();
            }
        };
    }

    @Override
    public Statement createTurnStatement(Expression angle, SourceLocation location) {
        return new actionStatement(location) {
            @Override
            public void execute() {
                setFailedToAdvance(false);
                getProgram().setLocation(getSourceLocation());
                if (getProgram().getExecutionTimeLeft() <0.2){
                    setFailedToAdvance(true);
                    return;
                }
                getProgram().getShip().turn((Double) angle.evaluate());
                getProgram().advanceTime();
            }
        };
    }

    @Override
    public Statement createSkipStatement(SourceLocation location) {
        return new actionStatement(location) {
            @Override
            public void execute() {
                setFailedToAdvance(false);
                getProgram().setLocation(getSourceLocation());
                if (getProgram().getExecutionTimeLeft() <0.2){
                    setFailedToAdvance(true);
                    return;
                }
                getProgram().advanceTime();
            }
        };
    }
}


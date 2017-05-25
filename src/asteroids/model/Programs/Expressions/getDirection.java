package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;


public class getDirection extends Expression<Double> {
    private Expression<?extends Ship> expression;

    public getDirection(SourceLocation location){
        super(location);
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return getProgram().getShip().getAngle();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return getProgram().getShip().getAngle();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
    }
}

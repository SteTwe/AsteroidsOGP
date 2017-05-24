package asteroids.model.Programs.Expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;


public class getDirection extends Expression<Double> {
    private Expression<?extends Ship> expression;

    public getDirection(Expression<?extends Ship> e, SourceLocation location){
        super(location);
        this.expression = e;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return expression.evaluate().getAngle();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return expression.evaluate(args, variables).getAngle();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}

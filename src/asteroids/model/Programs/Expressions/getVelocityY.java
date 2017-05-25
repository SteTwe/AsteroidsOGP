package asteroids.model.Programs.Expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class getVelocityY extends Expression<Double> {
    private Expression<?extends Entity> e;

    public getVelocityY(Expression<?extends Entity> expression, SourceLocation location){
        super(location);
        this.e = expression;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return e.evaluate().getVelocityY();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return e.evaluate(args, variables).getVelocityY();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        e.setProgram(program);
    }
}

package asteroids.model.Programs.Expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;


public class getVelocityX extends Expression<Double> {
    private Expression<?extends Entity> e;

    public getVelocityX(Expression<?extends Entity> e, SourceLocation location){
        super(location);
        this.e = e;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return e.evaluate().getVelocityX();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return e.evaluate(args, variables).getVelocityX();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        e.setProgram(program);
    }
}

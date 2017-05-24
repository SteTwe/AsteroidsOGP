package asteroids.model.Programs.Expressions;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class getX extends Expression<Double> {


    private Expression<? extends Entity> expression;


    public getX(Expression<?extends Entity> expression, SourceLocation location){
        super(location);
        this.expression = expression;
    }


    @Override
    public Double evaluate() throws IllegalArgumentException {
        return expression.evaluate().getPositionX();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return expression.evaluate(args, variables).getPositionX();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}

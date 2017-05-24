package asteroids.model.Programs.Expressions;


import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class getY extends Expression<Double> {
    private Expression<? extends Entity> expression;

    public getY(Expression<?extends Entity> expression, SourceLocation location){
        super(location);
        this.expression = expression;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return expression.evaluate().getPositionY();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return expression.evaluate(args, variables).getPositionY();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}

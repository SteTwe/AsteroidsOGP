package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class changeSign  extends Expression<Double>{
    private Expression<?extends Double> expression;

    public changeSign(Expression<? extends Double> expression, SourceLocation location){
        super(location);
        this.expression =expression;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return -expression.evaluate();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return -expression.evaluate(args, variables);
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}

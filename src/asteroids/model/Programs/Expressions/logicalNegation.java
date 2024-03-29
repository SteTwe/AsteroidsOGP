package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;


import java.util.Set;

public class logicalNegation extends Expression<Boolean> {

    private Expression<? extends Boolean> expression;

    public logicalNegation(Expression<? extends Boolean> expression, SourceLocation location){
        super(location);
        this.expression = expression;
    }


    @Override
    public Boolean evaluate() throws IllegalArgumentException {
        return !expression.evaluate();
    }

    @Override
    public Boolean evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException {
        return !expression.evaluate(actualArgs, variables);
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}
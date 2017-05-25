package asteroids.model.Programs.Statements;


import asteroids.model.Programs.Expressions.Expression;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

public class returnStatement extends  Statement{
    private Expression value;

    public returnStatement(Expression value, SourceLocation location){
        super(location);
        this.value = value;
    }

    @Override
    public void execute() {
        throw new IllegalArgumentException();
    }

    @Override
    public Optional execute(Object[] args, Set<Variable> localVariables) {
        return Optional.of(value.evaluate(args, localVariables));
    }


}

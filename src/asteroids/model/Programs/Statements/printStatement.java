package asteroids.model.Programs.Statements;


import asteroids.model.Program;
import asteroids.model.Programs.Expressions.Expression;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

public class printStatement extends Statement{
    private Expression value;

    public printStatement(Expression value, SourceLocation location){
        super(location);
        this.value = value;
    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        throw new IllegalArgumentException();
    }

    @Override
    public void execute() {
        Object evaluatedVal = value.evaluate();
        if (evaluatedVal == null) System.out.println("null");
        else System.out.println(evaluatedVal.toString());
        getProgram().addResult(evaluatedVal);
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        value.setProgram(program);

    }

    @Override
    public boolean activeBreakStatement() {
        return false;
    }

    @Override
    public boolean failedToAdvance() {
        return false;
    }
}

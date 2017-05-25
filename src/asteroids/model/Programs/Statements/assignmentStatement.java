package asteroids.model.Programs.Statements;

import asteroids.model.Program;
import asteroids.model.Programs.Expressions.Expression;
import asteroids.model.Programs.Expressions.callFunction;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;


public class assignmentStatement extends  Statement{
    private Expression value;
    private String name;
    private boolean activeBreakStatement;


    public assignmentStatement(Expression value, String name, SourceLocation location){
        super(location);
        this.name = name;
        this.value = value;
    }

    public void setActiveBreakStatement(boolean activeBreakStatement) {
        this.activeBreakStatement = activeBreakStatement;
    }

    public boolean hasActiveBreakStatement(){
        return activeBreakStatement;
    }

    @Override
    public void execute() {
        setActiveBreakStatement(false);
        try {
            getProgram().getFunction(name);
        } catch (Exception e){}
        Optional<Variable> variableToAssign = getProgram().getVariables().stream().filter(variable -> variable.getVariableName().equals(name)).findFirst();
        if (variableToAssign.isPresent()) variableToAssign.get().setValue(value.evaluate());
        else getProgram().addVariable(new Variable(name, value.evaluate()));
        if (value instanceof callFunction && ((callFunction)value).hasActiveBreak()) setActiveBreakStatement(true);

    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        Optional<Variable> variableToAssignTo = localVariables.stream().filter(variable -> variable.getVariableName().equals(name)).findFirst();
        if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate(actualArgs, localVariables));
        else localVariables.add(new Variable(name, value.evaluate(actualArgs, localVariables)));
        if (value instanceof callFunction && ((callFunction)value).hasActiveBreak()) setActiveBreakStatement(true);
        return Optional.empty();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        value.setProgram(program);
    }

    @Override
    public boolean failedToAdvance() {
        return false;
    }
}




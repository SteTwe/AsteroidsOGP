package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Function;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.List;
import java.util.Set;


public class callFunction extends Expression<Object> {
    private String functionName;
    private List<Expression> arguments;
    private boolean activeBreak;

    public callFunction(String functionName, List<Expression> arguments, SourceLocation location){
        super(location);
        this.arguments = arguments;
        this.functionName = functionName;
    }

    @Override
    public Object evaluate() throws IllegalArgumentException {
        Function function = getProgram().getFunction(functionName);
        Object[] evaluatedArgs = arguments.stream().map(args -> args.evaluate()).toArray();
        Object result = function.calculate(evaluatedArgs);
        if (function.isBreakActive()) setActiveBreak(true);
        else setActiveBreak(false);
        return  result;
    }

    @Override
    public Object evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException {
        setActiveBreak(false);
        Function function = getProgram().getFunction(functionName);
        Object[] evaluatedArgsVariables = arguments.stream().map(args -> args.evaluate(actualArgs, variables)).toArray();
        Object result = function.calculate(evaluatedArgsVariables);
        if (function.isBreakActive()) setActiveBreak(true);
        else setActiveBreak(false);
        return result;
    }

    public boolean hasActiveBreak() {
        return activeBreak;
    }

    public void setActiveBreak(boolean activeBreak) {
        this.activeBreak = activeBreak;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        for (Expression arg: arguments) arg.setProgram(program);
    }
}

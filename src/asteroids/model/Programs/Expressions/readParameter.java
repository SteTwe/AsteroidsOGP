package asteroids.model.Programs.Expressions;


import asteroids.facade.Facade;
import asteroids.model.Programs.Function;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class readParameter extends Expression<Object>{
    private String parameterName;
    private Function function;

    public readParameter(String parameterName, SourceLocation location){
        super(location);
        this.parameterName = parameterName;
    }

    @Override
    public Object evaluate() throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public Object evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException {
        int argIndex = Integer.parseInt(parameterName.substring(1, parameterName.length()));
        return actualArgs[argIndex -1];
    }


}

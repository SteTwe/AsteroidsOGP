package asteroids.model.Programs.Expressions;


import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class constantDouble  extends Expression<Double>{
    private double valueConstant;

    public constantDouble(double valueConstant, SourceLocation location){
        super(location);
        this.valueConstant = valueConstant;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return valueConstant;
    }

    //For functions
    @Override
    public Double evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException {
        return valueConstant;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
    }
}

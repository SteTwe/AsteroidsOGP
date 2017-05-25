package asteroids.model.Programs.Statements;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

/**
 * Created by joachim on 24/05/2017.
 */
public class breakStatement  extends  Statement{
    public breakStatement(SourceLocation location){
        super(location);
    }

    @Override
    public void execute() {
        return;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
    }

    public boolean hasActiveBreak(){
        return true;
    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        return Optional.empty();
    }
}

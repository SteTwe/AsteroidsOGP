package asteroids.model.Programs.Statements;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;


public class breakStatement  extends  Statement{

    public Statement breakInWhile;
    public Program program;

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

    @Override
    public boolean failedToAdvance() {
        return false;
    }
}

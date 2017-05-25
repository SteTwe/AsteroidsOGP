package asteroids.model.Programs.Statements;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

public abstract class Statement{
  private SourceLocation location;
  private Program program;
  private boolean failedToAdvance;

  protected Statement(SourceLocation location){
    this.location = location;
  }

  public abstract void execute();

  public void setProgram(Program program) {
    this.program = program;
  }

  public Program getProgram() {
    return program;
  }

  public SourceLocation getSourceLocation() {
    return location;
  }

  public boolean activeBreakStatement(){
    return false;
  }

  public abstract Optional execute(Object[] actualArgs, Set<Variable> localVariables) ;

  public boolean failedToAdvance(){
    return failedToAdvance;
  }
}
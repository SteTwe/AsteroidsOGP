package asteroids.model.Programs.Statements;

import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;
import asteroids.part3.programs.internal.generated.AsteroidsProgramParser;

import java.util.Optional;
import java.util.Set;


public abstract class actionStatement extends Statement{
    private boolean failedToAdvance;

    public actionStatement(SourceLocation location){
        super(location);
    }

    public void setFailedToAdvance(boolean failedToAdvance) {
        this.failedToAdvance = failedToAdvance;
    }

    public boolean hasFailedToAdvance() {
        return failedToAdvance;
    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        throw new IllegalArgumentException();
    }
}

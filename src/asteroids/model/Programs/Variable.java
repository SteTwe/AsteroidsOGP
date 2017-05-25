package asteroids.model.Programs;


public class Variable<T> {
    private String variableName;
    private T value;

    public Variable(String variableName, T value){
        this.variableName =variableName;
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        String string = "[Variable: " +variableName + "," + value + "]";
        return string;
    }
}

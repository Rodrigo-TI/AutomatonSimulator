package automaton;
import java.util.Objects;
public class State {
    
    // ATRIBUTES
    private String name;
    private boolean stateInitial;
    private boolean stateFinal;
    
    // CONSTRUCTOR
    public State(String name) {
        this.name = name;
        this.stateInitial = false;
        this.stateFinal = false;
    }
    public State(String name, boolean stateInitial, boolean stateFinal) {
        this.name = name;
        this.stateInitial = stateInitial;
        this.stateFinal = stateFinal;
    }

    // METHODS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    // GETTERS E SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isStateInitial() {
        return stateInitial;
    }
    public void setStateInitial(boolean stateInitial) {
        this.stateInitial = stateInitial;
    }
    public boolean isStateFinal() {
        return stateFinal;
    }
    public void setStateFinal(boolean stateFinal) {
        this.stateFinal = stateFinal;
    }
}
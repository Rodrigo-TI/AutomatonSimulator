package automaton;

import java.util.Objects;

public class Transition {
    
    // ATRIBUTES
    private State stateA;
    private State stateB;
    private String value;
    
    // CONSTRUCTOR
    public Transition(State stateA, State stateB, String value) {
        this.stateA = stateA;
        this.stateB = stateB;
        this.value = value;
    }
    
    // METHODS
    @Override
    public String toString() {
        return "("+this.stateA.getName() + "," + this.value + "," + this.stateB.getName() + ")"; 
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
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
        final Transition other = (Transition) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.stateA, other.stateA)) {
            return false;
        }
        if (!Objects.equals(this.stateB, other.stateB)) {
            return false;
        }
        return true;
    }
    
    // GETTERS E SETTERS
    public State getStateA() {
        return stateA;
    }
    public void setStateA(State stateA) {
        this.stateA = stateA;
    }
    public State getStateB() {
        return stateB;
    }
    public void setStateB(State stateB) {
        this.stateB = stateB;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
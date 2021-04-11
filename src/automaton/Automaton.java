package automaton;
import java.util.ArrayList;
public class Automaton {
    
    // ATRIBUTES
    private String typeAutomaton; // dfa(deterministic finite automaton) or ndfa(nondeterministic finite automaton)
    private State stateInitial;
    private ArrayList<State> states;
    private ArrayList<Transition> transitions;
    private final ArrayList<String> alphabet;
    
    // CONSTRUCTOR
    public Automaton(String typeAutomaton) {
        this.typeAutomaton = typeAutomaton;
        this.stateInitial = null;
        this.states = new ArrayList();
        this.transitions = new ArrayList();
        this.alphabet = new ArrayList();
    }
    
    // METHODS
    public boolean verifyWord(String word) {
        if (this.stateInitial == null) {
            return false;
        }
        if (this.typeAutomaton.equals("ndfa")) {
            this.transformationInDFA();
        }
        char[] symbols = word.toCharArray();
        State state = this.stateInitial;
        Transition transition;
        for (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < this.transitions.size(); j++) {
                transition = this.transitions.get(j);
                if ((transition.getStateA().equals(state)) && (transition.getValue().equals(String.valueOf(symbols[i])))) {
                    state = transition.getStateB();
                    break;
                }
            }
        }
        return state.isStateFinal();
    }
    public boolean validateWord(String word) {
        char[] symbols = word.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            if (!this.alphabet.contains(String.valueOf(symbols[i]))) {
                return false;
            }
        }
        return true;
    }
    public void transformationInDFA(){
        ArrayList<State> newStates = new ArrayList();
        newStates.add(this.stateInitial);
        ArrayList<Transition> newTransitions = new ArrayList();
        ArrayList<State> nextState = new ArrayList();
        nextState.add(this.stateInitial);
        ArrayList<ArrayList<State>> aux = new ArrayList();
        aux.add(nextState);
        for (int i = 0; i < aux.size(); i++) {
            for (int k = 0; k < this.alphabet.size(); k++) {
                ArrayList<State> set = new ArrayList();
                for (int l = 0; l < aux.get(i).size(); l++) {
                    for (int m = 0; m < this.transitions.size(); m++) {
                        boolean condition1 = this.transitions.get(m).getStateA().equals(aux.get(i).get(l));
                        boolean condition2 = this.transitions.get(m).getValue().equals(this.alphabet.get(k));
                        if (condition1 && condition2) {
                            set.add(this.transitions.get(m).getStateB());
                        }
                    }
                }
                if (!set.isEmpty()) {
                    String nameNewStateB = "";
                    for (int l = 0; l < set.size(); l++) {
                        nameNewStateB += set.get(l).getName();
                    }
                    State newStateB = new State(nameNewStateB);
                    if (!newStates.contains(newStateB)) {
                        for (int l = 0; l < set.size(); l++) {
                            if (set.get(l).isStateFinal()) {
                                newStateB.setStateFinal(true);
                                break;
                            }
                        }
                        newStates.add(newStateB);
                        aux.add(set);
                    }
                    else {
                        newStateB = newStates.get(newStates.indexOf(newStateB));
                    }

                    String nameNewStateA = "";
                    for (int l = 0; l < aux.get(i).size(); l++) {
                        nameNewStateA += aux.get(i).get(l).getName();
                    }
                    State newStateA = newStates.get(newStates.indexOf(new State(nameNewStateA)));
                    Transition newTransition = new Transition(newStateA,newStateB,this.alphabet.get(k));
                    if (!newTransitions.contains(newTransition)) {
                        newTransitions.add(newTransition);
                    }
                }
            }
        }
        this.states = newStates;
        this.transitions = newTransitions;
        this.typeAutomaton = "dfa";
    }
    public boolean addState(State s) {
        if (!this.stateExist(s.getName())) {
            this.states.add(s);
            if (s.isStateInitial() && this.stateInitial == null) {
                this.stateInitial = s;
            }
            return true;
        }
        return false;
    }
    public boolean addTransition(Transition t) {
        boolean condition = !(this.transitionExist(t.getStateA().getName(), t.getValue(), t.getStateB().getName()));
        if (condition) {
            this.transitions.add(t);
            return true;
        }
        return false;
    }
    public State removeState(State s) {
        if (!stateExist(s.getName())) {
            return null;
        }
        State stateRemoved = this.getState(s.getName());
        for (int i = 0; i < this.transitions.size(); i++) {
            if (this.transitions.get(i).getStateA().equals(s) || this.transitions.get(i).getStateB().equals(s)) {
                this.transitions.remove(this.transitions.get(i));
            }
        }
        this.states.remove(stateRemoved);
        if ((this.stateInitial != null) && (this.stateInitial.equals(stateRemoved))) {
            this.stateInitial = null;
        }
        return stateRemoved;
    }
    public Transition removeTransition(Transition t) {
        if (!transitionExist(t.getStateA().getName(), t.getValue(), t.getStateB().getName())) {
            return null;
        }
        Transition transitionRemoved = this.getTransition(t.getStateA().getName(), t.getValue(), t.getStateB().getName());
        this.transitions.remove(transitionRemoved);
        return transitionRemoved;
    }
    public boolean stateExist(String nameState) {
        State state = new State(nameState);
        boolean teste = this.states.contains(state);
        return teste;
    }
    public boolean transitionExist(String nameStateA, String value, String nameStateB) {
        boolean condition1;
        boolean condition2;
        boolean condition3;
        if (this.typeAutomaton.equals("dfa")) {
            for (int i = 0; i < this.transitions.size(); i++) {
                condition1 = (this.transitions.get(i).getStateA().getName().equals(nameStateA));
                condition2 = (this.transitions.get(i).getValue().equals(value));
                if (condition1 && condition2) {
                    return true;
                }
            }
        }
        else if (this.typeAutomaton.equals("ndfa")) {
            for (int i = 0; i < this.transitions.size(); i++) {
                condition1 = (this.transitions.get(i).getStateA().getName().equals(nameStateA));
                condition2 = (this.transitions.get(i).getValue().equals(value));
                condition3 = (this.transitions.get(i).getStateB().getName().equals(nameStateB));
                if (condition1 && condition2 && condition3) {
                    return true;
                }
            }
        }
        return false;
    }
    public String listAlphabet() {
        if (this.alphabet.isEmpty()) {
            return "Não existem símbolos nesse alfabeto";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.alphabet.get(0));
        for (int i = 1; i < this.alphabet.size(); i++) {
            sb.append(", ").append(this.alphabet.get(i));
        }
        return sb.toString();
    }
    public String listStates() {
        if (this.states.isEmpty()) {
            return "Não existem estados nesse autômato.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.states.get(0).getName());
        for (int i = 1; i < this.states.size(); i++) {
            sb.append(", ").append(this.states.get(i).getName());
        }
        return sb.toString();
    }
    public String listInicialsStates() {
        if (this.states.isEmpty()) {
            return "Não existem estados nesse autômato.";
        }
        StringBuilder sb = new StringBuilder();
        if (this.states.get(0).isStateInitial()) {
            sb.append(this.states.get(0).getName());
        }
        for (int i = 1; i < this.states.size(); i++) {
            if (this.states.get(i).isStateInitial()) {
                sb.append(this.states.get(i).getName());
            }
        }
        if (sb.toString().equals("")) {
            return "Não existem estados iniciais nesse autômato.";
        }
        return sb.toString();
    }
    public String listFinalsStates() {
        if (this.states.isEmpty()) {
            return "Não existem estados nesse autômato.";
        }
        StringBuilder sb = new StringBuilder();
        if (this.states.get(0).isStateFinal()) {
            sb.append(this.states.get(0).getName());
        }
        for (int i = 1; i < this.states.size(); i++) {
            if (this.states.get(i).isStateFinal()) {
                sb.append(this.states.get(i).getName());
            }
        }
        if (sb.toString().equals("")) {
            return "Não existem estados finais nesse autômato.";
        }
        return sb.toString();
    }
    public String listTransitions() {
        if (this.transitions.isEmpty()) {
            return "Não existem transições nesse autômato.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.transitions.get(0).toString());
        for (int i = 1; i < this.transitions.size(); i++) {
            sb.append(", ").append(this.transitions.get(i).toString());
        }
        return sb.toString();
    }
    public State getState(String nameState) {
        for (int i = 0; i < this.states.size(); i++) {
            if (this.states.get(i).getName().equals(nameState)) {
                return this.states.get(i);
            }
        }
        return null;
    }
    public Transition getTransition(String nameStateA, String value, String nameStateB) {
        boolean condition1;
        boolean condition2;
        boolean condition3;
        for (int i = 0; i < this.transitions.size(); i++) {
            condition1 = (this.transitions.get(i).getStateA().getName().equals(nameStateA));
            condition2 = (this.transitions.get(i).getValue().equals(value));
            condition3 = (this.transitions.get(i).getStateB().getName().equals(nameStateB));
            if (condition1 && condition2 && condition3) {
                return this.transitions.get(i);
            }
        }
        return null;
    }
    
    // GETTERS E SETTERS
    public String getTypeAutomaton() {
        return typeAutomaton;
    }
    public void setTypeAutomaton(String typeAutomaton) {
        this.typeAutomaton = typeAutomaton;
    }
    public State getStateInitial() {
        return stateInitial;
    }
    public void setStateInitial(State stateInitial) {
        this.stateInitial = stateInitial;
    }
    public ArrayList<State> getStates() {
        return states;
    }
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }
    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
}
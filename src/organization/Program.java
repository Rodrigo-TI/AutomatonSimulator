package organization;
import automaton.Automaton;
import automaton.State;
import automaton.Transition;
import java.util.Scanner;
public class Program {
    
    // ATRIBUTES
    private final Scanner s = new Scanner(System.in);
    private Automaton a;
    
    // METHODS
    public void start() {
        int option = -1;
        while (option != 0) {
            try {
                System.out.print(this.initialMenu());
                option = Integer.parseInt(s.nextLine());
                switch (option) {
                    case 1:
                        this.creatingAutomaton();
                        break;
                    case 0:
                        System.out.println("\nPROGRAMA FINALIZADO COM SUCESSO !");
                        break;
                    default:
                        System.out.println("\nEssa opção não existe !");
                        break;
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("\nOpção inválida !");
                }
            }
        }
    }
    public void creatingAutomaton() {
        int option = -1;
        while (option != 0) {
            try {
                System.out.print(this.typeAutomatonMenu());
                option = Integer.parseInt(s.nextLine());
                switch (option) {
                    case 1:
                        // criar automato finito deterministico
                        this.a = new Automaton("dfa");
                        System.out.println("\nAutômato Finito Determinístico criado com sucesso !");
                        this.usingAutomaton();
                        break;
                    case 2:
                        // criar automato finito nao deterministico
                        this.a = new Automaton("ndfa");
                        System.out.println("\nAutômato Finito Não-Determinístico criado com sucesso !");
                        this.usingAutomaton();
                        break;
                    case 0:
                        System.out.println("\nCriação de autômato cancelada !");
                        break;
                    default:
                        System.out.println("\nEssa opção não existe !");
                        break;
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("\nOpção inválida !");
                }
            }
        }
    }
    public void usingAutomaton() {
        int option = -1;
        while (option != 0) {
            try {
                System.out.print(this.optionsAutomatonMenu());
                option = Integer.parseInt(s.nextLine());
                switch (option) {
                    case 1: // Verificar uma palavra
                        System.out.print("\nPalavra : ");
                        String word = s.nextLine();
                        if ((!this.a.validateWord(word)) || (word.isEmpty())) {
                            System.out.println("\nERRO ! Algum símbolo da palavra não faz parte do alfabeto do autômato !");
                        }
                        else {
                            if (this.a.getStateInitial() == null) {
                                System.out.println("\nERRO ! O autômato não possui um estado inicial !");
                            }
                            else {
                                if (this.a.getTypeAutomaton().equals("ndfa")) {
                                    System.out.println("\nO Autômato Finito Não-Determinístico :");
                                    System.out.println("Estados : " + this.a.listStates());
                                    System.out.println("Estado Inicial : " + this.a.listInicialsStates());
                                    System.out.println("Estados Finais : " + this.a.listFinalsStates());
                                    System.out.println("Transições : " + this.a.listTransitions());
                                    this.a.transformationInDFA();
                                    System.out.println("\nFoi transformado em um Autômato Finito Determinístico :");
                                    System.out.println("Estados : " + this.a.listStates());
                                    System.out.println("Estado Inicial : " + this.a.listInicialsStates());
                                    System.out.println("Estados Finais : " + this.a.listFinalsStates());
                                    System.out.println("Transições : " + this.a.listTransitions());
                                }
                                if (this.a.verifyWord(word)) {
                                    System.out.println("\nA palavra foi ACEITA !");
                                }
                                else {
                                    System.out.println("\nA palavra foi RECUSADA !");
                                }
                            }
                        }
                        break;
                    case 2: // Inserir símbolo no alfabeto do autômato
                        System.out.print("\nSímbolo : ");
                        String symbolAdd = s.nextLine();
                        if (this.a.getAlphabet().contains(symbolAdd)) {
                            System.out.println("\nERRO ! O símbolo informado JÁ ESTÁ presente no alfabeto do autômato !");
                        }
                        else {
                            this.a.getAlphabet().add(symbolAdd);
                            System.out.println("\nO símbolo '"+symbolAdd+"' foi inserido com sucesso no alfabeto do autômato !");
                        }
                        break;
                    case 3: // Remover símbolo no alfabeto do autômato
                        System.out.print("\nSímbolo : ");
                        String symbolRemove = s.nextLine();
                        if (!this.a.getAlphabet().contains(symbolRemove)) {
                            System.out.println("\nERRO ! O símbolo informado NÃO ESTÁ presente no alfabeto do autômato !");
                        }
                        else {
                            this.a.getAlphabet().remove(symbolRemove);
                            System.out.println("\nO símbolo '"+symbolRemove+"' foi removido com sucesso do alfabeto do autômato !");
                        }
                        break;
                    case 4: // Mostrar alfabeto do autômato
                        System.out.println("\nALFABETO DO AUTÔMATO :");
                        System.out.println(this.a.listAlphabet());
                        break;
                    case 5: // Adicionar um estado
                        System.out.print("\nNome do estado : ");
                        String nameStateAdd = s.nextLine();
                        if (this.a.stateExist(nameStateAdd)) {
                            System.out.println("\nERRO ! Já existe um estado com esse nome !");
                        }
                        else {
                            State state = new State(nameStateAdd);
                            if (this.a.getStateInitial() == null) {
                                System.out.print("O estado é inicial (s/n) ? ");
                                String stateIsInitial = s.nextLine();
                                while (!(stateIsInitial.toLowerCase().equals("s")) && !(stateIsInitial.toLowerCase().equals("n"))) {
                                    System.out.println("\nOpção inválida !\n");
                                    System.out.print("O estado é inicial (s/n) ? ");
                                    stateIsInitial = s.nextLine();
                                }
                                if (stateIsInitial.toLowerCase().equals("s")) {
                                    state.setStateInitial(true);
                                    this.a.setStateInitial(state);
                                }
                            }
                            System.out.print("O estado é final (s/n) ? ");
                            String stateIsFinal = s.nextLine();
                            while (!(stateIsFinal.toLowerCase().equals("s")) && !(stateIsFinal.toLowerCase().equals("n"))) {
                                System.out.println("\nOpção inválida !\n");
                                System.out.print("O estado é final (s/n) ? ");
                                stateIsFinal = s.nextLine();
                            }
                            if (stateIsFinal.toLowerCase().equals("s")) {
                                state.setStateFinal(true);
                            }
                            if (a.addState(state) == true) {
                                System.out.println("\nO estado '"+state.getName()+"' foi adicionado com sucesso !");
                            }
                            else {
                                System.out.println("\nFalha ao adicionar o estado '"+state.getName()+"' !");
                            }
                        }
                        break;
                    case 6: // Adicionar uma transição
                        System.out.print("\nA transição está saindo do estado : ");
                        String nameStateAAddTransition = s.nextLine();
                        State stateAAddTransition = this.a.getState(nameStateAAddTransition);
                        if (stateAAddTransition == null) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            System.out.print("A transição está chegando no estado : ");
                            String nameStateBAddTransition = s.nextLine();
                            State stateBAddTransition = this.a.getState(nameStateBAddTransition);
                            if (stateBAddTransition == null) {
                                System.out.println("\nERRO ! Não existe estado com esse nome !");
                            }
                            else {
                                System.out.print("Valor da transição : ");
                                String valueTransition = s.nextLine();
                                if (this.a.addTransition(new Transition(stateAAddTransition,stateBAddTransition,valueTransition)) == true) {
                                    System.out.println("\nA transição de '"+nameStateAAddTransition+"' para '"+nameStateBAddTransition+"' ao ler '"+valueTransition+"' foi adicionada com sucesso !");
                                }
                                else {
                                    if (this.a.getTypeAutomaton().equals("dfa")) {
                                        System.out.println("\nERRO ! Já existe uma transição saindo de '"+nameStateAAddTransition+"' ao ler '"+valueTransition+"' !");
                                    }
                                    else if (this.a.getTypeAutomaton().equals("ndfa")) {
                                        System.out.println("\nERRO ! Já existe uma transição de '"+nameStateAAddTransition+"' para '"+nameStateBAddTransition+"' ao ler '"+valueTransition+"' !");
                                    }
                                }
                            }
                        }
                        break;
                    case 7: // Remover um estado
                        System.out.print("\nNome do estado : ");
                        String nameStateRemove = s.nextLine();
                        if (!this.a.stateExist(nameStateRemove)) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            if (this.a.removeState(new State(nameStateRemove)) != null) {
                                System.out.println("\nO estado '"+nameStateRemove+"' foi removido com sucesso !");
                            }
                            else {
                                System.out.println("\nFalha ao remover o estado '"+nameStateRemove+"' !");
                            }
                        }
                        break;
                    case 8: // Remover uma transição
                        System.out.print("\nA transição está saindo do estado : ");
                        String nameStateARemoveTransition = s.nextLine();
                        if (!this.a.stateExist(nameStateARemoveTransition)) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            System.out.print("A transição está chegando no estado : ");
                            String nameStateBRemoveTransition = s.nextLine();
                            if (!this.a.stateExist(nameStateBRemoveTransition)) {
                                System.out.println("\nERRO ! Não existe estado com esse nome !");
                            }
                            else {
                                System.out.print("Valor da transição : ");
                                String valueTransition = s.nextLine();
                                if (this.a.removeTransition(new Transition(new State(nameStateARemoveTransition), new State(nameStateBRemoveTransition), valueTransition)) != null) {
                                    System.out.println("\nA transição de '"+nameStateARemoveTransition+"' para '"+nameStateBRemoveTransition+"' ao ler '"+valueTransition+"' foi removida com sucesso !");
                                }
                                else {
                                    System.out.println("\nERRO ! Não existe uma transição de '"+nameStateARemoveTransition+"' para '"+nameStateBRemoveTransition+"' ao ler '"+valueTransition+"' !");
                                }
                            }
                        }
                        break;
                    case 9: // Checar existência de um estado
                        System.out.print("\nNome do estado : ");
                        String nameState = s.nextLine();
                        if (this.a.stateExist(nameState) == true) {
                            System.out.println("\nO estado '"+nameState+"' ESTÁ presente no autômato !");
                        }
                        else {
                            System.out.println("\nO estado '"+nameState+"' NÃO ESTÁ presente no autômato !");
                        }
                        break;
                    case 10: // Checar existência de uma transição
                        System.out.print("\nA transição está saindo do estado : ");
                        String nameStateA = s.nextLine();
                        if (!this.a.stateExist(nameStateA)) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            System.out.print("A transição está chegando no estado : ");
                            String nameStateB = s.nextLine();
                            if (!this.a.stateExist(nameStateB)) {
                                System.out.println("\nERRO ! Não existe estado com esse nome !");
                            }
                            else {
                                System.out.print("Valor da transição : ");
                                String valueTransition = s.nextLine();
                                if (this.a.transitionExist(nameStateA,valueTransition,valueTransition) == true) {
                                    System.out.println("\nA transição de '"+nameStateA+"' para '"+nameStateB+"' ao ler '"+valueTransition+"' ESTÁ presente no autômato !");
                                }
                                else {
                                    System.out.println("\nA transição de '"+nameStateA+"' para '"+nameStateB+"' ao ler '"+valueTransition+"' NÃO ESTÁ presente no autômato !");
                                }
                            }
                        }
                        break;
                    case 11: // Colocar um estado como 'estado inicial'
                        State stateInitial = this.a.getStateInitial();
                        if (stateInitial != null) {
                            System.out.println("\nO autômato já possui o estado '"+stateInitial+"' como estado inicial.");
                        }
                        else {
                            System.out.print("\nNome do estado : ");
                            String nameStateInitial = s.nextLine();
                            stateInitial = this.a.getState(nameStateInitial);
                            if (stateInitial == null) {
                                System.out.println("\nERRO ! Não existe estado com esse nome !");
                            }
                            else {
                                if (stateInitial.isStateInitial()) {
                                    System.out.println("\nERRO ! O estado '"+stateInitial.getName()+"' já se encontra como inicial !");
                                }
                                else {
                                    stateInitial.setStateInitial(true);
                                    this.a.setStateInitial(stateInitial);
                                    System.out.println("\nO estado '"+stateInitial.getName()+"' se tornou o estado inicial do autômato !");
                                }
                            }
                        }
                        break;
                    case 12: // Colocar um estado como 'estado final'
                        System.out.print("\nNome do estado : ");
                        String nameStateFinal = s.nextLine();
                        State stateFinal = this.a.getState(nameStateFinal);
                        if (stateFinal == null) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            if (stateFinal.isStateFinal()) {
                                System.out.println("\nERRO ! O estado '"+stateFinal.getName()+"' já se encontra como final !");
                            }
                            else {
                                stateFinal.setStateFinal(true);
                                System.out.println("\nO estado '"+stateFinal.getName()+"' agora é um estado final !");
                            }
                        }
                        break;
                    case 13: // Tirar o tipo 'estado inicial' de um estado
                        System.out.print("\nNome do estado : ");
                        String nameStateRemoveTypeInitialState = s.nextLine();
                        State stateRemoveTypeInitial = this.a.getState(nameStateRemoveTypeInitialState);
                        if (stateRemoveTypeInitial == null) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            if (stateRemoveTypeInitial.equals(this.a.getStateInitial())) {
                                this.a.setStateInitial(null);
                                stateRemoveTypeInitial.setStateInitial(false);
                                System.out.println("\nO estado '"+stateRemoveTypeInitial.getName()+"' não é mais um estado inicial !");
                            }
                            else {
                                System.out.println("\nERRO ! O estado '"+stateRemoveTypeInitial.getName()+"' já se encontra como não-inicial !");
                            }
                        }
                        break;
                    case 14: // Tirar o tipo 'estado final' de um estado
                        System.out.print("\nNome do estado : ");
                        String nameStateRemoveTypeFinalState = s.nextLine();
                        State stateRemoveTypeFinal = this.a.getState(nameStateRemoveTypeFinalState);
                        if (stateRemoveTypeFinal == null) {
                            System.out.println("\nERRO ! Não existe estado com esse nome !");
                        }
                        else {
                            if (stateRemoveTypeFinal.isStateFinal()) {
                                stateRemoveTypeFinal.setStateFinal(false);
                                System.out.println("\nO estado '"+stateRemoveTypeFinal.getName()+"' não é mais um estado final !");
                            }
                            else {
                                System.out.println("\nERRO ! O estado '"+stateRemoveTypeFinal.getName()+"' já se encontra como não-final !");
                            }
                        }
                        break;
                    case 15: // Mostrar estados do autômato
                        System.out.println("\nLISTA DE ESTADOS :");
                        System.out.println(this.a.listStates());
                        break;
                    case 16: // Mostrar transições do autômato
                        System.out.println("\nLISTA DE TRANSIÇÕES :");
                        System.out.println(this.a.listTransitions());
                        break;
                    case 17: // Mostrar estado inicial do autômato
                        System.out.println("\nLISTA DE ESTADOS INICIAIS :");
                        System.out.println(this.a.listInicialsStates());
                        break;
                    case 18: // Mostrar estados finais do autômato
                        System.out.println("\nLISTA DE ESTADOS FINAIS :");
                        System.out.println(this.a.listFinalsStates());
                        break;
                    case 0: // Destruir autômato
                        System.out.println("\nAutômato destruído com sucesso !");
                        break;
                    default:
                        System.out.println("\nEssa opção não existe !");
                        break;
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("\nOpção inválida !");
                }
            }
        }
    }
    public String initialMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n1 - Criar autômato\n");
        sb.append("0 - Finalizar programa\n");
        sb.append("------------------------\n");
        sb.append("Opção escolhida : ");
        return sb.toString();
    }
    public String typeAutomatonMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n1 - Autômato Finito Determinístico\n");
        sb.append("2 - Autômato Finito Não-Determinístico\n");
        sb.append("0 - Voltar\n");
        sb.append("-------------------------------------------\n");
        sb.append("Opção escolhida : ");
        return sb.toString();
    }
    public String optionsAutomatonMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  1 - Verificar palavra\n");
        sb.append("  2 - Inserir símbolo no alfabeto do autômato\n");
        sb.append("  3 - Remover símbolo do alfabeto do autômato\n");
        sb.append("  4 - Mostrar alfabeto do autômato\n");
        sb.append("  5 - Adicionar um estado\n");
        sb.append("  6 - Adicionar uma transição\n");
        sb.append("  7 - Remover um estado\n");
        sb.append("  8 - Remover uma transição\n");
        sb.append("  9 - Checar existência de um estado\n");
        sb.append("10 - Checar existência de uma transição\n");
        sb.append("11 - Colocar um estado como 'estado inicial'\n");
        sb.append("12 - Colocar um estado como 'estado final'\n");
        sb.append("13 - Tirar o tipo 'estado inicial' de um estado\n");
        sb.append("14 - Tirar o tipo 'estado final' de um estado\n");
        sb.append("15 - Mostrar estados do autômato\n");
        sb.append("16 - Mostrar transições do autômato\n");
        sb.append("17 - Mostrar estado inicial do autômato\n");
        sb.append("18 - Mostrar estados finais do autômato\n");
        sb.append("  0 - Destruir autômato\n");
        sb.append("-------------------------------------------------\n");
        sb.append("Opção escolhida : ");
        return sb.toString();
    }
}
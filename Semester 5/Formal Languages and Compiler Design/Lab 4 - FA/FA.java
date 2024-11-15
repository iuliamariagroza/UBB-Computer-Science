package FA;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FA {
    private final List<String> states;
    private final List<String> alphabet;
    private String initialState;
    private final List<String> finalStates;
    private final List<Transition> transitions;
    private final String fileName;

    public FA(String filePath) {
        this.fileName = filePath;
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.transitions = new ArrayList<>();
        this.initialState = "";
        try {
            loadAutomatonFromFile();
        } catch (IOException e) {
            System.err.println("Error initializing Finite Automaton: " + e.getMessage());
        }
    }

    public boolean isDeterministic() {
        Map<String, String> seenTransitions = new HashMap<>();
        for (Transition transition : transitions) {
            String key = transition.getFromState() + "," + transition.getLabel();
            if (seenTransitions.containsKey(key)) {
                return false;
            }
            seenTransitions.put(key, transition.getToState());
        }
        return true;
    }

    private void loadAutomatonFromFile() throws IOException {
        File file = new File(this.fileName);
        BufferedReader reader = Files.newBufferedReader(file.toPath());
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");
            String key = parts[0].trim();
            switch (key) {
                case "states":
                    String[] statesArray = parts[1].trim().split(",");
                    Collections.addAll(states, statesArray);
                    break;

                case "alphabet":
                    String[] alphabetArray = parts[1].trim().split(",");
                    Collections.addAll(alphabet, alphabetArray);
                    break;

                case "initial state":
                    initialState = parts[1].trim();
                    break;

                case "final states":
                    String[] finalStatesArray = parts[1].trim().split(",");
                    Collections.addAll(finalStates, finalStatesArray);
                    break;

                case "transitions":
                    String[] transitionsArray = parts[1].trim().split(",");
                    for (String transitionString : transitionsArray) {
                        String[] elements = transitionString.trim().split(" ");
                        if(elements.length < 3 || elements[2].isEmpty() || elements[2].equals("ε")) {
                            throw new IllegalArgumentException("Invalid transition detected: " + transitionString);
                        }
                        Transition transition = new Transition(elements[0], elements[2], elements[1]);
                        transitions.add(transition);
                    }
                    break;
            }
        }
    }

    private void printListAsFormattedString(String label, List<String> list) {
        System.out.print(label + " = {");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.println("}");
    }

    public void displayStates() {
        printListAsFormattedString("States", states);
    }

    public void displayAlphabet() {
        printListAsFormattedString("Alphabet", alphabet);
    }

    public void displayFinalStates() {
        printListAsFormattedString("Final States", finalStates);
    }

    public void displayInitialState() {
        System.out.println("Initial State = " + initialState);
    }

    public void displayTransitions() {
        System.out.print("Transitions = {");
        for (int i = 0; i < transitions.size(); i++) {
            if (i != transitions.size() - 1) {
                System.out.print("(" + transitions.get(i).getFromState() + ", " + transitions.get(i).getToState() + ", " + transitions.get(i).getLabel() + "); ");
            } else {
                System.out.print("(" + transitions.get(i).getFromState() + ", " + transitions.get(i).getToState() + ", " + transitions.get(i).getLabel() + ")");
            }
        }
        System.out.println("}");
    }

    public boolean isWordAccepted(String word) {
        String currentState = initialState;
        for (char symbol : word.toCharArray()) {
            boolean transitionFound = false;
            for (Transition transition : transitions) {
                if (transition.getFromState().equals(currentState) && transition.getLabel().equals(String.valueOf(symbol))) {
                    currentState = transition.getToState();
                    transitionFound = true;
                    break;
                }
            }
            if (!transitionFound) {
                return false;
            }
        }
        return finalStates.contains(currentState);
    }

    public String getAcceptedSubstring(String word) {
        String currentState = initialState;
        StringBuilder acceptedSubstring = new StringBuilder();
        for (char symbol : word.toCharArray()) {
            String nextState = null;
            for (Transition transition : transitions) {
                if (transition.getFromState().equals(currentState) && transition.getLabel().equals(String.valueOf(symbol))) {
                    nextState = transition.getToState();
                    acceptedSubstring.append(symbol);
                    break;
                }
            }
            if (nextState == null) {
                if (!finalStates.contains(currentState)) {
                    return null;
                } else {
                    return acceptedSubstring.toString();
                }
            }
            currentState = nextState;
        }
        return acceptedSubstring.toString();
    }
}

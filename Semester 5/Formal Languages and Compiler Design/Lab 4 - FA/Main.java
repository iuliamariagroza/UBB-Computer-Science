package FA;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        var fa = new FA("src/Files/FA.in");

        if(!fa.isDeterministic()){
            System.out.println("The finite automaton is non-deterministic.");
            return;
        }

        System.out.println("1. Print states");
        System.out.println("2. Print alphabet");
        System.out.println("3. Print final states");
        System.out.println("4. Print initial state");
        System.out.println("5. Print transactions");
        System.out.println("6. Check word");
        System.out.println("7. Get matching substring");
        System.out.println("0. Exit");

        while (true) {
            System.out.print(">>");
            var option = new java.util.Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    fa.displayStates();
                    break;

                case 2:
                    fa.displayAlphabet();
                    break;

                case 3:
                    fa.displayFinalStates();
                    break;

                case 4:
                    fa.displayInitialState();
                    break;
                case 5:
                    fa.displayTransitions();
                    break;

                case 6:
                    var word = new java.util.Scanner(System.in).nextLine();
                    System.out.println(fa.isWordAccepted(word));
                    break;
                case 7:
                    word = new java.util.Scanner(System.in).nextLine();
                    var accepted = fa.getAcceptedSubstring(word);
                    if (Objects.equals(accepted, "")) {
                        System.out.println("No matching substring");
                    } else {
                        System.out.println(accepted);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

    }
}

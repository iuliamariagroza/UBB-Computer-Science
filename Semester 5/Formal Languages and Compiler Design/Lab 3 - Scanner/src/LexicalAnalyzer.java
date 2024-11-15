import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.util.Objects;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private String program;
    private final List<String> tokens;
    private final List<String> reservedWords;
    private SymbolTable symbolTable;
    private int index = 0;
    private int currentLine = 1;
    private List<Pairs<String, Pairs<Integer, Integer>>> PIF;
    private int lexicalErrorCount = 0; // Counter for lexical errors

    public LexicalAnalyzer() {
        this.symbolTable = new SymbolTable(100);
        this.PIF = new ArrayList<>();
        this.reservedWords = new ArrayList<>();
        this.tokens = new ArrayList<>();
        try {
            readTokens();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProgram(String program) {
        this.program = program;
    }

    private void readTokens() throws IOException {
        File file = new File("src/Files/Tokens.in");
        BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "int", "read", "daca", "incelalaltcaz", "print", "cattimp", "ramasita", "impreunat", "inmultit" -> reservedWords.add(parts[0]);
                default -> tokens.add(parts[0]);
            }
        }
    }

    private void skipSpaces() {
        while (index < program.length() && Character.isWhitespace(program.charAt(index))) {
            if (program.charAt(index) == '\n') {
                currentLine++;
            }
            index++;
        }
    }

private boolean treatIntConstant() {
    var regexForIntConstant = Pattern.compile("^([+-]?[1-9][0-9]*|0)");
    var matcher = regexForIntConstant.matcher(program.substring(index));
    if (!matcher.find()) {
        return false;
    }
    if (Pattern.compile("^([+-]?[1-9][0-9]*|0)[a-zA-Z_]").matcher(program.substring(index)).find()) {
        return false;
    }
    var intConstant = matcher.group(1);
    index += matcher.group(0).length();
    int integerConstant;

    try {
        integerConstant = Integer.parseInt(intConstant);
    } catch (NumberFormatException e) {
        System.out.println("Error parsing integer constant at line " + currentLine + ", index " + index);
        return false;
    }

    Pairs<Integer, Integer> position;
    try {
        position = symbolTable.addConstant(integerConstant);
    } catch (Exception e) {
        position = symbolTable.findPositionConstant(integerConstant);
        System.out.println("Constant already in symbol table, found at position: " + position);
    }

    PIF.add(new Pairs<>("const", position));
    return true;
}


//    private boolean treatStringConstant() throws Exception {
//        var regexForStringConstant = Pattern.compile("^\"[a-zA-z0-9_ ?:*^+=.!]*\"");
//        var matcher = regexForStringConstant.matcher(program.substring(index));
//        if (!matcher.find()) {
//            if (Pattern.compile("^\"[^\"]\"").matcher(program.substring(index)).find()) {
//                throw new Exception("Invalid string constant at line " + currentLine);
//            }
//            if (Pattern.compile("^\"[^\"]").matcher(program.substring(index)).find()) {
//                throw new Exception("Missing \" at line " + currentLine);
//            }
//            return false;
//        }
//        var stringConstant = matcher.group(0);
//        index += stringConstant.length();
//        Pairs<Integer, Integer> position;
//        try {
//            position = symbolTable.addConstant(stringConstant);
//        } catch (Exception e) {
//            position = symbolTable.findPositionConstant(stringConstant);
//        }
//        PIF.add(new Pairs<>("const", position));
//        return true;
//    }

    private boolean checkIfValid(String possibleIdentifier, String programSubstring) {
        if (reservedWords.contains(possibleIdentifier)) {
            return false;
        }
        if (Pattern.compile("^[A-Za-z_][A-Za-z0-9_]*\\s+oftype int|char|str").matcher(programSubstring).find()) {
            return true;
        }
        return symbolTable.hasIdentifier(possibleIdentifier);
    }

    private boolean treatIdentifier() {
        var regexForIdentifier = Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*)");
        var matcher = regexForIdentifier.matcher(program.substring(index));

        if (Character.isDigit(program.charAt(index))) {
            while (index < program.length() && !Character.isWhitespace(program.charAt(index))) {
                index++;
            }
            return false;
        }

        if (!matcher.find()) {
            return false;
        }
        var identifier = matcher.group(1);

        if (!checkIfValid(identifier, program.substring(index))) {
            return false;
        }
        index += identifier.length();
        Pairs<Integer, Integer> position;
        try {
            position = symbolTable.addIdentifier(identifier);
        } catch (Exception e) {
            position = symbolTable.findPositionIdentifier(identifier);
        }
        PIF.add(new Pairs<>("identifier", position));
        return true;
    }

    private boolean treatFromTokenList() {
        String possibleToken = program.substring(index).split(" ")[0];
        for (var reservedToken : reservedWords) {
            if (possibleToken.startsWith(reservedToken)) {
                var regex = "^" + "[a-zA-Z0-9_]*" + reservedToken + "[a-zA-Z0-9_]+";
                if (Pattern.compile(regex).matcher(possibleToken).find()) {
                    return false;
                }
                index += reservedToken.length();
                PIF.add(new Pairs<>(reservedToken, new Pairs<>(-1, -1)));
                return true;
            }
        }
        for (var token : tokens) {
            if (Objects.equals(token, possibleToken)) {
                index += token.length();
                PIF.add(new Pairs<>(token, new Pairs<>(-1, -1)));
                return true;
            } else if (possibleToken.startsWith(token)) {
                index += token.length();
                PIF.add(new Pairs<>(token, new Pairs<>(-1, -1)));
                return true;
            }
        }
        return false;
    }

    private void nextToken() {
        skipSpaces();
        if (index == program.length()) {
            return;
        }

        try {
            if (treatIdentifier()) {
                return;
            }
            if (treatIntConstant()) {
                return;
            }
//            if (treatStringConstant()) {
//                return;
//            }
            if (treatFromTokenList()) {
                return;
            }

            lexicalErrorCount++;
            System.out.println("Lexical error: invalid token at line " + currentLine + ", index " + index);

            while (index < program.length() && !Character.isWhitespace(program.charAt(index))) {
                index++;
            }
        } catch (Exception e) {
            lexicalErrorCount++;
            System.out.println("Lexical error: " + e.getMessage() + " at line " + currentLine + ", index " + index);

            while (index < program.length() && !Character.isWhitespace(program.charAt(index))) {
                index++;
            }
        }
    }

    public void scan(String programFileName) {
        try {
            Path file = Path.of("src/Files/" + programFileName);
            setProgram(Files.readString(file));
            index = 0;
            PIF = new ArrayList<>();
            symbolTable = new SymbolTable(100);
            currentLine = 1;

            while (index < program.length()) {
                nextToken();
            }

            FileWriter fileWriter = new FileWriter("PIF" + programFileName.replace(".txt", ".out"));
            for (var pair : PIF) {
                fileWriter.write(pair.getFirstElemennt() + " -> (" + pair.getSecondElement().getFirstElemennt() + ", " + pair.getSecondElement().getSecondElement() + ")\n");
            }
            fileWriter.close();

            fileWriter = new FileWriter("ST" + programFileName.replace(".txt", ".out"));
            fileWriter.write(symbolTable.toString());
            fileWriter.close();

            if (lexicalErrorCount == 0) {
                System.out.println("Lexically correct");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

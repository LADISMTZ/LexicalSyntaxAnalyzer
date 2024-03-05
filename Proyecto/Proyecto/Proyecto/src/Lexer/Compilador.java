package Lexer;
import java_cup.runtime.Symbol;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Compilador
{

    public static void lexicAnalyze (Reader lector) throws IOException {
        Lexer lexer = new Lexer(lector);
        Tokens token;
        do {
            token = lexer.yylex();
            if (token != null)
            {
                System.out.println(token);
            }//end if
            else {
                System.out.println("End of lexical analysis");
            }
        } while (token != null);
    }

    public static void syntaxAnalyze(StringReader stringReader) {
        Syntax s = new Syntax(new LexerCup(stringReader));
        try {
            s.parse();
            System.out.println("Syntactic analysis ended correctly.");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right));
        }
    }

    /*
    private static void syntaxAnalyzeHelper(StringReader stringReader, Set<Integer> printedLines) {
        String originalContent = getStringReaderContent(stringReader);
        Syntax s = new Syntax(new LexerCup(new StringReader(originalContent)));
        System.out.println("\nSYNTACTIC ANALIZER\n");

        try {
            s.parse();
            System.out.println("Syntactic analysis ended correctly.");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right));

            // Eliminar la línea con el error
            originalContent = removeLineFromString(originalContent, sym.right);

            // Crear un nuevo StringReader con el contenido actualizado
            StringReader updatedStringReader = new StringReader(originalContent);
            System.out.println(originalContent);

            // Llamada recursiva para intentar analizar nuevamente después de remover la línea con el error
            syntaxAnalyzeHelper(updatedStringReader, printedLines);

            return; // Importante: salir de la función para evitar impresiones duplicadas
        }

        // Imprimir "Valid" solo para las líneas después de la línea con el error y que no se hayan impreso antes
        boolean errorFound = false;
        try (BufferedReader reader = new BufferedReader(new StringReader(originalContent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (errorFound && !printedLines.contains(line.hashCode())) {
                    System.out.println(line);
                    printedLines.add(line.hashCode()); // Registrar la línea impresa para evitar duplicados
                } else if (line.contains("Error de sintaxis")) {
                    errorFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String removeLineFromString(String originalContent, int lineToRemove) {
        StringBuilder updatedContent = new StringBuilder();
        String[] linesArray = originalContent.split("\n");

        for (int currentLine = 1; currentLine <= linesArray.length; currentLine++) {
            if (currentLine != lineToRemove) {
                updatedContent.append(linesArray[currentLine - 1]).append("\n");
            }
        }

        return updatedContent.toString();
    }
    private static String getStringReaderContent(StringReader stringReader) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(stringReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

     */
    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }




    public static void main(String[] args) throws IOException, Exception
    {
        String[] rutaS = {"-parser", "Syntax", "C:/Users/Fer Ahuatzin/Downloads/Proyecto/Proyecto/src/Lexer/Syntax.cup"};
        generar(rutaS);

        Reader lector = new BufferedReader(new FileReader("Proyecto/src/Lexer/input.txt"));
        System.out.println("\nLEXICAL ANALIZER\n");
        lexicAnalyze(lector);

        String fileContent = readFileToString("Proyecto/src/Lexer/input.txt");
        StringReader StringReader = new StringReader(fileContent);
        System.out.println("\nSYNTACTIC ANALIZER\n");
        syntaxAnalyze(StringReader);
    }//end main

    public static void generar(String[] rutaS) throws IOException, Exception
    {

        java_cup.Main.main(rutaS);
        Path rutaSym = Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/Proyecto/src/Lexer/sym.java");
        if (Files.exists(rutaSym))
        {

            Files.delete(rutaSym);

        }//end if

        Files.move(
                Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/sym.java"),
                Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/Proyecto/src/Lexer/sym.java"));
        Path rutaSin = Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/Proyecto/src/Lexer/Syntax.java");
        if (Files.exists(rutaSin))
        {

            Files.delete(rutaSin);

        }//end if
        Files.move(
                Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/Syntax.java"),
                Paths.get("C:/Users/Fer Ahuatzin/Downloads/Proyecto/Proyecto/src/Lexer/Syntax.java"));


    }//end generar

}//end Lexer
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyFileParser {

    //Fields (instance variables)
    private List<String> cleanAssemblyCode;
    private Scanner fileReader;

    public AssemblyFileParser(String fileName) throws FileNotFoundException {
        cleanAssemblyCode = new ArrayList<>();
        File assemblyFile = new File(fileName);
        //if file does not exist or has length
        //throw exception
        if (!assemblyFile.exists() || assemblyFile.length() == 0)
            throw new FileNotFoundException(fileName + " does not exist or is empty");

        fileReader = new Scanner(assemblyFile);
        makeFirstPass();
        //makeSecondPass();
        fileReader.close();
    }

    private void makeFirstPass() {
        //Loop through the assembly file line by line
        String rawLine, cleanLine;
        while (fileReader.hasNextLine()) {
            rawLine = fileReader.nextLine();
            //clean rawLine
            cleanLine = clean(rawLine);
            //check if clean line is not empty (valid code)
            //add to list if valid
            if (!cleanLine.isEmpty())
                cleanAssemblyCode.add(cleanLine);
        }
    }

    //Methods
    public String clean(String rawLine) {
        // There are different types of whitespace
        // Spaces, tabs, newline, carriage returns

        //Replacing all whitespace with a space character
        String cleanLine = rawLine.replaceAll("\\s+", " ").trim();
        //Remove comments
        int commentIndex = cleanLine.indexOf("//");
        if (commentIndex != -1)
            cleanLine = cleanLine.substring(0,commentIndex).trim();
        return cleanLine;
    }

    @Override
    public String toString() {
        //Loop through each line in cleanAssemblyCode
        StringBuilder output = new StringBuilder();
        for (String line : cleanAssemblyCode)
            output.append(line).append("\n");
        return output.toString();
    }
}

import java.util.HashMap;

public class SymbolTable {

    private static HashMap<String, Integer> theTable;
    //start with letter or _,$,.,:
    //followed by anything from before or number
    private static final String INITIAL_VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.$:";
    private static final String ALL_VALID_CHARS = INITIAL_VALID_CHARS + "0123456789";

    //to initialize static fields, use static block
    static {
        theTable = new HashMap<>();
        //to add pairs, use put
        theTable.put("R0", 0);
        theTable.put("R1", 1);
        theTable.put("R2", 2);
        theTable.put("R3", 3);
        theTable.put("R4", 4);
        theTable.put("R5", 5);
        theTable.put("R6", 6);
        theTable.put("R7", 7);
        theTable.put("R8", 8);
        theTable.put("R9", 9);
        theTable.put("R10", 10);
        theTable.put("R11", 11);
        theTable.put("R12", 12);
        theTable.put("R13", 13);
        theTable.put("R14", 14);
        theTable.put("R15", 15);
        theTable.put("SCREEN", 16384);
        theTable.put("KBD", 24576);
        theTable.put("SP", 0);
        theTable.put("LCL", 1);
        theTable.put("ARG", 2);
        theTable.put("THIS", 3);
        theTable.put("THAT", 4);
    }

    public static boolean contains(String symbol) {
        return theTable.containsKey(symbol);
    }

    public static int getAddress(String symbol) {
        return theTable.get(symbol); //returns address
    }

    public static boolean add(String symbol, int address) {
        //can't add if name is invalid
        //or symbol is already in table
        if (!isValidName(symbol) || theTable.containsKey(symbol)) {
            return false;
        } else {
            theTable.put(symbol, address);
            return true;
        }
    }

    private static boolean isValidName(String symbol) {
        //loop through all chars in string
        String validChars = INITIAL_VALID_CHARS;
        for (int i = 0; i < symbol.length(); i++) {
            if (!validChars.contains(symbol.substring(i,i+1)))
                return false;
            validChars = ALL_VALID_CHARS;
        }
        return true;
    }

}

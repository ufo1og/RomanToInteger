import java.util.HashMap;

public class RomanConverter {
    private HashMap<String, Integer> romanMap = new HashMap<>();

    public RomanConverter() {
        this.romanMap.put("I", 1);
        this.romanMap.put("V", 5);
        this.romanMap.put("X", 10);
        this.romanMap.put("L", 50);
        this.romanMap.put("C", 100);
        this.romanMap.put("D", 500);
        this.romanMap.put("M", 1000);
    }
    private boolean isCorrectSymbols (String[] romanNumbers) {
        for (String number : romanNumbers) {
            if (!romanMap.containsKey(number)) return false;
        }
        return true;
    }

    private boolean isCorrectSequence (String[] romanNumbers) {
        String previous = romanNumbers[0];
        for (int i = 1; i < romanNumbers.length; i++) {
            //TODO
        }
        return true;
    }

    private String[] splitRomanNumber (String romanNumber) {
        String[] romanNumbers = romanNumber.split("");
        //TODO
        return null;
    }
}

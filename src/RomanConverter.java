import java.util.HashMap;

public class RomanConverter {
    private final HashMap<String, Integer> romanMap = new HashMap<>();

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

    private int[] splitToArabicNumbers (String romanNumber) {
        String[] romanNumbers = romanNumber.split("");
        if (isCorrectSymbols(romanNumbers)) {
            int[] numbers = new int[romanNumbers.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = romanMap.get(romanNumbers[i]);
            }
            return numbers;
        }
        return null;
    }

    private boolean checkOverStreak (int[] numbers, int index) {
        if (index + 2 >= numbers.length) return true;
        return numbers[index] != numbers[index + 1] || numbers[index] != numbers[index + 2];
    }

    private boolean isCorrectSequence (int[] numbers) {
        if (numbers == null) return false;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] == numbers[i]) {
                if (numbers[i - 1] == numbers[i]) {
                    if (numbers[i] == romanMap.get("V") || numbers[i] == romanMap.get("L") || numbers[i] == romanMap.get("D")) return false;
                    if (numbers[i] == romanMap.get("I") || numbers[i] == romanMap.get("X") || numbers[i] == romanMap.get("C")) return checkOverStreak(numbers, i);
                }
            }
            if (numbers[i - 1] * 10 < numbers[i]) return false;
            else if (numbers[i - 1] * 10 == numbers[i] && (numbers[i - 1] == romanMap.get("L") || numbers[i - 1] == romanMap.get("V"))) return false;
            if (numbers[i - 1] == numbers[i] / 2) return false;
        }
        return true;
    }

    public int convertRomanToArabic (String romanNumber) {
        int[] numbers = splitToArabicNumbers(romanNumber);
        if (!isCorrectSequence(numbers)) return -1;
        int arabicNumber = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] < numbers[i + 1]) arabicNumber -= numbers[i];
            else arabicNumber += numbers[i];
        }
        arabicNumber += numbers[numbers.length - 1];
        return arabicNumber;
    }
}

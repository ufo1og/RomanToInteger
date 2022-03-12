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
    // Метод проверяет нет ли в строке символов отличных от тех, что есть в мапе
    private boolean isCorrectSymbols (String[] romanNumbers) {
        for (String number : romanNumbers) {
            if (!romanMap.containsKey(number)) return false;
        }
        return true;
    }
    // Метод переводит строку римских цифр в массив арабских чисел
    private int[] splitToArabicNumbers (String romanNumber) {
        String[] romanNumbers = romanNumber.split("");
        // Здесь проверяем корректные ли символы в строке
        if (isCorrectSymbols(romanNumbers)) {
            int[] numbers = new int[romanNumbers.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = romanMap.get(romanNumbers[i]);
            }
            return numbers;
        }
        return null;
    }
    // Метод вернёт false если от index - 1 будет 4 подряд идущих одинаковых символа (кроме M)
    private boolean checkOverStreak (int[] numbers, int index) {
        if (index + 2 >= numbers.length) return true;
        return numbers[index] != numbers[index + 1] || numbers[index] != numbers[index + 2];
    }
    // Метод ищет некорректную запись римского числа (возвращает false если найдет хотябы одно отклонение от правильного ввода)
    private boolean isCorrectSequence (int[] numbers) {
        if (numbers == null) return false; // Если вдруг закинули на вход пустой массив
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] == numbers[i]) {
                if (numbers[i - 1] == numbers[i]) {
                    if (numbers[i] == romanMap.get("V") || numbers[i] == romanMap.get("L") || numbers[i] == romanMap.get("D")) return false; // Если подряд идет два V, L или D то ввод некорректен
                    if (numbers[i] == romanMap.get("I") || numbers[i] == romanMap.get("X") || numbers[i] == romanMap.get("C")) return checkOverStreak(numbers, i); // Если подряд идет более 3 I, X или C то ввод некорректен
                }
            }
            if (numbers[i - 1] * 10 < numbers[i]) return false; // Если предыдущее число более чем в 10 раз меньше следующего - то ввод некорректен (отсекаем случаи типа IC или XM)
            else if (numbers[i - 1] * 10 == numbers[i] && (numbers[i - 1] == romanMap.get("L") || numbers[i - 1] == romanMap.get("V"))) return false; // Если предыдущий ровно в 10 раз меньше и это L или V - то тоже некорректный ввод
            if (numbers[i - 1] == numbers[i] / 2) return false; // Если предыдущий ровно в два раза меньше то некорректный ввод (отсекаем случаи DM, LC и VX)
        }
        return true;
    }
    // Метод конвертирует число если оно корректно, иначе возвращает -1
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

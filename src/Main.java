public class Main {
    public static void main(String[] args) {
        RomanConverter romanConverter = new RomanConverter();

        String romanNumber = "LD";
        int arabicNumber = romanConverter.convertRomanToArabic(romanNumber);
        if (arabicNumber < 0) System.out.println("Некорректное римское число!");
        else System.out.println(romanNumber + " по арабски это " + arabicNumber);
    }
}

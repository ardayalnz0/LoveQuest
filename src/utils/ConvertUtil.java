package utils;

public class ConvertUtil {

    private static ConvertUtil instance;

    public static ConvertUtil getInstance(){
        if (instance == null) {
            instance = new ConvertUtil();
        }
        return instance;
    }

    public String convertTurkishToEnglish(String input) {
        if (input == null) return null;
        return input.replace("ç", "c")
                .replace("Ç", "C")
                .replace("ğ", "g")
                .replace("Ğ", "G")
                .replace("ı", "i")
                .replace("İ", "I")
                .replace("ö", "o")
                .replace("Ö", "O")
                .replace("ş", "s")
                .replace("Ş", "S")
                .replace("ü", "u")
                .replace("Ü", "U");
    }
}

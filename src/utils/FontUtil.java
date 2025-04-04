package utils;

import controller.ScreenController;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontUtil {

    private static FontUtil instance;

    public static FontUtil getInstance(){
        if (instance == null) {
            instance = new FontUtil();
        }
        return instance;
    }

    public Font loadCustomFont(String stringPath, float size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(stringPath));
            return customFont.deriveFont(size); // Font boyutunu ayarlayın
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 18); // Yükleme başarısız olursa varsayılan font
        }
    }

    public static Font getFont(String name, int style, float size) {
        return new Font(name, style, (int) size);
    }
}

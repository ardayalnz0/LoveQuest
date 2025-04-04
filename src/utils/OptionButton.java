package utils;

import utils.FontUtil;

import javax.swing.*;
import java.awt.*;

public class OptionButton extends JToggleButton {

    private final Image normalImage;
    private final Image selectedImage;

    public OptionButton(String text) {
        super(text);

        // Normal ve seçili durum görsellerini yükle
        normalImage = new ImageIcon(getClass().getResource("/images/Button.png")).getImage();
        selectedImage = new ImageIcon(getClass().getResource("/images/ButtonPressed.png")).getImage();

        // Temel ayarlar
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // Yazı konumlandırma
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        // Yazı fontu (Roboto-Thin)
        Font robotoThin = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 24f);
        setFont(robotoThin);
        setForeground(Color.BLACK);

        // Boyut (Button.png ile uyumlu seçebilirsiniz)
        setPreferredSize(new Dimension(363, 56));
        setMinimumSize(new Dimension(363, 56));
        setMaximumSize(new Dimension(363, 56));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Seçiliyse veya buton basılıysa "ButtonPressed.png", aksi halde "Button.png"
        if (getModel().isPressed() || isSelected()) {
            g.drawImage(selectedImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(normalImage, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g);
    }
}

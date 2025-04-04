package utils;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import java.awt.*;

public class CustomRadioButtonIcon implements Icon, UIResource {
    private int size;
    private Color userColor;

    public CustomRadioButtonIcon(int size, Color userColor) {
        this.size = size;
        this.userColor = userColor;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c == null || !(c instanceof AbstractButton)) return;

        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        Graphics2D g2 = (Graphics2D) g.create();
        // Anti-aliasing etkinleştiriliyor, böylece çizimler daha pürüzsüz olur.
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Her durumda, kullanıcı tarafından verilen renk ile border çiziliyor.
        g2.setColor(userColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x, y, size, size);

        // Eğer buton seçili ise, borderin tamamını aynı renk ile dolduruyoruz.
        if (model.isSelected()) {
            g2.fillOval(x, y, size, size);
        }

        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}

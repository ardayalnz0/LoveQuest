package utils;

import javax.swing.*;
import java.awt.*;

public class EmptyIcon implements Icon {
    private int width;
    private int height;

    public EmptyIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // Hiçbir şey çizilmiyor.
    }
}


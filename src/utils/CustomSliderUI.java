package utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class CustomSliderUI extends BasicSliderUI {

    private final Image fillImage;
    private final Image knobImage;

    private static final int RIGHT_BUFFER = 23;

    public CustomSliderUI(JSlider slider, Image fillImage, Image knobImage) {
        super(slider);
        this.fillImage = fillImage;
        this.knobImage = knobImage;
    }

    @Override
    protected void calculateTrackBuffer() {
        trackBuffer = 0;
    }

    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();

        trackRect.x = contentRect.x;
        trackRect.width = contentRect.width - RIGHT_BUFFER;
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int trackX = trackRect.x;
        int trackY = trackRect.y + (trackRect.height - fillImage.getHeight(null)) / 2;
        int trackWidth = trackRect.width;

        int knobCenter = thumbRect.x + (thumbRect.width / 2);

        int fillStart = trackX;
        int fillEnd = knobCenter;

        if (fillEnd < fillStart) {
            fillEnd = fillStart;
        }
        if (fillEnd > trackX + trackWidth) {
            fillEnd = trackX + trackWidth;
        }

        int fillWidth = fillEnd - fillStart;
        if (fillWidth > 0) {
            g2.drawImage(
                    fillImage,
                    fillStart, trackY,
                    fillStart + fillWidth, trackY + fillImage.getHeight(null),
                    0, 0,
                    fillImage.getWidth(null), fillImage.getHeight(null),
                    null
            );
        }

        g2.dispose();
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(knobImage, thumbRect.x, thumbRect.y, null);
        g2.dispose();
    }

    @Override
    public void paintFocus(Graphics g) {
    }

    @Override
    protected Dimension getThumbSize() {
        if (knobImage != null) {
            return new Dimension(knobImage.getWidth(null), knobImage.getHeight(null));
        }
        return super.getThumbSize();
    }
}

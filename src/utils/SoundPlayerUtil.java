package utils;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayerUtil {
    private static final String CLICK_SOUND_PATH = "/sounds/click.wav";
    private static final String SELECT_SOUND_PATH = "/sounds/switch.wav";

    // Master volume 0.0 (mute) - 1.0 (tam ses) arası, default %100
    private static float masterVolume = 1.0f;

    public static void setMasterVolume(float volume) {
        if (volume < 0f) {
            volume = 0f;
        }
        if (volume > 1f) {
            volume = 1f;
        }
        masterVolume = volume;
    }

    public static float getMasterVolume() {
        return masterVolume;
    }

    public static void playClickSound() {
        playSound(CLICK_SOUND_PATH);
    }

    public static void playSelectSound(){
        playSound(SELECT_SOUND_PATH);
    }

    private static void playSound(String soundFilePath) {
        try (InputStream soundStream = SoundPlayerUtil.class.getResourceAsStream(soundFilePath)) {
            if (soundStream == null) {
                throw new IOException("Sound file not found: " + soundFilePath);
            }

            BufferedInputStream bufferedStream = new BufferedInputStream(soundStream);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (masterVolume == 0.0f) ? gainControl.getMinimum() : (float)(20.0 * Math.log10(masterVolume));
                gainControl.setValue(dB);
            }

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to play sound: " + soundFilePath + " - " + e.getMessage());
        }
    }
}

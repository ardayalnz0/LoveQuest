package view;

import controller.ScreenController;
import utils.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsScreen extends JFrame {

    public SettingsScreen(JFrame frame) {
        setTitle("Ayarlar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = screenSize.width;
        int frameHeight = screenSize.height;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setUndecorated(true);

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/images/SoruYazBackground.png"));
        Image scaledBg = bgIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        bgIcon = new ImageIcon(scaledBg);
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setLayout(null);
        setContentPane(bgLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        mainPanel.setOpaque(false);

        // "AYARLAR" başlığı
        JLabel headerLabel = new JLabel("AYARLAR");
        Font boldFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 60f);
        headerLabel.setFont(boldFont);
        headerLabel.setForeground(new Color(235, 173, 214));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(40));

        // "ANA SES" etiketi
        JLabel masterLabel = new JLabel("ANA SES");
        Font sectionLabelFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 36f);
        masterLabel.setFont(sectionLabelFont);
        masterLabel.setForeground(new Color(122, 170, 217));
        masterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(masterLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        ImageIcon containerIcon = new ImageIcon(getClass().getResource("/images/MasterVolumeContainer.png"));
        Image containerImg = containerIcon.getImage();

        ImagePanel sliderContainerPanel = new ImagePanel(containerImg);
        int containerW = containerIcon.getIconWidth();
        int containerH = containerIcon.getIconHeight();
        sliderContainerPanel.setPreferredSize(new Dimension(containerW, containerH));
        sliderContainerPanel.setMaximumSize(new Dimension(containerW, containerH));
        sliderContainerPanel.setMinimumSize(new Dimension(containerW, containerH));

        sliderContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        sliderContainerPanel.setOpaque(false);

        // 2.2) Slider oluştur
        float dB = SoundPlayerUtil.getMasterVolume();
        int curVal = (int) (dB * 100);
        JSlider volumeSlider = new JSlider(0, 100, curVal);
        volumeSlider.setOpaque(false);

        Image knobImg = new ImageIcon(getClass().getResource("/images/VolumeKnob.png")).getImage();
        Image fillImg = new ImageIcon(getClass().getResource("/images/VolumeFill.png")).getImage();

        // Custom UI
        volumeSlider.setUI(new CustomSliderUI(volumeSlider, fillImg, knobImg));

        volumeSlider.setPreferredSize(new Dimension(572, 96));

        JLabel volumeValueLabel = new JLabel(volumeSlider.getValue() + "%");

        Font thinFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 20f);
        volumeValueLabel.setFont(thinFont);

        volumeValueLabel.setForeground(Color.BLACK);

        volumeValueLabel.setPreferredSize(new Dimension(60, 25));
        volumeValueLabel.setHorizontalAlignment(SwingConstants.CENTER);



        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = volumeSlider.getValue();
                float volume = value / 100.0f;
                SoundPlayerUtil.setMasterVolume(volume);
                volumeValueLabel.setText(value + "%");
            }
        });

        volumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SoundPlayerUtil.playClickSound();
            }
        });


        sliderContainerPanel.add(volumeSlider);
        //sliderContainerPanel.add(Box.createVerticalStrut(10));
        sliderContainerPanel.add(volumeValueLabel);

        sliderContainerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(sliderContainerPanel);

        mainPanel.add(Box.createVerticalStrut(60));

        JLabel soundSettingsLabel = new JLabel("MUZIK AYARLARI");
        soundSettingsLabel.setFont(sectionLabelFont);
        soundSettingsLabel.setForeground(new Color(122, 170, 217));
        soundSettingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(soundSettingsLabel);

        // SoundSettingsContainer.png'yi arka plan yapacak panel
        ImageIcon soundContainerIcon = new ImageIcon(getClass().getResource("/images/SoundSettingsContainer.png"));
        Image soundContainerImg = soundContainerIcon.getImage();

        ImagePanel soundSettingsContainer = new ImagePanel(soundContainerImg);
        int soundContainerIconIconWidth = soundContainerIcon.getIconWidth();
        int soundContainerIconIconHeight = soundContainerIcon.getIconHeight();
        soundSettingsContainer.setPreferredSize(new Dimension(soundContainerIconIconWidth, soundContainerIconIconHeight));
        soundSettingsContainer.setMaximumSize(new Dimension(soundContainerIconIconWidth, soundContainerIconIconHeight));
        soundSettingsContainer.setMinimumSize(new Dimension(soundContainerIconIconWidth, soundContainerIconIconHeight));

        soundSettingsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        soundSettingsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        soundSettingsContainer.setOpaque(false);

        Font radioFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-BoldCondensed.ttf",24f);
        JRadioButton rbAcik = new JRadioButton("Acik");
        JRadioButton rbKapali = new JRadioButton("Kapali");
        rbAcik.setFont(radioFont);
        rbKapali.setFont(radioFont);

        Color selectedColor = new Color(61, 174, 239);
        int iconWidth = 16;
        int iconHeight = 16;
        EmptyIcon customIcon = new EmptyIcon(iconWidth,iconHeight);

        rbAcik.setIcon(customIcon);
        rbAcik.setBorderPainted(false);
        rbAcik.setContentAreaFilled(false);
        rbAcik.setFocusPainted(false);

        rbKapali.setIcon(customIcon);
        rbKapali.setBorderPainted(false);
        rbKapali.setContentAreaFilled(false);
        rbKapali.setFocusPainted(false);

        rbAcik.addActionListener(e ->
                SoundPlayerUtil.playSelectSound());

        rbKapali.addActionListener(e ->
                SoundPlayerUtil.playSelectSound());

        rbAcik.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbAcik.setForeground(selectedColor);
                } else {
                    rbAcik.setForeground(Color.BLACK);
                }
            }
        });

        rbKapali.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbKapali.setForeground(selectedColor);
                } else {
                    rbKapali.setForeground(Color.BLACK);
                }
            }
        });

        ButtonGroup bgMuzik = new ButtonGroup();
        bgMuzik.add(rbAcik);
        bgMuzik.add(rbKapali);
        rbAcik.setSelected(true);

        soundSettingsContainer.add(rbAcik);
        soundSettingsContainer.add(rbKapali);

        mainPanel.add(soundSettingsContainer);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel overlayPanel = new JPanel(null);
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, frameWidth, frameHeight);
        bgLabel.add(overlayPanel);

        int panelW = (int) (frameWidth * 0.71);
        int panelH = (int) (frameHeight * 0.69);

        JPanel transparentPanel = new JPanel(new BorderLayout());
        transparentPanel.setOpaque(false);
        transparentPanel.setBounds((frameWidth - panelW) / 2, (frameHeight - panelH) / 2,
                panelW, panelH);
        transparentPanel.add(scrollPane, BorderLayout.CENTER);
        overlayPanel.add(transparentPanel);

        ImageIcon backIcon = new ImageIcon(getClass().getResource("/images/back.png"));
        JButton btnBack = new JButton(backIcon);
        btnBack.setBounds(10, 45, backIcon.getIconWidth(), backIcon.getIconHeight());
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToFrame(this, frame);
        });
        overlayPanel.add(btnBack);

        setVisible(true);
    }
}

package view;

import controller.ScreenController;
import utils.SoundPlayerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private static MainMenu instance;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public MainMenu() {
        setTitle("LoveQuest 04.03");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width, height);
        setLocationRelativeTo(null);
        setUndecorated(true);

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/images/HomeScreenBackground.jpg"));
        Image scaledBg = bgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        bgIcon = new ImageIcon(scaledBg);
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setLayout(null);
        setContentPane(bgLabel);

        ImageIcon originalGif = new ImageIcon(getClass().getResource("/images/lovequest.gif"));
        Image scaledImage = originalGif.getImage().getScaledInstance(1066, 432, Image.SCALE_SMOOTH);
        ImageIcon scaledGif = new ImageIcon(scaledImage);
        JLabel gifLabel = new JLabel(scaledGif);

        int gifW = scaledGif.getIconWidth();
        int gifH = scaledGif.getIconHeight();
        int gifX = (width - gifW) / 2;
        int gifY = (height - gifH) / 2;
        gifY -= 60;
        gifLabel.setBounds(gifX, gifY, gifW, gifH);
        bgLabel.add(gifLabel);

        ImageIcon soruIcon = new ImageIcon(getClass().getResource("/images/SoruButton2.png"));
        Image soruImg = soruIcon.getImage().getScaledInstance(363, 56, Image.SCALE_SMOOTH);
        soruIcon = new ImageIcon(soruImg);
        JButton btnSoruYaz = new JButton(soruIcon);
        btnSoruYaz.setBorderPainted(false);
        btnSoruYaz.setContentAreaFilled(false);
        btnSoruYaz.setFocusPainted(false);

        ImageIcon havuzuIcon = new ImageIcon(getClass().getResource("/images/SoruHavuzuButton.png"));
        Image havuzuImg = havuzuIcon.getImage().getScaledInstance(363, 56, Image.SCALE_SMOOTH);
        havuzuIcon = new ImageIcon(havuzuImg);
        JButton btnSoruHavuzu = new JButton(havuzuIcon);
        btnSoruHavuzu.setBorderPainted(false);
        btnSoruHavuzu.setContentAreaFilled(false);
        btnSoruHavuzu.setFocusPainted(false);

        ImageIcon quizIcon = new ImageIcon(getClass().getResource("/images/QuizButton.png"));
        Image quizImg = quizIcon.getImage().getScaledInstance(363, 56, Image.SCALE_SMOOTH);
        quizIcon = new ImageIcon(quizImg);
        JButton btnQuizBaslat = new JButton(quizIcon);
        btnQuizBaslat.setBorderPainted(false);
        btnQuizBaslat.setContentAreaFilled(false);
        btnQuizBaslat.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        int panelW = (int) (width * 0.5);
        int panelH = (int) (height * 0.35);
        buttonPanel.setPreferredSize(new Dimension(panelW, panelH));

        btnSoruYaz.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSoruHavuzu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuizBaslat.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(btnSoruYaz);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(btnSoruHavuzu);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(btnQuizBaslat);

        JPanel buttonWrapper = new JPanel(null);
        buttonWrapper.setOpaque(false);
        buttonWrapper.setBounds(0, 0, width, height);
        bgLabel.add(buttonWrapper);

        buttonWrapper.add(buttonPanel);
        int wrapX = (width - panelW) / 2;
        int wrapY = (int) (height * 0.63);
        buttonPanel.setBounds(wrapX, wrapY, panelW, panelH);

        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/exit2.png"));
        JButton exitButton = new JButton(exitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(10, 45, exitIcon.getIconWidth(), exitIcon.getIconHeight());
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayerUtil.playClickSound();
                System.exit(0);
            }
        });
        bgLabel.add(exitButton);

        ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/images/settings.png"));
        JButton settingsButton = new JButton(settingsIcon);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        int lbW = settingsIcon.getIconWidth();
        int lbH = settingsIcon.getIconHeight();
        int lbX = width - lbW - 10;
        int lbY = height - lbH - 20;
        settingsButton.setBounds(lbX, lbY, lbW, lbH);
        bgLabel.add(settingsButton);
        settingsButton.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToSettingsMenu(this);
        });

        btnSoruYaz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayerUtil.playClickSound();
                ScreenController.getInstance().switchToNewQuestionScreen(MainMenu.this);
            }
        });
        btnSoruHavuzu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayerUtil.playClickSound();
                ScreenController.getInstance().switchToShowQuestionsScreen(MainMenu.this);
            }
        });
        btnQuizBaslat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayerUtil.playClickSound();
                ScreenController.getInstance().switchToGenerateQuizScreen(MainMenu.this);
            }
        });
    }
}

package view;

import controller.QuizController;
import controller.ScreenController;
import utils.FontUtil;
import utils.SoundPlayerUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EndScreen extends JFrame {
    private JLabel lblCorrectAnswerCount;
    private JLabel lblWrongAnswerCount;
    private JLabel lblResult;

    public EndScreen(QuizController quizController) {
        setTitle("Quiz Sonuçları");
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

        // Title Label: "QUIZ SONUCLARI"
        JLabel lblTitle = new JLabel("QUIZ SONUCLARI");
        lblTitle.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 48f));
        lblTitle.setForeground(new Color(235, 173, 214));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(80));

        // Correct Answer Section
        JPanel correctAnswerSection = new JPanel();
        correctAnswerSection.setLayout(new BoxLayout(correctAnswerSection, BoxLayout.Y_AXIS));
        correctAnswerSection.setOpaque(false);

        JLabel lblCorrectAnswer = new JLabel("DOGRU CEVAP SAYISI");
        lblCorrectAnswer.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblCorrectAnswer.setForeground(new Color(122, 170, 217));
        lblCorrectAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        correctAnswerSection.add(lblCorrectAnswer);

        lblCorrectAnswerCount = new JLabel(String.valueOf(quizController.getCorrectAnswerCount()));
        lblCorrectAnswerCount.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblCorrectAnswerCount.setForeground(new Color(122, 170, 217));
        lblCorrectAnswerCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        correctAnswerSection.add(lblCorrectAnswerCount);

        mainPanel.add(correctAnswerSection);
        mainPanel.add(Box.createVerticalStrut(80));

        // Wrong Answer Section
        JPanel wrongAnswerSection = new JPanel();
        wrongAnswerSection.setLayout(new BoxLayout(wrongAnswerSection, BoxLayout.Y_AXIS));
        wrongAnswerSection.setOpaque(false);

        JLabel lblWrongAnswer = new JLabel("YANLIS CEVAP SAYISI");
        lblWrongAnswer.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblWrongAnswer.setForeground(new Color(122, 170, 217));
        lblWrongAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrongAnswerSection.add(lblWrongAnswer);

        lblWrongAnswerCount = new JLabel(String.valueOf(quizController.getWrongAnswerCount()));
        lblWrongAnswerCount.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblWrongAnswerCount.setForeground(new Color(122, 170, 217));
        lblWrongAnswerCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrongAnswerSection.add(lblWrongAnswerCount);

        mainPanel.add(wrongAnswerSection);
        mainPanel.add(Box.createVerticalStrut(80));

        // PUAN (Result) Section
        JPanel resultSection = new JPanel();
        resultSection.setLayout(new BoxLayout(resultSection, BoxLayout.Y_AXIS));
        resultSection.setOpaque(false);

        JLabel lblResultTitle = new JLabel("PUAN");
        lblResultTitle.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblResultTitle.setForeground(new Color(122, 170, 217));
        lblResultTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultSection.add(lblResultTitle);

        lblResult = new JLabel(String.format("%.2f%%", quizController.getResult()));
        lblResult.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 32f));
        lblResult.setForeground(new Color(122, 170, 217));
        lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultSection.add(lblResult);

        mainPanel.add(resultSection);
        mainPanel.add(Box.createVerticalStrut(80));

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel overlayPanel = new JPanel(null);
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, frameWidth, frameHeight);
        bgLabel.add(overlayPanel);

        int panelW = (int)(frameWidth * 0.71);
        int panelH = (int)(frameHeight * 0.69);
        JPanel transparentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        transparentPanel.setOpaque(false);
        transparentPanel.setBounds((frameWidth - panelW) / 2, (frameHeight - panelH) / 2, panelW, panelH);
        transparentPanel.add(scrollPane, BorderLayout.CENTER);
        overlayPanel.add(transparentPanel);

        // Home Butonu (sol üst köşe)
        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/images/home.png"));
        JButton btnHome = new JButton(homeIcon);
        btnHome.setBounds(10, 45, homeIcon.getIconWidth(), homeIcon.getIconHeight());
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToMainMenu(this);
        });
        overlayPanel.add(btnHome);

        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/images/savex2.png"));
        JButton btnSave = new JButton(saveIcon);
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusPainted(false);
        int lbW = saveIcon.getIconWidth();
        int lbH = saveIcon.getIconHeight();
        int lbX = frameWidth - lbW - 10;
        int lbY = frameHeight - lbH - 20;
        btnSave.setBounds(lbX, lbY, lbW, lbH);
        bgLabel.add(btnSave);
        btnSave.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();

        });

        overlayPanel.add(btnSave);

        setVisible(true);
    }
}

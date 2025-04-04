package view;

import controller.GenerateQuizController;
import controller.QuizController;
import controller.ScreenController;
import domain.MCQuestion;
import domain.OEQuestion;
import domain.Quiz;
import utils.EmptyIcon;
import utils.FontUtil;
import utils.SoundPlayerUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GenerateQuizScreen extends JFrame {

    private JComboBox<Integer> comboQuestionCount;
    private JRadioButton rbKolay, rbOrta, rbZor, rbRastgele;
    private ButtonGroup bgDifficulty;
    private JRadioButton rbCoktan, rbAcik;
    private ButtonGroup bgFormat;

    public GenerateQuizScreen() {
        setTitle("Quiz Olustur");
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

        // Ana panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        mainPanel.setOpaque(false);

        // Başlık
        JLabel headerLabel = new JLabel("Quiz Olustur");
        Font boldFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 60f);
        headerLabel.setFont(boldFont);
        headerLabel.setForeground(new Color(235, 173, 214));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(40));

        Font sectionLabelFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 36f);

        // 1. Soru Sayisi Section
        JPanel countSection = new JPanel();
        countSection.setLayout(new BoxLayout(countSection, BoxLayout.Y_AXIS));
        countSection.setOpaque(false);
        JLabel lblCount = new JLabel("Soru Sayisi");
        lblCount.setFont(sectionLabelFont);
        lblCount.setForeground(new Color(122, 170, 217));
        lblCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        countSection.add(lblCount);
        JPanel countInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        countInputPanel.setOpaque(false);
        Integer[] countOptions = new Integer[9];
        for (int i = 0; i < 9; i++) {
            countOptions[i] = i + 2;
        }
        comboQuestionCount = new JComboBox<>(countOptions);
        comboQuestionCount.addActionListener(e-> SoundPlayerUtil.playSelectSound());
        comboQuestionCount.setFont(FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 18f));
        countInputPanel.add(comboQuestionCount);
        countSection.add(countInputPanel);
        mainPanel.add(countSection);
        mainPanel.add(Box.createVerticalStrut(60));

        // 2. Test Zorlugu Section
        JPanel difficultySection = new JPanel();
        difficultySection.setLayout(new BoxLayout(difficultySection, BoxLayout.Y_AXIS));
        difficultySection.setOpaque(false);
        JLabel lblDifficultySection = new JLabel("Test Zorlugu");
        lblDifficultySection.setFont(sectionLabelFont);
        lblDifficultySection.setForeground(new Color(122, 170, 217));
        lblDifficultySection.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultySection.add(lblDifficultySection);
        JPanel difficultyInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        difficultyInputPanel.setOpaque(false);
        Font radioFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-BoldCondensed.ttf", 24f);
        rbKolay = new JRadioButton("Kolay");
        rbOrta = new JRadioButton("Orta");
        rbZor = new JRadioButton("Zor");
        rbRastgele = new JRadioButton("Rastgele");
        rbKolay.setFont(radioFont);
        rbOrta.setFont(radioFont);
        rbZor.setFont(radioFont);
        rbRastgele.setFont(radioFont);
        Color selectedColor = new Color(235, 173, 214);
        int iconWidth = 16;
        int iconHeight = 16;
        EmptyIcon customIcon = new EmptyIcon(iconWidth, iconHeight);
        rbKolay.setIcon(customIcon);
        rbKolay.setBorderPainted(false);
        rbKolay.setContentAreaFilled(false);
        rbKolay.setFocusPainted(false);

        rbOrta.setIcon(customIcon);
        rbOrta.setBorderPainted(false);
        rbOrta.setContentAreaFilled(false);
        rbOrta.setFocusPainted(false);

        rbZor.setIcon(customIcon);
        rbZor.setBorderPainted(false);
        rbZor.setContentAreaFilled(false);
        rbZor.setFocusPainted(false);

        rbRastgele.setIcon(customIcon);
        rbRastgele.setBorderPainted(false);
        rbRastgele.setContentAreaFilled(false);
        rbRastgele.setFocusPainted(false);

        rbKolay.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbOrta.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbZor.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbRastgele.addActionListener(e -> SoundPlayerUtil.playSelectSound());

        rbKolay.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbKolay.setForeground(selectedColor);
            } else {
                rbKolay.setForeground(Color.BLACK);
            }
        });
        rbOrta.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbOrta.setForeground(selectedColor);
            } else {
                rbOrta.setForeground(Color.BLACK);
            }
        });
        rbZor.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbZor.setForeground(selectedColor);
            } else {
                rbZor.setForeground(Color.BLACK);
            }
        });
        rbRastgele.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbRastgele.setForeground(selectedColor);
            } else {
                rbRastgele.setForeground(Color.BLACK);
            }
        });

        bgDifficulty = new ButtonGroup();
        bgDifficulty.add(rbKolay);
        bgDifficulty.add(rbOrta);
        bgDifficulty.add(rbZor);
        bgDifficulty.add(rbRastgele);

        rbKolay.setSelected(true);
        difficultyInputPanel.add(rbKolay);
        difficultyInputPanel.add(rbOrta);
        difficultyInputPanel.add(rbZor);
        difficultyInputPanel.add(rbRastgele);
        difficultyInputPanel.add(Box.createHorizontalStrut(10));
        difficultySection.add(difficultyInputPanel);
        mainPanel.add(difficultySection);
        mainPanel.add(Box.createVerticalStrut(60));

        // 3. Quiz Formati Section
        JPanel formatSection = new JPanel();
        formatSection.setLayout(new BoxLayout(formatSection, BoxLayout.Y_AXIS));
        formatSection.setOpaque(false);
        JLabel lblFormatSection = new JLabel("Quiz Formati");
        lblFormatSection.setFont(sectionLabelFont);
        lblFormatSection.setForeground(new Color(122, 170, 217));
        lblFormatSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        formatSection.add(lblFormatSection);
        JPanel formatInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formatInputPanel.setOpaque(false);
        rbCoktan = new JRadioButton("Çoktan Seçmeli");
        rbAcik = new JRadioButton("Açık Uçlu");
        rbCoktan.setFont(radioFont);
        rbAcik.setFont(radioFont);
        rbCoktan.setIcon(customIcon);
        rbCoktan.setBorderPainted(false);
        rbCoktan.setContentAreaFilled(false);
        rbCoktan.setFocusPainted(false);
        rbAcik.setIcon(customIcon);
        rbAcik.setBorderPainted(false);
        rbAcik.setContentAreaFilled(false);
        rbAcik.setFocusPainted(false);

        rbCoktan.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbAcik.addActionListener(e -> SoundPlayerUtil.playSelectSound());

        rbCoktan.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbCoktan.setForeground(selectedColor);
            } else {
                rbCoktan.setForeground(Color.BLACK);
            }
        });
        rbAcik.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                rbAcik.setForeground(selectedColor);
            } else {
                rbAcik.setForeground(Color.BLACK);
            }
        });
        bgFormat = new ButtonGroup();
        bgFormat.add(rbCoktan);
        bgFormat.add(rbAcik);
        rbCoktan.setSelected(true);
        formatInputPanel.add(rbCoktan);
        formatInputPanel.add(rbAcik);
        formatInputPanel.add(Box.createHorizontalStrut(14));
        formatSection.add(formatInputPanel);
        mainPanel.add(formatSection);

        // Alt Panel: Bottom panel with Save and Settings buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.setOpaque(false);
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/images/save.png"));
        JButton btnSave = new JButton(saveIcon);
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            int questionNumber = (Integer) comboQuestionCount.getSelectedItem();

            String difficulty = "";
            if (rbKolay.isSelected()) {
                difficulty = "Kolay";
            } else if (rbOrta.isSelected()) {
                difficulty = "Orta";
            } else if (rbZor.isSelected()) {
                difficulty = "Zor";
            } else if (rbRastgele.isSelected()){
                difficulty = "Rastgele";
            }

            String questionType = "";
            if (rbCoktan.isSelected()) {
                questionType = "Çoktan Seçmeli";
            } else if (rbAcik.isSelected()) {
                questionType = "Açık Uçlu";
            }

            GenerateQuizController.getInstance().setQuizSettings(questionNumber, difficulty, questionType);

            if (!GenerateQuizController.getInstance().validateTotalQuestions()) {
                JOptionPane.showMessageDialog(this, "Havuzda yeterince soru yok");
            }else if (!GenerateQuizController.getInstance().validateDifficulty()) {
                 JOptionPane.showMessageDialog(this, "Belirlenen Zorlukta Yeterli Soru Yok");
            } else if (!GenerateQuizController.getInstance().validateFormatByDifficulty()) {
                 JOptionPane.showMessageDialog(this, "Secilen Zorluk Icin Bu Formatta Yeterli Soru Yok");
             } else {
                Quiz quiz = GenerateQuizController.getInstance().generateQuiz();
                QuizController quizController = new QuizController(quiz);

                if(quiz.getQuestions().get(0) instanceof OEQuestion){
                    ScreenController.getInstance().switchToOEQuizScreen(this, quizController);
                }
                else if(quiz.getQuestions().get(0) instanceof MCQuestion){
                    ScreenController.getInstance().switchToMCQuizScreen(this, quizController);
                }
             }
        });

        rightBottomPanel.add(btnSave);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel);

        // Scroll Panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Overlay Panel
        JPanel overlayPanel = new JPanel(null);
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, frameWidth, frameHeight);
        bgLabel.add(overlayPanel);

        int panelW = (int) (frameWidth * 0.71);
        int panelH = (int) (frameHeight * 0.69);
        JPanel transparentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        transparentPanel.setOpaque(false);
        transparentPanel.setBounds((frameWidth - panelW) / 2, (frameHeight - panelH) / 2, panelW, panelH);
        transparentPanel.setLayout(new BorderLayout());
        transparentPanel.add(scrollPane, BorderLayout.CENTER);
        overlayPanel.add(transparentPanel);

        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/images/home.png"));
        JButton btnHome = new JButton(homeIcon);
        btnHome.setBounds(10, 45, homeIcon.getIconWidth(), homeIcon.getIconHeight());
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToMainMenu(GenerateQuizScreen.this);
        });
        overlayPanel.add(btnHome);

        ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/images/settings.png"));
        JButton btnSettingsNew = new JButton (settingsIcon);
        btnSettingsNew.setBorderPainted(false);
        btnSettingsNew.setContentAreaFilled(false);
        btnSettingsNew.setFocusPainted(false);
        int sW = settingsIcon.getIconWidth();
        int sH = settingsIcon.getIconHeight();
        int sX = frameWidth - sW - 10;
        int sY = frameHeight - sH - 20;
        btnSettingsNew.setBounds(sX, sY, sW, sH);
        btnSettingsNew.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToSettingsMenu(GenerateQuizScreen.this);
        });
        overlayPanel.add(btnSettingsNew);

        setVisible(true);
    }
}

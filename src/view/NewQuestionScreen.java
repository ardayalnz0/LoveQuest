package view;

import controller.QuestionPool;
import controller.ScreenController;
import domain.MCQuestion;
import domain.OEQuestion;
import domain.Question;
import utils.CustomRadioButtonIcon;
import utils.EmptyIcon;
import utils.FontUtil;
import utils.SoundPlayerUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

public class NewQuestionScreen extends JFrame {

    private JTextArea questionTextArea;
    private JRadioButton rbKolay, rbOrta, rbZor;
    private JRadioButton rbCoktan, rbAcik;
    private JPanel mcPanel, openPanel;
    private JTextField optionAText, optionBText, optionCText, optionDText;
    private JRadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    private ButtonGroup bgCorrect;
    private JTextField openAnswerField;

    private JRadioButton rbFotoEvet, rbFotoHayir;
    private JPanel photoPanel;
    private JButton btnFotoYukle;
    private JLabel lblPhotoPreview;
    private File uploadedPhoto = null;

    public NewQuestionScreen() {
        setTitle("Yeni Soru Yaz");
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

        // Başlık
        JLabel headerLabel = new JLabel("Soru Ayarlari");
        Font boldFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf",60f);
        headerLabel.setFont(boldFont);
        headerLabel.setForeground(new Color(235,173,214));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(40));



        // Soru Metni Section
        JPanel questionSection = new JPanel();
        questionSection.setLayout(new BoxLayout(questionSection, BoxLayout.Y_AXIS));
        questionSection.setOpaque(false);

        //Top
        JLabel lblQuestionSection = new JLabel("Soru Metni");
        Font sectionLabelFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 36f);
        lblQuestionSection.setFont(sectionLabelFont);
        lblQuestionSection.setForeground(new Color(122, 170, 217));
        lblQuestionSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionSection.add(lblQuestionSection);

        //Bottom
        JPanel questionInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionInputPanel.setOpaque(false);

        questionTextArea = new JTextArea(2, 30);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        Font questionTextFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 18f);
        questionTextArea.setFont(questionTextFont);

        JScrollPane questionScrollPane = new JScrollPane(questionTextArea);
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        Color borderColor = new Color(235, 173, 214);
        questionScrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        questionScrollPane.setPreferredSize(new Dimension(600, 50));
        questionScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        questionInputPanel.add(questionScrollPane);
        questionSection.add(questionInputPanel);

        mainPanel.add(questionSection);
        mainPanel.add(Box.createVerticalStrut(60));


        // Zorluk Seviyesi Section
        JPanel difficultySection = new JPanel();
        difficultySection.setLayout(new BoxLayout(difficultySection, BoxLayout.Y_AXIS));
        difficultySection.setOpaque(false);

        JLabel lblDifficultySection = new JLabel("Zorluk Seviyesi");
        lblDifficultySection.setFont(sectionLabelFont);
        lblDifficultySection.setForeground(new Color(122, 170, 217));
        lblDifficultySection.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultySection.add(lblDifficultySection);

        JPanel difficultyInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        difficultyInputPanel.setOpaque(false);
        Font radioFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-BoldCondensed.ttf",24f);

        rbKolay = new JRadioButton("Kolay");
        rbOrta = new JRadioButton("Orta");
        rbZor = new JRadioButton("Zor");
        rbKolay.setFont(radioFont);
        rbOrta.setFont(radioFont);
        rbZor.setFont(radioFont);

        Color selectedColor = new Color(235, 173, 214);
        int iconWidth = 16;
        int iconHeight = 16;
        EmptyIcon customIcon = new EmptyIcon(iconWidth,iconHeight);

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

        rbKolay.addActionListener(e ->
                SoundPlayerUtil.playSelectSound());

        rbOrta.addActionListener(e ->
                SoundPlayerUtil.playSelectSound());

        rbZor.addActionListener(e ->
                SoundPlayerUtil.playSelectSound());

        rbKolay.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbKolay.setForeground(selectedColor);
                } else {
                    rbKolay.setForeground(Color.BLACK);
                }
            }
        });

        rbOrta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbOrta.setForeground(selectedColor);
                } else {
                    rbOrta.setForeground(Color.BLACK);
                }
            }
        });

        rbZor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbZor.setForeground(selectedColor);
                } else {
                    rbZor.setForeground(Color.BLACK);
                }
            }
        });

        ButtonGroup bgDifficulty = new ButtonGroup();
        bgDifficulty.add(rbKolay);
        bgDifficulty.add(rbOrta);
        bgDifficulty.add(rbZor);
        rbKolay.setSelected(true);

        difficultyInputPanel.add(rbKolay);
        difficultyInputPanel.add(rbOrta);
        difficultyInputPanel.add(rbZor);
        difficultyInputPanel.add(Box.createHorizontalStrut(15));
        difficultySection.add(difficultyInputPanel);

        mainPanel.add(difficultySection);
        mainPanel.add(Box.createVerticalStrut(60));


        // Fotograf Section
        JPanel photoSection = new JPanel();
        photoSection.setLayout(new BoxLayout(photoSection, BoxLayout.Y_AXIS));
        photoSection.setOpaque(false);

        JLabel lblPhotoSection = new JLabel("Fotograf");
        lblPhotoSection.setFont(sectionLabelFont);
        lblPhotoSection.setForeground(new Color(122, 170, 217));
        lblPhotoSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        photoSection.add(lblPhotoSection);

        JPanel photoRadioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        photoRadioPanel.setOpaque(false);

        rbFotoEvet = new JRadioButton("Evet");
        rbFotoHayir = new JRadioButton("Hayır");
        rbFotoEvet.setFont(radioFont);
        rbFotoHayir.setFont(radioFont);

        rbFotoHayir.setIcon(customIcon);
        rbFotoHayir.setBorderPainted(false);
        rbFotoHayir.setContentAreaFilled(false);
        rbFotoHayir.setFocusPainted(false);

        rbFotoEvet.setIcon(customIcon);
        rbFotoEvet.setBorderPainted(false);
        rbFotoEvet.setContentAreaFilled(false);
        rbFotoEvet.setFocusPainted(false);

        rbFotoEvet.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbFotoEvet.setForeground(selectedColor);
                } else {
                    rbFotoEvet.setForeground(Color.BLACK);
                }
            }
        });

        rbFotoHayir.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbFotoHayir.setForeground(selectedColor);
                } else {
                    rbFotoHayir.setForeground(Color.BLACK);
                }
            }
        });

        ButtonGroup bgFoto = new ButtonGroup();
        bgFoto.add(rbFotoEvet);
        bgFoto.add(rbFotoHayir);
        rbFotoHayir.setSelected(true);

        photoRadioPanel.add(rbFotoEvet);
        photoRadioPanel.add(rbFotoHayir);
        photoRadioPanel.add(Box.createHorizontalStrut(22));
        photoSection.add(photoRadioPanel);

        photoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        photoPanel.setOpaque(false);

        ImageIcon uploadIcon = new ImageIcon(getClass().getResource("/images/upload.png"));
        btnFotoYukle = new JButton(uploadIcon);
        btnFotoYukle.setBorderPainted(false);
        btnFotoYukle.setContentAreaFilled(false);
        btnFotoYukle.setFocusPainted(false);

        photoPanel.add(btnFotoYukle);
        photoPanel.add(Box.createHorizontalStrut(10));
        lblPhotoPreview = new JLabel();
        lblPhotoPreview.setPreferredSize(new Dimension(150, 30));
        lblPhotoPreview.setBorder(BorderFactory.createLineBorder(new Color(235,173,214)));
        lblPhotoPreview.setOpaque(false);
        lblPhotoPreview.setHorizontalAlignment(SwingConstants.CENTER);
        photoPanel.add(lblPhotoPreview);
        photoPanel.setVisible(false);
        photoSection.add(photoPanel);

        mainPanel.add(photoSection);
        mainPanel.add(Box.createVerticalStrut(60));

        rbFotoEvet.addActionListener(e -> {
            SoundPlayerUtil.playSelectSound();
            photoPanel.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        rbFotoHayir.addActionListener(e -> {
            SoundPlayerUtil.playSelectSound();
            photoPanel.setVisible(false);
            uploadedPhoto = null;
            lblPhotoPreview.setText("");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnFotoYukle.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Dosyaları", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int result = fileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION) {
                uploadedPhoto = fileChooser.getSelectedFile();
                lblPhotoPreview.setText(uploadedPhoto.getName());
                JOptionPane.showMessageDialog(this,
                        "Fotoğraf seçildi: " + uploadedPhoto.getName());
            }
        });

        // Soru Formati Section
        JPanel formatSection = new JPanel();
        formatSection.setLayout(new BoxLayout(formatSection, BoxLayout.Y_AXIS));
        formatSection.setOpaque(false);

        JLabel lblFormatSection = new JLabel("Soru Formati");
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

        rbCoktan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    rbCoktan.setForeground(selectedColor);
                } else {
                    rbCoktan.setForeground(Color.BLACK);
                }
            }
        });

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

        ButtonGroup bgFormat = new ButtonGroup();
        bgFormat.add(rbCoktan);
        bgFormat.add(rbAcik);
        rbCoktan.setSelected(true);

        formatInputPanel.add(rbCoktan);
        formatInputPanel.add(rbAcik);
        formatInputPanel.add(Box.createHorizontalStrut(14));
        formatSection.add(formatInputPanel);

        mainPanel.add(formatSection);

        // Çoktan Seçmeli Paneli
        mcPanel = new JPanel();
        mcPanel.setOpaque(false);
        mcPanel.setLayout(new BoxLayout(mcPanel, BoxLayout.Y_AXIS));

        JPanel optionsRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        optionsRow1.setOpaque(false);
        JPanel optionAPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionAPanel.setOpaque(false);
        rbOptionA = new JRadioButton();
        JLabel lblA = new JLabel("A:");
        CustomRadioButtonIcon optionIcon = new CustomRadioButtonIcon(16,selectedColor);
        rbOptionA.setIcon(optionIcon);
        rbOptionA.setBorderPainted(false);
        rbOptionA.setContentAreaFilled(false);
        rbOptionA.setFocusPainted(false);
        optionAText = new JTextField(10);
        optionAPanel.add(rbOptionA);
        optionAPanel.add(lblA);
        optionAPanel.add(optionAText);

        JPanel optionBPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionBPanel.setOpaque(false);
        rbOptionB = new JRadioButton();
        JLabel lblB = new JLabel("B:");
        rbOptionB.setIcon(optionIcon);
        rbOptionB.setBorderPainted(false);
        rbOptionB.setContentAreaFilled(false);
        rbOptionB.setFocusPainted(false);
        optionBText = new JTextField(10);
        optionBPanel.add(rbOptionB);
        optionBPanel.add(lblB);
        optionBPanel.add(optionBText);

        optionsRow1.add(optionAPanel);
        optionsRow1.add(optionBPanel);
        mcPanel.add(optionsRow1);

        JPanel optionsRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        optionsRow2.setOpaque(false);
        JPanel optionCPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionCPanel.setOpaque(false);
        rbOptionC = new JRadioButton();
        JLabel lblC = new JLabel("C:");
        rbOptionC.setIcon(optionIcon);
        rbOptionC.setBorderPainted(false);
        rbOptionC.setContentAreaFilled(false);
        rbOptionC.setFocusPainted(false);
        optionCText = new JTextField(10);
        optionCPanel.add(rbOptionC);
        optionCPanel.add(lblC);
        optionCPanel.add(optionCText);

        JPanel optionDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionDPanel.setOpaque(false);
        rbOptionD = new JRadioButton();
        JLabel lblD = new JLabel("D:");
        rbOptionD.setIcon(optionIcon);
        rbOptionD.setBorderPainted(false);
        rbOptionD.setContentAreaFilled(false);
        rbOptionD.setFocusPainted(false);
        optionDText = new JTextField(10);
        optionDPanel.add(rbOptionD);
        optionDPanel.add(lblD);
        optionDPanel.add(optionDText);

        rbOptionA.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbOptionB.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbOptionC.addActionListener(e -> SoundPlayerUtil.playSelectSound());
        rbOptionD.addActionListener(e -> SoundPlayerUtil.playSelectSound());

        optionAText.setFont(questionTextFont);
        optionBText.setFont(questionTextFont);
        optionCText.setFont(questionTextFont);
        optionDText.setFont(questionTextFont);

        optionAText.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        optionBText.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        optionCText.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        optionDText.setBorder(BorderFactory.createLineBorder(borderColor, 1));

        lblA.setFont(radioFont);
        lblB.setFont(radioFont);
        lblC.setFont(radioFont);
        lblD.setFont(radioFont);

        optionsRow2.add(optionCPanel);
        optionsRow2.add(optionDPanel);
        mcPanel.add(optionsRow2);

        bgCorrect = new ButtonGroup();
        bgCorrect.add(rbOptionA);
        bgCorrect.add(rbOptionB);
        bgCorrect.add(rbOptionC);
        bgCorrect.add(rbOptionD);
        mainPanel.add(mcPanel);

        // Açık Uçlu Paneli
        openPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        openPanel.setOpaque(false);
        JLabel lblOpenAnswer = new JLabel("Cevap:");
        Font cevapFont = FontUtil.getInstance().loadCustomFont("/fonts/Roboto-BoldCondensed.ttf",24f);
        lblOpenAnswer.setFont(cevapFont);
        lblOpenAnswer.setForeground(Color.BLACK);
        openAnswerField = new JTextField(20);
        openAnswerField.setFont(questionTextFont);
        openAnswerField.setBorder(BorderFactory.createLineBorder(new Color(235,173,214)));
        openPanel.add(lblOpenAnswer);
        lblOpenAnswer.setForeground(new Color(67,83,126));
        openPanel.add(openAnswerField);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(openPanel);

        mcPanel.setVisible(true);
        openPanel.setVisible(false);

        rbCoktan.addActionListener(e -> {
            SoundPlayerUtil.playSelectSound();
            mcPanel.setVisible(true);
            openPanel.setVisible(false);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        rbAcik.addActionListener(e -> {
            SoundPlayerUtil.playSelectSound();
            mcPanel.setVisible(false);
            openPanel.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Alt Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.setOpaque(false);
        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/images/reset.png"));
        JButton btnSifirla = new JButton(resetIcon);
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/images/save.png"));
        JButton btnKaydet = new JButton(saveIcon);

        btnSifirla.setBorderPainted(false);
        btnSifirla.setContentAreaFilled(false);
        btnSifirla.setFocusPainted(false);

        btnKaydet.setBorderPainted(false);
        btnKaydet.setContentAreaFilled(false);
        btnKaydet.setFocusPainted(false);

        btnSifirla.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            clearForm();});
        btnKaydet.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            saveQuestionAction();
        }
    );

        rightBottomPanel.add(btnSifirla);
        rightBottomPanel.add(btnKaydet);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

        mainPanel.add(bottomPanel);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);


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
        transparentPanel.setBounds((frameWidth - panelW) / 2, (frameHeight - panelH) / 2,
                panelW, panelH);
        transparentPanel.setLayout(new BorderLayout());
        transparentPanel.add(scrollPane, BorderLayout.CENTER);

        overlayPanel.add(transparentPanel);

        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/images/home.png"));
        JButton btnHome = new JButton(homeIcon);
        btnHome.setBounds(10, 45, homeIcon.getIconWidth(), homeIcon.getIconHeight());
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e ->
        {
            SoundPlayerUtil.playClickSound();
            ScreenController.getInstance().switchToMainMenu(this);
        });
        overlayPanel.add(btnHome);

        setVisible(true);
    }

    private void saveQuestionAction() {
        String questionText = questionTextArea.getText().trim();
        if(questionText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Soru metni boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String difficulty = rbKolay.isSelected() ? "Kolay" :
                rbOrta.isSelected() ? "Orta" : "Zor";

        if(rbFotoEvet.isSelected() && uploadedPhoto == null){
            JOptionPane.showMessageDialog(this, "Fotoğraf seçilmeden kayıt yapılamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(rbCoktan.isSelected()){
            String optionA = optionAText.getText().trim();
            String optionB = optionBText.getText().trim();
            String optionC = optionCText.getText().trim();
            String optionD = optionDText.getText().trim();
            if(optionA.isEmpty() || optionB.isEmpty() ||
                    optionC.isEmpty() || optionD.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tüm seçenekler doldurulmalı.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char correctOption = ' ';
            if(rbOptionA.isSelected())      correctOption = 'A';
            else if(rbOptionB.isSelected()) correctOption = 'B';
            else if(rbOptionC.isSelected()) correctOption = 'C';
            else if(rbOptionD.isSelected()) correctOption = 'D';
            else {
                JOptionPane.showMessageDialog(this, "Lütfen doğru cevabı seçiniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MCQuestion question;
            if(rbFotoEvet.isSelected()){
                ImageIcon photoIcon = new ImageIcon(uploadedPhoto.getAbsolutePath());
                question = new MCQuestion(questionText, difficulty,
                        optionA, optionB, optionC, optionD, correctOption, photoIcon, uploadedPhoto);
            } else {
                question = new MCQuestion(questionText, difficulty,
                        optionA, optionB, optionC, optionD, correctOption);
            }
            saveQuestionToFile(question);

        } else if(rbAcik.isSelected()){
            String correctAnswer = openAnswerField.getText().trim();
            if(correctAnswer.isEmpty()){
                JOptionPane.showMessageDialog(this, "Cevap boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }
            OEQuestion question;
            if(rbFotoEvet.isSelected()){
                ImageIcon photoIcon = new ImageIcon(uploadedPhoto.getAbsolutePath());
                question = new OEQuestion(questionText, difficulty, correctAnswer, photoIcon, uploadedPhoto);
            } else {
                question = new OEQuestion(questionText, difficulty, correctAnswer);
            }
            saveQuestionToFile(question);
        }
    }

    private void saveQuestionToFile(Question question) {
        Color customBg = new Color(253, 249, 250);
        UIManager.put("OptionPane.background", customBg);
        UIManager.put("Panel.background", customBg);

        String fileName = JOptionPane.showInputDialog(this, "Kayıt Adı:","Soru Ekle", JOptionPane.PLAIN_MESSAGE);

        if (fileName == null) {
            UIManager.put("OptionPane.background", null);
            UIManager.put("Panel.background", null);
            return;
        }

        if (fileName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Geçerli bir kayıt adı giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
            UIManager.put("OptionPane.background", null);
            UIManager.put("Panel.background", null);
            return;
        }
        fileName = fileName.trim() + ".dat";

        File jarDir = null;
        try {
            jarDir = new File(NewQuestionScreen.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File questionsDir = new File(jarDir, "src/questions");
        if (!questionsDir.exists()) {
            questionsDir.mkdirs();
        }

        File file = new File(questionsDir, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(question);
            JOptionPane.showMessageDialog(this, "Soru başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            ScreenController.getInstance().switchToMainMenu(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Soru kaydedilirken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        QuestionPool.update();
    }



    private void clearForm() {
        questionTextArea.setText("");
        optionAText.setText("");
        optionBText.setText("");
        optionCText.setText("");
        optionDText.setText("");
        openAnswerField.setText("");

        rbCoktan.setSelected(true);
        mcPanel.setVisible(true);
        openPanel.setVisible(false);
        rbKolay.setSelected(true);

        rbFotoHayir.setSelected(true);
        photoPanel.setVisible(false);
        uploadedPhoto = null;
        lblPhotoPreview.setText("");
    }
}

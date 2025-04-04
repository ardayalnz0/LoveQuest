package view;

import controller.LoadQuestionController;
import controller.QuestionPool;
import controller.ScreenController;
import domain.Question;
import utils.ConvertUtil;
import utils.FontUtil;
import utils.SoundPlayerUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ShowQuestionsScreen extends JFrame {

    public ShowQuestionsScreen() {
        setTitle("Sorular");
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

        // Baslik
        JLabel headerLabel = new JLabel("Sorular");
        Font boldFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 60f);
        headerLabel.setFont(boldFont);
        headerLabel.setForeground(new Color(235, 173, 214));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);
        mainPanel.add(Box.createVerticalStrut(40));

        // List Panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);

        File jarDir = null;
        try {
            jarDir = new File(ShowQuestionsScreen.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File questionsDir = new File(jarDir, "src/questions");
        if (questionsDir.exists() && questionsDir.isDirectory()) {
            File[] files = questionsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".dat"));
            if (files != null && files.length > 0) {
                for (File file : files) {

                    JPanel filePanel = new JPanel();
                    filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
                    filePanel.setOpaque(false);
                    filePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    String fName = file.getName();
                    if (fName.endsWith(".dat")) {
                        fName = fName.substring(0, fName.length() - 4);
                    }
                    fName = ConvertUtil.getInstance().convertTurkishToEnglish(fName);
                    JLabel fileLabel = new JLabel(fName);


                    Font sectionLabelFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 36f);
                    fileLabel.setFont(sectionLabelFont);
                    fileLabel.setForeground(new Color(122, 170, 217));

                    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    leftPanel.setOpaque(false);
                    leftPanel.add(fileLabel);

                    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    rightPanel.setOpaque(false);

                    Dimension fixedSize = new Dimension(300, 55);
                    leftPanel.setPreferredSize(fixedSize);
                    leftPanel.setMinimumSize(fixedSize);
                    leftPanel.setMaximumSize(fixedSize);

                    ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/images/settingsx0.5.png"));
                    JButton btnSettings = new JButton(settingsIcon);
                    btnSettings.setBorderPainted(false);
                    btnSettings.setContentAreaFilled(false);
                    btnSettings.setFocusPainted(false);
                    ImageIcon trashIcon = new ImageIcon(getClass().getResource("/images/trashcan.png"));
                    JButton btnTrash = new JButton(trashIcon);
                    btnTrash.setBorderPainted(false);
                    btnTrash.setContentAreaFilled(false);
                    btnTrash.setFocusPainted(false);

                    btnTrash.addActionListener(e -> {
                        SoundPlayerUtil.playClickSound();
                        int choice = JOptionPane.showConfirmDialog(
                                ShowQuestionsScreen.this,
                                "Bu Kaydı Silmek İstediğinize Emin Misiniz?",
                                "Kayit Sil",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if (choice == JOptionPane.YES_OPTION) {
                            if (file.delete()) {
                                listPanel.remove(filePanel);
                                listPanel.revalidate();
                                listPanel.repaint();
                                ScreenController.getInstance().switchToShowQuestionsScreen(this);
                                QuestionPool.update();
                            } else {
                                JOptionPane.showMessageDialog(ShowQuestionsScreen.this,
                                        "Kayıt silinemedi.",
                                        "Hata",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });


                    try {
                        Question loadedQuestion = LoadQuestionController.getInstance().loadQuestion(file);
                        btnSettings.addActionListener(e -> {
                            SoundPlayerUtil.playClickSound();
                            ScreenController.getInstance().switchToEditQuestionScreen(this, loadedQuestion, file.getName());
                        });
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    rightPanel.add(btnSettings);
                    rightPanel.add(Box.createHorizontalStrut(10));
                    rightPanel.add(btnTrash);

                    filePanel.add(Box.createRigidArea(new Dimension(100, 0)));
                    filePanel.add(leftPanel);
                    filePanel.add(Box.createHorizontalGlue());
                    filePanel.add(rightPanel);

                    listPanel.add(filePanel);
                    listPanel.add(Box.createVerticalStrut(10));
                }
            } else {
                JPanel emptyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                emptyPanel.setOpaque(false);
                JLabel emptyLabel = new JLabel("Henüz soru kaydı yok.");
                emptyLabel.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 24f));
                emptyLabel.setForeground(new Color(122, 170, 217));
                emptyPanel.add(emptyLabel);
                listPanel.add(emptyPanel);
            }
        } else {
            JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            errorPanel.setOpaque(false);
            JLabel errorLabel = new JLabel("Questions klasörü bulunamadı.");
            errorLabel.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 24f));
            errorLabel.setForeground(Color.RED);
            errorPanel.add(errorLabel);
            listPanel.add(errorPanel);
        }

        mainPanel.add(listPanel);
        mainPanel.add(Box.createVerticalStrut(40));


        // Alt Panel: Home butonu
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.setOpaque(false);

        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(bottomPanel);

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
            ScreenController.getInstance().switchToMainMenu(this);
        });
        overlayPanel.add(btnHome);

        setVisible(true);
    }
}

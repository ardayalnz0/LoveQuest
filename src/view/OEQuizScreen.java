package view;

import controller.QuizController;
import controller.ScreenController;
import utils.CorrectAnswerDialog;
import utils.FontUtil;
import utils.SoundPlayerUtil;
import utils.WrongAnswerDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OEQuizScreen extends JFrame {

    private JLabel lblQuestionNumber;
    private JLabel lblPhoto;
    private JLabel lblQuestionText;
    private JTextField txtAnswer; // Seçenekler paneli yerine kullanılacak büyük TextField
    private QuizController quizController;

    public OEQuizScreen(QuizController quizController) {
        this.quizController = quizController;

        setTitle("OE Quiz Screen");
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

        // Standart mainPanel (proje boyunca kullanılan yapı)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        mainPanel.setOpaque(false);

        // 1) "Soru X" etiketi
        lblQuestionNumber = new JLabel("Soru 1", SwingConstants.CENTER);
        lblQuestionNumber.setFont(FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 60f));
        lblQuestionNumber.setForeground(new Color(235, 173, 214));
        lblQuestionNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblQuestionNumber);

        // 2) Fotoğraf alanı (başlangıçta gizli, fotoğraf seçilirse gösterilecek)
        lblPhoto = new JLabel();
        lblPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        int photoW = (int) (frameWidth * 0.40);
        int photoH = (int) (frameWidth * 0.24);
        lblPhoto.setPreferredSize(new Dimension(photoW, photoH));
        lblPhoto.setMaximumSize(new Dimension(photoW, photoH));
        lblPhoto.setVisible(false);
        mainPanel.add(lblPhoto);
        mainPanel.add(Box.createVerticalStrut(20));

        // 3) Soru metni
        lblQuestionText = new JLabel("<html>Buraya soru metni gelecek...</html>", SwingConstants.CENTER);
        lblQuestionText.setFont(FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 24f));
        lblQuestionText.setForeground(new Color(67, 83, 126));
        lblQuestionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblQuestionText);
        mainPanel.add(Box.createVerticalStrut(40));

        // 4) Cevap alanı: pembe kenarlı bir TextField
        txtAnswer = new JTextField();
        txtAnswer.setFont(FontUtil.getInstance().loadCustomFont("/fonts/Roboto-Thin.ttf", 24f));
        txtAnswer.setForeground(new Color(67, 83, 126));
        txtAnswer.setHorizontalAlignment(JTextField.CENTER);
        txtAnswer.setBorder(new LineBorder(new Color(235, 173, 214), 3)); // Pembe kenarlık
        txtAnswer.setMaximumSize(new Dimension(800, 50));
        txtAnswer.setPreferredSize(new Dimension(800, 50));
        txtAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtAnswer);
        mainPanel.add(Box.createVerticalStrut(40));

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setOpaque(false);
        containerPanel.add(Box.createVerticalGlue());
        containerPanel.add(mainPanel);
        containerPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        int panelW = (int) (frameWidth * 0.71);
        int panelH = (int) (frameHeight * 0.69);
        JPanel transparentPanel = new JPanel(new BorderLayout());
        transparentPanel.setOpaque(false);
        transparentPanel.setBounds((frameWidth - panelW) / 2, (frameHeight - panelH) / 2, panelW, panelH);
        transparentPanel.add(scrollPane, BorderLayout.CENTER);
        bgLabel.add(transparentPanel);

        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/images/home.png"));
        JButton btnHome = new JButton(homeIcon);
        btnHome.setBounds(10, 45, homeIcon.getIconWidth(), homeIcon.getIconHeight());
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Quizi Bırakıp Çıkmak İstediğinize Emin misiniz?",
                    "Uyarı",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                SoundPlayerUtil.playClickSound();
                ScreenController.getInstance().switchToMainMenu(this);
            }
        });
        getLayeredPane().add(btnHome, JLayeredPane.POPUP_LAYER);

        initializeScreen();

        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/images/savex2.png"));
        JButton btnSave = new JButton(saveIcon);
        btnSave.setBounds(frameWidth - saveIcon.getIconWidth() - 10, frameHeight - saveIcon.getIconHeight() - 20,
                saveIcon.getIconWidth(), saveIcon.getIconHeight());
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> {
            SoundPlayerUtil.playClickSound();
            if(quizController.validateAnswer(quizController.getCurrentQuestion(), getSelectedOption())){
                new CorrectAnswerDialog(this).setVisible(true);
                quizController.incrementCorrectAnswerCount();
            }
            else{
                new WrongAnswerDialog(this).setVisible(true);
            }

            txtAnswer.setText("");

            if(quizController.getCurrentQuestionIndex() < quizController.getQuiz().getQuestions().size() - 1){
                quizController.nextQuestion();
                quizController.setUpCurrentOEQuestion(this);
            } else{
                ScreenController.getInstance().switchToEndScreen(this, quizController);
            }
        });
        getLayeredPane().add(btnSave, JLayeredPane.POPUP_LAYER);

        setVisible(true);
    }

    // Güncelleme metodları
    public void setQuestionNumber(int number) {
        lblQuestionNumber.setText("Soru " + number);
    }

    public void setQuestionText(String text) {
        lblQuestionText.setText("<html><div style='text-align:center;'>" + text + "</div></html>");
    }

    public void setQuestionPhoto(ImageIcon photo) {
        if (photo != null) {
            int w = lblPhoto.getPreferredSize().width;
            int h = lblPhoto.getPreferredSize().height;
            Image scaled = photo.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new ImageIcon(scaled));
            lblPhoto.setVisible(true);
        } else {
            lblPhoto.setVisible(false);
        }
    }


    public String getSelectedOption() {
        return txtAnswer.getText();
    }

    public void initializeScreen(){
        quizController.initializeOEScreen(this);
    }
}

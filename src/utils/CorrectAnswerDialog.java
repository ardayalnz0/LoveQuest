package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CorrectAnswerDialog extends JDialog {
    public CorrectAnswerDialog(JFrame parent) {
        super(parent, "Doğru Cevap", true);
        setSize(832, 440);
        setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(parent);
        getRootPane().setBorder(BorderFactory.createEmptyBorder());

        // Arka plan resmi
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/PopUpContainer.png")));
        background.setBounds(0, 0, 832, 440);
        add(background);

        // "DOĞRU CEVAP" Label
        JLabel correctLabel = new JLabel("DOGRU CEVAP!", SwingConstants.CENTER);
        correctLabel.setBounds(0, 100, 832, 100);
        Font customFont = FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 50f);
        correctLabel.setFont(customFont);
        correctLabel.setForeground(new Color(141,227,179));
        background.add(correctLabel);

        // "DEVAM ET" Butonu
        JButton continueButton = new JButton(new ImageIcon(getClass().getResource("/images/NextButton.png")));
        continueButton.setBounds((832 - 229) / 2, 250, 229, 80);
        continueButton.setBorderPainted(false);
        continueButton.setFocusPainted(false);
        continueButton.setContentAreaFilled(false);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //QuizController.getInstance().nextQuestion();
            }
        });
        background.add(continueButton);
    }
}

package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WrongAnswerDialog extends JDialog {
    public WrongAnswerDialog(JFrame parent) {
        super(parent, "Yanlış Cevap", true);
        setSize(832, 440);
        setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Arka planı tamamen şeffaf yap
        setLocationRelativeTo(parent);

        // Arka plan resmi
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/PopUpContainerRed.png")));
        background.setBounds(0, 0, 832, 440);
        add(background);

        // "DOĞRU CEVAP" Label
        JLabel correctLabel = new JLabel("YANLIS CEVAP!", SwingConstants.CENTER);
        correctLabel.setBounds(0, 100, 832, 100);
        Font customFont =FontUtil.getInstance().loadCustomFont("/fonts/THEBOLDFONT-FREEVERSION.ttf", 50f);
        correctLabel.setFont(customFont);
        correctLabel.setForeground(new Color(239,82,61));
        background.add(correctLabel);

        // "DEVAM ET" Butonu
        JButton continueButton = new JButton(new ImageIcon(getClass().getResource("/images/NextRed.png")));
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
        getRootPane().setBorder(BorderFactory.createEmptyBorder());
    }
}

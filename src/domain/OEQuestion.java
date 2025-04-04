package domain;

import javax.swing.*;
import java.io.File;

public class OEQuestion extends Question {
    private static final long serialVersionUID = 1L;

    private String correctAnswer;

    public OEQuestion(String questionText, String difficulty, String correctAnswer) {
        super(questionText, difficulty);
        this.correctAnswer = correctAnswer;
    }

    public OEQuestion(String questionText, String difficulty, String correctAnswer, ImageIcon photo, File photoFile){
        super(questionText, difficulty);
        this.correctAnswer = correctAnswer;
        setPhoto(photo);
        setPhotoFile(photoFile);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

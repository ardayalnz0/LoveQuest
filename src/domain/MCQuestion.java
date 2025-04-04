package domain;

import javax.swing.*;
import java.io.File;

public class MCQuestion extends Question {
    private static final long serialVersionUID = 1L;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctOption; // 'A', 'B', 'C', 'D'

    public MCQuestion(String questionText, String difficulty,
                      String optionA, String optionB, String optionC, String optionD,
                      char correctOption) {
        super(questionText, difficulty);
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    public MCQuestion(String questionText, String difficulty,
                      String optionA, String optionB, String optionC, String optionD,
                      char correctOption, ImageIcon photo, File photoFile) {
        super(questionText, difficulty);
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        setPhoto(photo);
        setPhotoFile(photoFile);
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public char getCorrectOption() {
        return correctOption;
    }

    public String getCorrectOptionAsString(){
        return correctOption + "";
    }

    public void setCorrectOption(char correctOption) {
        this.correctOption = correctOption;
    }
}

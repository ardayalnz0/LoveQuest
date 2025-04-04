package domain;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String questionText;
    protected String difficulty;
    private ImageIcon photo;
    private File photoFile;

    public Question(String questionText, String difficulty) {
        this.questionText = questionText;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ImageIcon getPhotoIcon() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public String getPhotoName(){
        return photoFile.getName();
    }

    public void setPhotoFile(File photoFile){
        this.photoFile = photoFile;
    }

    public File getPhotoFile(){
        return photoFile;
    }
}

package controller;

import domain.Question;
import java.io.*;

public class LoadQuestionController {
    private static LoadQuestionController instance;

    private LoadQuestionController() { }

    public static LoadQuestionController getInstance() {
        if (instance == null) {
            instance = new LoadQuestionController();
        }
        return instance;
    }

    public Question loadQuestion(File file) throws IOException, ClassNotFoundException {
        if(file == null || !file.exists()) {
            throw new IllegalArgumentException("File not found: " + file);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Question) {
                return (Question) obj;
            } else {
                throw new IOException("Deserialized object is not a Question instance.");
            }
        }
    }
}

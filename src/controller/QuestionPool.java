package controller;

import domain.Question;
import domain.MCQuestion;
import domain.OEQuestion;
import view.ShowQuestionsScreen;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class QuestionPool {

    private static List<Question> questionList;

    static {
        loadQuestions();
    }

    private static void loadQuestions() {
        questionList = new ArrayList<>();
        File jarDir = null;
        try {
            jarDir = new File(ShowQuestionsScreen.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File questionDir = new File(jarDir, "src/questions");

        if (!questionDir.exists() || !questionDir.isDirectory()) {
            System.err.println("Questions directory not found: " + questionDir.getAbsolutePath());
            return;
        }

        File[] files = questionDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".dat"));
        if (files != null) {
            for (File file : files) {
                try {
                    Question q = LoadQuestionController.getInstance().loadQuestion(file);
                    questionList.add(q);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading question from file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getTotalQuestionCount() {
        return questionList.size();
    }

    public static int getQuestionCountByDifficulty(String difficulty) {
        int count = 0;
        for (Question q : questionList) {
            if (q.getDifficulty().equalsIgnoreCase(difficulty)) {
                count++;
            }
        }
        return count;
    }

    public static int getQuestionCountByFormat(String questionType) {
        int count = 0;
        for (Question q : questionList) {
            if (questionType.equalsIgnoreCase("Çoktan Seçmeli") && q instanceof MCQuestion) {
                count++;
            } else if (questionType.equalsIgnoreCase("Açık Uçlu") && q instanceof OEQuestion) {
                count++;
            }
        }
        return count;
    }

    public static int getQuestionCountByDifficultyAndFormat(String difficulty, String questionType) {
        int count = 0;
        for (Question q : questionList) {
            if (q.getDifficulty().equalsIgnoreCase(difficulty)) {
                if (questionType.equalsIgnoreCase("Çoktan Seçmeli") && q instanceof MCQuestion) {
                    count++;
                } else if (questionType.equalsIgnoreCase("Açık Uçlu") && q instanceof OEQuestion) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void update() {
        loadQuestions();
    }

    public static List<Question> getQuestionList() {
        return questionList;
    }
}

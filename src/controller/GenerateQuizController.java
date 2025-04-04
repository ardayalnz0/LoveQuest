package controller;

import domain.MCQuestion;
import domain.OEQuestion;
import domain.Question;
import domain.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateQuizController {

    private int questionNumber;
    private String difficulty;
    private String questionType; //

    private static GenerateQuizController instance;

    private GenerateQuizController() { }

    public static GenerateQuizController getInstance() {
        if (instance == null) {
            instance = new GenerateQuizController();
        }
        return instance;
    }

    public void setQuizSettings(int questionNumber, String difficulty, String questionType) {
        this.questionNumber = questionNumber;
        this.difficulty = difficulty;
        this.questionType = questionType;

    }

    public boolean validateTotalQuestions() {
        int totalQuestions = QuestionPool.getTotalQuestionCount();
        if (totalQuestions < questionNumber) {
            return false;
        }
        return true;
    }

    public boolean validateDifficulty(){
        if(difficulty.equalsIgnoreCase("Rastgele")){
            return validateTotalQuestions();
        }
        int difficultyCount = QuestionPool.getQuestionCountByDifficulty(difficulty);
        if (difficultyCount < questionNumber) {
            return false;
        }
        return true;
    }

    public boolean validateFormat(){
        int formatCount = QuestionPool.getQuestionCountByFormat(questionType);
        return formatCount >= questionNumber;
    }

    public boolean validateFormatByDifficulty(){
        if(difficulty.equalsIgnoreCase("Rastgele")){
            return validateFormat();
        }
        int formatCount = QuestionPool.getQuestionCountByDifficultyAndFormat(difficulty, questionType);
        return formatCount >= questionNumber;
    }

    public Quiz generateQuiz() {
        List<Question> allQuestions = QuestionPool.getQuestionList();
        List<Question> filteredQuestions = new ArrayList<>();

        for (Question q : allQuestions) {
            if ((difficulty.equalsIgnoreCase("Rastgele") || q.getDifficulty().equalsIgnoreCase(difficulty)) &&
                    (questionType.equalsIgnoreCase("Rastgele") ||
                            (questionType.equalsIgnoreCase("Çoktan Seçmeli") && q instanceof MCQuestion) ||
                            (questionType.equalsIgnoreCase("Açık Uçlu") && q instanceof OEQuestion))) {
                filteredQuestions.add(q);
            }
        }
        Collections.shuffle(filteredQuestions);
        if (filteredQuestions.size() < questionNumber) {
            throw new IllegalArgumentException("Yeterli sayıda uygun soru bulunamadı.");
        }
        return new Quiz(filteredQuestions.subList(0, questionNumber));
    }
}


package controller;

import domain.MCQuestion;
import domain.OEQuestion;
import domain.Question;
import domain.Quiz;
import utils.ConvertUtil;
import view.MCQuizScreen;
import view.OEQuizScreen;

public class QuizController {
    private Quiz quiz;
    private int currentQuestionIndex;
    private Question firstQuestion;
    private int correctAnswerCount;

    public QuizController(Quiz quiz) {
        this.quiz = quiz;
        this.currentQuestionIndex = 0;
        this.firstQuestion = quiz.getQuestions().get(0);
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < quiz.getQuestions().size()) {
            return quiz.getQuestions().get(currentQuestionIndex);
        }
        return null;
    }

    public void nextQuestion() {
        if (currentQuestionIndex < quiz.getQuestions().size() - 1) {
            currentQuestionIndex++;
        }
    }

    public double getResult(){
        return (double) (getCorrectAnswerCount() * 100) / quiz.getQuestions().size();
    }

    public int getCorrectAnswerCount(){
        return correctAnswerCount;
    }

    public int getWrongAnswerCount(){
        return quiz.getQuestions().size() - correctAnswerCount;
    }

    public void incrementCorrectAnswerCount(){
        correctAnswerCount++;
    }

    public boolean validateAnswer(Question question, String answer){
        if(question instanceof MCQuestion){
            return ((MCQuestion) question).getCorrectOptionAsString().equalsIgnoreCase(answer);
        }
        else if(question instanceof OEQuestion){
            String formattedAnswer = ConvertUtil.getInstance().convertTurkishToEnglish(((OEQuestion) question).getCorrectAnswer());
            String formattedInput = ConvertUtil.getInstance().convertTurkishToEnglish(answer);
            return formattedAnswer.equalsIgnoreCase(formattedInput);
        }
        return false;
    }

    public void setUpCurrentOEQuestion(OEQuizScreen oeQuizScreen){
        Question currentQ = quiz.getQuestions().get(currentQuestionIndex);
        oeQuizScreen.setQuestionNumber(currentQuestionIndex);
        oeQuizScreen.setQuestionText(currentQ.getQuestionText());
        if(currentQ.getPhotoFile() != null){
            oeQuizScreen.setQuestionPhoto(currentQ.getPhotoIcon());
        } else{
            oeQuizScreen.setQuestionPhoto(null);
        }
    }

    public void initializeOEScreen(OEQuizScreen oeQuizScreen){
        oeQuizScreen.setQuestionNumber(1);
        oeQuizScreen.setQuestionText(firstQuestion.getQuestionText());
        if(firstQuestion.getPhotoFile() != null){
            oeQuizScreen.setQuestionPhoto(firstQuestion.getPhotoIcon());
        }
    }

    public void setUpCurrentMCQuestion(MCQuizScreen mcQuizScreen){
        Question currentQ = quiz.getQuestions().get(currentQuestionIndex);
        mcQuizScreen.setQuestionNumber(currentQuestionIndex + 1);
        mcQuizScreen.setQuestionText(currentQ.getQuestionText());
        if(currentQ.getPhotoFile() != null){
            mcQuizScreen.setQuestionPhoto(currentQ.getPhotoIcon());
        }
        else{
            mcQuizScreen.setQuestionPhoto(null);
        }
        mcQuizScreen.setOptions(((MCQuestion) currentQ).getOptionA(), ((MCQuestion) currentQ).getOptionB(), ((MCQuestion) currentQ).getOptionC(), ((MCQuestion) currentQ).getOptionD());
    }

    public void initializeMCScreen(MCQuizScreen mcQuizScreen){
        mcQuizScreen.setQuestionNumber(1);
        mcQuizScreen.setQuestionText(firstQuestion.getQuestionText());
        if(firstQuestion.getPhotoFile() != null){
            mcQuizScreen.setQuestionPhoto(firstQuestion.getPhotoIcon());
        }
        mcQuizScreen.setOptions(((MCQuestion) firstQuestion).getOptionA(), ((MCQuestion) firstQuestion).getOptionB(), ((MCQuestion) firstQuestion).getOptionC(), ((MCQuestion) firstQuestion).getOptionD());

    }

    public Quiz getQuiz(){
        return quiz;
    }

    public int getCurrentQuestionIndex(){
        return currentQuestionIndex;
    }
}
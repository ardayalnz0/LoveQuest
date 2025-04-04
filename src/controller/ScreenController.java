package controller;

import domain.Question;
import domain.Quiz;
import view.*;

import javax.swing.JFrame;

public class ScreenController {
    private static ScreenController instance;

    private ScreenController() { }

    public static ScreenController getInstance() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

    public void switchToMainMenu(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }

    public void switchToSettingsMenu(JFrame currentFrame){
        if(currentFrame != null){
            currentFrame.dispose();
        }
        SettingsScreen settingsScreen = new SettingsScreen(currentFrame);
        settingsScreen.setVisible(true);
    }

    public void switchToFrame(JFrame currentFrame, JFrame frame){
        if(currentFrame != null){
            currentFrame.dispose();
        }
        frame.setVisible(true);
    }

    public void switchToEditQuestionScreen(JFrame currentFrame,Question question, String saveFileName){
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        EditQuestionScreen editQuestionScreen = new EditQuestionScreen(question, saveFileName);
        editQuestionScreen.setVisible(true);
    }

    public void switchToNewQuestionScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        NewQuestionScreen newQuestionScreen = new NewQuestionScreen();
        newQuestionScreen.setVisible(true);
    }

    public void switchToMCQuizScreen(JFrame currentFrame, QuizController quizController) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        MCQuizScreen mcQuizScreen = new MCQuizScreen(quizController);
        mcQuizScreen.setVisible(true);
    }

    public void switchToOEQuizScreen(JFrame currentFrame, QuizController quizController){
        if(currentFrame != null){
            currentFrame.dispose();
        }
        OEQuizScreen oeQuizscreen = new OEQuizScreen(quizController);
        oeQuizscreen.setVisible(true);
    }

    public void switchToEndScreen(JFrame currentFrame, QuizController quizController){
        if(currentFrame != null){
            currentFrame.dispose();
        }
        EndScreen endScreen = new EndScreen(quizController);
        endScreen.setVisible(true);
    }

    public void switchToShowQuestionsScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        ShowQuestionsScreen showQuestionsScreen = new ShowQuestionsScreen();
        showQuestionsScreen.setVisible(true);
    }

    public void switchToGenerateQuizScreen(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        GenerateQuizScreen generateQuizScreen = new GenerateQuizScreen();
        generateQuizScreen.setVisible(true);
    }
}

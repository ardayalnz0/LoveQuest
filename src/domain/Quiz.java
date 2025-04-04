package domain;

import java.util.List;

public class Quiz {
    private List<Question> questions;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Quiz Questions:\n");
        for (Question q : questions) {
            sb.append(q.toString()).append("\n");
        }
        return sb.toString();
    }
}
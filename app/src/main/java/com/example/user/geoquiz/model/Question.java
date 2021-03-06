package com.example.user.geoquiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {

    private int image;
    private String imageDescription;
    private String questionText;
    private List<String> answers = new ArrayList<>();
    private int rightAnswer;

    public Question(int image, String imageDescription, String questionText, int rightAnswer) {
        this.image = image;
        this.imageDescription = imageDescription;
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswerText() {
        return answers.get(rightAnswer);
    }

    public void copyAnswers(List<String> answers) {
        this.answers.addAll(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return image == question.image
                && rightAnswer == question.rightAnswer
                && imageDescription.equals(question.imageDescription)
                && questionText.equals(question.questionText)
                && answers.equals(question.answers);

    }

    @Override
    public int hashCode() {
        int result = image;
        result = 31 * result + imageDescription.hashCode();
        result = 31 * result + questionText.hashCode();
        result = 31 * result + answers.hashCode();
        result = 31 * result + rightAnswer;
        return result;
    }
}

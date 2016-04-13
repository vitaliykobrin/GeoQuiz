package com.example.user.geoquiz.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class QuizUtil {

    public static final int QUESTIONS_COUNT = 5;

    public static Quiz prepareQuiz() {
        return new Quiz("Quiz", "sdsdfs sdfsdf sdfsdf", getQuestions());
    }

    private static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question1", getAnswers(4), 0));
        questions.add(new Question("Question2", getAnswers(5), 2));
        questions.add(new Question("Question3", getAnswers(3), 1));
        questions.add(new Question("Question4", getAnswers(4), 3));
        questions.add(new Question("Question5", getAnswers(3), 2));
        questions.add(new Question("Question6", getAnswers(4), 0));
        questions.add(new Question("Question7", getAnswers(5), 2));
        questions.add(new Question("Question8", getAnswers(3), 1));
        questions.add(new Question("Question9", getAnswers(4), 3));
        questions.add(new Question("Question10", getAnswers(3), 2));
        return questions;
    }

    private static List<String> getAnswers(int count) {
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            answers.add("answer" + i);
        }
        return answers;
    }

    public static List<Question> getRandomQuestions(List<Question> questionsList) {
        Set<Question> questionsSet = null;
        try {
            questionsSet = new HashSet<>();
            if (questionsList.size() < QUESTIONS_COUNT) {
                throw new IOException("");
            }
            while (questionsSet.size() < QUESTIONS_COUNT) {
                int randomIndex = (int) (Math.random() * questionsList.size());
                questionsSet.add(questionsList.get(randomIndex));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(questionsSet);
    }

    public static int countRightAnswers(Quiz quiz, Map<Integer, Integer> userAnswers) {
        int result = 0;
        for (Integer questionNum : userAnswers.keySet()) {
            if (quiz.getQuestions().get(questionNum).getRightAnswer() == userAnswers.get(questionNum)) {
                result++;
            }
        }
        quiz.setLastResult(result);
        if (result > quiz.getBestResult()) {
            quiz.setBestResult(result);
        }
        return result;
    }

    public static String generateMessage(int result) {
        switch (result) {
            case 1: return "BAD!";
            case 2: return "Not enough!";
            case 3: return "Not bad!";
            case 4: return "Nice!";
            case 5: return "Awesome!";
            default: return "Looser!";
        }
    }

    public static String countSpentTime(long startTime) {
        long currentTime = System.currentTimeMillis();
        long spentTime = Math.abs(currentTime - startTime);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(spentTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(spentTime) - minutes * 60;
        return minutes + " minutes, " + seconds + " seconds";
    }
}

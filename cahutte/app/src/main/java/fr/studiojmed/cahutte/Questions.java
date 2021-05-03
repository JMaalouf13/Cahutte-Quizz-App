package fr.studiojmed.cahutte;

import org.json.JSONArray;

public class Questions {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String  correct_answer;
    private JSONArray incorrect_answers;

    public Questions(String category, String type, String difficulty, String question, String correct_answer, JSONArray incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", incorrect_answers='" + incorrect_answers + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public JSONArray getIncorrect_answers() {
        return incorrect_answers;
    }
}

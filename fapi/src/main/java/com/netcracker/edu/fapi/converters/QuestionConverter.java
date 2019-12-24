package com.netcracker.edu.fapi.converters;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.AnswerService;
import com.netcracker.edu.fapi.service.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionConverter {

    @Autowired
    private TypeQuestionService typeQuestionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    AnswerConverter answerConverter;

    public ViewQuestion convertQuestionToViewQuestionWithAnswer(Question question) {

        ViewQuestion viewQuestion = new ViewQuestion();
        viewQuestion.setId(question.getId());
        viewQuestion.setTextTitle(question.getTextTitle());
        viewQuestion.setIdPoll(question.getIdPoll());
        viewQuestion.setRequired(question.getRequired());

        String type = typeQuestionService.getById(question.getIdType()).getType();
        viewQuestion.setType(type);

        List<Answer> answers = answerService.getAllAnswerByQuestionId(question.getId());
        List<ViewAnswer> viewAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            viewAnswers.add(answerConverter.convertAnswerToViewAnswer(answer));
        }
        viewQuestion.setAnswers(viewAnswers);

        return viewQuestion;
    }

    public Question convertViewQuestToQuestion(ViewQuestion viewQuestion) {

        Question question = new Question();

        question.setId(viewQuestion.getId());
        question.setIdPoll(viewQuestion.getIdPoll());
        question.setIdType(typeQuestionService.getIdByType(viewQuestion.getType()));
        question.setRequired(viewQuestion.getRequired());
        question.setTextTitle(viewQuestion.getTextTitle());

        return question;
    }

    public ViewQuestion convertQuestionToViewQuestionWithAnswerWithStats(Question question) {

        ViewQuestion viewQuestion = new ViewQuestion();
        viewQuestion.setId(question.getId());
        viewQuestion.setTextTitle(question.getTextTitle());
        viewQuestion.setIdPoll(question.getIdPoll());
        viewQuestion.setRequired(question.getRequired());

        String type = typeQuestionService.getById(question.getIdType()).getType();
        viewQuestion.setType(type);

        List<Answer> answers = answerService.getAllAnswerByQuestionId(question.getId());
        List<ViewAnswer> viewAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            viewAnswers.add(answerConverter.convertAnswerToViewAnswerWithStats(answer));
        }
        viewQuestion.setAnswers(viewAnswers);

        return viewQuestion;
    }

    public ViewQuestion convertViewQuestionToViewQuestionWithAnswers(ViewQuestion viewQuestion) {
        List<Answer> answers = answerService.getAllAnswerByQuestionId(viewQuestion.getId());
        List<ViewAnswer> viewAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            viewAnswers.add(answerConverter.convertAnswerToViewAnswerWithStats(answer));
        }
        viewQuestion.setAnswers(viewAnswers);

        return viewQuestion;
    }

}

package com.netcracker.edu.fapi.converters;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.viewModels.ViewAnswer;
import com.netcracker.edu.fapi.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnswerConverter {

    @Autowired
    private StatsService statsService;

    public ViewAnswer convertAnswerToViewAnswer(Answer answer){
        ViewAnswer viewAnswer=new ViewAnswer();

        viewAnswer.setId(answer.getId());
        viewAnswer.setText(answer.getText());
        viewAnswer.setIdQuestion(answer.getIdQuestion());

        return viewAnswer;
    }

    public Answer convertViewAnswerToAnswer(ViewAnswer viewAnswer,Integer idQuestion){

        Answer answer=new Answer();

        answer.setId(viewAnswer.getId());
        answer.setText(viewAnswer.getText());
        answer.setIdQuestion(idQuestion);

        return answer;
    }

    public ViewAnswer convertAnswerToViewAnswerWithStats(Answer answer){
        ViewAnswer viewAnswer=new ViewAnswer();

        viewAnswer.setId(answer.getId());
        viewAnswer.setText(answer.getText());
        viewAnswer.setIdQuestion(answer.getIdQuestion());
        viewAnswer.setStats(statsService.getByIdAnswer(answer.getId()));

        return viewAnswer;
    }
}

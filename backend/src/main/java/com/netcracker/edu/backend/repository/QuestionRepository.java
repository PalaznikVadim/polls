package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.entity.QuestionWithAnswerCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    List<Question> findAllByIdPoll(int idPoll);

    @Query("select new com.netcracker.edu.backend.entity.QuestionWithAnswerCount(q.id,q.textTitle,count (ua.id)) " +
            "from Question q left join UserAnswer ua on q.id=ua.idQuestion group by q.id order by q.id asc ")
    List<QuestionWithAnswerCount> getQuestionStat();
}

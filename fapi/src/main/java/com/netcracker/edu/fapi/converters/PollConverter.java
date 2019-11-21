package com.netcracker.edu.fapi.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.Question;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

@Service
public class PollConverter {

    @Autowired
    private ThemeService themeService;


    public Poll convertViewPollToPoll(ViewPoll viewPoll){
        Poll poll=new Poll();

        if(viewPoll.getDate()!=null)
            poll.setDataTime(viewPoll.getDate());
        else
            poll.setDataTime(new Date());

        poll.setDescription(viewPoll.getDescription());
        if (viewPoll.getId()!=null)
            poll.setId(viewPoll.getId());
        poll.setIdTheme(themeService.findThemeByName(viewPoll.getTheme()).getId());
        poll.setIdUser(viewPoll.getIdUser());
        poll.setShared(viewPoll.getShared());
        poll.setStatus(viewPoll.getStatus());
        poll.setName(viewPoll.getName());
        poll.setLink(viewPoll.getLink());
        return poll;
    }

    public ViewPoll convertPollToViewPoll(Poll poll){
        ViewPoll viewPoll=new ViewPoll();

        viewPoll.setDate(poll.getDataTime());
        viewPoll.setDescription(poll.getDescription());
        viewPoll.setId(poll.getId());
        viewPoll.setName(poll.getName());
        viewPoll.setLink(poll.getLink());
        viewPoll.setIdUser(poll.getIdUser());
        viewPoll.setShared(poll.getShared());
        viewPoll.setStatus(poll.getStatus());
        viewPoll.setTheme(themeService.findThemeById(poll.getIdTheme()).getName());

        return viewPoll;
    }

    public Function<List<Poll>, List<ViewPoll>> collectionTransform=polls -> {
        ObjectMapper m = new ObjectMapper();
        List<ViewPoll> viewPolls=new ArrayList<>();
        for(int i=0;i<polls.size();i++){
            viewPolls.add(convertPollToViewPoll(m.convertValue(polls.get(i),Poll.class)));
        }
         return viewPolls;
    };

}

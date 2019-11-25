package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.converters.PollConverter;
import com.netcracker.edu.fapi.models.Poll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import com.netcracker.edu.fapi.models.viewModels.ViewQuestion;
import com.netcracker.edu.fapi.service.PollService;
import com.netcracker.edu.fapi.service.QuestionService;
import com.netcracker.edu.fapi.validators.PollValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public Poll findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Poll poll=restTemplate.getForObject(backendServerUrl+"api/poll/id?id="+id,Poll.class);
        return poll;
    }

    @Override
    public Page<Poll> findAllByUserId(Integer userId, int page, int size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"api/poll/user?userId="+userId+"&page="+page
                +"&size="+size+"&sort="+sort+"&order="+order, RestPageImpl.class);
    }

    @Override
    public Page<Poll> searchBySubstr(String substr,Integer idUser, Integer page,Integer size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/poll/search/"+substr+"?idUser="+idUser+
                "&page="+page+"&size="+size+"&sort="+sort+"&order="+order,RestPageImpl.class);
    }

    @Override
    public Poll[] findAllTemplateByTheme(String theme) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/poll/template?theme="+theme,Poll[].class);
    }

    @Override
    public Poll save(Poll poll) {


        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/poll", poll, Poll.class).getBody();

    }

    @Override
    public void deletePoll(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/poll/delete?id=" + id);
    }

    @Override
    public Poll findByLink(String link) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(backendServerUrl+"/api/poll?link="+link,Poll.class);
    }
}

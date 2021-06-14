package com.inspiro.data.service;



import com.inspiro.data.dao.LoveRepository;
import com.inspiro.data.entity.Love;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoveServiceImp {

    private final LoveRepository loveRepository;

    public void save(Love love) {
        loveRepository.save(love);
    }
}

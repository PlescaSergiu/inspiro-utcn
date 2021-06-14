package com.inspiro.data.service;


import com.inspiro.data.dao.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PersonServiceImp {

    private final PersonRepository personRepository;


}

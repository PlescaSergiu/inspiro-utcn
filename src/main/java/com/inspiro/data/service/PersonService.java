package com.inspiro.data.service;



import com.inspiro.data.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService  {

    private PersonRepository repository;

    public PersonService(@Autowired PersonRepository repository) {
        this.repository = repository;
    }


    protected PersonRepository getRepository() {
        return repository;
    }

}

package com.thoughtworks.service;

import com.thoughtworks.model.Greeting;
import com.thoughtworks.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private CounterService counterService;

    public Collection<Greeting> findAll() {
        counterService.increment("method.invoked.greetingService.findAll");
        return greetingRepository.findAll();
    }

    @Cacheable(value = "greetings", key = "#id")
    public Greeting findOne(Long id){
        counterService.increment("method.invoked.greetingService.findOne");
        Greeting greeting = greetingRepository.findOne(id);
        if (greeting == null){
            throw new NoResultException();
        }
        return greeting;
    }

    @CachePut(value = "greetings", key = "#greeting.id")
    @Transactional
    public Greeting create(Greeting greeting){
        counterService.increment("method.invoked.greetingService.create");
        if (greeting.getId() != null){
            throw new EntityExistsException("Cannot create new Greeting with supplied id, The id attribute must be null to create an entity.");
        }

        return greetingRepository.save(greeting);
    }

    @CachePut(value = "greetings", key = "#greeting.id")
    @Transactional
    public Greeting update(Greeting greeting){
        counterService.increment("method.invoked.greetingService.update");
        Greeting greetingToUpdate = findOne(greeting.getId());
        if (greetingToUpdate == null){
            throw new NoResultException("Requested Greeting not found.");
        }

        greetingToUpdate.setText(greeting.getText());
        return greetingRepository.save(greetingToUpdate);
    }

    @CacheEvict(value = "greetings", key = "#id")
    @Transactional
    public void delete(Long id){
        counterService.increment("method.invoked.greetingService.delete");
        greetingRepository.delete(id);
    }

    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache(){
        counterService.increment("method.invoked,greetingService.evictCache");
    }
}

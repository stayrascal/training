package com.thoughtworks.web;

import com.thoughtworks.model.Greeting;
import com.thoughtworks.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/greeting")
public class GreetingController extends BaseController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<String> sayHello(@PathVariable String userName) {
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/greetings", method = RequestMethod.GET)
    public ResponseEntity<Collection<Greeting>> getGreetings() {
        return new ResponseEntity<>(greetingService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.GET)
    public ResponseEntity<Greeting> getGreeting(@PathVariable Long id) {
        return ResponseEntity.ok(greetingService.findOne(id));
    }

    @RequestMapping(value = "/api/greetings", method = RequestMethod.POST)
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        return new ResponseEntity<>(greetingService.create(greeting), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/greetings", method = RequestMethod.PUT)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
        return new ResponseEntity<>(greetingService.update(greeting), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable Long id) {
        greetingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

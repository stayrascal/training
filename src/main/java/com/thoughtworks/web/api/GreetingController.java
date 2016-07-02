package com.thoughtworks.web.api;

import com.thoughtworks.model.Greeting;
import com.thoughtworks.model.errors.Error;
import com.thoughtworks.model.errors.Errors;
import com.thoughtworks.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class GreetingController extends BaseController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(value = "/api/{userName}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/api/greetings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGreeting(@Valid @RequestBody Greeting greeting, BindingResult result) {
        if (result.hasErrors()) {
            return handleErrors(result.getAllErrors());
        }
        return new ResponseEntity<>(greetingService.create(greeting), HttpStatus.CREATED);
    }

    private ResponseEntity handleErrors(List<ObjectError> allErrors) {
        List<Error> errors = new ArrayList<>();
        allErrors.forEach(error -> errors.add(new Error(HttpStatus.BAD_REQUEST.value(), "validation error", error.getDefaultMessage())));
        return new ResponseEntity(new Errors(errors), HttpStatus.BAD_REQUEST);
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

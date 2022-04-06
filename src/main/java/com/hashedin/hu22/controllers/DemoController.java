package com.hashedin.hu22.controllers;

import com.hashedin.hu22.entities.Demo;
import com.hashedin.hu22.repositories.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    DemoRepository demoRepository;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRoot() {
        return "Hello HashedIn By Deloitte";
    }

    @GetMapping("/demos")
    public List<Demo> getAllDemos() {
        return demoRepository.findAll();
    }

    @GetMapping("demo/{id}")
    public Demo getDemo(@PathVariable long id) throws Exception {
        Optional<Demo> employeeOptional = demoRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new Exception("Demo id doesn't exist");
        }
        return employeeOptional.get();
    }

    @PostMapping("demo")
    public Demo postDemo(@RequestBody Demo demo) {
        return demoRepository.save(demo);
    }
}

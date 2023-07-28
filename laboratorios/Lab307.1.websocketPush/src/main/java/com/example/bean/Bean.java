package com.example.bean;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Bean {

	Logger logger = Logger.getLogger("Bean");
	
    @Inject
    @Push(channel = "test-1")
    private PushContext test;
    
    public void submit() {
    	logger.info("SUBMIT");
        test.send("Hello World!");
    }

}
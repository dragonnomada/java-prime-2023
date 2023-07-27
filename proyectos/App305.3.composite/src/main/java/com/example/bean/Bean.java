package com.example.bean;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

public class Bean {

	Logger logger = Logger.getLogger("Bean");
	
	public void foo() {
		logger.info("foo");
	}
	
}

package com.infrrd.processManagement.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExecuteProcess {
	private static final Logger logger = LoggerFactory.getLogger(ExecuteProcess.class);
	
	@Async
	public Future<String> executeProcess() {
		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {	
			logger.warn("Process was interuppted");
			logger.warn("An exception occured",e);
		}		
		logger.info("Process was executed sucessfully");

		return null;

	}
}

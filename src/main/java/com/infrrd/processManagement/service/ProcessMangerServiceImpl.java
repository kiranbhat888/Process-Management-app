package com.infrrd.processManagement.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProcessMangerServiceImpl implements ProcessMangerService {
	private static final Logger logger = LoggerFactory.getLogger(ProcessMangerService.class);
	
	@Async
	public Future<String> executeProcess() {
		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {			
			logger.error("An exception occured",e);
		}		
		
		return null;
		
	}

}

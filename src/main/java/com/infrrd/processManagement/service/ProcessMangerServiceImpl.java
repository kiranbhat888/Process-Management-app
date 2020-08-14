package com.infrrd.processManagement.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.infrrd.processManagement.dto.Response;

@Service
public class ProcessMangerServiceImpl implements ProcessMangerService {

	private static final Logger logger = LoggerFactory.getLogger(ProcessMangerService.class);
	private static Future<String> future = null;

	@Autowired
	ExecuteProcess executeProcess;

	public ResponseEntity<Response> start(){		
		
		Response response = null;
		try {
		if(future == null || future.isDone() || future.isCancelled()) {
			future = executeProcess.executeProcess();
			logger.info("New Process was started");
			response = new Response("Success","New process Started");
		}
		else if(!future.isDone()) {		    
			if(future.cancel(true)) {
				future = executeProcess.executeProcess();
				logger.info("Process was Restarted");
				response = new Response("Success","Running Process was Terminated and Restarted");}
			else
				response = new Response("Failed","Failed to restart the process");
		}}catch (Exception e) {
			logger.error("An Exception occured",e);
		}

		return new ResponseEntity<Response>(response,new HttpHeaders(), HttpStatus.OK);

	}

	public ResponseEntity<Response> end() {
		Response response = null;		
		try {
		if(future != null){
			if(future.isCancelled()) 
				response = new Response("Success","Previous process was interuppted.No Process running currently");			
			else if( future.isDone() )
				response = new Response("Success","previous process was completed ,No process running");
			else if(!future.isDone()) {		    
				if(future.cancel(true)) {				
					logger.info("Terminated the process successfully");
					response = new Response("Success","Running Process was Terminated ");}
				else
					response =  new Response("Failed","Failed to restart the process");
			}
		}
		else
			response = new Response("Success","No Process running currently");
		}catch (Exception e) {
			logger.error("An Exception occured",e);
		}
		return new ResponseEntity<Response>(response,new HttpHeaders(), HttpStatus.OK);
	}




}

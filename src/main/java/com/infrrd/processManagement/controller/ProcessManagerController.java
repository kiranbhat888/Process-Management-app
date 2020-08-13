package com.infrrd.processManagement.controller;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infrrd.processManagement.dto.Response;
import com.infrrd.processManagement.service.ProcessMangerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;



@RestController
@RequestMapping("/process")
public class ProcessManagerController {
	@Autowired
	ProcessMangerService processMangerService;

	private static final Logger logger = LoggerFactory.getLogger(ProcessManagerController.class);
	private static Future<String> future = null;

	@GetMapping("/start")
	@ApiOperation(value = "Start or Resart the process",
	notes="This API strats a new process or terminates the running process and restarts it",response=Response.class)	
	public ResponseEntity<Response> start(){

		logger.info("Starting the Process");
		
		try {
			if(future == null)
				future = processMangerService.executeProcess();			
			else if(!future.isDone()) {		    
				if(future.cancel(true)) {
					future = processMangerService.executeProcess();
					logger.info("Process was Restarted");
					return new ResponseEntity<Response>(new Response("Success","Running Process was Terminated and Restarted"),new HttpHeaders(), HttpStatus.OK);}
				else
					return new ResponseEntity<Response>(new Response("Failed","Failed to restart the process"),new HttpHeaders(), HttpStatus.OK);
			}

		}catch (Exception e) {
			logger.error("An Exception occured",e);
		}
		
		logger.info("Process was started sucessfully");
		return new ResponseEntity<Response>(new Response("Success","New process Started"),new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/end")
	@ApiOperation(value = "Terminate the running process",
	notes="This API terminates the running process if any",response=Response.class)
	public ResponseEntity<Response> end(){
		logger.info("Terminating the Process");
		try {
			if(future == null || future.isDone() )
				return new ResponseEntity<Response>(new Response("Success","No process running"),new HttpHeaders(), HttpStatus.OK);
			else if(!future.isDone()) {		    
				if(future.cancel(true)) {
					logger.info("Terminated the process successfully");
					return new ResponseEntity<Response>(new Response("Success","Running Process was Terminated "),new HttpHeaders(), HttpStatus.OK);}
				else
					return new ResponseEntity<Response>(new Response("Failure","Failed to restart the process"),new HttpHeaders(), HttpStatus.OK);
			}
		}catch (Exception e) {
			logger.error("An Exception occured",e);
		}

		return new ResponseEntity<Response>(new Response("Success","Process Terminated"),new HttpHeaders(), HttpStatus.OK);

	}
}

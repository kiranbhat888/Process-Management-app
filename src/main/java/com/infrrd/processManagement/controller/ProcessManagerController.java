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

/**
 * 
 * @author M1043005
 *
 */
@RestController
@RequestMapping("/process")
public class ProcessManagerController {
	@Autowired
	ProcessMangerService processMangerService;

	private static final Logger logger = LoggerFactory.getLogger(ProcessManagerController.class);
	
	/**
	 * <p>Starts the process</p>
	 * @return Status of the request and message contain current status of the process
	 */
	@GetMapping("/start")
	@ApiOperation(value = "Start or Resart the process",
	notes="This API strats a new process or terminates the running process and restarts it",response=Response.class)	
	public ResponseEntity<Response> start(){

		logger.info("Starting the Process");			
		return processMangerService.start();

	}
	
	
	/**
	 * <p>terminates the process</p>
	 * @return Request status and Process status
	 */
	@GetMapping("/end")
	@ApiOperation(value = "Terminate the running process",
	notes="This API terminates the running process if any",response=Response.class)
	public ResponseEntity<Response> end(){
		
		logger.info("Terminating the Process");
		return processMangerService.end();

	}
}

package com.infrrd.processManagement.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;

import com.infrrd.processManagement.dto.Response;

public interface ProcessMangerService {

	
	public ResponseEntity<Response> start();
	public ResponseEntity<Response> end();
//	public Future<String> executeProcess();
}

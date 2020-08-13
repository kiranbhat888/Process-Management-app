package com.infrrd.processManagement.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;

public interface ProcessMangerService {

	public Future<String> executeProcess();
}

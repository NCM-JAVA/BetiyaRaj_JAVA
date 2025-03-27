package com.bor.rcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bor.rcms.dto.LoginRequest;
import com.bor.rcms.dto.UserRegistrationRequest;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.service.ObjectionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/objection")
//@CrossOrigin(origins = "http://125.20.102.86:4200") 
public class ObjectionController {
	
	
//	  @Autowired
//	    private ObjectionService objectionService;
//
//	    @GetMapping("/getobjection")
//	    public ResponseEntity<?> getAllObjections() {
//	        try {
//	            List<NewObjection> newObjection = objectionService.findAll();
//	            return ResponseEntity.ok(newObjection);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: " + e.getMessage());
//	        }
//	    }
//	
//
//	    
//
//	    @GetMapping("/getobjection")
//	    public ResponseEntity<?> getAllObjections(@RequestParam Long obId) {
//	        try {
//	            List<NewObjection> newObjection = objectionService.findAll();
//	            return ResponseEntity.ok(newObjection);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: " + e.getMessage());
//	        }
//	    }
	    
}

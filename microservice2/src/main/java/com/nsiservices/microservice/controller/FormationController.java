package com.nsiservices.microservice.controller;

import com.nsiservices.microservice.exception.ResourceNotFoundException;
import com.nsiservices.microservice.model.Formation;
import com.nsiservices.microservice.repository.FormationRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials="false")
public class FormationController {
	@Autowired
	private FormationRepository formationRepository;

	@GetMapping("/formations")
	public List<Formation> getAllFormations() {
		return formationRepository.findAll();
	}

	@GetMapping("/formations/{id}")
	public ResponseEntity<Formation> getFormationById(@PathVariable(value = "id") Long formationId)
			throws ResourceNotFoundException {
		Formation formation = formationRepository.findById(formationId)
				.orElseThrow(() -> new ResourceNotFoundException("Formation not found for this id :: " + formationId));
		return ResponseEntity.ok().body(formation);
	}

	@PostMapping("/formations")
	public Formation createFormation(@Valid @RequestBody Formation formation) {
		return formationRepository.save(formation);
	}

	@PutMapping("/formations")
	public ResponseEntity<Formation> updateFormation(@Valid @RequestBody Formation formationDetails)
			throws ResourceNotFoundException {
		Formation formation = formationRepository.findById(formationDetails.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Formation not found for this id :: " + formationDetails.getId()));

		formation.setTheme(formationDetails.getTheme());

		final Formation updatedFormation = formationRepository.save(formation);
		return ResponseEntity.ok(updatedFormation);
	}

	@DeleteMapping("/formations/{id}")
	public Map<String, Boolean> deleteFormation(@PathVariable(value = "id") Long formationId)
			throws ResourceNotFoundException {
		Formation formation = formationRepository.findById(formationId)
				.orElseThrow(() -> new ResourceNotFoundException("Formation not found for this id :: " + formationId));

		formationRepository.delete(formation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
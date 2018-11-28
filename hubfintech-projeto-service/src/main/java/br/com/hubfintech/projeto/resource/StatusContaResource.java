package br.com.hubfintech.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.projeto.dto.StatusContaDTO;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.service.StatusContaService;

@RestController
@RequestMapping("/api/statusConta")
public class StatusContaResource {

	@Autowired
	private StatusContaService service;
		
	@RequestMapping(method = RequestMethod.GET, path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find() {
		try {
			return new ResponseEntity<List<StatusContaDTO>>(service.findAll(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		} 
    }
		
}

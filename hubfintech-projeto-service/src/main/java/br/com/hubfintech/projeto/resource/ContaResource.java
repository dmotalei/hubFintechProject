package br.com.hubfintech.projeto.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.projeto.service.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaResource {

		@Autowired
		private ContaService service;
		
}

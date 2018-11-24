package br.com.hubfintech.projeto.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.projeto.dto.PessoaDTO;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaResource {

		@Autowired
		private PessoaService pessoaService;
		
		@RequestMapping(method=RequestMethod.GET, path="/consultarVendaCliente", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<?> consultarVendaCliente(@RequestParam(name="id") Long id) {
			try {
				PessoaDTO pessoa= pessoaService.findById(id);
				
				if(pessoa != null) {
			    	return new ResponseEntity<PessoaDTO>(pessoa,HttpStatus.OK);
				} else {
					throw new ServiceException("Pessoa não encontrada!");
				}
			} catch (ServiceException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} 
	    }
		
}

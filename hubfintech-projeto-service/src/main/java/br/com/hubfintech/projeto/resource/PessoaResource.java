package br.com.hubfintech.projeto.resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hubfintech.projeto.dto.PessoaDTO;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.service.PessoaService;
import br.com.hubfintech.projeto.util.ConstanteUtils;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService service;
		
	@RequestMapping(method = RequestMethod.GET, path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find(
				@RequestParam(name = "cpfCnpj", required=false, defaultValue = StringUtils.EMPTY) String cpfCnpj
				, @RequestParam(name="page", required=false, defaultValue = ConstanteUtils.PRIMEIRA_PAGINA) Integer page
				, @RequestParam(name="size", required=false, defaultValue = ConstanteUtils.QTD_PAGINACAO_DEFAULT) Integer size
				, @RequestParam(name="direcaoOrdenacao", required=false, defaultValue = ConstanteUtils.ORDENACAO_CRESCENTE) Direction direction
				, @RequestParam(name="colunaOrdenacao", required=false, defaultValue = ConstanteUtils.COLUNA_ORDENACAO_DEFAULT_TABELA_PESSOA) String colunaOrdenacao) {
		try {
			return new ResponseEntity<Page<PessoaDTO>>(service.findByCpfCnpj(cpfCnpj, page, size, direction, colunaOrdenacao), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		} 
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find(
				@RequestBody(required=true) PessoaDTO pessoa) {
		try {
			service.save(pessoa);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
    }
		
}

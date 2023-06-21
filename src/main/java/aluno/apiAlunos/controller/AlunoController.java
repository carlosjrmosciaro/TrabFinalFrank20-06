package aluno.apiAlunos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aluno.apiAlunos.model.AlunoModel;
import aluno.apiAlunos.service.AlunoService;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")

public class AlunoController {

	
	
	@Autowired
	AlunoService alunoService = new AlunoService();

	@GetMapping("/todos")
	public ResponseEntity<List<AlunoModel>> retornaAlunos() {
		List<AlunoModel> listaDeAlunos = new ArrayList<AlunoModel>();
		try {
			listaDeAlunos = alunoService.buscarTodos();
			return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	  @GetMapping(value = "/unico/{id}")
	  public ResponseEntity<Optional<AlunoModel>> retornaAluno(@PathVariable(value = "id") long id)
	  {
		  Optional<AlunoModel> aluno = null;
		  try {
			  aluno = alunoService.buscarPorId(id);
			  return new ResponseEntity<Optional<AlunoModel>>(aluno, HttpStatus.OK);
		  } catch(Exception e) {
			  return new ResponseEntity<Optional<AlunoModel>>(aluno, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	  
	  }
	  
	  @GetMapping(value = "/nome/{nome}")
	  public ResponseEntity<Optional<AlunoModel>>retornaNomeAluno(@PathVariable(value = "nome") String nome) {
		  Optional<AlunoModel> aluno = null;
		  try {
			  aluno = alunoService.buscarPorNome(nome);
			  return new ResponseEntity<Optional<AlunoModel>>(aluno, HttpStatus.OK);
		  }
		  catch (Exception e) {
			  return new ResponseEntity<Optional<AlunoModel>>(aluno, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	  
	  }
	  
//	  @GetMapping(value = "/curso/{curso}")
//	  public ResponseEntity<Optional<AlunoModel>>retornaCursoAluno(@PathVariable(value = "curso") String curso) {
//		  Optional<AlunoModel> aluno = null;
//		  try {
//			  aluno = alunoService.buscarPorCurso(curso);
//			  return new ResponseEntity<Optional<AlunoModel>>(aluno, HttpStatus.OK);
//		  }
//		  catch (Exception e) {
//			  return new ResponseEntity<Optional<AlunoModel>>(aluno,HttpStatus.INTERNAL_SERVER_ERROR);
//		  }
//	  }
	  
	  @GetMapping(value = "/curso/{curso}")
		public ResponseEntity<List<AlunoModel>>retornaCursoAluno(@PathVariable(value = "curso") String curso){
			List<AlunoModel> listaDeAlunos = null;
			try {
				listaDeAlunos = alunoService.buscarPorCurso(curso);
				return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	
	@GetMapping(value = "/campus/{campus}")
		public ResponseEntity<List<AlunoModel>>retornaCampusAluno(@PathVariable(value = "campus") String campus){
			List<AlunoModel> listaDeAlunos = null;
			try {
				listaDeAlunos = alunoService.buscarPorCampus(campus);
				return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<AlunoModel>>(listaDeAlunos, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	 
	@PostMapping
	public ResponseEntity<AlunoModel> salvar(@RequestBody AlunoModel aluno) {
		try {
			alunoService.salvar(aluno);
			return new ResponseEntity<AlunoModel>(aluno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<AlunoModel>(aluno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remover(@PathVariable(value = "id") long id) {
		try {
			Optional<AlunoModel> aluno = alunoService.buscarPorId(id); // procurar por o que é Optional e fazer o
																		// tratamento caso a pessoa tenha digitado um id
																		// inexistente.
			alunoService.removerPorId(id);
			return new ResponseEntity<String>("Aluno " + aluno.get().getNome() + " removido com sucesso",
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Id não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@PutMapping(value = "/alterar/{id}")
	public ResponseEntity<AlunoModel> alterar(@PathVariable(value = "id") long id,
			@Valid @RequestBody AlunoModel aluno) {
		System.out.println("aqui");
		Optional<AlunoModel> AlunoModelQueSeraAlteradoOptional = alunoService.buscarPorId(id);
		if (AlunoModelQueSeraAlteradoOptional.isPresent()) {
			AlunoModel alunoQueSeraAlterado = AlunoModelQueSeraAlteradoOptional.get();

			alunoQueSeraAlterado.setNome(aluno.getNome());
			alunoQueSeraAlterado.setIdade(aluno.getIdade());
			alunoQueSeraAlterado.setSexo(aluno.getSexo());
			alunoQueSeraAlterado.setCurso(aluno.getCurso());

			alunoService.salvar(alunoQueSeraAlterado);
			return new ResponseEntity<AlunoModel>(alunoQueSeraAlterado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
}

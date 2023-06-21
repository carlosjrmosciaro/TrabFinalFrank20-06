package aluno.apiAlunos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aluno.apiAlunos.model.ProfessorModel;
import aluno.apiAlunos.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	ProfessorService professorService = new ProfessorService();
	
	@GetMapping
	public List<ProfessorModel> retornaTodosOsProfessor() {
		return professorService.buscarTodos();
	}
	
	@PostMapping
	public void salvar(@RequestBody ProfessorModel professor) {
		professorService.salvar(professor);
	}
}

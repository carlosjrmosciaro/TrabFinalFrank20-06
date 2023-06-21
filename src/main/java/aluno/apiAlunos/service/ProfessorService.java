package aluno.apiAlunos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aluno.apiAlunos.model.ProfessorModel;
import aluno.apiAlunos.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	public void salvar(ProfessorModel professor) {
		professorRepository.saveAndFlush(professor);
	}
	
	public List<ProfessorModel> buscarTodos(){
		return professorRepository.findAll();
	}
}

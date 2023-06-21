package aluno.apiAlunos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aluno.apiAlunos.model.AlunoModel;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long>{
	
	public Optional<AlunoModel> findByNome(String nome);
	// ------>>>> public List<AlunoModel> findByNomeList(String nome);
//	public Optional<AlunoModel> findByCurso(String curso);
	public List<AlunoModel> findByCurso(String curso);
	public List<AlunoModel> findByNomeContaining(String nome);
	public List<AlunoModel> findByCampus(String campus);
	public List<AlunoModel> findAllById(Long id);


}

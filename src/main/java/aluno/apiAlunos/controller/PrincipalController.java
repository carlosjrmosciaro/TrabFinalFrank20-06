package aluno.apiAlunos.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aluno.apiAlunos.model.AlunoModel;
import aluno.apiAlunos.repository.AlunoRepository;
import aluno.apiAlunos.service.AlunoService;

@Controller
public class PrincipalController {
	
	@Autowired
	AlunoService alunoService = new AlunoService();

	@GetMapping("/")
	public String principal() {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("indexNovo");
		return modelAndView;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastroAluno() {
		ModelAndView modelAndView = new ModelAndView("cadastrarAlunos");
		modelAndView.addObject("aluno", new AlunoModel());
		return modelAndView;
	}

	@GetMapping("/alunos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listarAlunos");
		//modelAndView.addObject("aluno", alunoService.buscarPorId(5));
		modelAndView.addObject("sucesso");
		modelAndView.addObject("erro");
		modelAndView.addObject("alunos", alunoService.buscarTodos());
		//modelAndView.addObject("aluno", new AlunoModel());
		return modelAndView;
	}

	/*@PostMapping("/alunos")
	public String salvar(AlunoModel aluno) {
		alunoService.salvar(aluno);
		return "redirect:/alunos";
	}*/
	
	@PostMapping("/alunos")
	public ModelAndView salvar(AlunoModel aluno) {
		boolean teste;
		ModelAndView modelAndView = new ModelAndView();
		try {
			alunoService.salvar(aluno);
			teste = true;
			System.out.println("entrou sucesso");
			modelAndView.addObject("sucesso", teste);
			modelAndView.addObject("alunos", alunoService.buscarTodos());
			modelAndView.setViewName("listarAlunos");
			return modelAndView;
		} catch(Exception e) {
			System.out.println("entrou erro");
			teste = true;
			modelAndView.addObject("erro", teste);
			modelAndView.addObject("alunos", alunoService.buscarTodos());
			modelAndView.setViewName("listarAlunos");
			return modelAndView;
		}
	}
	
	@GetMapping("/aluno/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("editarAlunos");

		modelAndView.addObject("aluno", alunoService.buscarPorId(id));

		return modelAndView;
	}
	
	@GetMapping("/buscarId")
	public ModelAndView buscaId() {
		ModelAndView modelAndView = new ModelAndView("buscarId");
		return modelAndView;
		
	}
	
	@GetMapping("/buscarNome")
	public ModelAndView buscarNome() {
		ModelAndView modelAndView = new ModelAndView("buscarNome");
		return modelAndView;
		
	}
	
	@GetMapping("/buscarCurso")
	public ModelAndView buscarCurso() {
		ModelAndView modelAndView = new ModelAndView("buscarCurso");
		return modelAndView;
		
	}
	
	@GetMapping("/buscarCampus")
	public ModelAndView buscarCampus() {
		ModelAndView modelAndView = new ModelAndView("buscarCampus");
		return modelAndView;
		
	}

	@PostMapping("/buscarNome")
	public ModelAndView buscarNome(@RequestParam(value="searchTerm", required=false, defaultValue="None") String searchTerm, AlunoModel aluno) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(searchTerm);
		List<AlunoModel> alunos = alunoService.buscarPorNomeLista(searchTerm);
		//System.out.println(alunos.isEmpty());
		modelAndView.addObject("alunos", alunos);
		modelAndView.setViewName("resultados");
		return modelAndView;
		
	}
	
	@PostMapping("/buscarCurso")
	public ModelAndView buscarCurso(@RequestParam(value="searchTerm", required=false, defaultValue="None") String searchTerm, AlunoModel aluno) {
		ModelAndView modelAndView = new ModelAndView();
		//System.out.println(searchTerm);
		List<AlunoModel> alunos = alunoService.buscarPorCurso(searchTerm);
		//System.out.println(alunos.isEmpty());
		modelAndView.addObject("alunos", alunos);
		modelAndView.setViewName("resultados");
		return modelAndView;
		
	}
	
	@PostMapping("/buscarCampus")
	public ModelAndView buscarCampus(@RequestParam(value="searchTerm", required=false, defaultValue="None") String searchTerm, AlunoModel aluno) {
		ModelAndView modelAndView = new ModelAndView();
		//System.out.println(searchTerm);
		List<AlunoModel> alunos = alunoService.buscarPorCampus(searchTerm);
		//System.out.println(alunos.isEmpty());
		modelAndView.addObject("alunos", alunos);
		modelAndView.setViewName("resultados");
		return modelAndView;
		
	}
	
	@PostMapping("/buscarId")
	public ModelAndView buscarId(@RequestParam(value="id", required = false, defaultValue="None") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(id);
		//System.out.println(searchTerm);
	//	Long id = Long.parseLong(searchTerm);
		//List<AlunoModel> alunos = alunoService.buscarPorIdLista(id);
		//System.out.println(alunos.isEmpty());
		modelAndView.addObject("alunos", alunoService.buscarPorIdTeste(id));
		modelAndView.setViewName("resultados");
		return modelAndView;
		
	}
	
	/*@GetMapping("/aluno/deletar/{id}")
	public String deletar(@PathVariable("id") Long id) {
		alunoService.removerPorId(id);
		return "redirect:/alunos";
	}*/
	
	@GetMapping("/aluno/deletar/{id}")
	public ModelAndView deletar(@PathVariable("id") Long id) {
		boolean teste;
		ModelAndView modelAndView = new ModelAndView();
		try {
			alunoService.removerPorId(id);
			teste = true;
			modelAndView.addObject("sucesso", teste);
			modelAndView.addObject("alunos", alunoService.buscarTodos());
			modelAndView.setViewName("listarAlunos");
			return modelAndView;
		} catch(Exception e) {
			teste = true;
			modelAndView.addObject("erro", teste);
			modelAndView.addObject("alunos", alunoService.buscarTodos());
			modelAndView.setViewName("listarAlunos");
			return modelAndView;
		}
	}

	public List<AlunoModel> buscarAlunos() {
		List<AlunoModel> alunos = new ArrayList<AlunoModel>();
		return alunos;
	}
	
}
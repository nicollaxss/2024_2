package web.controlevacinacao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.controlevacinacao.filter.VacinaFilter;
import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.repository.VacinaRepository;




@Controller
@RequestMapping("/vacinas")
public class VacinaController {

    // uso para ver se é erro do banco
    private static final Logger logger = LoggerFactory.getLogger(VacinaController.class);
    
    //@Autowired // ja iria pegar o repositorio criado e inserir aqui
    private VacinaRepository vacinaRepository;

    // construtor
    public VacinaController(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    @GetMapping("/todas")
    public String mostrarTodasVacinas(Model model) {

        List<Vacina> vacinas = vacinaRepository.findAll();
        logger.info("Vacinas buscadas: {}", vacinas);

        // passar as vacinas para o tipo model, indo no metodo e importando o do spring
        model.addAttribute("vacinas", vacinas); // posso colocar qlqr nome antes da list

        // view q eu quero q o usuario receba
        return "vacinas/todas";
    }

    @GetMapping("/nova")
    // spring ja cria para mim e adicionado no model
    public String abrirCadastroVacina(Vacina vacina) {
        return "vacinas/nova";
    }
    
    @PostMapping("/nova")
    public String cadastrarVacina(Vacina vacina) {
        vacinaRepository.save(vacina);

        return "redirect:/vacinas/sucesso";
    }

    @GetMapping("/sucesso")
    public String abrirMensagem() {
        return "vacinas/sucesso";
    }
    
    @GetMapping("/pesquisar")
    public String abrirPaginaPesquisa() {
        return "vacinas/pesquisar";
    }

    @PostMapping("/pesquisar")
    public String pesquisar(VacinaFilter filtro) {
        
        return "vacinas/";
    }
    
}

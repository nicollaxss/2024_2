package web.controlevacinacao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import web.controlevacinacao.filter.VacinaFilter;
import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.pagination.PageWrapper;
import web.controlevacinacao.repository.VacinaRepository;
import web.controlevacinacao.service.VacinaService;

@Controller
@RequestMapping("/vacinas")
public class VacinaController {

    // uso para ver se é erro do banco
    private static final Logger logger = LoggerFactory.getLogger(VacinaController.class);

    // @Autowired // ja iria pegar o repositorio criado e inserir aqui
    private VacinaRepository vacinaRepository;
    private VacinaService vacinaService;

    // construtor
    public VacinaController(VacinaRepository vacinaRepository, VacinaService vacinaService) {
        this.vacinaRepository = vacinaRepository;
        this.vacinaService = vacinaService;
    }

    // @GetMapping("/todas")
    // public String mostrarTodasVacinas(Model model) {

    // List<Vacina> vacinas = vacinaRepository.findAll();
    // logger.info("Vacinas buscadas: {}", vacinas);

    // // passar as vacinas para o tipo model, indo no metodo e importando o do
    // spring
    // model.addAttribute("vacinas", vacinas); // posso colocar qlqr nome antes da
    // list

    // // view q eu quero q o usuario receba
    // return "vacinas/todas";
    // }

    @GetMapping("/nova")
    // spring ja cria para mim e adicionado no model
    public String abrirCadastroVacina(Vacina vacina) {
        return "vacinas/nova";
    }

    @PostMapping("/nova")
    public String cadastrarVacina(Vacina vacina) {
        vacinaService.salvar(vacina);
        return "redirect:/vacinas/sucesso";
    }

    @GetMapping("/sucesso")
    public String abrirMensagem(Model model) {
        model.addAttribute("mensagem", "Vacina cadastrada com sucesso!");
        return "mensagem";
    }

    @GetMapping("/abrirpesquisar")
    public String abrirPaginaPesquisa() {
        return "redirect:/vacinas/pesquisar";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(VacinaFilter filtro, Model model,
            @PageableDefault(size = 7) 
            @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<Vacina> pagina = vacinaRepository.pesquisar(filtro, pageable);
        logger.info("Vacinas pesquisadas: {}", pagina.getContent());
        PageWrapper<Vacina> paginaWrapper = new PageWrapper<>(pagina, request);
        model.addAttribute("pagina", paginaWrapper);
        return "vacinas/vacinas";
    }

    @PostMapping("/abriralterar")
    public String abrirAlterar(Vacina vacina) {
        return "vacinas/alterar";
    }

    @PostMapping("/alterar")
    public String alterar(Vacina vacina) {
        vacinaService.alterar(vacina);
        return "redirect:/vacinas/sucesso2";
    }

    @GetMapping("/sucesso2")
    public String abrirMensagemSucesso2(Model model) {
        model.addAttribute("mensagem", "Vacina alterada com sucesso");
        return "mensagem";
    }

    @PostMapping("/abrirremover")
    public String abrirRemover(Vacina vacina) {
        return "vacinas/confirmarremocao";
    }

    @PostMapping("/remover")
    public String remover(Vacina vacina) {
        vacinaService.remover(vacina);
        return "redirect:/vacinas/sucesso3";
    }

    @GetMapping("/sucesso3")
    public String abrirMensagemSucesso3(Model model) {
        model.addAttribute("mensagem", "Vacina alterada com sucesso");
        return "mensagem";
    }

}

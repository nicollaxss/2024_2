package web.springbootmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.springbootmvc.model.ClasseQualquer;

@Controller
public class SpringBootMVCController {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootMVCController.class);

	@GetMapping("/retornarview")
	public String retornarView() {
		logger.trace(">>>>>>>>>>>>>>>> Entrou em retornarView");
		logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view viewretornada");
		return "viewretornada";
	}

	// Como meu name em form ja esta como "nome", o spring ja faz a conexao
	// diretamente e mostra esse nome
	@PostMapping("/bemvindo")
	public String darBoasVindas(@RequestParam(name = "nome", defaultValue = "João Ninguem") String nome, Model model) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou em darBoasVindas");
		logger.debug(">>>>>>>>>>>>>>>> Recebeu o nome {}", nome);
		model.addAttribute("nome", nome);
		logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view mensagemboasvindas");
		return "mensagemboasvindas";
	}

	// @PostMapping("/objetoqualquer")
	// public String metodoObjetoQualquer(ClasseQualquer objetoQualquer, Model model) {
	// 	logger.trace(">>>>>>>>>>>>>>>> Entrou em metodoObjetoQualquer");
	// 	logger.debug(">>>>>>>>>>>>>>>> Recebeu o objetoQualquer {}", objetoQualquer);
	// 	model.addAttribute("objeto", objetoQualquer);
	// 	logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view mostrarobjetoqualquer");
	// 	return "mostrarobjetoqualquer";
	// }

	// // Sem add um model, o springboot add automatico ja, mas tenho que usar o nome padrao que é o nome da classe do tipo
	// @PostMapping("/objetoqualquer")
	// public String metodoObjetoQualquer(ClasseQualquer objetoQualquer) {
	// 	logger.trace(">>>>>>>>>>>>>>>> Entrou em metodoObjetoQualquer");
	// 	logger.debug(">>>>>>>>>>>>>>>> Recebeu o objetoQualquer {}", objetoQualquer);
	// 	logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view mostrarobjetoqualquer");
	// 	return "mostrarobjetoqualquer";
	// }


	// Método retornando o nome da view via String, recebendo um objeto e um Model como parâmetros do controlador mas não colocando o objetoQualquer no Model
	@PostMapping("/objetoqualquer")
	public String metodoObjetoQualquer(ClasseQualquer objetoQualquer, Model model) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou em metodoObjetoQualquer");
		logger.debug(">>>>>>>>>>>>>>>> Recebeu o objetoQualquer {}", objetoQualquer);
		
		ClasseQualquer outroObjeto = new ClasseQualquer(55, "blabla", true);
		model.addAttribute("outro", outroObjeto);
		
		logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view mostrarobjetoqualquer");
		return "mostrarobjetoqualquer";
	}
	// Podemos acessar o objetoQualquer na view usando o nome do tipo → classeQualquer
	// Podemos acessar o outroObjeto na view usando o nome do atributo → outro
}
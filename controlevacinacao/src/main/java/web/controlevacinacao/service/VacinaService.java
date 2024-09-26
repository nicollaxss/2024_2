package web.controlevacinacao.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.repository.VacinaRepository;

@Service
@Transactional // todos os metodos dessa classe serao Transactional
public class VacinaService {
    
    private VacinaRepository vacinaRepository;

    public VacinaService(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    public void salvar(Vacina vacina) {
        vacinaRepository.save(vacina);
    }

    public void alterar(Vacina vacina) {
        vacinaRepository.save(vacina);
    }

    public void remover(Vacina vacina) {
        vacinaRepository.delete(vacina);
    }
}

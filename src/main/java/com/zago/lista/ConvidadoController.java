package com.zago.lista;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zago.lista.entities.Convidado;
import com.zago.lista.repository.ConvidadoRepository;

import br.com.zago.enviadorEmail.enviadorEmail.EmailService;





@Controller
public class ConvidadoController {

	@Autowired
	ConvidadoRepository repository;
	
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("listaconvidados")
    public String listaConvidados(Model model) {
    	Iterable<Convidado> convidados = repository.findAll();
    	model.addAttribute("convidados", convidados);
    	return "listaconvidados";
    }
    
    @RequestMapping(value= "salvar", method = RequestMethod.POST)
    public String salvar(@RequestParam("nome") String nome
    		, @RequestParam("email") String email
    		, @RequestParam("telefone") String telefone
    		, Model model) {
    	repository.save(new Convidado(nome, email, telefone));
    	new EmailService().enviar(nome, email);
    	Iterable<Convidado> convidados = repository.findAll();
    	model.addAttribute("convidados", convidados);
    	return "listaconvidados";
    }

}
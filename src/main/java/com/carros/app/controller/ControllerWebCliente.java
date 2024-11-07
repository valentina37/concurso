package com.carros.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carros.app.entity.cliente;
import com.carros.app.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "cliente")
	
public class ControllerWebCliente {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@GetMapping("/resultadoTotal")
    public String clienteResultadoTotalTemplate(Model model, HttpSession session) {
     	cliente cliente = (cliente) session.getAttribute("usuarioLogeado");
        
     if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado-total";
    }
	
	@GetMapping("/resultadoUnico")
    public String clienteResultadoUnicoTemplate(Model model, HttpSession session) {
     cliente cliente = (cliente) session.getAttribute("usuarioLogeado");
        
       if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "cliente-resultado";
    }
	
	@GetMapping("/index")
    public String clienteIndexTemplate(Model model, HttpSession session) {
      cliente cliente = (cliente) session.getAttribute("usuarioLogeado");
        
       if (cliente != null) {
            model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());
        }
        
        return "index-cliente";
    }
	
	@PostMapping("/logear")
    public String clienteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
    	cliente cliente = null;
        for (cliente c : clienteRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
            	cliente = c;
                break;
            }
        }
        
         if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", cliente);
            return "redirect:/cliente/index";
        } else {
          model.addAttribute("error", true);
            return "login-cliente";
        }
    }
	
	@GetMapping("/login")
	public String clienteLoginTemplate(Model model) {
		return "login-cliente";
	}
	
	
	@GetMapping("/informe/{id}")
	public String verInformeCliente(@PathVariable("id") String id, Model model) {
	   
		cliente cliente = clienteRepository.findById(id).orElse(null);
	    
	    if (cliente != null) {
	       
	    	model.addAttribute("nombre", cliente.getPrimerNombre());
            model.addAttribute("apellido", cliente.getPrimerApellido());	        
	    }
	    
	    return "informe-cliente"; 
	}

	
	
	
}

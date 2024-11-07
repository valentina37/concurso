package com.carros.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carros.app.entity.administrador;
import com.carros.app.entity.cliente;
import com.carros.app.entity.empleado;
import com.carros.app.exception.NotFoundException;
import com.carros.app.repository.AdministradorRepository;
import com.carros.app.repository.ClienteRepository;
import com.carros.app.repository.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class ControllerWebAdministrador {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository; 
    
    @GetMapping("/index")
    public String administradorIndexTemplate(Model model, HttpSession session) {
        administrador administrador = (administrador) session.getAttribute("usuarioLogeado");
        
        if (administrador != null) {
            model.addAttribute("usuario", administrador.getUsuario());
            model.addAttribute("nombre", administrador.getNombre());
        }
        
        return "index-administrador";
    }
    
    @GetMapping("/login")
    public String administradorLoginTemplate(Model model) {
        return "login-administrador";
    }
    
    @PostMapping("/logear")
    public String administradorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        administrador administrador = null;
        for (administrador c : administradorRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
                administrador = c;
                break;
            }
        }
        
        if (administrador != null && administrador.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", administrador);
            return "redirect:/administrador/index";
        } else {
            model.addAttribute("error", true);
            return "login-administrador";
        }
    }
    
    @GetMapping("/cliente/crear")
    public String administradorCrearClienteTemplate(Model model) {
        model.addAttribute("cliente", new cliente());
        return "cliente-form";
    }
    
    @GetMapping("/cliente/lista")
    public String clienteListTemplate(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente-lista";
    }

    @GetMapping("/cliente/edit/{id}")
    public String administradorEditClienteTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("cliente",
                clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado")));
        return "cliente-form";
    }

    @PostMapping("/cliente/save")
    public String administradorSaveClienteProcess(@ModelAttribute("cliente") cliente cliente) {
        if (cliente.getId().isEmpty()) {
            cliente.setId(null);
        }
        clienteRepository.save(cliente);
        return "redirect:/administrador/cliente/lista";
    }

    @GetMapping("/cliente/delete/{id}")
    public String administradorDeleteClienteProcess(@PathVariable("id") String id) {
        clienteRepository.deleteById(id);
        return "redirect:/administrador/cliente/lista";
    }
    
    @GetMapping("/empleado/crear")
    public String administradorCrearEmpleadoTemplate(Model model) {
        model.addAttribute("empleado", new empleado());
        return "empleado-form";
    }
    
    @GetMapping("/empleado/lista")
    public String empleadoListTemplate(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "empleado-lista";
    }

    @GetMapping("/empleado/edit/{id}")
    public String administradorEditEmpleadoTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("empleado",
                empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado")));
        return "empleado-form";
    }

    @PostMapping("/empleado/save")
    public String administradorSaveEmpleadoProcess(@ModelAttribute("empleado") empleado empleado) {
        if (empleado.getId().isEmpty()) {
            empleado.setId(null);
        }
        empleadoRepository.save(empleado);
        return "redirect:/administrador/empleado/lista";
    }

    @GetMapping("/empleado/delete/{id}")
    public String administradorDeleteEmpleadoProcess(@PathVariable("id") String id) {
        empleadoRepository.deleteById(id);
        return "redirect:/administrador/empleado/lista";
    }
}

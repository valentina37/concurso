package com.carros.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carros.app.entity.empleado;
import com.carros.app.repository.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "empleado")
public class ControllerWebEmpleado {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @GetMapping("/resultadoTotal")
    public String empleadoResultadoTotalTemplate(Model model, HttpSession session) {
    	empleado empleado = (empleado) session.getAttribute("usuarioLogeado");
        
        if (empleado != null) {
            model.addAttribute("nombre", empleado.getNombre());
            model.addAttribute("apellido", empleado.getApellido());
        }
        
        return "empleado-resultado-total";
    }
    
    @GetMapping("/resultadoUnico")
    public String empleadoResultadoUnicoTemplate(Model model, HttpSession session) {
    	empleado empleado = (empleado) session.getAttribute("usuarioLogeado");
        
        if (empleado != null) {
            model.addAttribute("nombre", empleado.getNombre());
            model.addAttribute("apellido", empleado.getApellido());
        }
        
        return "empleado-resultado";
    }
    
    @GetMapping("/index")
    public String empleadoIndexTemplate(Model model, HttpSession session) {
    	empleado empleado = (empleado) session.getAttribute("usuarioLogeado");
        
        if (empleado != null) {
            model.addAttribute("nombre", empleado.getNombre());
            model.addAttribute("apellido", empleado.getApellido());
        }
        
        return "index-empleado";
    }
    
    @PostMapping("/logear")
    public String empleadoLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
    	empleado empleado = null;
        
        for (empleado e : empleadoRepository.findAll()) {
            if (e.getUsuario().equals(usuario)) {
                empleado = e;
                break;
            }
        }
        
        if (empleado != null && empleado.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", empleado);
            return "redirect:/empleado/index";
        } else {
            model.addAttribute("error", true);
            return "login-empleado";
        }
    }
    
    @GetMapping("/login")
    public String empleadoLoginTemplate(Model model) {
        return "login-empleado";
    }
    
    @GetMapping("/informe/{id}")
    public String verInformeEmpleado(@PathVariable("id") String id, Model model) {
    	empleado empleado = empleadoRepository.findById(id).orElse(null);
        
        if (empleado != null) {
            model.addAttribute("nombre", empleado.getNombre());
            model.addAttribute("apellido", empleado.getApellido());
            model.addAttribute("correo", empleado.getCorreo());
            model.addAttribute("telefono", empleado.getTelefono());
            model.addAttribute("rol", empleado.getRol());
        }
        
        return "informe-empleado"; 
    }
}

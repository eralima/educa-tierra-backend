package com.g4.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
//	@Autowired
//	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> auth (@RequestBody Optional<UserLogin> user){
		return usuarioService.
				
				

	}	

}

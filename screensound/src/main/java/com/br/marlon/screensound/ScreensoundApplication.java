package com.br.marlon.screensound;

import com.br.marlon.screensound.model.Artista;
import com.br.marlon.screensound.principal.Principal;
import com.br.marlon.screensound.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repositorio;
	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibirMenu();

	}
}

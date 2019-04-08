package br.com.t3mb.measure.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.t3mb.measure.api.DAO.UserDAO;
import br.com.t3mb.measure.api.utils.SenhaUtils;

@SpringBootApplication
public class T3mbMeasureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(T3mbMeasureApiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			/*String senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha Encoded: "+senhaEncoded);
			
			senhaEncoded = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: "+senhaEncoded);
			
			System.out.println("Senha Válida: "+ SenhaUtils.senhaValida("123456", senhaEncoded));
			
			UserDAO userDAO = new UserDAO();*/
		};
	}

}

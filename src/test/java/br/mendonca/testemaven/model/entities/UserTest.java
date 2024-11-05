package br.mendonca.testemaven.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
	
	private User user;
	
	@BeforeEach
	void init() {
		user = new User();
	}

	@Test
	@DisplayName("Configurando o UUID")
	void testUUID() {
		String UUID = "1";
		user.setUuid(UUID);
		
		assertEquals(UUID, user.getUuid(), "O UUID configurado no objeto User n�o � o mesmo retornado.");
		assertNotEquals("11", user.getUuid(), "O UUID configurado no objeto User deveria apresentar erro.");
	}

	@Test
	@DisplayName("Configurando o NAME")
	void testName() {
		String name = "Fulano de Tal";
		user.setName(name);
		
		assertEquals(name, user.getName(), "O Name configurado no objeto User nao � o mesmo retornado.");
		assertNotEquals("Beltrano", user.getName(), "O Name configurado no objeto deveria apresentar erro.");
	}

	@Test
	@DisplayName("Configurando o EMAIL")
	void testEmail() {
		String email = "fulano@email.com";
		user.setEmail(email);
		
		assertEquals(email, user.getEmail(), "O E-mail configurado no objeto User n�o � o mesmo retornado.");
		assertNotEquals("email@email", user.getEmail(), "O E-mail configurado no objeto deveria apresentar erro.");
	}

	@Test
	@DisplayName("Configurando o PASSWORD")
	void testPassword() {
		String pass = "xyz";
		user.setPassword(pass);
		
		assertEquals(pass, user.getPassword(), "O Password configurado no objeto User n�o � o mesmo retornado.");
		assertNotEquals("zxy", user.getPassword(), "O Password configurado no objeto deveria apresentar erro.");
	}
}

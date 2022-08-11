package br.com.alura.forum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.modelo.Curso;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void retornaCursoPeloNome() {
		
		Curso novoCurso = new Curso();
		novoCurso.setNome(".NET");
		novoCurso.setCategoria("WEB");
		em.persist(novoCurso);
		
		String nome = novoCurso.getNome();
		Curso cursoEncontrado = repository.findByNome(nome);
		
		assertNotNull(cursoEncontrado);
		assertEquals(nome, cursoEncontrado.getNome());
	}
	
	@Test
	public void naoRetornaCurso() {
		String nomeCurso = "JPA";
		Curso curso = repository.findByNome(nomeCurso);
		
		assertNull(curso);
	}

}

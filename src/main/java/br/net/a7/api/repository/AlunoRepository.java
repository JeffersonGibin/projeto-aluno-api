package br.net.a7.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import br.net.a7.api.entity.Aluno;
import java.util.List;

/*
* Interface responsável por cuidar de todos os métodos que fazem a persistência dos dados de Aluno
*/
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
 
  /*
  * Verifica se o nome de um aluno existe
  * @param nome = nome do aluno
  */
  public boolean existsByNome(String nome);
  
  /*
  * Busca um aluno ativo
  *@param id - id do aluno
  */
  @Query(value = "SELECT * FROM aluno WHERE status = 'A' AND id = :aluno_id", nativeQuery = true)
  public Aluno findAlunoAtivo(@Param("aluno_id") Long Id);

  /*
  * Busca todos os alunos ativos
  */
  @Query(value = "SELECT * FROM aluno WHERE status = 'A'", nativeQuery = true)
  public List<Aluno> buscaTodosAlunosAtivos();
  
  /*
  * Busca um aluno 
  *@param id - nome do aluno
  */
  @Query(value = "SELECT * FROM aluno WHERE nome = :nome and status = 'A'", nativeQuery = true)
  public Aluno findAlunoByNome(@Param("nome") String nome);

}

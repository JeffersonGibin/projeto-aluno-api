package br.net.a7.api.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import br.net.a7.api.entity.Prova;
import java.util.List;


/*
* Interface responsável por cuidar de todos os métodos que fazem a persistência dos dados de uma Prova
*/
public interface ProvaRepository extends CrudRepository <Prova, Long>{
  
  /*
  * Busca todas as provas de um aluno
  *@param id - id do aluno
  */
  @Query(value = "SELECT * FROM prova  WHERE aluno_id = :aluno_id", nativeQuery = true)
  public List<Prova> buscaTodasProvasPorAlunoId(@Param("aluno_id") Long Id);
}

package br.net.a7.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import br.net.a7.api.error.ValidationValueException;
import br.net.a7.api.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import br.net.a7.api.error.NotFoundException;
import br.net.a7.api.entity.Aluno;
import br.net.a7.api.dto.AlunoDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
/*
* Classe responsável por cuidar de toda regra de negócio de alunos e salvar as informações no 
* banco de dados.
*/
public class AlunoService {

  @Autowired
  private final AlunoRepository alunoRepository;
/*
  * Os objetos repository são injetados no classe Service
  *@param alunoRepository - objeto que contém métodos para realizar a persitência de dados de alunos
  */
  public AlunoService(AlunoRepository alunoRepository) {
    this.alunoRepository = alunoRepository;
  }

  /*
  * Verifica se o nome do aluno existe
  * @param nome - nome do aluno
  */
  private void verificaSeNomeAlunoExiste(String nome) {
    if (alunoRepository.existsByNome(nome)) {
      throw new ValidationValueException("O aluno(a) [" + nome + "] já está cadastrado");
    }
  }

  /*
  * Salva um aluno no banco de dados
  * @param aluno - Objeto contendo as informações de alunos que serão salvas no banco de dados
  * @return retorna um objeto Aluno contendo as informações salvas
  */
  public AlunoDTO salvar(Aluno aluno) {
    String nome = aluno.getNome();
    verificaSeNomeAlunoExiste(nome);
    aluno.setStatus('A');
    
    Aluno alunoSave = alunoRepository.save(aluno);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dataNascimento = String.valueOf(alunoSave.getDataNascimento());
    
    AlunoDTO alunoDTO = new AlunoDTO();
    
    alunoDTO.setId(alunoSave.getId());
    alunoDTO.setNome(alunoSave.getNome());
    alunoDTO.setDataNascimento(alunoSave.getDataNascimento());
    alunoDTO.setMedia(alunoSave.getMedia());
    alunoDTO.setStatus(alunoSave.getStatus());
    
    return alunoDTO;
  }

  /*
  * Atualiza as informações de um aluno
  * @param id - id do aluno
  * @param aluno - Objeto contendo as informações de alunos que serão atualizadas no banco de dados
  * @return retorna um objeto Aluno contendo as informações atualizadas
  */
  public AlunoDTO atualizar(Long id, Aluno aluno) {

    // A média não pode ser maior que 10
    if (aluno.getMedia() > 10) {
     throw new ValidationValueException("Erro: A Média não pode ser maior que 10 "
        + "[ " + aluno.getMedia() + " ]");
    }
    
    Aluno alunoR = alunoRepository.findAlunoByNome(aluno.getNome());
    /*
    /*
    * Verifica se o id do aluno que veio de alunoR possui id diferente do id enviado na requisição. 
    * Isso é necessário para que seja possível editar as mesmas informações do aluno sem 
    * receber exception. O aluno(a) já está cadastrado
    */ 
    if(alunoR != null && alunoR.getId() != id){
      throw new ValidationValueException("O aluno(a) [" + alunoR.getNome()+ "] já está cadastrado");
    }
   
    Aluno alunoAtivo = alunoRepository.findAlunoAtivo(id);
    AlunoDTO alunoDTO = new AlunoDTO();
    
    alunoAtivo.setNome(aluno.getNome());
    alunoAtivo.setDataNascimento(aluno.getDataNascimento());
    alunoAtivo.setMedia(aluno.getMedia());
    alunoAtivo.setStatus('A');
    
    Aluno alunoUpdated = alunoRepository.save(alunoAtivo);
    
    String dataNascimento = String.valueOf(aluno.getDataNascimento());
    dataNascimento = dataNascimento != null ? dataNascimento : "-";
    
    alunoDTO.setId(alunoUpdated.getId());
    alunoDTO.setNome(alunoUpdated.getNome());
    alunoDTO.setDataNascimento(alunoUpdated.getDataNascimento());
    alunoDTO.setMedia(alunoUpdated.getMedia());
    alunoDTO.setStatus(alunoUpdated.getStatus()); 
    
    return alunoDTO;
  }
  
  /*
  * Busca todos os alunos ativos
  * @return Iterable<Aluno>
  */
  public List <AlunoDTO> buscarTodosAlunos() {
    List<Aluno> todosAlunos = alunoRepository.buscaTodosAlunosAtivos();
    
     List <AlunoDTO> todosAlunosDTO = new ArrayList();
    
      for(int i = 0; i < todosAlunos.size(); i++){
        AlunoDTO alunoDTO = new AlunoDTO();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = todosAlunos.get(i).getDataNascimento();
        
        String dateFormated = "";
        if(dataNascimento != null){
          dateFormated = String.valueOf(formatter.format(dataNascimento));
        }
            
        alunoDTO.setId(todosAlunos.get(i).getId());
        alunoDTO.setNome(todosAlunos.get(i).getNome());
        alunoDTO.setDataNascimento(todosAlunos.get(i).getDataNascimento());
        alunoDTO.setMedia(todosAlunos.get(i).getMedia());
        alunoDTO.setStatus(todosAlunos.get(i).getStatus());
        alunoDTO.setDataNascimentoFormated(dateFormated);
        
        todosAlunosDTO.add(alunoDTO);
      }
      
    return todosAlunosDTO;
  }
  
  /*
  * Inativa um aluno no banco de dados
  * @param id - id do aluno
  * @return retorna um objeto Aluno
  */
  public AlunoDTO deletar(Long id) {

    Aluno alunoAtivo = alunoRepository.findAlunoAtivo(id);
    if (alunoAtivo == null) {
      throw new NotFoundException("Aluno não encontrado");
    }

    alunoAtivo.setId(id);
    alunoAtivo.setStatus('I');
    
    AlunoDTO alunoDTO = new AlunoDTO();
    Aluno alunoDeleted = alunoRepository.save(alunoAtivo);
    
    alunoDTO.setId(alunoDeleted.getId());
    alunoDTO.setNome(alunoDeleted.getNome());
    alunoDTO.setDataNascimento(alunoDeleted.getDataNascimento());
    alunoDTO.setMedia(alunoDeleted.getMedia());
    alunoDTO.setStatus(alunoDeleted.getStatus());
    
    return alunoDTO;

  }
}

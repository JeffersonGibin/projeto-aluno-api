package br.net.a7.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import br.net.a7.api.dto.ResponseRequestDTO;
import org.springframework.http.HttpStatus;
import br.net.a7.api.service.AlunoService;
import br.net.a7.api.entity.Aluno;
import javax.validation.Valid;
import java.util.Date;
/*
* Contém as rotas de manipulação de aluno
 */
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

  @Autowired
  private final AlunoService alunoService;

  /*
  *@param alunoService - objeto de serviço onde estão as regras de negócio
   */
  public AlunoController(AlunoService alunoService) {
    this.alunoService = alunoService;
  }

  /*
  * Rota que lista todos os alunos
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
   */
  @GetMapping("/listaTodosAlunos")
  @CrossOrigin
  public ResponseEntity fetchAllAluno() {

    ResponseRequestDTO responseRequestDTO = new ResponseRequestDTO();
    responseRequestDTO.setTimestamp(new Date().getTime());
    responseRequestDTO.setMessage("Listagem de alunos");
    responseRequestDTO.setStatus(HttpStatus.OK.value());
    responseRequestDTO.setDados(alunoService.buscarTodosAlunos());

    return new ResponseEntity<>(responseRequestDTO, HttpStatus.OK);
  }

  /*
  * Rota para cadastrar um aluno
  * @method POST
  * @param aluno - contém os campos vindos na requisição
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
   */
  @PostMapping("/cadastrarAluno")
  @CrossOrigin
  public ResponseEntity cadastrarAluno(@Valid @RequestBody Aluno aluno) {

    ResponseRequestDTO responseRequestDTO = new ResponseRequestDTO();
    responseRequestDTO.setTimestamp(new Date().getTime());
    responseRequestDTO.setMessage("Cadastrado com sucesso");
    responseRequestDTO.setStatus(HttpStatus.OK.value());
    responseRequestDTO.setDados(alunoService.salvar(aluno));

    return new ResponseEntity<>(responseRequestDTO, HttpStatus.CREATED);
  }

  /*
  * Rota para editar um aluno
  * @method PUT
  * @param id - id do aluno
  * @param aluno - contém os campos enviados na requisição
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
   */
  @PutMapping("/editarAluno/{id}")
  @CrossOrigin
  public ResponseEntity editarAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
    
    ResponseRequestDTO responseRequestDTO = new ResponseRequestDTO();
    responseRequestDTO.setTimestamp(new Date().getTime());
    responseRequestDTO.setMessage("Editado com sucesso!");
    responseRequestDTO.setStatus(HttpStatus.OK.value());
    responseRequestDTO.setDados(alunoService.atualizar(id, aluno));

    return new ResponseEntity(responseRequestDTO, HttpStatus.OK);
  }

  /*
  * Rota para remover um aluno
  * @method DELETE
  * @param id - id do aluno
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
   */
  @DeleteMapping("/deletarAluno/{id}")
  @CrossOrigin
  public ResponseEntity DeletarAluno(@PathVariable("id") Long id) {

    ResponseRequestDTO responseRequestDTO = new ResponseRequestDTO();
    responseRequestDTO.setTimestamp(new Date().getTime());
    responseRequestDTO.setMessage("Removido com sucesso!");
    responseRequestDTO.setStatus(HttpStatus.OK.value());
    responseRequestDTO.setDados(alunoService.deletar(id));

    return new ResponseEntity(responseRequestDTO, HttpStatus.OK);
  }
}

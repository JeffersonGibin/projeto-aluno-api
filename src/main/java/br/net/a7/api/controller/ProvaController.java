package br.net.a7.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import br.net.a7.api.dto.ResponseRequestDTO;
import org.springframework.http.HttpStatus;
import br.net.a7.api.service.ProvaService;
import br.net.a7.api.dto.FileResponseDTO;
import br.net.a7.api.dto.ProvaDTO;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/*
* Contém as rotas de manipulação de prova
*/
@RestController
@RequestMapping( "/api/prova")
public class ProvaController {

  @Autowired
  private ProvaService provaService;

  /*
  * Rota de importação de notas
  * @method POST
  * @param arquivo - arquivo CSV
  * @param id - id do aluno
  * @throws Exception
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
  */
  @PostMapping("/importarNota")
  @CrossOrigin
  public ResponseEntity upload(@RequestParam MultipartFile arquivo, 
      @Valid @RequestParam Long id) throws Exception {
    try {
      // Utilizado como informação de retorno da rota
      FileResponseDTO fileResponseDTO = new FileResponseDTO();
      
      provaService.importarDadosProva(id, arquivo);

      fileResponseDTO.setFileName(arquivo.getName());
      fileResponseDTO.setFileType(arquivo.getContentType());
      fileResponseDTO.setSize(arquivo.getSize());
      fileResponseDTO.setMessage("Arquivo Importado com sucesso!");

      return new ResponseEntity(fileResponseDTO, HttpStatus.OK);

    } catch (Exception e) {
      ResponseRequestDTO responseError = new ResponseRequestDTO();
      responseError.setTimestamp(new Date().getTime());
      responseError.setStatus(HttpStatus.BAD_REQUEST.value());
      responseError.setMessage(e.getMessage());

      return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
    }
  }
  
    /*
  * Rota que lista todos os alunos
  * @return ResponseEntity contém o cabeçalho de resposta da requisição
  */
  @CrossOrigin
  @GetMapping("/buscaProvasAluno/{id}")
  public ResponseEntity buscaProvaAluno(@PathVariable("id") Long id) {
    List <ProvaDTO> provas = provaService.buscaProvaPorAlunoId(id);
    

    ResponseRequestDTO responseRequestDTO = new ResponseRequestDTO();
    responseRequestDTO.setTimestamp(new Date().getTime());
    responseRequestDTO.setMessage("Listagem de provas");
    responseRequestDTO.setStatus(HttpStatus.OK.value());
    responseRequestDTO.setDados(provas);
    
    return new ResponseEntity<>(responseRequestDTO, HttpStatus.OK);
  }
}

package br.net.a7.api.error;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import br.net.a7.api.dto.ResponseRequestDTO;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;

/*
* Classe criada para manupular o cabecalho de algumas exceptions
*/
@ControllerAdvice
public class HandlerResponseError {
  
  /*
  * Realiza a normalização de um cabecalho de uma exceção NotFoundException
  *@param rfnException - obejeto NotFoundException
  */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity handlerNotFoundException(NotFoundException rfnException) {
    ResponseRequestDTO responseError = new ResponseRequestDTO();
    
    responseError.setTimestamp(new Date().getTime());
    responseError.setMessage(rfnException.getMessage());
    responseError.setStatus(HttpStatus.NOT_FOUND.value());

    return new ResponseEntity(responseError, HttpStatus.NOT_FOUND);
  }

  /*
  * Realiza a normalização de um cabecalho de uma exceção FileException
  *@param fException - Objeto FileException
  */
  @ExceptionHandler(FileException.class)
  public ResponseEntity handlerFileException(FileException fException) {
    ResponseRequestDTO responseError = new ResponseRequestDTO();
    
    responseError.setTimestamp(new Date().getTime());
    responseError.setMessage(fException.getMessage());
    responseError.setStatus(HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
  }

  /*
  * Realiza a normalização de um cabecalho de uma exceção ValidationValueException
  *@param rfnException - Objeto ValidationValueException
  */
  @ExceptionHandler(ValidationValueException.class)
  public ResponseEntity handlerValueExists(ValidationValueException rfnException) {
    
    ResponseRequestDTO responseError = new ResponseRequestDTO();
    
    responseError.setTimestamp(new Date().getTime());
    responseError.setMessage(rfnException.getMessage());
    responseError.setStatus(HttpStatus.OK.value());

    return new ResponseEntity(responseError, HttpStatus.OK);
  }
  
  /*
  * Realiza a normalização de um cabecalho de uma exceção comum
  *@param eException - Objeto eException
  */
  @ExceptionHandler(Exception.class)
  public ResponseEntity handlerException(Exception eException) {
    
    ResponseRequestDTO responseError = new ResponseRequestDTO();
    responseError.setTimestamp(new Date().getTime());
    responseError.setStatus(HttpStatus.BAD_REQUEST.value());
    responseError.setMessage(eException.getMessage());
    
    System.out.println(eException.getMessage());

    return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
  }

  /*
  * Realiza a normalização de um cabecalho de uma exceção MethodArgumentNotValidException
  *@param manvException - Objeto MethodArgumentNotValidException
  */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handlerMethodArgumentNotValidException(
      MethodArgumentNotValidException manvException) {

    List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
     
    String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
    
    ResponseRequestDTO responseError = new ResponseRequestDTO();
    responseError.setTimestamp(new Date().getTime());
    responseError.setStatus(HttpStatus.BAD_REQUEST.value());
    responseError.setMessage("Existem campos obrigatórios : "+ fields);
    
    return new ResponseEntity(responseError, HttpStatus.BAD_REQUEST);
  }

}

package br.net.a7.api.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
* Classe de exceção criada para ser usada em possíveis mensagens de erro na manipulação de arquivo
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileException extends RuntimeException {
  
    /*
    * @param message - mensagem da exceção
    */
    public FileException(String message) {
        super(message);
    }
}

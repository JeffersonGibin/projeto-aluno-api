package br.net.a7.api.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
* Classe de exceção criada para ser usada em possíveis mensagens de erro de algo não encontrado
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    
    /*
    * @param message - mensagem da exceção
    */
    public NotFoundException(String message) {
        super(message);
    }
}
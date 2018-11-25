package br.net.a7.api.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
* Classe de exceção criada para ser usada em possíveis mensagens de erro de verificações comuns que
devem retornar status 200;
* Ex: throw new ValidationValueException("Aluno já existe")
*/
@ResponseStatus(HttpStatus.OK)
public class ValidationValueException extends RuntimeException {
    /*
    * @param message - mensagem da exceção
    */
    public ValidationValueException(String message) {
        super(message);
    }
}
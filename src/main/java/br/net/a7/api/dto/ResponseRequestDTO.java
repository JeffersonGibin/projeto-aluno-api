package br.net.a7.api.dto;

/*
* Classe criada para ser usada como um objeto de retorno nas requisições
*/
public class ResponseRequestDTO {

  private int status;
  private String message;
  private long timestamp;
  private Object dados;

  public ResponseRequestDTO() {
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public Object getDados() {
    return dados;
  }

  public void setDados(Object dados) {
    this.dados = dados;
  }

}

package br.net.a7.api.dto;

/*
* Classe criada para ser usada como um objeto de retorno para ResponseEntity na
* manipulação de arquivo
*/
public class FileResponseDTO {

  private String fileName;
  private String fileType;
  private long size;
  private String message;

  public FileResponseDTO() {
  }

  public FileResponseDTO(String fileName, String fileType, long size, String message) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.size = size;
    this.message = message;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

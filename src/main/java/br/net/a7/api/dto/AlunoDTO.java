package br.net.a7.api.dto;

import java.util.Date;

/*
* Classe utilizada para transição dos dados de aluno
*/
public class AlunoDTO{

  private Long id;
  
  private Character status; 

  private String nome;

  private Date dataNascimento;

  private double media;
  
  private String dataNascimentoFormated;

  
  public AlunoDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public double getMedia() {
    return media;
  }

  public void setMedia(double media) {
    this.media = media;
  }

  public String getDataNascimentoFormated() {
    return dataNascimentoFormated;
  }

  public void setDataNascimentoFormated(String dataNascimentoFormated) {
    this.dataNascimentoFormated = dataNascimentoFormated;
  }

}
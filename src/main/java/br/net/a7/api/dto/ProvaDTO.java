package br.net.a7.api.dto;

import java.util.Date;

/*
* Classe utilizada para transição de dados das provas
*/
public class ProvaDTO {
  private Long id;
  private Date data;
  private double nota;
  private Character materia;
  private String dataFormatada;
  private String materiaDescricaoCompleta;
  
  public ProvaDTO(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public double getNota() {
    return nota;
  }

  public void setNota(double nota) {
    this.nota = nota;
  }

  public Character getMateria() {
    return materia;
  }

  public void setMateria(Character materia) {
    this.materia = materia;
  }

  public String getDataFormatada() {
    return dataFormatada;
  }

  public void setDataFormatada(String dataFormatada) {
    this.dataFormatada = dataFormatada;
  }

  public String getMateriaDescricaoCompleta() {
    return materiaDescricaoCompleta;
  }

  public void setMateriaDescricaoCompleta(String materiaDescricaoCompleta) {
    this.materiaDescricaoCompleta = materiaDescricaoCompleta;
  }

  
      
}

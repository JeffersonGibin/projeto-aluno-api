package br.net.a7.api.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/*
* Classe utilizada pelo hibernate para criar a entidade Prova no banco de dados
*/
@Entity
@Table(name = "prova")
public class Prova implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private Character materia;

  @NotNull
  private Date data;

  @Column(columnDefinition = "numeric(15,4) DEFAULT 0.0000")
  @NotNull
  private double nota;

  @OneToOne
  @NotNull
  @JoinTable(name = "id")
  private Aluno aluno;

  public Prova(Long id, Character materia, Date data, double nota) {
    this.id = id;
    this.materia = materia;
    this.data = data;
    this.nota = nota;
  }

  public Prova() {
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Character getMateria() {
    return materia;
  }

  public void setMateria(Character materia) {
    this.materia = materia;
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

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

}

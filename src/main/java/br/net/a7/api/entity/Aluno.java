package br.net.a7.api.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import br.net.a7.api.dto.AlunoDTO;
import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;

/*
* Classe utilizada pelo hibernate para criar a entidade Aluno no banco de dados
*/
@Entity
public class Aluno implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(columnDefinition="char(1) NOT NULL DEFAULT 'A'")
  private Character status; 

  @NotNull
  @NotBlank
  @Column(columnDefinition = "varchar(40)")
  public String nome;

  @Temporal(javax.persistence.TemporalType.DATE)
  private Date dataNascimento;

  @NotNull
  @Column(columnDefinition = "numeric(15,4) DEFAULT 0.0000")
  private double media;
  
  public Aluno(AlunoDTO aluno) {
    this.id = aluno.getId();
  }

  public Aluno() {
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
}

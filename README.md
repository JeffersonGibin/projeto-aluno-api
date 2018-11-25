# Projeto Aluno API

## O que é ?

O Projeto Aluno é um projeto desenvolvido para a participação do
processo seletivo da **ALPHA7 DESENVOLVIMENTO DE SOFTWARE LTDA**. Essa aplicação é o Back-end do projeto.

### Dependências antes de usar o projeto

Antes de começar garanta que você tenha instalado em sua máquina as seguintes tecnologias;

* PostgreSQL 9.4.20
* Java version "1.8.0_181

### Configuração da base de dados

Para configurar a base de dados abra o projeto em uma IDE de sua preferência e procure pelo  diretório **src\main\resources\application.properties**.
Abra o arquivo application.properties e edite as seguintes linhas:

```java
#Exemplo
spring.datasource.url=jdbc:postgresql://localhost:5433/projeto_aluno
spring.datasource.username=postgres
spring.datasource.password=123
```

## Documentação API

#### Aluno


* Lista todos os alunos ativos
* GET - http://localhost:8080/api/aluno/listaTodosAlunos

**Retorno**
```json
{
    "status": 200,
    "message": "Listagem de alunos",
    "timestamp": 1543167148350,
    "dados": [
        {
            "id": 105,
            "status": "A",
            "nome": "Jefferson",
            "dataNascimento": null,
            "media": 0,
            "dataNascimentoFormated": ""
        }
    ]
}
```

* Cadastra um aluno
* POST - http://localhost:8080/api/aluno/cadastrarAluno

| Parâmetros     | tipo       | Descrição|
| -------------  |-------------| -------------|
| nome           | String  | nome do aluno|
| dataNascimento | Date    | data de nascimento do aluno|

**Retorno**
```json
{
    "status": 200,
    "message": "Cadastrado com sucesso",
    "timestamp": 1543167485887,
    "dados": {
        "id": 161,
        "status": "A",
        "nome": "Antonio Carlos",
        "dataNascimento": "1993-12-08T00:00:00.000+0000",
        "media": 0,
        "dataNascimentoFormated": "08/12/1993"
    }
}
```

* Editar um aluno
* PUT - http://localhost:8080/api/aluno/editarAluno/ [ID do aluno]

| Parâmetros     | tipo       | Descrição|
| -------------  |-------------| -------------|
| nome           | String  | nome do aluno|
| dataNascimento | Date    | data de nascimento do aluno|
| media          | Double  | média de um aluno|

**Retorno**
```json
{
    "status": 200,
    "message": "Editado com sucesso!",
    "timestamp": 1543167848021,
    "dados": {
        "id": 161,
        "status": "A",
        "nome": "Maria Cleonice",
        "dataNascimento": "1993-12-08T00:00:00.000+0000",
        "media": 10,
        "dataNascimentoFormated": null
    }
}
```

* Deletar Aluno
* http://localhost:8080/api/aluno/deletarAluno/ [ID do aluno]
**Retorno**
```json
{
    "status": 200,
    "message": "Removido com sucesso!",
    "timestamp": 1543167986709,
    "dados": {
        "id": 161,
        "status": "I",
        "nome": "Maria Cleonice",
        "dataNascimento": "1993-12-07",
        "media": 10,
        "dataNascimentoFormated": null
    }
}
```

#### Prova
* Importa um arquivo CSV contendo as notas
* POST - http://localhost:8080/api/prova/importarNota
* Header : Content-Type : text/csv

| Parâmetros     | tipo       | Descrição                      |
| -------------  |------------| -------------                  |
| arquivo        |  file CSV  | arquivo csv com as notas do ano|
| id             |   Long     | id do aluno                    |

**Retorno**
```json
{
    "fileName": "arquivo",
    "fileType": "text/csv",
    "size": 50,
    "message": "Arquivo Importado com sucesso!"
}
```


* Lista as provas de um aluno
* GET - http://localhost:8080/api/prova/buscaProvasAluno/ [ID do aluno - Long]

**Retorno**

```json
{
    "status": 200,
    "message": "Listagem de provas",
    "timestamp": 1543168577068,
    "dados": [
        {
            "id": 162,
            "data": "2018-10-17T03:00:00.000+0000",
            "nota": 10,
            "materia": "M",
            "dataFormatada": "17/10/2018",
            "materiaDescricaoCompleta": "Matemática"
        },
        {
            "id": 163,
            "data": "2018-10-18T03:00:00.000+0000",
            "nota": 8,
            "materia": "P",
            "dataFormatada": "18/10/2018",
            "materiaDescricaoCompleta": "Português"
        },
        {
            "id": 164,
            "data": "2018-10-20T03:00:00.000+0000",
            "nota": 7,
            "materia": "H",
            "dataFormatada": "20/10/2018",
            "materiaDescricaoCompleta": "História"
        }
    ]
}
```

#### Tecnologias utilizadas

* JAVA 8
* Hibernate
* Maven
* Spring Boot v2.1.0.RELEASE
* JPA
* PostgreSQL 9.4.20

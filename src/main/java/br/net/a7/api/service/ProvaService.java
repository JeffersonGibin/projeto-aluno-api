package br.net.a7.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import br.net.a7.api.error.ValidationValueException;
import br.net.a7.api.repository.AlunoRepository;
import br.net.a7.api.repository.ProvaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import br.net.a7.api.error.FileException;
import br.net.a7.api.dto.ProvaDTO;
import br.net.a7.api.entity.Aluno;
import br.net.a7.api.entity.Prova;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
* Classe responsável por cuidar de toda regra de negócio das provas e salvar as informações no 
* banco de dados.
*/
@Service
public class ProvaService {
  
  private final ProvaRepository provaRepository;
  private final AlunoRepository alunoRepository;

  /*
  *@param provaRepository - objeto que contém métodos para realizar a persitência de dados de provas
  *@param alunoRepository - objeto que contém métodos para realizar a persitência de dados de alunos
  */
  @Autowired
  public ProvaService(ProvaRepository provaRepository, AlunoRepository alunoRepository) {
    this.provaRepository = provaRepository;
    this.alunoRepository = alunoRepository;
  }

  /*
  * Converte uma String no formato dd/MM/yyyy para uma data
  * @param Data
  * @return Date
  */
  private Date parseDate(String data) throws ParseException {
    //Transforma uma string no padrão dd/MM/yyyy em timestamp
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    return formato.parse(data);
  }

  /*
  * Normaliza o arquivo csv e realiza todos tratamentos necessários
  * @param alunoid
  * @param inputStream - contém a leitura dos dados 
  * @return List<Prova> - Retorna um list do tipo prova
  * @throws FileException - Exceção para o caso que uma nota é maior que 10
  * @throws Exception - Exceção caso aconteça alguma coisa na conversão de data
  * @throws ValidationValueException - Exceção caso o aluno não exista
  */
  public List<Prova> normalizeArquivoNota(Long alunoid, InputStream inputStream)
      throws FileException, Exception, ValidationValueException {

    Aluno alunoR = alunoRepository.findAlunoAtivo(alunoid);
    if (alunoR == null) {
      throw new ValidationValueException("Aluno não existe!") {
      };
    }

    //cria um scanner para ler o arquivo
    Scanner scanner = new Scanner(inputStream);
    String linhasDoArquivo;

    //Lista para armazenar as informações das provas
    List<Prova> provas = new ArrayList();

    Aluno aluno = new Aluno();
    aluno.setId(alunoid);

    while (scanner.hasNext()) {
      linhasDoArquivo = scanner.nextLine();

      // Entidade Prova
      Prova prova = new Prova();

      // armazena cada colunado arquivo
      String[] colunasArquivo = linhasDoArquivo.split(";");

      // Colunas do arquivo
      char materia = colunasArquivo[0].charAt(0);
      String data = colunasArquivo[1];
      String nota = colunasArquivo[2];

      prova.setMateria(materia);
      prova.setNota(Double.parseDouble(nota));
      prova.setData(parseDate(data));
      prova.setAluno(aluno);

      // verifica se a coluna matéria não está vazia
      if (prova.getMateria() == null) {
          throw new FileException("O arquivo está faltando informação na coluna matéria!");
      }

      Character[] materiasPermitidas = { 'M', 'P', 'H' };

      // verifica se existem materérias inválidas
      if (!Arrays.asList(materiasPermitidas).contains(prova.getMateria())) {
          throw new FileException("O arquivo possui matérias inválidas!");
      }
      
      // verifica se a nota é menor ou igual a 10 porque este é o valor máximo permitido
      if (prova.getNota() > 10) {
        throw new FileException("Existem notas que estão maior que 10. "
            + "[ " + prova.getNota() + " ]");
      }

      provas.add(prova);
    }

    return provas;
  }

  /*
  * Importa os dados das provas
  * @param alunoid
  * @param arquivo - arquivo csv
  * @throws Exception - exception da conversão de data
  */
  public void importarDadosProva(Long alunoid, MultipartFile arquivo) throws Exception {

    //Normaliza o nome do arquivo
    String fileName = StringUtils.cleanPath(arquivo.getOriginalFilename());
    
    Iterable<Prova> prova = normalizeArquivoNota(alunoid, arquivo.getInputStream());
    
    // Salva as informações das provas
    provaRepository.saveAll(prova);
  }
  
  /*
  * Busca todas as provas de um aluno por aluno id
  * @return provas
  */
  public List<ProvaDTO> buscaProvaPorAlunoId(Long alunoId) {
    List <Prova> todasProvas = provaRepository.buscaTodasProvasPorAlunoId(alunoId);
    
    List <ProvaDTO> provas = new ArrayList();
    
    for(int i = 0; i < todasProvas.size(); i++){
      ProvaDTO provaDTO = new ProvaDTO();
      
      String dateFormated;
      Date dataProva = todasProvas.get(i).getData();
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      dateFormated = String.valueOf(formatter.format(dataProva));
      
      provaDTO.setId(todasProvas.get(i).getId());
      provaDTO.setData(dataProva);
      provaDTO.setMateria(todasProvas.get(i).getMateria());
      provaDTO.setNota(todasProvas.get(i).getNota());
      provaDTO.setDataFormatada(dateFormated);
      
      String materia;
      
      if(todasProvas.get(i).getMateria().equals('P')){
        materia = "Português";
      }else if(todasProvas.get(i).getMateria().equals('M')){
        materia = "Matemática";
      }else if(todasProvas.get(i).getMateria().equals('H')){
        materia = "História";
      }else{
        materia = "-";
      }
      provaDTO.setMateriaDescricaoCompleta(materia);
      provas.add(provaDTO);
    }
    
    return provas;
  }
}

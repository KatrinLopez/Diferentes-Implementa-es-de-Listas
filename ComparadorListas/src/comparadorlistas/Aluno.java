
package comparadorlistas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Katrin López
 */


/**
 * Representa um aluno com nome, matrícula e data de nascimento.
 */
public class Aluno implements Comparable<Aluno> {
    private final String nome;
    private final String matricula;
    private final LocalDate dataNascimento;

    // Formato para exportação no CSV
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor da classe Aluno.
     * @param nome Nome completo do aluno.
     * @param matricula Matrícula do aluno (5 números).
     * @param dataNascimento Data de nascimento do aluno.
     */
    public Aluno(String nome, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Retorna a data de nascimento formatada como String (dd/MM/yyyy).
     * @return Data de nascimento formatada.
     */
    public String getDataNascimentoFormatada() {
        return dataNascimento.format(FORMATTER);
    }

    /**
     * Retorna a linha do aluno no formato CSV.
     * @return Uma string contendo os dados do aluno separados por vírgula.
     */
    public String toCsvString() {
        // Garantir que não haja vírgulas no nome, ou envolvê-lo em aspas se necessário
        return nome + "," + matricula + "," + getDataNascimentoFormatada();
    }

    /**
     * Compara alunos pelo nome para fins de ordenação.
     * @param outro O outro aluno a ser comparado.
     * @return Um valor negativo, zero ou positivo se este aluno for lexicograficamente
     * menor, igual ou maior que o aluno especificado.
     */
    @Override
    public int compareTo(Aluno outro) {
        return this.nome.compareTo(outro.nome);
    }

    // Métodos equals e hashCode simplificados
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(matricula, aluno.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
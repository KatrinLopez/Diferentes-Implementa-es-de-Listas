package comparadorlistas;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author Katrin López
 */

/**
 * Classe utilitária para geração de dados aleatórios de alunos.
 */
public class GeradorDeDados_1 {
    private static final String[] NOMES = {"Ana", "João", "Carlos", "Maria", "Pedro", "Juliana", "Lucas", "Fernanda", "Gabriel", "Isabela", "Rafael", "Camila"};
    private static final String[] SOBRENOMES = {"Silva", "Souza", "Oliveira", "Costa", "Pereira", "Rodrigues", "Almeida", "Ferreira", "Gomes", "Martins", "Ribeiro", "Santos"};
    private static final Random RANDOM = new Random();

    /**
     * Gera um nome completo aleatório.
     * @return Nome e sobrenome concatenados.
     */
    public static String gerarNomeCompleto() {
        String nome = NOMES[RANDOM.nextInt(NOMES.length)];
        String sobrenome = SOBRENOMES[RANDOM.nextInt(SOBRENOMES.length)];
        return nome + " " + sobrenome;
    }

    /**
     * Gera uma matrícula aleatória com 5 dígitos numéricos.
     * @return Uma string de 5 números.
     */
    public static String gerarMatricula() {
        // Gera um número entre 10000 e 99999 (inclusive)
        int matriculaInt = RANDOM.nextInt(90000) + 10000;
        return String.valueOf(matriculaInt);
    }

    /**
     * Gera uma data de nascimento aleatória dentro de um intervalo de anos razoável.
     * (Ex: entre 1990 e 2005)
     * @return Um objeto LocalDate.
     */
    public static LocalDate gerarDataNascimento() {
        int anoMin = 1990;
        int anoMax = 2005;
        
        int ano = RANDOM.nextInt(anoMax - anoMin + 1) + anoMin;
        int mes = RANDOM.nextInt(12) + 1; // 1 a 12
        
        // Define o máximo de dias para o mês/ano
        int diaMax;
        if (mes == 2) {
            // Verifica se é bissexto para 29 dias
            boolean bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
            diaMax = bissexto ? 29 : 28;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            diaMax = 30;
        } else {
            diaMax = 31;
        }

        int dia = RANDOM.nextInt(diaMax) + 1; // 1 até diaMax
        
        return LocalDate.of(ano, mes, dia);
    }
}
        


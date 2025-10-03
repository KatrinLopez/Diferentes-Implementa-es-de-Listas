package comparadorlistas;

import comparadorlistas.Aluno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Katrin López
 */


/**
 * Classe principal para comparar as operações de Cadastro, Ordenação e Exportação
 * entre ArrayList, LinkedList e Vector em uma grande massa de dados.
 */
public class ComparadorListas {

    private static final int NUM_ALUNOS = 350000;
    private static final String CSV_HEADER = "Nome,Matricula,Data de Nascimento";

    // Mapa para armazenar os tempos de execução (Poderia ser um mapa, mas
    // para simplicidade, usaremos um array 3x3)
    // Linhas: Cadastro, Ordenacao, Exportacao
    // Colunas: ArrayList, LinkedList, Vector
    private static long[][] tempos = new long[3][3]; 
    private static final int CADASTRO = 0;
    private static final int ORDENACAO = 1;
    private static final int EXPORTACAO = 2;

    private static final int ARRAYLIST_COL = 0;
    private static final int LINKEDLIST_COL = 1;
    private static final int VECTOR_COL = 2;

    public static void main(String[] args) {
        // 1. Executa todas as operações e mede o tempo
        executarComparacao();

        // 2. Apresenta a tabela de resultados
        apresentarResultados();
    }

    /**
     * Executa a sequência de operações (Cadastro, Ordenação, Exportação) para 
     * cada tipo de lista e armazena os tempos.
     */
    private static void executarComparacao() {
        System.out.println("Iniciando a comparação com " + NUM_ALUNOS + " alunos...");
        System.out.println("-----------------------------------------------------");

        // --- ArrayList ---
        System.out.println("Executando operações para ArrayList...");
        List<Aluno> arrayList = new ArrayList<>();
        tempos[CADASTRO][ARRAYLIST_COL] = cadastrarAlunos(arrayList);
        tempos[ORDENACAO][ARRAYLIST_COL] = ordenarLista(arrayList);
        tempos[EXPORTACAO][ARRAYLIST_COL] = exportarParaCsv(arrayList, "alunos_arraylist.csv");

        // --- LinkedList ---
        System.out.println("Executando operações para LinkedList...");
        List<Aluno> linkedList = new LinkedList<>();
        tempos[CADASTRO][LINKEDLIST_COL] = cadastrarAlunos(linkedList);
        tempos[ORDENACAO][LINKEDLIST_COL] = ordenarLista(linkedList);
        tempos[EXPORTACAO][LINKEDLIST_COL] = exportarParaCsv(linkedList, "alunos_linkedlist.csv");
        
        // --- Vector ---
        System.out.println("Executando operações para Vector...");
        List<Aluno> vector = new Vector<>();
        tempos[CADASTRO][VECTOR_COL] = cadastrarAlunos(vector);
        tempos[ORDENACAO][VECTOR_COL] = ordenarLista(vector);
        tempos[EXPORTACAO][VECTOR_COL] = exportarParaCsv(vector, "alunos_vector.csv");
        
        System.out.println("-----------------------------------------------------");
        System.out.println("Comparação concluída. Arquivos CSV gerados.");
    }

    /**
     * Cadastra um número predefinido de alunos na lista.
     * @param lista A implementação de List a ser preenchida.
     * @return O tempo de execução da inserção em milissegundos (ms).
     */
    private static long cadastrarAlunos(List<Aluno> lista) {
        long inicio = System.currentTimeMillis();
        
        for (int i = 0; i < NUM_ALUNOS; i++) {
            String nome = GeradorDeDados_1.gerarNomeCompleto();
            String matricula = GeradorDeDados_1.gerarMatricula();
            Aluno aluno = new Aluno(nome, matricula, GeradorDeDados_1.gerarDataNascimento());
            lista.add(aluno);
        }
        
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        System.out.println("  -> Cadastro concluído em: " + tempo + " ms.");
        return tempo;
    }

    /**
     * Ordena a lista de alunos utilizando Collections.sort().
     * @param lista A lista de alunos a ser ordenada (implementa Comparable).
     * @return O tempo de execução da ordenação em milissegundos (ms).
     */
    private static long ordenarLista(List<Aluno> lista) {
        long inicio = System.currentTimeMillis();
        
        // Utiliza o método de ordenação padrão, que usa o compareTo da classe Aluno
        Collections.sort(lista);
        
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        System.out.println("  -> Ordenação concluída em: " + tempo + " ms.");
        return tempo;
    }

    /**
     * Exporta os dados da lista de alunos para um arquivo CSV.
     * @param lista A lista de alunos.
     * @param nomeArquivo O nome do arquivo CSV a ser criado.
     * @return O tempo de execução da exportação em milissegundos (ms).
     */
    private static long exportarParaCsv(List<Aluno> lista, String nomeArquivo) {
        long inicio = System.currentTimeMillis();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Escreve o cabeçalho
            writer.write(CSV_HEADER);
            writer.newLine();

            // Escreve os dados dos alunos
            for (Aluno aluno : lista) {
                writer.write(aluno.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + nomeArquivo + " - " + e.getMessage());
            return -1; // Indica falha na medição
        }
        
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        System.out.println("  -> Exportação para " + nomeArquivo + " concluída em: " + tempo + " ms.");
        return tempo;
    }

    /**
     * Imprime a tabela final de tempos de execução.
     */
    private static void apresentarResultados() {
        System.out.println("\n=====================================================");
        System.out.println("         TABELA DE TEMPOS DE EXECUÇÃO (ms)           ");
        System.out.println("=====================================================");

        String formato = "%-15s%-18s%-18s%-18s\n";

        // Cabeçalho da tabela
        System.out.printf(formato, "Operação", "ArrayList (ms)", "LinkedList (ms)", "Vector (ms)");
        System.out.println("------------------------------------------------------------------");

        // Linha de Cadastro
        System.out.printf(formato, 
            "Cadastro", 
            tempos[CADASTRO][ARRAYLIST_COL], 
            tempos[CADASTRO][LINKEDLIST_COL], 
            tempos[CADASTRO][VECTOR_COL]);

        // Linha de Ordenação
        System.out.printf(formato, 
            "Ordenação", 
            tempos[ORDENACAO][ARRAYLIST_COL], 
            tempos[ORDENACAO][LINKEDLIST_COL], 
            tempos[ORDENACAO][VECTOR_COL]);

        // Linha de Exportação
        System.out.printf(formato, 
            "Exportação", 
            tempos[EXPORTACAO][ARRAYLIST_COL], 
            tempos[EXPORTACAO][LINKEDLIST_COL], 
            tempos[EXPORTACAO][VECTOR_COL]);
            
        System.out.println("=====================================================");
    }
}
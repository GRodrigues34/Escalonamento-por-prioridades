import java.util.Objects;

public class Processo {
    private String nome;
    private int tempoExecucao;
    private int tempoRestante;
    private int prioridade;



    public Processo(String nome, int tempoExecucao, int prioridade) {
        this.nome = nome;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void executarProcesso(){
        this.tempoExecucao += -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processo processo = (Processo) o;
        return nome == processo.nome;  // Compara por ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);  // Gera hash baseado no ID
    }
}

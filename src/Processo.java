public class Processo {
    private String nome;
    private int tempoExecucao;
    private int tempoRestante;


    public Processo(String nome, int tempoExecucao) {
        this.nome = nome;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
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

}

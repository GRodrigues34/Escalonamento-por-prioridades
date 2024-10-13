import java.util.*;

public class RoundRobin {
    LinkedList<Processo> listaProcessos = new LinkedList<>();
    private int quantum;
    private int tempoDeAumento;
    private int numeroDePrioridades;

    LinkedList<LinkedList<Processo>> processosPorPrioridade = new LinkedList<>();

    public int getTempoDeAumento() {
        return tempoDeAumento;
    }

    public void setTempoDeAumento(int tempoDeAumento) {
        this.tempoDeAumento = tempoDeAumento;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public LinkedList<Processo> getListaProcessos() {
        return listaProcessos;
    }

    public void setListaProcessos(LinkedList<Processo> listaProcessos) {
        this.listaProcessos = listaProcessos;
    }

    public void adicionaProcesso(Processo processo) {
        listaProcessos.add(processo);
    }

    public void removerProcesso(Processo processo) {
        listaProcessos.remove(processo);
    }

    public int getNumeroDePrioridades() {
        return numeroDePrioridades;
    }

    public void setNumeroDePrioridades(int numeroDePrioridades) {
        this.numeroDePrioridades = numeroDePrioridades;
    }
    //i -> numero de linhas (numero de processos)
    //j -> numero de colunas (numero de prioridades)

    public LinkedList<Processo> organizarProcessosPorPrioridade(LinkedList<Processo> p) {
        int tamanho = p.size();
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (p.get(j).getPrioridade() < p.get(j+1).getPrioridade()) {
                    Processo temp = p.get(j);
                    p.set(j, p.get(j+1));
                    p.set(j+1, temp);
                }
            }
        }
        return p;
    }
    // i = numero de linhas (numero de Processos)
    // j = numero de colunas (numero de prioridades)

    public LinkedList<Processo> separarPorPrioridade(LinkedList<Processo> p, int prioridade) {
        LinkedList<Processo> aux = new LinkedList<>();
        int i = 0;
        while (p.get(i).getPrioridade() > prioridade){
            i++;
        }

        int j = i;
        while (p.get(j).getPrioridade() == prioridade){
            j++;
        }
        for (int k = i; k < j; k++) {
            aux.add(p.get(k));
        }

        return aux;
    }



    public void executar(){


    }


}

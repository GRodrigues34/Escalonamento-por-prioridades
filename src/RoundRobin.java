import java.util.*;

public class RoundRobin {
    LinkedList<Processo> listaProcessos = new LinkedList<>();
    private int quantum;
    private int tempoDeAumento;

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


}

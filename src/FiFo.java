import java.util.*;

public class FiFo {
    private int tempoTotal = 0;
    ArrayList<Processo>fila = new ArrayList<>();

    public ArrayList<Processo> getFila() {
        return fila;
    }

    public void adicionarProcesso(Processo p) {
        fila.add(p);
    }

    public void removerProcesso() {
        fila.remove(0);
    }

    public void executar(){
        int tamanho = fila.size();
        for (int i = 0; i < tamanho; i++) {
           Processo p = fila.get(0);
           this.tempoTotal += p.getTempoExecucao();
           System.out.println("Executando processo " + p.getNome() + " tempo total executado : " + this.tempoTotal);
           fila.remove(0);
        }
        System.out.println("Escalonamento FIFO executado com sucesso!. Tempo total = " + this.tempoTotal + ". Numero de processos: " + tamanho);
    }


}

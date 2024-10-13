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
                if (p.get(j).getPrioridade() < p.get(j + 1).getPrioridade()) {
                    Processo temp = p.get(j);
                    p.set(j, p.get(j + 1));
                    p.set(j + 1, temp);
                }
            }
        }
        return p;
    }

    public LinkedList<Processo> separarPorPrioridade(LinkedList<Processo> p, int prioridade) {
        LinkedList<Processo> aux = new LinkedList<>();
        int i = 0;
     while (i < p.size()) {
         if (p.get(i).getPrioridade() == prioridade) {
             aux.add(p.get(i));
             i++;
         } else {
             i++;
         }
     }
     return aux;
    }

    // linhas (numero de Processos)
    // colunas (numero de prioridades)

    public LinkedList<LinkedList<Processo>> ordenarListasDeProcessos(LinkedList<LinkedList<Processo>> p, LinkedList<Processo> p1) {
        int j = numeroDePrioridades;
        int i = 0;
        LinkedList<Processo> p2 = new LinkedList<>();
        while(i < numeroDePrioridades){
            p2 = separarPorPrioridade(p1, j) ;
            p.add(i, p2);
            i++;
            j--;
        }
        return p;
    }



    // Função genérica para imprimir valores de uma matriz de LinkedLists
    public static void printMatriz(LinkedList<LinkedList<Processo>> matriz) {
        for (int i = 0; i < matriz.size(); i++) {
            LinkedList<Processo> linha = matriz.get(i);
            for (int j = 0; j < linha.size(); j++) {
                System.out.print("Processo " + linha.get(j).getNome() + " Prioridade: " + linha.get(j).getPrioridade() + " ");
            }
            System.out.println(); // Pula para a próxima linha após imprimir uma linha da matriz
        }
    }


    public void execucao (){
        int tempoAtual = 1;
        //prioridade atual
        int i = 0;
        //processo atual
        int j = 0;
        LinkedList<Processo> Lista = organizarProcessosPorPrioridade(listaProcessos);
        processosPorPrioridade = ordenarListasDeProcessos(processosPorPrioridade, Lista);
        while(!processosPorPrioridade.isEmpty() ){
            if(tempoDeAumento % tempoAtual == 0){

            } else if(tempoDeAumento % quantum == 0) {
                j++;
                processosPorPrioridade.get(i).get(j).executarProcesso();

                if(processosPorPrioridade.get(i).get(j).getTempoExecucao() <= 0){
                    processosPorPrioridade.get(i).remove(j);
                    if(processosPorPrioridade.get(i).isEmpty()){
                        i++;
                    }
                }
            } else {
                processosPorPrioridade.get(i).get(j).executarProcesso();

                if(processosPorPrioridade.get(i).get(j).getTempoExecucao() <= 0){
                    processosPorPrioridade.get(i).remove(j);
                    if(processosPorPrioridade.get(i).isEmpty()){
                        i++;
                    }
                }
            }
            tempoAtual++;
        }
    }

    public LinkedList<LinkedList<Processo>> getProcessosPorPrioridade() {
        return processosPorPrioridade;
    }

    public void setProcessosPorPrioridade(LinkedList<LinkedList<Processo>> processosPorPrioridade) {
        this.processosPorPrioridade = processosPorPrioridade;
    }
}

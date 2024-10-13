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

    //ordena um array de processos de diferentes prioridades pelas suas prioridades
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

    //gera um array com todos os processos de uma prioridade especifica
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


    //gera a matriz de processos e prioridades
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



    // Função genérica para imprimir valores de uma matriz
    public void printMatriz(LinkedList<LinkedList<Processo>> matriz) {
        for (int i = 0; i < matriz.size(); i++) {
            LinkedList<Processo> linha = matriz.get(i);
            System.out.println("Prioridade: " + (numeroDePrioridades - i));
            for (int j = 0; j < linha.size(); j++) {
                System.out.print(" Processo: " + linha.get(j).getNome() + ",");
            }
            System.out.println(); // Pula para a próxima linha após imprimir uma linha da matriz
        }
    }


    public void execucao (){
        LinkedList<Processo> auxiliar = new LinkedList<>();
        int contagemAumento = 0;
        int contagemQuantum = 0;
        int tempoAtual = 0;
        int processosRestantes = listaProcessos.size();
        //prioridade atual
        int i = 0;
        //processo atual
        int j = 0;
        LinkedList<Processo> Lista = organizarProcessosPorPrioridade(listaProcessos);
        processosPorPrioridade = ordenarListasDeProcessos(processosPorPrioridade, Lista);
        while(processosRestantes > 0){
            if(contagemAumento == tempoDeAumento) {
                System.out.println("tempo de aumento atingido, aumentando prioridade de processos");
                LinkedList<LinkedList<Processo>> auxiliar1 = processosPorPrioridade;
                processosPorPrioridade = aumentoDePrioridade(auxiliar1);
                contagemAumento = 0;
            }
            if(contagemQuantum == quantum) {
                System.out.println("Quantum atingido");
                auxiliar.add(0, processosPorPrioridade.get(i).get(j));
                processosPorPrioridade.get(i).remove(j);
                System.out.println("Processo: " + auxiliar.get(0).getNome() + " removido do começo");
                processosPorPrioridade.get(i).add(auxiliar.get(0));
                auxiliar.remove(0);
                System.out.println("Processo: " + processosPorPrioridade.get(i).getLast().getNome() + " movido ao fim da fila");
                System.out.println("Processo no inicio: " + processosPorPrioridade.get(i).getFirst().getNome());

                contagemQuantum = 0;


                    processosPorPrioridade.get(i).get(j).executarProcesso();
                    System.out.println("Processo: " + processosPorPrioridade.get(i).get(j).getNome() + " executado em 1 unidade de tempo");

                    if(processosPorPrioridade.get(i).get(0).getTempoExecucao() <= 0){
                        System.out.println("Processo: " + processosPorPrioridade.get(i).getFirst().getNome() + " Foi finalizado");
                        processosPorPrioridade.get(i).remove(j);
                        processosRestantes --;
                        if(processosPorPrioridade.get(i).isEmpty()){
                            i++;
                            System.out.println("Lista de Prioridade vazia, mudando para prioridade " + (numeroDePrioridades - i - 1));

                        }
                    }
            } else {
                processosPorPrioridade.get(i).get(j).executarProcesso();
                System.out.println("Processo: " + processosPorPrioridade.get(i).get(j).getNome() + " executado em 1 unidade de tempo");

                if(processosPorPrioridade.get(i).get(0).getTempoExecucao() <= 0){
                    System.out.println("Processo: " + processosPorPrioridade.get(i).getFirst().getNome() + " Foi finalizado");
                    processosPorPrioridade.get(i).remove(j);
                    processosRestantes --;
                    if(processosPorPrioridade.get(i).isEmpty()){
                        System.out.println("Lista de Prioridade vazia, mudando para prioridade " + (numeroDePrioridades - i - 1));;
                        i++;

                    }
                }
            }
            tempoAtual++;
            contagemAumento++;
            contagemQuantum++;
            System.out.println("Tempo atual: " + tempoAtual);



        }
        System.out.println("Processos executados com sucesso!");
    }

    public LinkedList<LinkedList<Processo>> getProcessosPorPrioridade() {
        return processosPorPrioridade;
    }

    public void setProcessosPorPrioridade(LinkedList<LinkedList<Processo>> processosPorPrioridade) {
        this.processosPorPrioridade = processosPorPrioridade;
    }

    public LinkedList<LinkedList<Processo>> aumentoDePrioridade(LinkedList<LinkedList<Processo>> matriz) {
        if (matriz.isEmpty() || matriz.getFirst().isEmpty()) {
            return matriz;  // Se a matriz estiver vazia, não há nada para mover
        }

        // Percorre a matriz a partir da segunda linha
        for (int i = 1; i < matriz.size(); i++) {
            LinkedList<Processo> linhaAtual = matriz.get(i);
            LinkedList<Processo> linhaAcima = matriz.get(i - 1);

            // Utilizamos um iterator para evitar ConcurrentModificationException ao remover elementos
            Iterator<Processo> iterator = linhaAtual.iterator();
            while (iterator.hasNext()) {
                Processo processo = iterator.next();
                if (!linhaAcima.contains(processo)) {
                    linhaAcima.add(processo);  // Adiciona à linha acima
                    iterator.remove();  // Remove da linha atual
                }
            }
        }

        // Limpa a última linha após a movimentação
        matriz.getLast().clear();

        printMatriz(matriz);
        return matriz;
    }


}



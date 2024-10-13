import java.util.*;


public class RoundRobin {

    //Array com todos os processos
    LinkedList<Processo> listaProcessos = new LinkedList<>();
    //quantum do escalonamento
    private int quantum;
    //unidades de tempo para o aumento de prioridade
    private int tempoDeAumento;
    //quantos arrays de prioridades teremos
    private int numeroDePrioridades;

    //matriz (array de arrays) contendo todos os processos (divididos por prioridade)
    LinkedList<LinkedList<Processo>> processosPorPrioridade = new LinkedList<>();

    // getters e setters
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
    //i -> numero de linhas (numero de colunas)
    //j -> numero de colunas (numero de processos)

    //ordena um array de processos pelas suas prioridades (quanto maior mais a esquerda) utilizando o metodo bubble sort
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

    //separa todos os processos de um array (com eles em sequencia aleatoria) com uma prioridade especifica e os separa em um array
    public LinkedList<Processo> separarPorPrioridade(LinkedList<Processo> p, int prioridade) {
        LinkedList<Processo> aux = new LinkedList<>();
        int i = 0;
     while (i < p.size()) {
         //percorre o array parametro e adiciona a prioridade procurada em um array auxiliar
         if (p.get(i).getPrioridade() == prioridade) {
             aux.add(p.get(i));
             i++;
         } else {
             i++;
         }
     }
     return aux;
    }
    //geração a matriz de processos e prioridades
    // int j linhas (numero de Processos)
    // int i colunas (numero de prioridades)
    public LinkedList<LinkedList<Processo>> ordenarListasDeProcessos(LinkedList<LinkedList<Processo>> p, LinkedList<Processo> p1) {
        int j = numeroDePrioridades;
        int i = 0;
        LinkedList<Processo> p2 = new LinkedList<>();
        while(i < numeroDePrioridades){
            //cria varios arrays, cada um de uma prioridade especifica e os adiciona em uma matriz
            p2 = separarPorPrioridade(p1, j) ;
            p.add(i, p2);
            i++;
            j--;
        }
        return p;
    }



    // Função para printar os processos de uma matriz, os separando por prioridades
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

    //e la vamos nós...
    public void execucao (){
        //declaração de variaveis genericas que serão utilizadas ao longo da execução
        LinkedList<Processo> auxiliar = new LinkedList<>();
        int contagemAumento = 0; //contagem de u.t para o aumento de prioridade
        int contagemQuantum = 0; //contagem de u.t para a troca de processos
        int tempoAtual = 0; //controle
        int processosRestantes = listaProcessos.size(); //controle
        //prioridade atual
        int i = 0;
        //processo atual
        int j = 0;
        //ordenação de processos e criação da matriz de prioridades
        LinkedList<Processo> Lista = organizarProcessosPorPrioridade(listaProcessos);
        processosPorPrioridade = ordenarListasDeProcessos(processosPorPrioridade, Lista);
        while(processosRestantes > 0){
            if(contagemAumento == tempoDeAumento) {
                //aumento de prioridades caso u.t tenham passado
                System.out.println("tempo de aumento atingido, aumentando prioridade de processos");
                LinkedList<LinkedList<Processo>> auxiliar1 = processosPorPrioridade;
                processosPorPrioridade = aumentoDePrioridade(auxiliar1);
                contagemAumento = 0;
            }
            if(contagemQuantum == quantum) {
                //troca de processos caso u.t quantum seja o suficiente
                System.out.println("Quantum atingido");
                //com a utilização de um auxiliar, o processo em execução é jogado para o fim da fila, e o após ele começa a ser executado
                auxiliar.add(0, processosPorPrioridade.get(i).get(j));
                processosPorPrioridade.get(i).remove(j);
                System.out.println("Processo: " + auxiliar.get(0).getNome() + " removido do começo");
                processosPorPrioridade.get(i).add(auxiliar.get(0));
                auxiliar.remove(0);
                System.out.println("Processo: " + processosPorPrioridade.get(i).getLast().getNome() + " movido ao fim da fila");
                System.out.println("Processo no inicio: " + processosPorPrioridade.get(i).getFirst().getNome());

                contagemQuantum = 0;//contagem quantum reiniciada

                //controle de execução de processos
                    processosPorPrioridade.get(i).get(j).executarProcesso();
                    System.out.println("Processo: " + processosPorPrioridade.get(i).get(j).getNome() + " executado em 1 unidade de tempo");
                    //caso processo tenha sido executado completamente, é removido da fila
                    if(processosPorPrioridade.get(i).get(0).getTempoExecucao() <= 0){
                        System.out.println("Processo: " + processosPorPrioridade.get(i).getFirst().getNome() + " Foi finalizado");
                        processosPorPrioridade.get(i).remove(j);
                        processosRestantes --;
                        if(processosPorPrioridade.get(i).isEmpty()){// quando todos os processos de uma prioridade forem executados, a prioridade atual é trocada
                            i++;
                            System.out.println("Lista de Prioridade vazia, mudando para prioridade " + (numeroDePrioridades - i - 1));

                        }
                    }
            } else {
                //mesmo codigo acima, repetido para a garantia de execução no if/else
                processosPorPrioridade.get(i).get(j).executarProcesso();
                System.out.println("Processo: " + processosPorPrioridade.get(i).get(j).getNome() + " executado em 1 unidade de tempo");

                if(processosPorPrioridade.get(i).get(0).getTempoExecucao() <= 0){
                    System.out.println("Processo: " + processosPorPrioridade.get(i).getFirst().getNome() + " Foi finalizado");
                    processosPorPrioridade.get(i).remove(j);
                    processosRestantes --;
                    if(processosPorPrioridade.get(i).isEmpty()){
                        System.out.println("Lista de Prioridade vazia, mudando para prioridade " + (numeroDePrioridades - i));;
                        i++;

                    }
                }
            }
            //controle
            tempoAtual++;
            contagemAumento++;
            contagemQuantum++;
            System.out.println("Tempo atual: " + tempoAtual);



        }
        // finalização
        System.out.println("Processos executados com sucesso!");
    }
    //mais getters e setters
    public LinkedList<LinkedList<Processo>> getProcessosPorPrioridade() {
        return processosPorPrioridade;
    }

    public void setProcessosPorPrioridade(LinkedList<LinkedList<Processo>> processosPorPrioridade) {
        this.processosPorPrioridade = processosPorPrioridade;
    }
    //aumento das prioridades por movimentações de processos dentro da matriz.
    public LinkedList<LinkedList<Processo>> aumentoDePrioridade(LinkedList<LinkedList<Processo>> matriz) {
        if (matriz.isEmpty() || matriz.getFirst().isEmpty()) {
            return matriz;  // Se a matriz estiver vazia retorna-se nada.
        }

        // Percorre a matriz a partir da segunda linha
        for (int i = 1; i < matriz.size(); i++) {
            LinkedList<Processo> linhaAtual = matriz.get(i);
            LinkedList<Processo> linhaAcima = matriz.get(i - 1);

            // Utilizamos um iterator para evitar processos duplicados nas movimentações e apagar copias indesejadas
            Iterator<Processo> iterator = linhaAtual.iterator();
            while (iterator.hasNext()) {
                Processo processo = iterator.next();
                if (!linhaAcima.contains(processo)) {
                    linhaAcima.add(processo);  // Adiciona à linha acima
                    iterator.remove();  // Remove da linha atual
                }
            }
        }

        // Limpa a última linha após a movimentação (esta é a unica linha no qual teremos certeza que não possuira nenhum processo)
        matriz.getLast().clear();

        printMatriz(matriz);
        return matriz;
    }


}



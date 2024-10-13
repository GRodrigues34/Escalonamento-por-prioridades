import java.util.*;

public class Main {
    public static void main(String[] args) {
    RoundRobin r = new RoundRobin();
    Processo P1 = new Processo("P1", 7, 4);
    Processo P2 = new Processo("P2", 3, 3);
    Processo P3 = new Processo("P3", 2, 2);
    Processo P4 = new Processo("P4", 8, 1);
    Processo P5 = new Processo("P5", 1, 2);
    Processo P6 = new Processo("P6", 11, 2);

    r.adicionaProcesso(P4);
    r.adicionaProcesso(P3);
    r.adicionaProcesso(P2);
    r.adicionaProcesso(P1);
    r.adicionaProcesso(P5);
    r.adicionaProcesso(P6);


    r.setNumeroDePrioridades(4);
    r.setQuantum(3);
    r.setTempoDeAumento(4);

    LinkedList<Processo> processos = new LinkedList<>();
    processos = r.organizarProcessosPorPrioridade(r.getListaProcessos());
    System.out.println(processos);

    LinkedList<LinkedList<Processo>> matriz = new LinkedList<>();
    matriz = r.ordenarListasDeProcessos(matriz, processos);
    System.out.println(matriz);


    //r.execucao();
        r.aumentoDePrioridade(matriz);



    }

}
import java.util.*;

public class Main {
    public static void main(String[] args) {
    RoundRobin r = new RoundRobin();
    Processo P1 = new Processo("P1", 10, 4);
    Processo P2 = new Processo("P2", 10, 3);
    Processo P3 = new Processo("P3", 10, 2);
    Processo P4 = new Processo("P4", 10, 1);
    Processo P5 = new Processo("P5", 10, 2);
    Processo P6 = new Processo("P6", 10, 2);

    r.adicionaProcesso(P4);
    r.adicionaProcesso(P3);
    r.adicionaProcesso(P2);
    r.adicionaProcesso(P1);
    r.adicionaProcesso(P5);
    r.adicionaProcesso(P6);

    LinkedList<Processo> teste = new LinkedList<>();

    for(int i = 0; i < r.getListaProcessos().size(); i++){
        System.out.println("Processo: " + r.getListaProcessos().get(i).getNome());
    }

    teste = r.organizarProcessosPorPrioridade(r.getListaProcessos());

        System.out.println("Ordenação feita");

        for(int i = 0; i < r.getListaProcessos().size(); i++){
            System.out.println("Processo: " + r.getListaProcessos().get(i).getNome());
        }

     teste = r.separarPorPrioridade(r.getListaProcessos(), 2);

        System.out.println("Separação Feita");
        for(int i = 0; i < teste.size(); i++){
            System.out.println("Processo: " + teste.get(i).getNome() + ". Prioridade: " + teste.get(i).getPrioridade());
        }

    }
}
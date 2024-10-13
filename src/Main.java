import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    RoundRobin r = new RoundRobin();
    boolean sair = false;
    int maiorPrioridade = 0;
    int entrada;
    while(!sair){
        System.out.println("Escalonamento por Prioridades \n");
        System.out.println("1- Adicionar Processo\n2- Remover Processo\n3- Definir quantum\n4- Definir tempo de aumento de prioridade \n5- Iniciar\n6- sair  ");
        entrada = sc.nextInt();
        if(entrada == 6){
            sair = true;
        } else {
            if(entrada == 1){
                System.out.println("Digite o nome do processo: ");
                String nome = sc.next();
                System.out.println("Digite o tempo do processo: ");
                int tempo = sc.nextInt();
                System.out.println("Digite a prioridade do processo: ");
                int prioridade = sc.nextInt();
                if(prioridade > maiorPrioridade){
                    maiorPrioridade = prioridade;
                }
                r.adicionaProcesso(new Processo(nome, tempo, prioridade));
                System.out.println("Processo adicionado com sucesso!\n");
            } else if (entrada == 2){
                System.out.println("Processos adicionados: \n");
                for(int i = 0; i < r.listaProcessos.size(); i++ ){
                    System.out.println( (i + 1)+ "- Processo: " + r.listaProcessos.get(i).getNome() + "\n");
                }
                System.out.println("Digite o numero do processo a ser removido: ");
                 int numero = sc.nextInt();
                 r.listaProcessos.remove(numero);
                System.out.println("Processo removido com sucesso!\n");
            } else if (entrada == 3 ) {
                System.out.println("Digite o valor do quantum: ");
                int quantum = sc.nextInt();
                r.setQuantum(quantum);
                System.out.println("Quantum definido com sucesso!\n");
            } else if(entrada == 4){
                System.out.println("Digite o valor do tempo de aumento de propridades (em unidades de tempo): \n");
                int tempo = sc.nextInt();
                r.setTempoDeAumento(tempo);
            } else {
                r.setNumeroDePrioridades(maiorPrioridade);
                r.execucao();
            }
        }
    }


        //r.execucao();
    }

}
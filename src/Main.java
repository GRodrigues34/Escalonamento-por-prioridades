import java.util.*;

public class Main {
    public static void main(String[] args) {
        FiFo f = new FiFo();
        Processo p1 = new Processo( "P1", 3, 0);
        Processo p2 = new Processo( "P2", 2, 0);
        Processo p3 = new Processo( "P3", 4, 0);
        Processo p4 = new Processo( "P4", 1, 0);

        f.adicionarProcesso(p1);
        f.adicionarProcesso(p2);
        f.adicionarProcesso(p3);
        f.adicionarProcesso(p4);

        f.executar();

    }
}
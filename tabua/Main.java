import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> tabela_inicial = new ArrayList<Integer>();

        Scanner leitor = new Scanner(System.in);
        String numeros_lidos = leitor.nextLine();
        String[] ok = numeros_lidos.split(" ");

        for (var i : ok) {
            tabela_inicial.add(Integer.parseInt(i));
        }

        String solucao = leitor.nextLine();

        leitor.close();
        Table no = new Table(tabela_inicial);
        boolean verdade = no.aplicarSolucao(solucao);

        if (verdade == true) {
            System.out.println("Posicao final: true");
        } else {
            System.out.println("Posicao final: false");
        }
    }
}
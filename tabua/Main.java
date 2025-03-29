import java.util.Vector;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> tabela_inicial = new Vector<Integer>(1, 2);

        Scanner leitor = new Scanner(System.in);
        String numeros_lidos = leitor.nextLine();
        String[] ok = numeros_lidos.split(" ");

        for (var i : ok) {
            tabela_inicial.addElement(Integer.parseInt(i));
        }

        leitor.close();
        Table no = new Table(tabela_inicial);
    }
}
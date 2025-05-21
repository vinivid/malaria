import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dog = new Scanner(System.in);
        Store loja = new Store();

        System.out.print("\n");
        while (dog.hasNext()) {
            String lin = dog.nextLine();
            char op = lin.charAt(0);

            if (op == 'I') {
                String value = lin.substring(2);
                loja.insertProduct(value);
            } else if (op == 'A') {
                String value = lin.substring(2);
                loja.addProduct(value);
            } else if (op == 'V') {
                String value = lin.substring(2);
                loja.sellProduct(value);
            } else if (op == 'P') {
                String value = lin.substring(2);
                loja.searchProd(value);
            } else if (op == 'S') {
                loja.summarize();
            }
        }

        dog.close();
    }
}
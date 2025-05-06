import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dog = new Scanner(System.in);
        ArvBin bin = new ArvBin(1000);
        ArvAvl avl = new ArvAvl(1000); 

        while (dog.hasNext()) {
            String line = dog.nextLine();
            String[] dem = line.split(" ");

            if (dem[0].equals("i")) {
                bin.insert(dem[1]);
                avl.insert(dem[1]);
            } else if (dem[0].equals("d")) {
                bin.remove(dem[1]);
                avl.remove(dem[1]);
            } else if (dem[0].equals("end")) {
                break;
            }
        }

        System.out.println(bin);
        System.out.print(avl);

        dog.close();
    }
}

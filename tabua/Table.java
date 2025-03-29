import java.util.Vector;

public class Table {
    private Vector<Integer> tab;
    private int size;
    private int zero_index;

    public Table(Vector<Integer> jogo) {
        this.tab = jogo;
        this.size = (int)(Math.sqrt(jogo.size()));

        for (int i = 0; i < size; ++i) {
            if (tab.get(i) == 0) {
                this.zero_index = i;
                break;
            }
        }
    }

    public void setTable(Vector<Integer> newTable) {
        this.tab = newTable;
    }

    private void printCurrentState () {
        System.out.print("+");

        for (int i = 0; i < this.size; ++i) {
            System.out.print("------+");
        }

        System.out.print("\n");

        for (int i = 0; i < this.size; ++i) {
            System.out.print("|");

            for (int j = 0; j < this.size; ++j) {
                System.out.printf("%d |", this.tab.get(i * this.size + j));
            }

            System.out.print("\n");

            System.out.print("+");

            for (int zeta = 0; zeta < this.size; ++zeta) {
                System.out.print("------+");
            }

            System.out.print("\n");
        }
    }

    private void up() {
        
    }

    private void down () {

    }

    private void left () {

    }

    private void right() {

    }

    public boolean aplicarSolucao (String sol) {
        for (int i = 0; i < sol.length(); ++i) {
            var dengo = sol.charAt(i);
            
            switch (dengo) {
                case 'u':
                    this.up();    

                    break;
                case 'd':
                    this.down();

                    break;
                case 'l':
                    this.left();

                    break;
                case 'r':
                    this.right();

                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
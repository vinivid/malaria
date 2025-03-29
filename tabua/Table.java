import java.util.ArrayList;

public class Table {
    private ArrayList<Integer> tab;
    private int size;
    private int zero_index;

    public Table(ArrayList<Integer> jogo) {
        this.tab = jogo;
        this.size = (int)(Math.sqrt(jogo.size()));

        for (int i = 0; i < jogo.size(); ++i) {
            if (tab.get(i) == 0) {
                this.zero_index = i;
                break;
            }
        }
    }

    public void setTable(ArrayList<Integer> newTable) {
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
                Integer print_val = this.tab.get(i * this.size + j);

                if (print_val <= 9) {
                    System.out.printf("   %d  |", print_val);
                } else {
                    System.out.printf("  %d  |", print_val);
                }
            }

            System.out.print("\n");

            System.out.print("+");

            for (int zeta = 0; zeta < this.size; ++zeta) {
                System.out.print("------+");
            }

            System.out.print("\n");
        } 
        System.out.print("\n");
    }

    private void moveZeroToLoc (int new_pos) {
        tab.set(zero_index, tab.get(new_pos));
        tab.set(new_pos, 0); 
        zero_index = new_pos;
    }

    //Por algum motivo todos os movimentos são invertidos
    private void up() {
        if ( (this.zero_index / this.size) == this.size - 1)
            return;

        int new_pos = this.zero_index + this.size;
        moveZeroToLoc(new_pos);
    }

    private void down () {
        if ( (this.zero_index / this.size) == 0)
            return;

        int new_pos = this.zero_index - this.size;
        moveZeroToLoc(new_pos);
    }

    private void left () {
        if ( (this.zero_index % this.size) == this.size - 1)
            return;

        int new_pos = this.zero_index + 1;
        moveZeroToLoc(new_pos);
    }

    private void right() {
        if ( (this.zero_index % this.size) == 0)
            return;

        int new_pos = this.zero_index - 1;
        moveZeroToLoc(new_pos);
    }

    private boolean checkIfSolved () {
        ArrayList<Integer> cpygame = new ArrayList<Integer>(tab);
        cpygame.sort(null);

        for (int i = 0; i < cpygame.size(); ++i) {
            if (cpygame.get(i) != tab.get(i))
                return false;
        }
        return true;
    }

    public boolean aplicarSolucao (String sol) {
        this.printCurrentState();

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

            this.printCurrentState();
        }
        return this.checkIfSolved();
    }
}
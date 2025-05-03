public class ArvBin {
    private String[] tree;
    protected int size;

    protected void insEle(int index, String val) {
        tree[index] = val;
    }

    protected String getEle(int index) {
        return tree[index];
    }

    public ArvBin(int len) {
        tree = new String[len];
        size = 0;
    }

    public void insert(String v) {
        int current = 0;

        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                return;
            } else if (v.compareTo(tree[current]) < 0) {
                current = current * 2 + 1;
            } else {
                current = current * 2 + 2;
            }
        }
        tree[current] = v;
        size += 1;
    }

    public boolean find(String v) {
        int current = 0;
        
        while (tree[current] != null) {
            if (v.compareTo(tree[current]) == 0) {
                return true;
            } else if (v.compareTo(tree[current]) < 0) {
                current = current * 2 + 1;
            } else {
                current = current * 2 + 2;
            }
        }

        return false; 
    }

    //Retorna o index do maximo da arvore
    private int maximum(int root) {
        int prev = root;
        int right = 2 * root + 2;

        while (tree[right] != null) {
            prev = right;
            right = 2 * right + 2;
        }

        return prev;
    }

    public boolean remove(String v) {
        int current = 0;

        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                int left = 2 * current + 1;
                int right = 2 * current + 2;

                if (tree[left] == null) {
                    tree[current] = tree[right];
                    tree[right] = null;
                } else if (tree[right] == null) {
                    tree[current] = tree[left];
                    tree[left] = null;
                } else {
                    int max_current = maximum(current);
                    tree[current] = tree[max_current];
                    tree[max_current] = null;
                }
            } else if (v.compareTo(tree[current]) < 0) {
                current = current * 2 + 1;
            } else {
                current = current * 2 + 2;
            }
        }

        return false;
    }

    public int len() {return size;}

    private void toStringTraverse(StringBuilder acc, int criatura) {
        if (tree[criatura] == null)
            return;
        
        int left = 2 * criatura + 1;
        int right = 2 * criatura + 2;

        if (tree[left] != null) {
            acc.append("\"" + Integer.toString(criatura) + " " + tree[criatura] + "\" ->" + "\"" + Integer.toString(left) + " " + tree[left] + "\"\n");
        }

        if (tree[right] != null) {
            acc.append("\"" + Integer.toString(criatura) + " " + tree[criatura] + "\" ->" + "\"" + Integer.toString(right) + " " + tree[right] + "\"\n");
        }

        toStringTraverse(acc, left);
        toStringTraverse(acc, right);
    }

    @Override
    public String toString() {
        StringBuilder acc = new StringBuilder("digraph {\n");
        toStringTraverse(acc, 0);
        acc.append( "}\n");
        return acc.toString();
    }
}
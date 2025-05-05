public class ArvBin {
    private String[] tree;
    protected int size;

    public ArvBin() {
        tree = new String[10000];
        size = 0;
    }

    public ArvBin(int len) {
        tree = new String[len];
        size = 0;
    }

    protected int left_child(int current) {
        return 2 * current + 1;
    }

    protected int right_child(int current) {
        return 2 * current + 1;
    }

    protected int arrSize() {
        return tree.length;
    }

    public void insert(String v) {
        int current = 0;

        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                return;
            } else if (v.compareTo(tree[current]) < 0) {
                current = left_child(current);
            } else {
                current = right_child(current);
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
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }

        return false; 
    }

    protected int maximum(int root) {
        int prev = root;
        int right = right_child(prev);

        while (tree[right] != null) {
            prev = right;
            right = right_child(right);
        }

        return prev;
    }

    public boolean remove(String v) {
        int current = 0;

        while (tree[current] != null) {
            // Sem elementos repitidos
            if (v.compareTo(tree[current]) == 0) {
                int left = left_child(current);
                int right = right_child(current);

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
                current = left_child(current);
            } else {
                current = right_child(current);
            }
        }

        return false;
    }

    public int len() {return size;}

    private void toStringTraverse(StringBuilder acc, int criatura) {
        if (tree[criatura] == null)
            return;
        
        int left = left_child(criatura);
        int right = right_child(criatura);

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

    protected void insEle(int index, String val) {
        tree[index] = val;
    }

    protected String getEle(int index) {
        return tree[index];
    }
}
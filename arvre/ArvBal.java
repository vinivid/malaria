public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        balance();
    }

    @Override
    public boolean remove(String v) {
        boolean removeu = super.remove(v);
        if (removeu) balance();
        return removeu;
    }

    private int countNodes(int i) {
        if (i >= arrSize() || getEle(i) == null) 
            return 0;

        return 1 + countNodes(left_child(i)) + countNodes(right_child(i));
    }

    private boolean isBalance() {
        for (int i = 0; i < arrSize(); i++) {
            if (getEle(i) != null) {
                int left = left_child(i);
                int right = right_child(i);
                int leftCount = countNodes(left);
                int rightCount = countNodes(right);
                int fatorBal = leftCount - rightCount;

                if (fatorBal > 1 || fatorBal < -1)
                    return false;
            }
        }
        return true;
    }
 
    private void balance() {
        if (isBalance()) return;

        String[] ordenado = new String[len()];
        int usados = inorder(0, ordenado, 0);
        
        for (int i = 0; i < arrSize(); i++) {
            insEle(i, null);
            if(len() > 0) removeSize();
        }

        rebuild(ordenado, 0, usados-1);
    }

    private int inorder(int i, String[] ordenado, int idx) {
        if (i >= arrSize() || getEle(i) == null) return idx;

        idx = inorder(left_child(i), ordenado, idx);
        ordenado[idx++] = getEle(i);
        idx = inorder(right_child(i), ordenado, idx);

        return idx;
    }

    private void rebuild(String[] ordenado, int ini, int fim) {
        if (ini > fim) return;
        int meio = (ini + fim) / 2;
        if (ordenado[meio] != null)
            super.insert(ordenado[meio]);
        rebuild(ordenado, ini, meio - 1);
        rebuild(ordenado, meio + 1, fim);
    }
}
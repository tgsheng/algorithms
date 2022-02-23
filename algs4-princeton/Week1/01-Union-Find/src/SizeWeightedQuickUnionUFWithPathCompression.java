import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SizeWeightedQuickUnionUFWithPathCompression {
    final private int[] root;
    final private int[] size;
    private int count;

    SizeWeightedQuickUnionUFWithPathCompression(int n) {
        count = n;
        root = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        // Make every other node in path point to its grandparent
        while (p != root[p]) {
            root[p] = root[root[p]];
            p = root[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (size[pRoot] < size[qRoot]) {
            root[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            root[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SizeWeightedQuickUnionUFWithPathCompression uf = new SizeWeightedQuickUnionUFWithPathCompression(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(uf.count() + " components");
    }
}



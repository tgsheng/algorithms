import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class HeightWeightedQuickUnionUFWithPathCompression {
    final private int[] root;
    final private int[] height;
    private int count;

    HeightWeightedQuickUnionUFWithPathCompression(int n) {
        count = n;
        root = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            height[i] = 1;
        }
    }

    public int find(int p) {
        // Set the root[] of each examined node to the root
        if (p != root[p])
            root[p] = find(root[p]);
        return root[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (height[pRoot] < height[qRoot]) {
            root[pRoot] = qRoot;
        } else {
            if (height[pRoot] == height[qRoot])
                height[pRoot]++;
            root[qRoot] = pRoot;
        }
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        HeightWeightedQuickUnionUFWithPathCompression uf = new HeightWeightedQuickUnionUFWithPathCompression(n);
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



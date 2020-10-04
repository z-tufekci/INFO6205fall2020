package edu.neu.coe.info6205.union_find;

public class UnionFind {
	static UF_HWQUPC uf = null;

	public static void main(String[] args) {
		/*
		 * Command line input: N "number of sites"
		 */
		System.out.println("Input N number to run Weighted Quick Union with Path Compression:");
		int N = Integer.parseInt(args[0]);
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N= 10;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N= 20;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N= 50;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N = 100;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N = 1000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();

		N = 2500;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();
		
		N = 5000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();

		N = 10000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();

		N = 25000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();
		
		N = 50000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		//uf.show();
		
		N = 100000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N = 200000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");
		
		N = 500000;
		uf = new UF_HWQUPC(N);
		System.out.println(count(N) + " (m) random pairs are generated for " + N + " (n) sites.");

	}

	public static int count(int N) {

		int random1 = 0;
		int random2 = 0;

		if (uf.components() == 1)
			return 1;
		int generation = 0;
		while (uf.components() != 1) {

			random1 = (int) (Math.random() * N);// 0 - (N-1)
			random2 = (int) (Math.random() * N);// 0 - (N-1)
			/*
			 * uf.connect(random1, random2); it's also possible.
			 */
			generation++;

			if (!uf.connected(random1, random2)) {
				uf.union(random1, random2);
				// System.out.println(random1+" "+random2+" is connected now by
				// union("+random1+","+random2+") method!");
			} else {
				// System.out.println(random1+" "+random2+" is already connected!");
			}
		}
		// number of connections
		return generation;
	}

}

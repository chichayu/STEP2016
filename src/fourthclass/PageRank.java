package fourthclass;

import java.io.*;

public class PageRank {

	static class Node {
		String name;
		int id;

		Node(String _name, int _id) {
			name = _name;
			id = _id;
		}

		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}
	}

	static class Edge {
		int from_id;
		int to_id;

		Edge(int _from_id, int _to_id) {
			from_id = _from_id;
			to_id = _to_id;
		}

		public int getFrom_id() {
			return from_id;
		}

		public int getTo_id() {
			return to_id;
		}
	}

	static Node[] readNode(BufferedReader br) throws NumberFormatException,
			IOException {
		int nodeSize = Integer.parseInt(br.readLine());
		Node[] node = new Node[nodeSize];
		for (int i = 0; i < nodeSize; i++) {
			node[i] = new Node(br.readLine(), i);
			// System.out.println("node[" + i + "]は" + node[i].getName());
		}
		return node;
	}

	static Edge[] readEdge(BufferedReader br, Node[] node) throws IOException {
		int edgeNum = Integer.parseInt(br.readLine());
		Edge[] edge = new Edge[edgeNum];
		for (int i = 0; i < edgeNum; i++) {
			String str = br.readLine();
			String[] strAry = str.split(" ");
			int getfrom_id = 0;
			int getto_id = 0;
			for (int j = 0; j < node.length; j++) {
				if (strAry[0].equals(node[j].getName())) {
					getfrom_id = node[j].getId();
				}
			}
			for (int j = 0; j < node.length; j++) {
				if (strAry[1].equals(node[j].getName())) {
					getto_id = node[j].getId();
				}
			}
			edge[i] = new Edge(getfrom_id, getto_id);
			// System.out.println("edge[" + i + "]は" + edge[i].from_id + " " +
			// edge[i].to_id);
		}
		return edge;
	}

	static double[] makePageRankVector(Node[] node) {
		double[] PageRankVector = new double[node.length];
		for (int i = 0; i < node.length; i++) {
			PageRankVector[i] = 100;
		}
		return PageRankVector;
	}

	static double[][] makeGoogleMatrix(Node[] node, Edge[] edge) {
		double[][] GoogleMatrix = new double[node.length][node.length];
		for (int i = 0; i < node.length; i++) {
			for (int k = 0; k < node.length; k++) {
				GoogleMatrix[i][k] = 0;
			}
			int num = 0;
			for (int l = 0; l < edge.length; l++) {
				if (edge[l].getFrom_id() == i) {
					// from_idの値がiであるedge[]の要素の数をカウントする
					num = num + 1;
				}
			}
			for (int j = 0; j < edge.length; j++) {
				if (edge[j].getFrom_id() == i) {
					int to_id = edge[j].getTo_id();
					GoogleMatrix[i][to_id] = 1 / num;
				}
			}
			for (int m = 0; m < node.length; m++) {
				System.out.println("GoogleMatrix[" + i + "][" + m + "]は"
						+ GoogleMatrix[i][m]);
			}
		}
		return GoogleMatrix;
	}

	static double[] calculate(double[] nodes, double[][] matrix) {
		int n = nodes.length;
		double[] PageRank = new double[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				PageRank[i] += nodes[i] * matrix[j][i];
			}
		}
		return PageRank;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("/Users/xixi/Downloads/homework4/small_data.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		Node[] node = readNode(br);
		Edge[] edge = readEdge(br, node);
		double[] PageRank = makePageRankVector(node);
		double[][] GoogleMatrix = makeGoogleMatrix(node, edge);

		System.out.println("何回試行しますか。");
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(System.in));
		int times = Integer.parseInt(br2.readLine());
		for (int i = 0; i < times; i++) {
			PageRank = calculate(PageRank, GoogleMatrix);
		}
		for (int i = 0; i < PageRank.length; i++) {
			System.out.println(node[i].name + "のPageRankは" + PageRank[i]);
		}
		br.close();
	}

}

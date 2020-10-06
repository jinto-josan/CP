package algorithms.graph;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;


public class gen_graph {
	int test_cases;
	boolean directed;
	int vertices;
	int edges;
	boolean acyclic;
	boolean connected;
	int start_index;
	

	gen_graph(int test_case, boolean directed, int start_index, int vertices, int edges) {

		assert vertices > 0 : " No. of vertices has to be positive";
		assert start_index >= 0 : " Index should be positive";
		this.start_index = start_index;
		if (directed) {
			assert edges <= (vertices) * (vertices - 1) && edges > 0 : " such graph doesnt exist ";
			directed_graph(vertices, edges);
		} else {
			assert edges <= (vertices) * (vertices - 1) / 2 && edges > 0 : " such graph doesnt exist ";
			undirected_graph(vertices, edges);
		}

	}

	void directed_graph(int vertices, int edges) {
		// ensuring that no multi edges. if (1 2) then no more (1 2)
		int i = 0;
		LinkedList<Edge> e = new LinkedList<Edge>();
		int min = start_index;
		int max = vertices + start_index;
		while (i < edges) {
			// Math.random generates double in between 0.0 to 1.0
			// Random edge inclusive of min and max
			Edge edg = new Edge((int) (Math.random() * (max - min) + min), (int) (Math.random() * (max - min) + min));
			// check for same edge present or not
			if (edg.x != edg.y) {
				boolean isThere = false;
				for (Edge ed : e) {
					if (edg.equals(ed)) {
						isThere = true;
						break;
					}
				}
				if (!isThere) {
					e.add(edg);
					++i;
				}
			}

		}
		for (Edge ed : e) {
			System.out.println(ed.x + " -> " + ed.y+" ;");
		}

	}

	void undirected_graph(int vertices, int edges) {
		// ensuring that no multi edges. If (1 2) then no (1 2) or (2 1) again
		int i = 0;
		LinkedList<Edge> e = new LinkedList<Edge>();
		int min = start_index;
		int max = vertices + start_index;
		while (i < edges) {
			// Math.random generates double in between 0.0 to 1.0
			// Random edge inclusive of min and max
			Edge edg = new Edge((int) (Math.random() * (max - min) + min), (int) (Math.random() * (max - min) + min));
			// check for same edge present or not
			if (edg.x != edg.y) {
				boolean isThere = false;
				for (Edge ed : e) {
					if (ed.check_undirected(edg)) {
						isThere = true;
						break;
					}
				}
				if (!isThere) {
					e.add(edg);
					++i;
				}
			}

		}
		for (Edge ed : e) {
			System.out.println(ed.x + " -- " + ed.y+" ;");
		}
	}

	public static void main(String args[]) {
		
//		final String OUTPUT = "undirected_graph.gv";
		final String OUTPUT = "directed_graph.gv";
		PrintStream outstream = null;
		try {
			
			//this always create new files rather than appending
			outstream = new PrintStream(new FileOutputStream(OUTPUT));
			System.setOut(outstream);
		} catch (Exception e) {
			System.err.println("Error Occurred.");
		}
		
//		System.out.println("graph {");
//		new gen_graph(1, false, 1, 5, 7);
//		System.out.println("}");
		
		System.out.println("digraph {");
		new gen_graph(1, true, 1, 5, 7);
		System.out.println("}");
		
		System.err.println("done.");
		return;
	}
	

}

class Edge {
	int x;
	int y;

	Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean check_undirected(Object o) {
		// checking if both object same
		if (this == o) {
			return true;
		}
		// checking for null
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Edge e = (Edge) o;
		return x == e.x && y == e.y || x == e.y && y == e.x;
	}

	@Override
	public boolean equals(Object o) {
		// checking if both object same
		if (this == o) {
			return true;
		}
		// checking for null
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Edge e = (Edge) o;
		return x == e.x && y == e.y;
	}
}

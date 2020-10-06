package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Iterator;

public class bfs {
	/*
	 * Adjacent elements of a vertex is pushed to queue(FIFO)
	 * After pushing of elements of a vertex to queue pop queue
	 * And then find adjacent element of popped element and push it in queue
	 * Repeat till queue empty
	 */
	bfs(graph g, int elem){
		g.reset();
		BFS(elem,g);
	}

	void BFS(int s,graph g) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>(); // dequeue pushes to the front
		q.push(s);
		g.visited[s] = true;
		while (!q.isEmpty()) {
			int el = q.pop();
			System.out.println(el);
			Iterator<Integer> i = g.adj[el].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!g.visited[n]) {
					g.visited[n] = true;
					q.push(n);
				}
			}
		}
	}

}

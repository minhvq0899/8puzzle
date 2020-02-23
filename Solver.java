import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private MinPQ<SearchNode> pq;
    private Board initial;
    private SearchNode minNode;


    private class SearchNode implements Comparable<SearchNode> {
        public Board board;
        public int move;
        public int priority;
        public SearchNode previous;

        public SearchNode(Board a, int moves, SearchNode previousNode) {
            this.board = a;
            this.move = moves;
            this.priority = this.move + a.manhattan();
            this.previous = previousNode;
        }

        public int compareTo(SearchNode other) {
            return this.priority - other.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Board is null");
        if (!initial.isSolvable()) throw new IllegalArgumentException("Unsolvable puzzle");

        this.initial = initial;
        pq = new MinPQ<SearchNode>();
        SearchNode start = new SearchNode(initial, 0, null);
        pq.insert(start);
        this.minNode = start;

        // while we haven't found our goal board yet
        while (!this.minNode.board.isGoal()) {
            this.minNode = pq.delMin();
            // now add neighbor of minNode
            for (Board neighbor : this.minNode.board.neighbors()) {
                // if minNode is our initial node
                if (this.minNode.previous == null) {
                    // we add that neighbor into our pq
                    pq.insert(new SearchNode(neighbor, this.minNode.move + 1, minNode));
                } else if (!neighbor.equals(minNode.previous.board)) { // or if this neighbor is not going back
                    pq.insert(new SearchNode(neighbor, this.minNode.move + 1, minNode));
                }
            }
        }

    }

    // min number of moves to solve initial board
    public int moves() {
        if (!this.initial.isSolvable()) return -1;
        return this.minNode.move;
    }


    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!this.initial.isSolvable()) return null;

        Stack<Board> sol = new Stack<Board>();
        SearchNode current = this.minNode;
        // go into this while loop until we find the initial board
        while (current.previous != null) {
            sol.push(current.board);
            current = current.previous;
        }
        sol.push(this.initial);
        return sol;
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] a2d = {{1, 2, 3},
                {0, 7, 6},
                {5, 4, 8}};

        Board a = new Board(a2d);
        Solver testCase = new Solver(a);
        StdOut.println("Minimum number of moves = " + testCase.moves());
        for (Board solution : testCase.solution()) {
            StdOut.println(solution.toString() + "\n");
        }

    }

}

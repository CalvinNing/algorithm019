class Solution {
    private int[][] direct = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int N = grid.length;
        if (grid[0][0] == 1 || grid[N - 1][N - 1] == 1) return -1;
        if (N == 1) return 1;
        Set<Pair> startSet = new HashSet<>();
        startSet.add(new Pair(0, 0));
        Set<Pair> endSet = new HashSet<>();
        endSet.add(new Pair(N - 1, N - 1));
        int distance = 1;
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            distance++;
            for (Pair pair : startSet) {
                grid[pair.x][pair.y] = 1;
            }
            Set<Pair> nextSet = new HashSet<>();
            for (Pair pair : startSet) {
                for (int i = 0; i < direct.length; i++) {
                    Pair nP = new Pair(pair.x + direct[i][0], pair.y + direct[i][1]);
                    if (isValidPair(nP, N, grid)) {
                        if (endSet.contains(nP)) {
                            return distance;
                        }
                        nextSet.add(nP);
                    }
                }
            }
            if (nextSet.size() > endSet.size()) {
                startSet = endSet;
                endSet = nextSet;
            }else {
                startSet = nextSet;
            }
        }
        return -1;
    }

    private boolean isValidPair(Pair pair, int N, int[][] grid) {
        int x = pair.x;
        int y = pair.y;
        if (x < 0 || x >= N || y < 0 || y >= N || grid[x][y] == 1) return false;
        return true;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            if (y != pair.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
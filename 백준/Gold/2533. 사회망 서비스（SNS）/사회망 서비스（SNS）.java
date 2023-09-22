import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        
        for (int i=1;i<N+1;i++){
            dp[i][1] = 1;
        }
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i=0;i<N+1;i++){
            tree.add(new ArrayList<>());
        }

        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
        dfs(tree, 1, 0);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }
    private static void dfs(ArrayList<ArrayList<Integer>> tree, int n, int p){

        for (int i:tree.get(n)){
            if (p != i){
                dfs(tree, i, n);
                dp[n][0] += dp[i][1];
                dp[n][1] += Math.min(dp[i][1], dp[i][0]);
            }
        }

    }
}
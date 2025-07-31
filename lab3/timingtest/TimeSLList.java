package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCnts = new AList<>();
        int M = 10000;
        int[] nVals = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        for (int N : nVals) {

                /* 1. 构造一条长度为 N 的 SLList */
                SLList<Integer> L = new SLList<>();
                for (int k = 0; k < N; k++) {
                    L.addLast(1);
                }

                /* 2. 计时 M 次 getLast */
                Stopwatch sw = new Stopwatch();
                for (int t = 0; t < M; t++) {
                    L.getLast();
                }
                double timeInSeconds = sw.elapsedTime();

                Ns.addLast(N);             // ① 收集 N
                times.addLast(timeInSeconds); // ② 收集用时
                opCnts.addLast(M);         // ③ 本实验 #ops = N 次 addLast
    }
        printTimingTable(Ns, times, opCnts);
    }

}

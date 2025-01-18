import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo("P1", 0, 5, 2));
        processos.add(new Processo("P2", 2, 3, 1));
        processos.add(new Processo("P3", 4, 58, 3));
        processos.add(new Processo("P4", 5, 6, 2));

        System.out.println("------------------------------------------------------------------");
        FirstComeFirstServed fcfs = new FirstComeFirstServed();
        fcfs.executarFCFS(processos);

        System.out.println("------------------------------------------------------------------");

        ShortestJobFirst sjf = new ShortestJobFirst();
        sjf.executarSJF(processos);

        System.out.println("------------------------------------------------------------------");

        RoundRobin roundRobin = new RoundRobin(2);
        roundRobin.executarRoundRobin(processos);

        System.out.println("------------------------------------------------------------------");

        PriorityScheduling priority = new PriorityScheduling();
        priority.executarPriorityScheduling(processos);

        System.out.println("------------------------------------------------------------------");

        PriorityMultipleSchedules priorityMultiple = new PriorityMultipleSchedules();
        priorityMultiple.executarPriorityMultipleSchedules(processos);

        System.out.println("------------------------------------------------------------------");

        LotteryScheduling lottery = new LotteryScheduling();
        lottery.executarLotteryScheduling(processos);
    }
}

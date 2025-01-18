import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityMultipleSchedules {

    public void executarPriorityMultipleSchedules(List<Processo> processos) {

        if (processos.isEmpty()) {
            System.out.println("Nenhum processo para agendar.");
            return;
        }

        List<Processo> processosCopia = new ArrayList<>(processos);

        PriorityQueue<Processo> filaProntos = new PriorityQueue<>(
                (p1, p2) -> Integer.compare(p1.getPrioridade(), p2.getPrioridade()));

        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        filaProntos.addAll(processosCopia);

        System.out.println("Priority Multiple Schedules");

        StringBuilder ordemExecucao = new StringBuilder();

        while (!filaProntos.isEmpty()) {
            Processo p = filaProntos.poll();

            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }

            p.setTempoEspera(calcularTempoEspera(p, tempoAtual));

            tempoAtual += p.getTempoExecucao();

            p.setTempoRetorno(calcularTempoRetorno(p, tempoAtual));

            somaEspera += p.getTempoEspera();
            somaRetorno += p.getTempoRetorno();

            ordemExecucao.append(p.getId()).append(" -> ");
        }

        if (ordemExecucao.length() > 0) {
            ordemExecucao.setLength(ordemExecucao.length() - 4);
        }

        System.out.println("Ordem de Execução: " + ordemExecucao);
        System.out.println();

        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");
        for (Processo processo : processos) {
            System.out.printf("%s        | %d              | %d\n",
                    processo.getId(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        System.out.println();
        System.out.printf("Tempo Médio de Espera: %.2f\n", somaEspera / processos.size());
        System.out.printf("Tempo Médio de Retorno: %.2f\n", somaRetorno / processos.size());
    }

    private int calcularTempoEspera(Processo p, int tempoAtual) {
        return tempoAtual - p.getTempoChegada();
    }

    private int calcularTempoRetorno(Processo p, int tempoAtual) {
        return tempoAtual - p.getTempoChegada();
    }
}

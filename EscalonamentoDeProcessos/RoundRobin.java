import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class RoundRobin {
    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void executarRoundRobin(List<Processo> processos) {
        Queue<Processo> fila = new LinkedList<>(processos);
        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        System.out.println("Round Robin (Quantum = " + timeQuantum + ")");
        System.out.print("Ordem de Execução: " );

        while (!fila.isEmpty()) {
            Processo p = fila.poll();

            System.out.print(p.getId() + " ");

            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            int tempoExecutado = Math.min(timeQuantum, p.getTempoExecucao());
            p.setTempoExecucao(p.getTempoExecucao() - tempoExecutado);
            tempoAtual += tempoExecutado;

            if (p.getTempoExecucao() > 0) {
                fila.add(p);
            } else {
                p.setTempoRetorno(tempoAtual - p.getTempoChegada());
                p.setTempoEspera(p.getTempoRetorno() - (p.getTempoExecucao() + tempoExecutado));

                somaEspera += p.getTempoEspera();
                somaRetorno += p.getTempoRetorno();
            }
        }

        System.out.println("\n");
        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");

        for (Processo processo : processos) {
            System.out.printf("%s        | %d              | %d\n",
                    processo.getId(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        double tempoMedioEspera = somaEspera / processos.size();
        double tempoMedioRetorno = somaRetorno / processos.size();

        System.out.println();
        System.out.printf("Tempo Médio de Espera: %.2f\n", tempoMedioEspera);
        System.out.printf("Tempo Médio de Retorno: %.2f\n", tempoMedioRetorno);
    }
}
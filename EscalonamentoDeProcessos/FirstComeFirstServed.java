import java.util.Comparator;
import java.util.List;

public class FirstComeFirstServed {

    public void executarFCFS(List<Processo> processos) {
        processos.sort(Comparator.comparingInt(Processo::getTempoChegada));

        int tempoAtual = 0;
        double somaEspera = 0;
        double somaRetorno = 0;

        System.out.println("FCFS - First Come First Served");
        System.out.print("Ordem de Execução: ");

        for (int i = 0; i < processos.size(); i++) {
            Processo processo = processos.get(i);

            System.out.print(processo.getId());
            if (i < processos.size() - 1) {
                System.out.print(" -> ");
            }

            if (tempoAtual < processo.getTempoChegada()) {
                tempoAtual = processo.getTempoChegada();
            }
            processo.setTempoEspera(tempoAtual - processo.getTempoChegada());
            tempoAtual += processo.getTempoExecucao();
            processo.setTempoRetorno(tempoAtual - processo.getTempoChegada());

            somaEspera += processo.getTempoEspera();
            somaRetorno += processo.getTempoRetorno();
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

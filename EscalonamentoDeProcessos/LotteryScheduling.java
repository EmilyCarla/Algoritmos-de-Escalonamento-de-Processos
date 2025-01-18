import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryScheduling {
    public void executarLotteryScheduling(List<Processo> processos) {
        List<Processo> fila = new ArrayList<>(processos);
        Random random = new Random();


        List<Processo> bilhetes = new ArrayList<>();
        for (Processo processo : fila) {
            for (int i = 0; i < processo.getPrioridade(); i++) {
                bilhetes.add(processo);
            }
        }

        int tempoAtual = 0;
        List<String> ordemExecucao = new ArrayList<>();

        while (!fila.isEmpty()) {
            Processo sorteado = bilhetes.get(random.nextInt(bilhetes.size()));
            fila.remove(sorteado);

            if (tempoAtual < sorteado.getTempoChegada()) {
                tempoAtual = sorteado.getTempoChegada(); 
            }

            sorteado.setTempoEspera(tempoAtual - sorteado.getTempoChegada());
            tempoAtual += sorteado.getTempoExecucao();
            sorteado.setTempoRetorno(tempoAtual);

            ordemExecucao.add(sorteado.getId());

            bilhetes.removeIf(p -> p.getId().equals(sorteado.getId()));
        }

        System.out.println("Lottery Scheduling");
        System.out.println("Ordem de Execução: " + String.join(" -> ", ordemExecucao));
        double tempoTotalEspera = 0;
        double tempoTotalRetorno = 0;
        System.out.println("Processo | Tempo de Espera | Tempo de Retorno");

        for (Processo processo : processos) {
            tempoTotalEspera += processo.getTempoEspera();
            tempoTotalRetorno += processo.getTempoRetorno();
            System.out.printf("%s        | %d              | %d\n",
                processo.getId(), processo.getTempoEspera(), processo.getTempoRetorno());
        }

        System.out.printf("\nTempo Médio de Espera: %.2f%n", tempoTotalEspera / processos.size());
        System.out.printf("Tempo Médio de Retorno: %.2f%n", tempoTotalRetorno / processos.size());
    }
}
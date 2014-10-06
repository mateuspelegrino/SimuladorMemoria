package simuladormemoria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SimuladorMemoria {

    public static void main(String[] args) {
        Scanner leituraDaTela = new Scanner(System.in);
        int politica, contador;
        int acertos =0;
        float fracaoAcertosErros =0;
        int totalQuadros;
        String dadoLido;
        ArrayList<Pagina> memoria;
        boolean paginaEstaNaMemoria;
        do {
            System.out.println("Qual tipo de política de substituição de quadros deseja usar?\n");
            System.out.println("Digite: 1 para lru;\n\t 2 para fifo;\n\t 3 para random.\n");
            System.out.println("Para sair, digite qualquer outro número.\n");
            politica = leituraDaTela.nextInt();
        } while ((politica < 1) || (politica > 3));
        try {
            Pagina aux;
            BufferedReader in = new BufferedReader(new FileReader("entrada.txt"));
            totalQuadros = Integer.parseInt(args[0]);
             dadoLido = in.readLine();
            memoria = new ArrayList<>(totalQuadros);//memoria tem o tamanho passado como argumento
            contador = -1;  //indica posicao de memoria atual
            paginaEstaNaMemoria = false;
            while (dadoLido != null) {
                for (int i = 0; ((i < contador ) && (i < totalQuadros)); i++) {
                    if (memoria.get(i).getNumeroPagina() == Integer.parseInt(dadoLido)) {
                        memoria.get(i).setUltimaUtilizacao(++contador);
                        paginaEstaNaMemoria = true;
                        acertos++;
                        break;
                    }
                }
                if (!paginaEstaNaMemoria) {
                    if (contador >= totalQuadros) {
                        contador++;
                        aux = new Pagina(Integer.parseInt(dadoLido), contador, contador);
                        switch (politica) {
                            case 1:
                                memoria = lru(memoria, aux);
                                break;
                            case 2:
                                memoria = fifo(memoria, aux);
                                break;
                            case 3:
                                memoria = random(memoria, aux);
                                break;
                            default:
                                System.out.println("Política inválida.");
                                break;
                        }
                    } else {
                        contador++;
                        aux = new Pagina(Integer.parseInt(dadoLido), contador, contador);
                        memoria.add(aux);
                    }
                }
                paginaEstaNaMemoria = false;
                imprimeMemoria(memoria,contador);
                dadoLido = in.readLine();
            }
            fracaoAcertosErros = (contador < 0)? 0 : (float)acertos/(float)(contador+1); //se contador <0 fração de acertos é 0
            //caso contrário, a fração de acertos é acertos/contador+1, porque o contador começa em -1 e o primeiro quadro entra no tempo 0.

            System.out.println("Fração de Acertos = "+fracaoAcertosErros);
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void imprimeMemoria(ArrayList<Pagina> memoria, int contador) {
        System.out.println("Estado atual da memória no tempo " + (contador+1));
        for (int i = 0; i < memoria.size(); i++) {
            if ((contador < memoria.size()) && (i > contador)) {
                break;
            }
            System.out.println("Página " + memoria.get(i).getNumeroPagina());
        }
        System.out.println("***************************************************");
    }

    public static ArrayList<Pagina> lru(ArrayList<Pagina> memoria, Pagina paginaEscalonada) {
        Pagina menosUsada = memoria.get(0);
        for (int i = 1; i < memoria.size(); i++) {
            if (memoria.get(i).getUltimaUtilizacao() < menosUsada.getUltimaUtilizacao()) {
                menosUsada = memoria.get(i);
            }
        }
        memoria.remove(menosUsada);
        memoria.add(paginaEscalonada);
        return memoria;
    }

    public static ArrayList<Pagina> fifo(ArrayList<Pagina> memoria, Pagina paginaEscalonada) {
        Pagina menosUsada = memoria.get(0);
        for (int i = 1; i < memoria.size(); i++) {
            if (memoria.get(i).getTempoAdicionado() < menosUsada.getTempoAdicionado()) {
                menosUsada = memoria.get(i);
            }
        }
        memoria.remove(menosUsada);
        memoria.add(paginaEscalonada);
        return memoria;
    }

    public static ArrayList<Pagina> random(ArrayList<Pagina> memoria, Pagina paginaEscalonada) {
        Pagina retirada = memoria.remove((int) (Math.random() * memoria.size()));
        memoria.remove(retirada);
        memoria.add(paginaEscalonada);
        return memoria;
    }
}
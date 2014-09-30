/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladormemoria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author MateusPelegrino
 */
public class SimuladorMemoria {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("entrada.txt"));
            int totalQuadros = Integer.parseInt(args[0]);
            String dadoLido = in.readLine();
            ArrayList <Pagina> memoria = new ArrayList<Pagina>(totalQuadros);//memoria tem o tamanho passado como argumento
            int contador = -1;  //indica posicao de memoria atual
            boolean paginaEstaNaMemoria = false;
            while (dadoLido != null) {
                if (contador >= totalQuadros) {
                    // implementar politica de substituicao de quadros
                } else {
                    for (int i = 0; i <= contador + 1; i++) {
                        if (memoria.get(i).getNumeroPagina() == Integer.parseInt(dadoLido)) {
                            memoria.get(i).setUltimaUtilizacao(++contador);
                            paginaEstaNaMemoria = true;
                            break;
                        }
                    }
                    if (!paginaEstaNaMemoria) {
                        //Implementar politica de substituicao de quadros
                    }
                    paginaEstaNaMemoria = false;
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void imprimeMemoria(ArrayList<Pagina> memoria, int contador) {
        System.out.println("Estado atual da memória no tempo "+contador);
        for (int i = 0; i < memoria.size(); i++) {
            if((contador < memoria.size()) && (i > contador))
                break;
            System.out.println("Página "+memoria.get(i).getNumeroPagina());
        }
        System.out.println("***************************************************");
    }
    
    public ArrayList<Pagina> lru(ArrayList<Pagina> memoria, Pagina paginaEscalonada){
        Pagina menosUsada = memoria.get(0);
        for (int i = 1; i < memoria.size(); i++) {
            if(memoria.get(i).getUltimaUtilizacao() < menosUsada.getUltimaUtilizacao())
                menosUsada = memoria.get(i);
        }
        memoria.remove(menosUsada);
        memoria.add(paginaEscalonada);
        return memoria;
    }
    public ArrayList<Pagina> fifo(ArrayList<Pagina> memoria, Pagina paginaEscalonada){
        Pagina menosUsada = memoria.get(0);
        for (int i = 1; i < memoria.size(); i++) {
            if(memoria.get(i).getTempoAdicionado() < menosUsada.getTempoAdicionado())
                menosUsada = memoria.get(i);
        }
        memoria.remove(menosUsada);
        memoria.add(paginaEscalonada);
        return memoria;
    }
    
    public ArrayList<Pagina> random(ArrayList<Pagina> memoria, Pagina paginaEscalonada){
        Pagina retirada = memoria.remove((int)(Math.random()*10));
        memoria.remove(retirada);
        memoria.add(paginaEscalonada);
        return memoria;
    }
    
}

package simuladormemoria;
public class Pagina {
    private int numeroPagina;
    private int ultimaUtilizacao;
    private int tempoAdicionado;

    public Pagina(int numeroPagina, int ultimaUtilizacao, int tempoAdicionado){
        this.numeroPagina = numeroPagina;
        this.ultimaUtilizacao = ultimaUtilizacao;
        this.tempoAdicionado = tempoAdicionado;
    }

    /**
     * @return the numeroPagina
     */
    public int getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * @param numeroPagina the numeroPagina to set
     */
    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    /**
     * @return the ultimaUtilizacao
     */
    public int getUltimaUtilizacao() {
        return ultimaUtilizacao;
    }

    /**
     * @param ultimaUtilizacao the ultimaUtilizacao to set
     */
    public void setUltimaUtilizacao(int ultimaUtilizacao) {
        this.ultimaUtilizacao = ultimaUtilizacao;
    }

    /**
     * @return the tempoAdicionado
     */
    public int getTempoAdicionado() {
        return tempoAdicionado;
    }

    /**
     * @param tempoAdicionado the tempoAdicionado to set
     */
    public void setTempoAdicionado(int tempoAdicionado) {
        this.tempoAdicionado = tempoAdicionado;
    }
    
}
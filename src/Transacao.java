import java.util.Date;

public class Transacao {
    private double valor;
    private String categoria;
    private String descricao;
    private Date data;
    private int id;
    private Tipo tipo;
    private Conta conta;


    public Transacao(int id, double valor, String descricao, Date data,String categoria, Tipo tipo){
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.id = id;
    }


    public Date getData() {
        return data;
    }


    public void setData(Date data) {
        this.data = data;
    }

    
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public int getId() {
        return id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


    public void setId(int id) {
        this.id = id;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   

    @Override
    public String toString() {
        return "Transicao ID: " + id + "\n" + "Valor: " + valor + "\n"+ "Desricao: " + descricao + "\n" + "Categoria: " + categoria + "\n" + "Tipo: " + tipo + "\n" + "Data: " + data + "\n";
    }

}

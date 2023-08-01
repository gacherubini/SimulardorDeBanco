import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Conta {
     
   private int id;
   private String banco;
   private int agencia;
   private int numero;
   private double saldo;
   private List<Transacao> transacoes;

    public Conta(int id,String banco, int agencia, int numero, double saldo){
    this.id = id;
    this.banco = banco;
    this.agencia = agencia;
    this.numero = numero;
    this.saldo = 0;
   }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanco() {
    return banco;
        }

    public void setBanco(String banco) {
        this.banco = banco;
        }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
        
    }
    //retorna a lista de transacoes
    public List<Transacao> getTransacoes() {
        return transacoes;
    }

      public void setTransacoes(List<Transacao> transacoes) {
           this.transacoes = transacoes;

    }

    
    
    //metodo para adicionar as transacoes na lista, caso nao exista a lista, o metodo cria uma
    public void addTransacao(Transacao transacao) {
        if (transacoes == null) {
            transacoes = new ArrayList<Transacao>();
        }
        transacoes.add(transacao);
        atualizaSaldo(transacao);
    }

    //atualiza o saldo na conta quando recebe transacao, se for receita aumenta se for despesa diminui
    public void atualizaSaldo(Transacao transacao){
       if (transacao.getTipo() == Tipo.RECEITA) {
        saldo = saldo + transacao.getValor();
       } else if (transacao.getTipo() == Tipo.DESPESA) {
        saldo = saldo - transacao.getValor();
       }
    }

  

    public Transacao procurarTransacaoPeloID(int id){
        for (Transacao transacao : transacoes) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }

    public void substituiATransacao(int id, Transacao novaTransacao){
        for (Transacao transadaoFiltrada : transacoes) {
            if (transadaoFiltrada.getId() == id) {
                transacoes.set(transacoes.indexOf(transadaoFiltrada), novaTransacao);    
            }
        }
    }

     
    public void exibeOTotalDeReceitaETotalDeDespesasDoMesAtual(){
        Date dataAtual = new Date();
        Calendar calendarioDaDataAtual = Calendar.getInstance();
        calendarioDaDataAtual.setTime(dataAtual);
        double despesa=0;
        double receta=0;

        for (Transacao transacao : transacoes) {
            Calendar calendarioDaTransacao = Calendar.getInstance();
            calendarioDaTransacao.setTime(transacao.getData());
            if (calendarioDaDataAtual.get(Calendar.MONTH) == calendarioDaTransacao.get(Calendar.MONTH) 
            && calendarioDaDataAtual.get(Calendar.YEAR) == calendarioDaTransacao.get(Calendar.YEAR)) {
               if (transacao.getTipo() == Tipo.RECEITA) {
                receta = receta + transacao.getValor();
                 } else if (transacao.getTipo() == Tipo.DESPESA) {
                despesa = despesa - transacao.getValor();
                }
            }
        }
        System.out.println("=======================");
       System.out.println("O total de Despesas do mes atual é R$: " + despesa);
       System.out.println("O total de Receitas do mes atual é R$: " + receta);
    }

    public void exibeOTotalDeReceitaETotalDeDespesasDos6Meses(){
        Date dataAtual = new Date();
        Calendar CalendarioDaDataAtual = Calendar.getInstance();
        CalendarioDaDataAtual.setTime(dataAtual);
        double saldo = 0;
        
        
        Date data6MesesAntes = new Date();
        Calendar CalendarioDe6MesesAntes = Calendar.getInstance();
        CalendarioDe6MesesAntes.setTime(data6MesesAntes);
        CalendarioDe6MesesAntes.add(Calendar.MONTH, -6);   //Go to date, 6 months ago 27 July, 2011
        CalendarioDe6MesesAntes.set(Calendar.DAY_OF_MONTH, 1); //set date, to make it 1 July, 2011
        
        
        for (Transacao transacao : transacoes) {
            Calendar calendarioDaTransacao = Calendar.getInstance();
            calendarioDaTransacao.setTime(transacao.getData());
            if (calendarioDaTransacao.compareTo(CalendarioDe6MesesAntes) >= 0 
            && calendarioDaTransacao.compareTo(CalendarioDaDataAtual) <= 0) {
                System.out.println("a data " + transacao.getData() + " está dentro do periodo de 6 meses");
                if (transacao.getTipo() == Tipo.RECEITA) {
                 saldo = saldo + transacao.getValor();
                } else if (transacao.getTipo() == Tipo.DESPESA) {
                  saldo = saldo - transacao.getValor();
               }
               System.out.println("O saldo da conta nesses 6 meses é: "+saldo);
            }
        }
    }
    
    // toString serve para printar todos valores da transacao e checa se existe transacao, se nao exisitr printar a mensagem abaixo
    @Override
    public String toString() { 
        String transacoesTexto = "Nao possui transacoes";
        if (transacoes != null) {
            transacoesTexto = transacoes.toString();
        }
        return " ID: " + id + "\n" + "Banco: " + banco + "\n" + "Agencia: " + agencia + "\n"+ "Numero: " + numero + "\n" + "Saldo: " + saldo + "\n" + transacoesTexto;
    }


}

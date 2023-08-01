import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private List<Conta> contas;

    public Usuario(){
    }

    //retorna a lista de contas
    public List<Conta> getContas() {
        return contas;
    }

    public Conta procuraConta(int id){
        for (Conta conta : contas) {
            if (conta.getId() == id) {
                return conta;
            }
        }
        return null;
    }

     public Conta procuraNaListaDeContasUltimaTransacaoCadastradaRetornandoContaComEsseID(Transacao transacao){
         for (Conta conta : contas) {
            if (conta.getTransacoes().indexOf(transacao) == conta.getId()-1) {
                return conta;
            }
        }
        return null;
    }

     
    
    public boolean removerContaPorId(List<Conta> listaContas, int idConta) {
        Iterator<Conta> iterator = listaContas.iterator();
        while (iterator.hasNext()) {
            Conta conta = iterator.next();
            if (conta.getId() == idConta) {
                iterator.remove();
                return true;  // Conta removida com sucesso
            }
        }
        return false;  // ID de conta não encontrado
    }

    public void contarTodosSaldos(){
       double contarSaldo = 0;
        for (Conta conta : contas) {
            contarSaldo = contarSaldo + conta.getSaldo();
        }
        System.out.println("=========================================");
        System.out.println("O usuario tem ao todo de saldo: R$ " + contarSaldo );
    }

    

    //metodo para adicionar as contas na lista, caso nao exista lista cria uma lista
    public void addContas(Conta conta) {
        if (contas == null) {
          contas = new ArrayList<Conta>();  
        }
        contas.add(conta);
    }
    
}

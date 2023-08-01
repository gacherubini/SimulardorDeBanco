import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class GerenciadorContas {
    public GerenciadorContas(){
    }

    int ultimoIdContaCadastrado = 0;
    int ultimoIdTransacaoCadastrado = 0;
    Transacao ultimaTransacaoCadastrada = null;

    public void cadastrarContas(Usuario usuario){
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Cadastro de conta");
            System.out.println("===========================");
            System.out.println("Digite seu banco");
            String banco = in.nextLine();
            System.out.println("Digite sua agencia");
            int agencia = in.nextInt();
            System.out.println("Digite seu numero");
            int numero = in.nextInt(); 
            //aumenta o ID +1 a cada conta criada 
            ultimoIdContaCadastrado++;
            //Cria a nova conta e adiciona ela na lista de contas que fica no usuario
            Conta novaConta = new Conta(ultimoIdContaCadastrado, banco, agencia, numero, 0);
            usuario.addContas(novaConta);
           System.out.println("Conta Adicionada ID: " + ultimoIdContaCadastrado);
        }  catch(Exception ex){
            System.out.println("Aconteceu alguma coisa errada!");
        }
       
    }

     public void removerContas(Usuario usuario){
        try {
         List<Conta> checarConta = usuario.getContas();
         if (checarConta == null) {
            System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
            System.out.println("=============================");
        return;
            }
        Scanner in = new Scanner(System.in);
        System.out.println("Remocao de conta");
        System.out.println("=============================");
        System.out.println("Digite o ID da conta que voce deseja remover");
        int id = in.nextInt();
        //procura na lista de contas e remove o id desejado, é id-1 por causa do index que sempre atualiza na lista
      
        usuario.removerContaPorId(checarConta, id);
        System.out.println("Conta do ID: " + id + ", Removido");
        } catch (Exception e) {
            System.out.println("Aconteceu alguma coisa errada!");      
          }

    }

     public void mesclarContas(Usuario usuario){
     try {
        List<Conta> checarConta = usuario.getContas();
        if (checarConta == null) {
            System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
            System.out.println("=============================");
         return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Unificar as contas");
        System.out.println("=============================");
        System.out.println("Digite o ID da primeira conta que voce deseja unificar");
        int idConta1 =  Integer.parseInt(in.nextLine());
        System.out.println("Digite o ID da segunda conta que voce deseja unificar");
        int idConta2 =  Integer.parseInt(in.nextLine());
        //novo nome do banco para a nova conta, junto com as outras informacoes
        System.out.println("Digite o novo Banco da conta");
        String novoBanco = in.nextLine();
        System.out.println("Digite o nova Agencia da conta");
        int novaAgencia = Integer.parseInt(in.nextLine());
        System.out.println("Digite o novo Numero da conta");
        int novoNumero =  Integer.parseInt(in.nextLine());
        System.out.println("Essa nova conta herdadara a transacao das duas contas anteriores");
        

       // buscar a conta atravez do idconta1
        Conta novaConta1 = usuario.procuraConta(idConta1);
        // buscar a outra conta atravez do idconta2
        Conta novaConta2 = usuario.procuraConta(idConta2);
        //buscar as transacoes da conta 1
        List<Transacao> transacoesDaConta1 = novaConta1.getTransacoes();
        //buscar as transacaoes da conta 2
        List<Transacao> transacoesDaConta2 = novaConta2.getTransacoes();
        //criar uma conta com os dados inputados e adiciona na lista que fica no usuario pelo usuario e aumenta o ID
        ultimoIdContaCadastrado++;

        double valorTransacao1 = novaConta1.getSaldo();
        double valortransacao2 = novaConta2.getSaldo();
        double valorOficial = valorTransacao1 + valortransacao2;

        Conta novaContaUnificada = new Conta(ultimoIdContaCadastrado, novoBanco, novaAgencia, novoNumero, valorOficial);
        usuario.addContas(novaContaUnificada);
        //adicionar as trancoes das duas contas e checa se tem as contas tem transacoes
        List<Transacao> novaListaDeTransacoes = new ArrayList<Transacao>();

        
        if (transacoesDaConta1 != null) {  
            novaListaDeTransacoes.addAll(transacoesDaConta1);
            novaContaUnificada.setTransacoes(novaListaDeTransacoes);
        }
        if (transacoesDaConta2 != null) {
            novaListaDeTransacoes.addAll(transacoesDaConta2);
            novaContaUnificada.setTransacoes(novaListaDeTransacoes);
        }

        novaContaUnificada.setSaldo(valorOficial);

        //IMPORTANTE: arrumar caso o ID tiver alto nao funciona
        usuario.removerContaPorId(checarConta, idConta1);
        usuario.removerContaPorId(checarConta, idConta2);

        System.out.println("===========================");
        System.out.println("Contas unidas com sucesso!!");
        } catch (Exception e) {
            System.out.println("Aconteceu alguma coisa errada!");      
            e.printStackTrace();
          }

    }   

    public void ListarContas(Usuario usuario) {
       //pergunta se existe contas, caso nao exista da a mensagem
        if (usuario.getContas() == null) {
            System.out.println("Usuario nao tem contas ainda");
            return;
        }
        //for simples para imprimir as contas com seu toString que tem o toString das transacoes
        for (Conta conta : usuario.getContas()) {
                System.out.println("Conta" + conta.toString() +  "\n");
        }
    }

    public void efetuarTransacoes(Usuario usuario) {
        try{
            List<Conta> checarConta = usuario.getContas();
              if (checarConta == null) {
                System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
                System.out.println("=============================");
                return;
            }

            Scanner in = new Scanner(System.in);
            System.out.println("Efetuar transacao");
            System.out.println("===========================");
            System.out.println("Digite ID da conta");
            int idContaparaTransacao = Integer.parseInt(in.nextLine());
            System.out.println("Digite a categoria da transacao");
            String categoria = in.nextLine();
            System.out.println("Digite a descricao da transacao");
            String descricao = in.nextLine();
            System.out.println("Digite valor da transacao");
            double valor = in.nextDouble();
            System.out.println("Digite o tipo (Receita ou Despesa):");
            String tipoEntradaScanner = in.next().toUpperCase();
            Tipo tipo = Tipo.valueOf(tipoEntradaScanner);
            System.out.println("Digite a data neste formato (dd/MM/yyyy):");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(in.next());
            
            //aumenta o id da ultima transacao
            ultimoIdTransacaoCadastrado++;
            //filtra a ultima conta para adicionar na transacao
          // Conta filtrarConta = usuario.getContas().get(idContaparaTransacao -1 );
            Conta filtrarConta = usuario.procuraConta(idContaparaTransacao);
            //cria uma nova transacao e adiciona na lista de transacoes que fica dentro da classe conta
            Transacao novaTransacao = new Transacao(ultimoIdTransacaoCadastrado, valor, descricao, date, categoria, tipo);
            ultimaTransacaoCadastrada = novaTransacao;
            novaTransacao.setConta(filtrarConta);
            filtrarConta.addTransacao(novaTransacao);
     
           System.out.println("Transacao Adicionada ID: " + ultimoIdTransacaoCadastrado);
        }  catch(Exception ex){
            System.out.println("Aconteceu alguma coisa errada!");
        }
       
    }
    //IMPORTANTE: achar um jeito de excluir e adicionar a ultima transacao
    public void editarUltimaTransacao(Usuario usuario) {
         try{
              if (ultimaTransacaoCadastrada == null) {
                System.out.println("Nenhuma Transacao encontrada!!" + "\n" + "voltou para o menu");
                System.out.println("=============================");
                return;
            }
            Scanner in = new Scanner(System.in);
            System.out.println("===========================");
            System.out.println("Editar ultima Transacao");
            System.out.println("===========================");
            System.out.println("Digite a categoria da transacao");
            String categoria = in.nextLine();
            System.out.println("Digite a descricao da transacao");
            String descricao = in.nextLine();
            System.out.println("Digite valor da transacao");
            double valor = in.nextDouble();
            System.out.println("Digite o tipo (Receita ou Despesa):");
            String tipoEntradaScanner = in.next().toUpperCase();
            Tipo tipo = Tipo.valueOf(tipoEntradaScanner);
            System.out.println("Digite a data neste formato (dd/MM/yyyy):");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(in.next());

            // buscar a ultima transacao  
            Conta antigo = usuario.getContas().get(ultimaTransacaoCadastrada.getConta().getId()-1);

            System.out.println(usuario.procuraNaListaDeContasUltimaTransacaoCadastradaRetornandoContaComEsseID(ultimaTransacaoCadastrada));
            Conta contaQueContemUltimaTranscao = usuario.procuraNaListaDeContasUltimaTransacaoCadastradaRetornandoContaComEsseID(ultimaTransacaoCadastrada);
            //adicionar a nova transacao alterada
            Transacao NovaUltimaTransacao = new Transacao(ultimoIdTransacaoCadastrado, valor, descricao, date, categoria, tipo);
            
            contaQueContemUltimaTranscao.substituiATransacao(ultimoIdTransacaoCadastrado, NovaUltimaTransacao);
            
            contaQueContemUltimaTranscao.setSaldo(valor);
            
        }  catch(Exception ex){
            System.out.println("Aconteceu alguma coisa errada!");
            ex.printStackTrace();
        }
    }

    public void transferirFundos(Usuario usuario) {
        try {
        List<Conta> checarConta = usuario.getContas();
         if (checarConta == null) {
            System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
            System.out.println("=============================");
        return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("=============================");
        System.out.println("Transferir Fundos");
        System.out.println("=============================");
        System.out.println("Digite o valor da transferencia");
        double valorTransferencia = in.nextDouble();
        System.out.println("Digite o ID da conta que voce desejar retirar fundos para transferir");
        int idContaParaTransferir = in.nextInt();
        System.out.println("Digite o ID da conta que voce desejar enviar fundos");
        int idContaParaReceber = in.nextInt();

        //buscar a conta atravez do contaParaTransferir
        Conta contaParaRetirar = usuario.procuraConta(idContaParaTransferir);
        //buscar a conta atravez do contaParaReceber
        Conta contaParaReceber = usuario.procuraConta(idContaParaReceber);
        //chechar se a conta da tranferencia tem o valor desejado para tranferir, se tiver ja seta os saldos necessarios
        if (contaParaRetirar.getSaldo() >= valorTransferencia) {
            contaParaReceber.setSaldo(contaParaReceber.getSaldo() + valorTransferencia);
            contaParaRetirar.setSaldo(contaParaRetirar.getSaldo() - valorTransferencia);
        }
        else{
            System.out.println("=============================================");
            System.out.println("Nao tem dinheiro suficiente para transferencia");
        }
        
        } catch (Exception e) {
            System.out.println("Aconteceu alguma coisa errada!");      
          }
    }

    public void ResumoDasContas(Usuario usuario) {
           //pergunta se existe contas, caso nao exista da a mensagem
        if (usuario.getContas() == null) {
            System.out.println("Usuario nao tem contas ainda");
            return;
        }
        usuario.contarTodosSaldos();
        //for simples para imprimir as contas com seu toString que tem o toString das transacoes
        for (Conta conta : usuario.getContas()) {
                System.out.println("Conta" + conta.toString() +  "\n");
        }
    }

    public void ResumoEDespesaDoMes(Usuario usuario) {
        try {
        List<Conta> checarConta = usuario.getContas();
        if (checarConta == null) {
            System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
            System.out.println("=============================");
         return;
        }
  
        for (Conta conta : usuario.getContas()) {
            System.out.println("Conta ID: " + conta.getId());
            conta.exibeOTotalDeReceitaETotalDeDespesasDoMesAtual();   
        }

        } catch (Exception e) {
            System.out.println("Aconteceu alguma coisa errada!");      
          } 
    }

    public void SaldoGeralNosUltimosMeses(Usuario usuario) {
        try {
        List<Conta> checarConta = usuario.getContas();
        if (checarConta == null) {
            System.out.println("Nenhuma conta encontrada!!" + "\n" + "voltou para o menu");
            System.out.println("=============================");
         return;
        }
  
        for (Conta conta : usuario.getContas()) {
            System.out.println("Conta ID: " + conta.getId());
            conta.exibeOTotalDeReceitaETotalDeDespesasDos6Meses();   
        }

        } catch (Exception e) {
            System.out.println("Aconteceu alguma coisa errada!");      
          } 
    }
}

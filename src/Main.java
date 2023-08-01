import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        
        boolean cadastrando = false;
        Usuario usuario = new Usuario();
        GerenciadorContas gerenciador = new GerenciadorContas();
        // while para ficar sempre rodando o menu e caso aconteca algum erro volta do inicio
        while(cadastrando == false){
            try {
              Scanner in = new Scanner(System.in);
                System.out.println("MENU");
                System.out.println("===============================");
                System.out.println("DIGITE 1 PARA CRIAR UMA CONTA NOVA");
                System.out.println("DIGITE 2 PARA REMOVER UMA DAS CONTAS");
                System.out.println("DIGITE 3 PARA UNIFICAR DUAS CONTAS");
                System.out.println("DIGITE 4 PARA VER O EXTRATO DAS CONTAS");
                System.out.println("DIGITE 5 PARA FAZER TRANSACOES");
                System.out.println("DIGITE 6 PARA TRANFESFERIR FUNDOS"); 
                System.out.println("DIGITE 7 PARA EDITAR A ULTIMA TRANSACAO");
                System.out.println("DIGITE 8 PARA VER O SALDO DO USUARIO E SUAS CONTAS");
                System.out.println("DIGITE 9 PARA VER O RESUMO DAS DESPESAS DO MES"); 
                System.out.println("DIGITE 10 PARA VER O RESUMO DE 6 MESES");
                System.out.println("100 PARA SAIR"); 
                System.out.println("===============================");
                int opcao = in.nextInt();
                    switch (opcao) {
                    case 1:
                    gerenciador.cadastrarContas(usuario);
                        break;
                    case 2:
                    gerenciador.removerContas(usuario);
                        break;
                    case 3:
                    gerenciador.mesclarContas(usuario);
                    break; 
                    case 4:
                    gerenciador.ListarContas(usuario);
                    break;
                    case 5:
                    gerenciador.efetuarTransacoes(usuario);
                    break;
                    case 6:
                    gerenciador.transferirFundos(usuario);
                    break; 
                    case 7:
                    gerenciador.editarUltimaTransacao(usuario);
                    break;
                    case 8:
                    gerenciador.ResumoDasContas(usuario);
                    break; 
                    case 9:
                    gerenciador.ResumoEDespesaDoMes(usuario);
                    break;  
                    case 10:
                    gerenciador.SaldoGeralNosUltimosMeses(usuario);
                    break; 
                    case 100:
                    cadastrando = true;
                    break; 
                }
                } catch (Exception e) {
                    System.out.println("Aconteceu alguma coisa errada!");      
                }
        }

    }
}   


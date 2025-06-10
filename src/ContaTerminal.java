import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ContaTerminal {
    //Método para limpar a tela do console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        // Conhecer e importar a classe Scanner
        Scanner scanner = new Scanner(System.in);
        final int numbConta = 12345678; //Constante para número da conta
        final int passWord = 2025;      //Constante para senha
        double Saldo = 1000;            //Saldo inicial

        // Listas para armazenar as transações
        List<String> tipoMovin = new ArrayList<>();
        List<Double> valorMovin = new ArrayList<>();
        List<String> descrMovin = new ArrayList<>();

        boolean autenticado = false;
        boolean sistAtivo = true;
        int nConta;
        int Senha;

        while (sistAtivo) {
            if (!autenticado) {
                clearScreen();
                System.out.println("=============== BANCO SANTANDER ===============");
                System.out.println("");

                System.out.print("Favor digite o numero da conta:");
                try {
                    nConta = scanner.nextInt();                
                } catch (InputMismatchException e) {
                    System.out.println("Entrada invalida.");
                    scanner.next();
                    TimeUnit.SECONDS.sleep(2);
                    continue;
                }
                if (nConta == numbConta) {
                    System.out.print("Agora digite sua senha de 4 digitos:");
                    try {
                        Senha = scanner.nextInt();                    
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida.");
                        scanner.next();
                        TimeUnit.SECONDS.sleep(2);
                        continue;                        
                    }
                    if (Senha == passWord){
                        autenticado = true;
                        System.out.println("Login bem-sucedido!");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        System.out.println("Senha invalida");
                        sistAtivo = false;
                        TimeUnit.SECONDS.sleep(2);
                    }
                } else {
                    System.out.println("Numero de conta invalido");
                    sistAtivo = false;
                    TimeUnit.SECONDS.sleep(2);
                }
            } else {
                clearScreen();
                System.out.println("=============== BANCO SANTANDER ===============");
                System.out.println("");
                System.out.printf("Seu saldo é: R$ %.2f \n", Saldo);
                System.out.println("");
                System.out.println("Selecione os serviços abaixo:");
                System.out.println("1 - Extrato");
                System.out.println("2 - Depositos");
                System.out.println("3 - Pagamentos");
                System.out.println("4 - Sair");
                System.out.print("Sua opção: ");
                int opcao = 0;
                try {
                    opcao = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nOpção inválida.");
                    scanner.next();
                    TimeUnit.SECONDS.sleep(2);
                    continue;
                }
                scanner.nextLine();
                switch (opcao) {
                    case 1: // Extrato
                        clearScreen();
                        System.out.println("=============== BANCO  SANTANDER ===============");
                        System.out.println("=============== EXTRATO DA CONTA ===============");
                        if (tipoMovin.isEmpty()){
                            System.out.println("Nenhuma movimentação registrada.");
                        } else {
                            System.out.println(" Tipo         | Valor         | Descrição");
                            System.out.println("------------------------------------------------");
                            for (int i = 0; i < tipoMovin.size(); i++) {
                                String sinal = (tipoMovin.get(i).equals("Credito")) ? "+" : "-";
                                System.out.printf("%s | %s %-10.2f | %s\n", tipoMovin.get(i), sinal, valorMovin.get(i), descrMovin.get(i));
                            }
                        }
                        System.out.printf("\nSALDO FINAL: R$ %.2f \n", Saldo);
                        System.out.println("================================================");
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        scanner.nextLine(); //Espera o usuário pressionar Enter;
                        break;
                    case 2: // Depósito
                        clearScreen();
                        System.out.println("=============== BANCO  SANTANDER ===============");
                        System.out.println("=================== DEPOSITO ===================");
                        System.out.print("Informe o valor a depositar: R$ ");
                        double valorDeposito = 0;
                        try {
                            valorDeposito = scanner.nextDouble();                        
                        } catch (InputMismatchException e) {
                            System.out.println("\nValor inválida.");
                            scanner.next();
                            TimeUnit.SECONDS.sleep(2);
                            continue;
                        }
                        if (valorDeposito <= 0) {
                            System.out.println("O valor invalido.");
                            TimeUnit.SECONDS.sleep(2);
                            break;
                        }
                        System.out.println("Informe uma descrição para o deposito.");
                        var descDeposito = scanner.next();
                        Saldo += valorDeposito;
                        tipoMovin.add("Credito");
                        valorMovin.add(valorDeposito);
                        descrMovin.add(descDeposito);
                        System.out.printf("Deposito de R$ %.2f realizado com sucesso! \n", valorDeposito);
                        System.out.printf("Novo saldo: R$ %.2f \n", Saldo);
                        System.out.println("================================================");
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        scanner.nextLine(); //Espera o usuário pressionar Enter;
                        break;
                    case 3: // Pagamento
                        clearScreen();
                        System.out.println("=============== BANCO  SANTANDER ===============");
                        System.out.println("================== PAGAMENTOS ==================");
                        System.out.print("Informe o valor a pagar: R$ ");
                        double valorPagar = 0;
                        try {
                            valorPagar = scanner.nextDouble();                         
                        } catch (InputMismatchException e) {
                            System.out.println("\nValor inválida.");
                            scanner.next();
                            TimeUnit.SECONDS.sleep(2);
                            continue;
                        }
                        if (valorPagar <= 0) {
                            System.out.println("O valor invalido.");
                            TimeUnit.SECONDS.sleep(2);
                            break;
                        }
                        if (valorPagar > Saldo) {
                            System.out.println("Saldo insuficiente para realizar este pagamento.");
                            TimeUnit.SECONDS.sleep(2);
                            break;
                        }
                        System.out.println("Informe uma descrição para o pagamento.");
                        var descPagar = scanner.next();
                        Saldo -= valorPagar;
                        tipoMovin.add("Debito");
                        valorMovin.add(valorPagar);
                        descrMovin.add(descPagar);
                        System.out.printf("Pagamento de R$ %.2f realizado com sucesso! \n", valorPagar);
                        System.out.printf("Novo saldo: R$ %.2f \n", Saldo);
                        System.out.println("================================================");
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        scanner.nextLine(); //Espera o usuário pressionar Enter;
                        break;
                    case 4: // Sair
                        sistAtivo = false;
                        System.out.println("\nObrigado por usar o Banco Santander. Volte sempre!");
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    default:
                        System.out.println("\nOpção inválida. Por favor, selecione uma opçã de 1 a 4.");
                        TimeUnit.SECONDS.sleep(2);
                        break;
                }
            }
            //Exibir as mensagens para o nosso usuários
            //Obter pela scanner os valores digitados no terminal
            //Exibir a mensagem conta criada
        }
        scanner.close();
    }
}

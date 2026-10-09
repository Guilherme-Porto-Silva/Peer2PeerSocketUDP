import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner scanf = new Scanner(System.in);

        int tamanhoMensagem = 100;



        try{
            DatagramSocket socketPassiva = new DatagramSocket(1501);

            DatagramSocket socketAtivo = new DatagramSocket();

            System.out.println("Cliente conectado!");



            new Thread(() -> {

                while (true) {

                    byte[] mensagem = new byte[tamanhoMensagem];

                    DatagramPacket pacote = new DatagramPacket(mensagem, tamanhoMensagem);

                    try { socketPassiva.receive(pacote); }

                    catch (IOException e) { System.out.println("Erro no socket: " + e.getMessage()); }

                    System.out.println("Mensagem recebida: " + new String(mensagem) + "\n\nRemetente: " + pacote.getAddress().getHostAddress());
                }
            }).start();



            while (true) {

                System.out.print("Digite a mensagem que será enviada: ");

                String texto = scanf.nextLine();

                if (texto.strip().equalsIgnoreCase("siar")) break;

                byte[] mensagem = texto.getBytes();

                DatagramPacket pacote = new DatagramPacket(mensagem, mensagem.length, InetAddress.getByName("172.16.0.16"), 1500);

                socketAtivo.send(pacote);

                System.out.print("\nPacote enviado!");
            }



            socketPassiva.close();

            socketAtivo.close();

        }

        catch (IOException e){ System.out.println("Erro no socket: " + e.getMessage()); }
    }
}

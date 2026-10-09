import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        Scanner scanf = new Scanner(System.in);

        int tamanhoMensagem = 100;



        try{
            DatagramSocket socketPassiva = new DatagramSocket(1500);

            DatagramSocket socketAtivo = new DatagramSocket();

            System.out.println("Servidor conectado!");



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

                System.out.print("\nDigite a mensagem que será enviada: ");

                String texto = scanf.nextLine();

                if (texto.strip().equalsIgnoreCase("siar")) break;

                byte[] mensagem = texto.getBytes();

                DatagramPacket pacote = new DatagramPacket(mensagem, tamanhoMensagem, InetAddress.getByName("127.0.0.1"), 1501);

                socketAtivo.send(pacote);
            }



            socketPassiva.close();

            socketAtivo.close();
        }

        catch (IOException e) {

            System.out.println("Erro no socket: " + e.getMessage());
        }

    }
}
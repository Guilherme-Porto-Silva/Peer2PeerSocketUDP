import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        try{
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Cliente conectado!");

            while (true) {

                System.out.print("Cliente: ");

                String texto = leitor.nextLine();

                byte[] mensagem = texto.getBytes();

                DatagramPacket pacote = new DatagramPacket(mensagem, mensagem.length, InetAddress.getByName("172.16.0.16"), 1500);
                
                socket.send(pacote);

                System.out.println("Pacote enviado!");
            }

            socket.close();

        }

        catch(IOException e){ System.out.println("Erro no socket: "+ e.getMessage()); }
        
    }
}

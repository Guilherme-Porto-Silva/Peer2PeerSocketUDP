import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(1500);
            System.out.println("Servidor conectado!");
            byte[] mensagem = new byte[100];
            while(true){
                DatagramPacket pacote = new DatagramPacket(mensagem,mensagem.length);
                socket.receive(pacote);
                System.out.println("Mensagem recebida: "+new String(mensagem)
                                +"\nRemetente: "+pacote.getAddress().getHostAddress());
            }

        }catch(IOException e){
            System.out.println("Erro no socket: "+ e.getMessage());
        }

    }
}

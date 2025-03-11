package services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LocationServer {

    private ServerSocket socketServidor;
    private int porta;

    public LocationServer(int porta) {
        this.porta = porta;
        this.rodar();
    }

    private void rodar() {
        try {
            socketServidor = new ServerSocket(porta);
            System.out.println("Servidor de localização rodando na porta " + porta);

            while (true) {
                // Aguarda conexão do cliente
                Socket cliente = socketServidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                // Cria e inicia uma thread para lidar com a conexão
                new Thread(new LocationServerImpl(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LocationServer(5555); // Rodando na porta fixa 5555
    }
}



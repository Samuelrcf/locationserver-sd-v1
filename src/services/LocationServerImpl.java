package services;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class LocationServerImpl implements Runnable {
    private Socket socketCliente;

    // Endereço fixo do servidor proxy
    private static final String PROXY_IP = "localhost"; // definir end ip correto
    private static final int PROXY_PORTA = 5556;

    public LocationServerImpl(Socket cliente) {
        this.socketCliente = cliente;
    }

    public void run() {
        System.out.println("Cliente conectado ao servidor de localização: " +
                socketCliente.getInetAddress().getHostAddress());

        try {
            // Enviar IP e porta do proxy ao cliente
            PrintStream saida = new PrintStream(socketCliente.getOutputStream());
            saida.println(PROXY_IP + ":" + PROXY_PORTA);
            System.out.println("Enviado ao cliente: " + PROXY_IP + ":" + PROXY_PORTA);

            // Fechar conexão
            saida.close();
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Erro ao enviar IP do proxy ao cliente: " + e.getMessage());
        }
    }
}



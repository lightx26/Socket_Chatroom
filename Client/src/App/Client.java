package App;

import java.io.IOException;
import java.net.Socket;

import DataTransfer.DataTransfer;
import View.ClientGUI;

public class Client {
    private String username;
    private ClientGUI clientGUI;
    private DataTransfer dataTransfer;
    private boolean _isConnected;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void send(String chat) {
        dataTransfer.send(chat);
    }

    public String receive() {
        return dataTransfer.receive();
    }

    public void start() {
        clientGUI = new ClientGUI(this);
    }

    public boolean connect() {
        try {
            String serverIP = "127.0.0.1";
            int serverPort = 2626;

            Socket socket = new Socket(serverIP, serverPort);
            dataTransfer = new DataTransfer(socket);
            _isConnected = true;
            return true;
        } catch (IOException e) {
            System.err.println("Cannot connect to server: " + e.getMessage());
            return false;
        }
    }

    public boolean isConnected() {
        return this._isConnected;
    }

    public void stop() {
        _isConnected = false;
        dataTransfer.close();
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new Client().start();
    }
}

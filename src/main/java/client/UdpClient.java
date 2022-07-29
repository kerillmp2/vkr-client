package client;

import java.io.*;
import java.net.Socket;

import request.ClientRequest;
import response.ResponseBuilder;
import response.ServerResponse;

public class UdpClient {

    private Socket clientSocket;
    private String clientName;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    public UdpClient(Socket clientSocket, String clientName) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }

    public ServerResponse sendRequestToServer(ClientRequest clientRequest) {
        try {
            try {
                inObj = new ObjectInputStream(clientSocket.getInputStream());
                outObj = new ObjectOutputStream(clientSocket.getOutputStream());

                outObj.writeObject(clientRequest);
                outObj.flush();

                return (ServerResponse) inObj.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                inObj.close();
                outObj.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseBuilder.empty(ServerResponse.ResponseType.UNKNOWN).withParam("error_msg", "Не был получен ответ от сервера").build();
    }

    public String getClientName() {
        return clientName;
    }
}
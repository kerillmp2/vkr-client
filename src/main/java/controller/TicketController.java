package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.UdpClient;
import request.ClientRequest;
import request.RequestBuilder;
import response.ServerResponse;

public class TicketController {

    public static void processWithClient(UdpClient udpClient) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите начальную точку маршрута");
            String origin = reader.readLine();
            System.out.println("Введите конечную точку маршрута");
            String destination = reader.readLine();
            System.out.println("Введите дату в формате yyyy-MM-dd");
            String date = reader.readLine();

            ClientRequest clientRequest = RequestBuilder.buildTicketRequest(udpClient.getClientName(), origin, destination, date);

            ServerResponse serverResponse = udpClient.sendRequestToServer(clientRequest);
            if (serverResponse.hasParam("error_msg")) {
                System.out.println(serverResponse.getStringParam("error_msg"));
            } else {
                System.out.println("Ваш билет: ");
                System.out.println("Origin: " + serverResponse.getStringParam("origin") + "(" + serverResponse.getStringParam("origin_airport") + ")");
                System.out.println("Destination: " + serverResponse.getStringParam("destination") + "(" + serverResponse.getStringParam("destination_airport") + ")");
                System.out.println("Price: " + serverResponse.getIntParam("price") + " " + serverResponse.getStringParam("currency"));
                System.out.println("Airline: " + serverResponse.getStringParam("airline"));
                System.out.println("Flight number: " + serverResponse.getStringParam("flight_number"));
                System.out.println("Departure at: " + serverResponse.getStringParam("departure_at"));
                System.out.println("Link: " + serverResponse.getStringParam("link"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

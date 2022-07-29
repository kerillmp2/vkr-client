import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.UdpClient;
import controller.RecommendationController;
import controller.TicketController;
import utils.CommandType;
import utils.Selector;

public class Main {

    public static void main(String[] args) {
        int port = 4004;
        System.out.println("Подключаемся к серверу с портом " + port);

        try (Socket clientSocket = new Socket("localhost", port)) {

            UdpClient udpClient = new UdpClient(clientSocket, "Kirill");
            System.out.println("Успешное подключение к серверу!");

            System.out.println("Выберите тип запроса");

            List<CommandType> options = new ArrayList<>();
            options.add(CommandType.BACK);
            options.add(CommandType.GET_RECOMMENDATION);
            options.add(CommandType.GET_TICKET);

            CommandType selectedCommand = Selector.select(options);
            if (selectedCommand == null) {
                selectedCommand = CommandType.BACK;
            }
            System.out.println(selectedCommand.getName());

            switch (selectedCommand) {
                case BACK -> {
                    System.out.println("Благодарим за использование!");
                    System.out.println("До встречи!");
                }
                case GET_TICKET -> {
                    TicketController.processWithClient(udpClient);
                }
                case GET_RECOMMENDATION -> {
                    RecommendationController.processWithClient(udpClient);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

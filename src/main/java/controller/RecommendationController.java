package controller;

import java.util.HashSet;
import java.util.Set;

import client.UdpClient;
import request.ClientRequest;
import request.RequestBuilder;
import response.ServerResponse;
import utils.ScoreType;
import utils.Selector;

public class RecommendationController {

    public static void processWithClient(UdpClient udpClient) {
        Set<ScoreType> scoreTypes = new HashSet<>();
        System.out.println("Выберите значимые параметры поиска из списка:");
        ScoreType scoreType = Selector.select(ScoreType.values());
        while (scoreType != null) {
            if (scoreTypes.contains(scoreType)) {
                scoreTypes.remove(scoreType);
            } else {
                scoreTypes.add(scoreType);
            }
            StringBuilder builder = new StringBuilder("Вы выбрали параметры: [ ");
            scoreTypes.forEach(t -> builder.append(t.getName()).append("; "));
            builder.append("]");
            System.out.println(builder);
            System.out.println("Выберите значимые параметры поиска из списка (Чтобы получить рекомендации введите -1):");
            scoreType = Selector.select(ScoreType.values());
        }

        ClientRequest clientRequest = RequestBuilder.buildRecommendationRequest(udpClient.getClientName(), scoreTypes);

        ServerResponse serverResponse = udpClient.sendRequestToServer(clientRequest);

        if (serverResponse.hasParam("error_msg")) {
            System.out.println(serverResponse.getStringParam("error_msg"));
        } else {
            StringBuilder builder = new StringBuilder("Ваши рекомендации по параметрам: [ ");
            scoreTypes.forEach(t -> builder.append(t.getName()).append("; "));
            builder.append("]");
            System.out.println(builder);
            System.out.println(serverResponse.getStringParam("recommendation_string"));
        }
    }
}

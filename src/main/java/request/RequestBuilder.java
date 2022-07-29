package request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import utils.ScoreType;

public class RequestBuilder {

    private static RequestTemplate empty(ClientRequest.RequestType requestType) {
        return new RequestTemplate(requestType, new HashMap<>(), new HashMap<>());
    }

    public static ClientRequest buildTicketRequest(String clientName, String origin, String destination, String date) {
        return RequestBuilder.empty(ClientRequest.RequestType.GET_TICKET)
                .withParam("client_name", clientName)
                .withParam("origin", origin)
                .withParam("destination", destination)
                .withParam("date", date)
                .build();
    }

    public static ClientRequest buildRecommendationRequest(String clientName, Set<ScoreType> scoreTypes) {
        StringBuilder scoreTypesParam = new StringBuilder();
        scoreTypes.forEach(type -> scoreTypesParam.append(type.name()).append(","));

        return RequestBuilder.empty(ClientRequest.RequestType.GET_RECOMMENDATION)
                .withParam("client_name", clientName)
                .withParam("score_types", scoreTypesParam.toString())
                .build();
    }

    public record RequestTemplate(ClientRequest.RequestType requestType, Map<String, String> stringParams, Map<String, Integer> intParams) {
        public RequestTemplate withParam(String name, String value) {
            stringParams.put(name, value);
            return this;
        }

        public RequestTemplate withParam(String name, int value) {
            intParams.put(name, value);
            return this;
        }

        public ClientRequest build() {
            return new ClientRequest(requestType, stringParams, intParams);
        }
    }
}
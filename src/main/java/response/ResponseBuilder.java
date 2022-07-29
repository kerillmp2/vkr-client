package response;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    public static ResponseTemplate empty(ServerResponse.ResponseType responseType) {
        return new ResponseTemplate(responseType, new HashMap<>(), new HashMap<>());
    }

    public record ResponseTemplate(ServerResponse.ResponseType responseType, Map<String, String> stringParams, Map<String, Integer> intParams) {
        public ResponseTemplate withParam(String name, String value) {
            stringParams.put(name, value);
            return this;
        }

        public ResponseTemplate withParam(String name, int value) {
            intParams.put(name, value);
            return this;
        }

        public ServerResponse build() {
            return new ServerResponse(responseType, stringParams, intParams);
        }
    }
}
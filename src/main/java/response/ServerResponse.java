package response;

import java.io.Serializable;
import java.util.Map;

public class ServerResponse implements Serializable {
    private final ResponseType responseType;
    private final Map<String, String> stringParams;
    private final Map<String, Integer> intParams;

    public ServerResponse(ResponseType responseType, Map<String, String> stringParams, Map<String, Integer> intParams) {
        this.responseType = responseType;
        this.stringParams = stringParams;
        this.intParams = intParams;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Map<String, String> getStringParams() {
        return stringParams;
    }

    public Map<String, Integer> getIntParams() {
        return intParams;
    }

    public boolean hasParam(String key) {
        return stringParams.containsKey(key) || intParams.containsKey(key);
    }

    public String getStringParam(String key) {
        return stringParams.getOrDefault(key, "UNKNOWN");
    }

    public int getIntParam(String key) {
        return intParams.getOrDefault(key, -1);
    }

    public enum ResponseType implements Serializable {
        UNKNOWN,
        TEST,
        GET_RECOMMENDATION,
        GET_TICKET;
    }
}

package request;

import java.io.Serializable;
import java.util.Map;

import response.ServerResponse;

public class ClientRequest implements Serializable {
    private final RequestType requestType;
    private final Map<String, String> stringParams;
    private final Map<String, Integer> intParams;

    public ClientRequest(RequestType requestType, Map<String, String> stringParams, Map<String, Integer> intParams) {
        this.requestType = requestType;
        this.stringParams = stringParams;
        this.intParams = intParams;
    }

    public RequestType getRequestType() {
        return requestType;
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

    public enum RequestType implements Serializable {
        GET_RECOMMENDATION,
        GET_TICKET;
    }
}

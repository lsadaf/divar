package Communication;

import java.io.Serializable;

public class RequestPacket implements Serializable{
    private final Object data;
    private final RequestType requestType;

    public RequestPacket(RequestType requestType, Object data) {
        this.data = data;
        this.requestType = requestType;
    }

    public Object getData() {
        return data;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}

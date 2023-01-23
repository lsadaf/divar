package Communication;

import java.io.Serializable;

public class RequestPacket implements Serializable{
    Object data;
    RequestType requestType;

    public RequestPacket(RequestType requestType, Object data) {
        this.data = data;
        this.requestType = requestType;
    }
}

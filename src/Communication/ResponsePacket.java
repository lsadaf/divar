package Communication;

import java.io.Serializable;
import java.util.Collections;

public class ResponsePacket implements Serializable {
    private final Object initialData;

    public ResponsePacket(Object initialData) {
        this.initialData = initialData;
    }

    public Object getInitialData() {
        return initialData;
    }
}

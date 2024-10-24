package vn.edu.hust.project.crossplatform.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import vn.edu.hust.project.crossplatform.constant.MessageStatus;

import java.io.IOException;

public class MessageStatusSerializer extends StdSerializer<MessageStatus> {

    public MessageStatusSerializer() {
        super(MessageStatus.class);
    }

    @Override
    public void serialize(MessageStatus value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Chuyển MessageStatus thành 1 (UNREAD) hoặc 0 (READ)
        int statusCode = (value == MessageStatus.UNREAD) ? 1 : 0;
        gen.writeNumber(statusCode);
    }
}

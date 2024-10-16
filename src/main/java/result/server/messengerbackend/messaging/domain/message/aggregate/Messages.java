package result.server.messengerbackend.messaging.domain.message.aggregate;


import java.util.List;

import result.server.messengerbackend.shared.error.domain.Assert;

public record Messages(List<Messages> messages) {
    public Messages {
        Assert.field("messages", messages).notNull().noNullElement();
    }
}

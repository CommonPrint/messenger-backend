package result.server.messengerbackend.messaging.domain.message.aggregate;

import result.server.messengerbackend.shared.error.domain.Assert;

import java.util.List;


public record Conversations(List<Conversation> conversations) {

    public Conversations {
        Assert.field("conversations", conversations).notNull().noNullElement();
    }
}

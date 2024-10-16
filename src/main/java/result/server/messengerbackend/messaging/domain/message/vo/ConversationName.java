package result.server.messengerbackend.messaging.domain.message.vo;

import result.server.messengerbackend.shared.error.domain.Assert;

public record ConversationName(String name) {

    public ConversationName {
        Assert.field("name", name).minLength(3).maxLength(255);
    }
}

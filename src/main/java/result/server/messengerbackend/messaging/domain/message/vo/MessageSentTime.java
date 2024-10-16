package result.server.messengerbackend.messaging.domain.message.vo;

import result.server.messengerbackend.shared.error.domain.Assert;

import java.time.Instant;


public record MessageSentTime(Instant date) {
    public MessageSentTime {
        Assert.field("date", date).notNull();
    }
}
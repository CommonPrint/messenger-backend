package result.server.messengerbackend.messaging.domain.user.vo;

import result.server.messengerbackend.shared.error.domain.Assert;

public record UserEmail(String value) {

    public UserEmail {
        Assert.field(value, value).maxLength(255);
    }
}

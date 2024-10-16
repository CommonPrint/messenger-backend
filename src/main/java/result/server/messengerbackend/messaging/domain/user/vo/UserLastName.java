package result.server.messengerbackend.messaging.domain.user.vo;

import result.server.messengerbackend.shared.error.domain.Assert;

public record UserLastName(String value) {

    public UserLastName {
        Assert.field(value, value).maxLength(255);
    }
}
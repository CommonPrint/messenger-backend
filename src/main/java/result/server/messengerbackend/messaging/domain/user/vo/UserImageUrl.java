package result.server.messengerbackend.messaging.domain.user.vo;

import result.server.messengerbackend.shared.error.domain.Assert;


public record UserImageUrl(String value) {

    public UserImageUrl {
        Assert.field(value, value).maxLength(255);
    }
}
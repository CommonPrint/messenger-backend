package result.server.messengerbackend.messaging.domain.user.vo;

import result.server.messengerbackend.shared.error.domain.Assert;


public record UserFirstname(String value) {

    public UserFirstname {
        Assert.field(value, value).maxLength(255);
    }
}
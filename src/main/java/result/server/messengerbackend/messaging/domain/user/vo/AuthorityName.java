package result.server.messengerbackend.messaging.domain.user.vo;

import result.server.messengerbackend.shared.error.domain.Assert;


public record AuthorityName(String name) {

    public AuthorityName {
        Assert.field("name", name).notNull();
    }
}

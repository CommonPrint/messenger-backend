package result.server.messengerbackend.messaging.domain.user.aggregate;

import org.jilt.Builder;

import result.server.messengerbackend.messaging.domain.user.vo.AuthorityName;
import result.server.messengerbackend.shared.error.domain.Assert;


@Builder
public class Authority {

    private AuthorityName name;

    public Authority(AuthorityName name) {
        Assert.notNull("name", name);
        this.name = name;
    }

    public AuthorityName getName() {
        return name;
    }
}
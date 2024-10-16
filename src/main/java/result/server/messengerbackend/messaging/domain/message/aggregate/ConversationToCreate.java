package result.server.messengerbackend.messaging.domain.message.aggregate;

import org.jilt.Builder;

import java.util.Set;

import result.server.messengerbackend.messaging.domain.message.vo.ConversationName;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;
import result.server.messengerbackend.shared.error.domain.Assert;


@Builder
public class ConversationToCreate {

    private final Set<UserPublicId> members;

    private final ConversationName name;

    public ConversationToCreate(Set<UserPublicId> members, ConversationName name) {
        assertMandatoryFields(members, name);
        this.members = members;
        this.name = name;
    }

    private void assertMandatoryFields(Set<UserPublicId> members, ConversationName name) {
        Assert.notNull("name", name);
        Assert.notNull("members", members);
    }

    public Set<UserPublicId> getMembers() {
        return members;
    }

    public ConversationName getName() {
        return name;
    }
}

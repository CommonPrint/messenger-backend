package result.server.messengerbackend.infrastructure.primary.conversation;

import org.jilt.Builder;

import result.server.messengerbackend.messaging.domain.message.aggregate.ConversationToCreate;
import result.server.messengerbackend.messaging.domain.message.vo.ConversationName;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Builder
public record RestConversationToCreate(Set<UUID> members, String name) {

    public static ConversationToCreate toDomain(RestConversationToCreate restConversationToCreate) {
        RestConversationToCreateBuilder restConversationToCreateBuilder = RestConversationToCreateBuilder.restConversationToCreate();

        Set<UserPublicId> userUUIDs = restConversationToCreate.members
                .stream()
                .map(UserPublicId::new)
                .collect(Collectors.toSet());

        return ConversationToCreateBuilder.conversationToCreate()
                .name(new ConversationName(restConversationToCreate.name()))
                .members(userUUIDs)
                .build();
    }
}
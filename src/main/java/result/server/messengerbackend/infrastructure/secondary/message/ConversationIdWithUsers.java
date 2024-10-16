package result.server.messengerbackend.infrastructure.secondary.message;


import java.util.List;

import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;


public record ConversationIdWithUsers(ConversationPublicId conversationPublicId,
                                      List<UserPublicId> users) {
}
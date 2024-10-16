package result.server.messengerbackend.infrastructure.secondary.message;


import java.util.List;

import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;


public record MessageIdWithUsers(ConversationViewedForNotification conversationViewedForNotification,
                                 List<UserPublicId> usersToNotify) {
}
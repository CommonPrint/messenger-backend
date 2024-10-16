package result.server.messengerbackend.infrastructure.secondary.message;


import java.util.List;

import result.server.messengerbackend.messaging.domain.message.aggregate.Message;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;


public record MessageWithUsers(Message message, List<UserPublicId> userToNotify) {
}
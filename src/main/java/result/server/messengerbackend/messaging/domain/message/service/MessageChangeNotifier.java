package result.server.messengerbackend.messaging.domain.message.service;


import java.util.List;

import result.server.messengerbackend.infrastructure.secondary.message.ConversationViewedForNotification;
import result.server.messengerbackend.messaging.domain.message.aggregate.Message;
import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;
import result.server.messengerbackend.shared.service.State;

public interface MessageChangeNotifier {

    State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

    State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify);
}
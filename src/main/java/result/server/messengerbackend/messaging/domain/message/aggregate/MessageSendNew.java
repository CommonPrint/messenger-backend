package result.server.messengerbackend.messaging.domain.message.aggregate;

import org.jilt.Builder;

import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.message.vo.MessageContent;


@Builder
public record MessageSendNew(MessageContent messageContent,
                             ConversationPublicId conversationPublicId) {
}
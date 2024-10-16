package result.server.messengerbackend.messaging.domain.message.service;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import result.server.messengerbackend.messaging.domain.message.aggregate.Conversation;
import result.server.messengerbackend.messaging.domain.message.aggregate.Message;
import result.server.messengerbackend.messaging.domain.message.aggregate.MessageSendNew;
import result.server.messengerbackend.messaging.domain.message.repository.MessageRepository;
import result.server.messengerbackend.messaging.domain.message.vo.MessagePublicId;
import result.server.messengerbackend.messaging.domain.message.vo.MessageSendState;
import result.server.messengerbackend.messaging.domain.message.vo.MessageSentTime;
import result.server.messengerbackend.messaging.domain.user.aggregate.User;
import result.server.messengerbackend.shared.service.State;


public class MessageCreator {

    private final MessageRepository messageRepository;
    private final MessageChangeNotifier messageChangeNotifier;
    private final ConversationReader conversationReader;

    public MessageCreator(MessageRepository messageRepository, MessageChangeNotifier messageChangeNotifier,
                          ConversationReader conversationReader) {
        this.messageRepository = messageRepository;
        this.messageChangeNotifier = messageChangeNotifier;
        this.conversationReader = conversationReader;
    }


    public State<Message, String> create(MessageSendNew messageSendNew, User sender) {
    	result.server.messengerbackend.messaging.domain.message.aggregate.Message newMessage = MessageBuilder.message()
                .content(messageSendNew.messageContent())
                .publicId(new MessagePublicId(UUID.randomUUID()))
                .sendState(MessageSendState.RECEIVED)
                .sentTime(new MessageSentTime(Instant.now()))
                .conversationId(messageSendNew.conversationPublicId())
                .sender(sender.getUserPublicId())
                .build();

        State<Message, String> creationState;
        Optional<Conversation> conversationToLink = conversationReader.getOneByPublicId(messageSendNew.conversationPublicId());
        if (conversationToLink.isPresent()) {
        	result.server.messengerbackend.messaging.domain.message.aggregate.Message messageSaved = messageRepository.save(newMessage, sender, conversationToLink.get());
            messageChangeNotifier.send(newMessage, conversationToLink.get().getMembers().stream()
                    .map(User::getUserPublicId).toList());
            creationState = State.<Message, String>builder().forSuccess(messageSaved);
        } else {
            creationState = State.<Message, String>builder().forError(
                    String.format("Unable to find the conversation to link the message with the " +
                            "following publicId %s", messageSendNew.conversationPublicId().value())
            );
        }
        return creationState;
    }

}
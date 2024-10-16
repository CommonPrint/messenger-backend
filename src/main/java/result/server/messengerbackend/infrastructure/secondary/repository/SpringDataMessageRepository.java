package result.server.messengerbackend.infrastructure.secondary.repository;

import org.springframework.stereotype.Repository;

import result.server.messengerbackend.infrastructure.secondary.entity.ConversationEntity;
import result.server.messengerbackend.infrastructure.secondary.entity.MessageEntity;
import result.server.messengerbackend.infrastructure.secondary.entity.UserEntity;
import result.server.messengerbackend.messaging.domain.message.aggregate.Conversation;
import result.server.messengerbackend.messaging.domain.message.aggregate.Message;
import result.server.messengerbackend.messaging.domain.message.aggregate.MessageSendNew;
import result.server.messengerbackend.messaging.domain.message.repository.MessageRepository;
import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.message.vo.MessageSendState;
import result.server.messengerbackend.messaging.domain.message.vo.MessageType;
import result.server.messengerbackend.messaging.domain.user.aggregate.User;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;

import java.util.List;


@Repository
public class SpringDataMessageRepository implements MessageRepository {

    private final JpaMessageRepository jpaMessageRepository;
    private final JpaMessageBinaryContent jpaMessageBinaryContent;

    public SpringDataMessageRepository(JpaMessageRepository jpaMessageRepository, JpaMessageBinaryContent jpaMessageBinaryContent) {
        this.jpaMessageRepository = jpaMessageRepository;
        this.jpaMessageBinaryContent = jpaMessageBinaryContent;
    }

    @Override
    public Message save(Message message, User sender, Conversation conversation) {
        MessageEntity messageEntity = MessageEntity.from(message);
        messageEntity.setSender(UserEntity.from(sender));
        messageEntity.setConversation(ConversationEntity.from(conversation));

        if (message.getContent().type() != MessageType.TEXT) {
            jpaMessageBinaryContent.save(messageEntity.getContentBinary());
        }

        MessageEntity messageSaved = jpaMessageRepository.save(messageEntity);
        return MessageEntity.toDomain(messageSaved);
    }

    @Override
    public int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state) {
        return jpaMessageRepository.updateMessageSendState(conversationPublicId.value(), userPublicId.value(), state);
    }

    @Override
    public List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId) {
        return jpaMessageRepository.findMessageToUpdateSendState(conversationPublicId.value(), userPublicId.value())
                .stream().map(MessageEntity::toDomain).toList();
    }

	@Override
	public int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId,
			MessageSendNew state) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
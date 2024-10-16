package result.server.messengerbackend.messaging.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import result.server.messengerbackend.messaging.domain.message.aggregate.Message;
import result.server.messengerbackend.messaging.domain.message.aggregate.MessageSendNew;
import result.server.messengerbackend.messaging.domain.message.repository.ConversationRepository;
import result.server.messengerbackend.messaging.domain.message.repository.MessageRepository;
import result.server.messengerbackend.messaging.domain.message.service.ConversationReader;
import result.server.messengerbackend.messaging.domain.message.service.MessageChangeNotifier;
import result.server.messengerbackend.messaging.domain.message.service.MessageCreator;
import result.server.messengerbackend.messaging.domain.user.aggregate.User;
import result.server.messengerbackend.messaging.domain.user.repository.UserRepository;
import result.server.messengerbackend.messaging.domain.user.service.UserReader;
import result.server.messengerbackend.messaging.domain.user.vo.UserEmail;
import result.server.messengerbackend.shared.authentication.application.AuthenticatedUser;
import result.server.messengerbackend.shared.service.State;

import java.util.Optional;


@Service
public class MessageApplicationService {

    private final MessageCreator messageCreator;
    private final UserReader userReader;

    public MessageApplicationService(MessageRepository messageRepository, UserRepository userRepository,
                                     ConversationRepository conversationRepository, MessageChangeNotifier messageChangeNotifier) {
        ConversationReader conversationReader = new ConversationReader(conversationRepository);
        this.messageCreator = new MessageCreator(messageRepository, messageChangeNotifier, conversationReader);
        this.userReader = new UserReader(userRepository);
    }

    @Transactional
    public State<Message, String> send(MessageSendNew messageSendNew) {
        State<Message, String> creationState;
        Optional<User> connectedUser = this.userReader.getByEmail(new UserEmail(AuthenticatedUser.username().username()));
        if(connectedUser.isPresent()) {
            creationState = this.messageCreator.create(messageSendNew, connectedUser.get());
        } else {
            creationState = State.<Message, String>builder()
                    .forError(String.format("Error retrieving user information inside the DB : %s", AuthenticatedUser.username().username()));
        }
        return creationState;
    }
}
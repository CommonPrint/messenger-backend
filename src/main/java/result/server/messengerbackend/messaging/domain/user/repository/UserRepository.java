package result.server.messengerbackend.messaging.domain.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.user.aggregate.User;
import result.server.messengerbackend.messaging.domain.user.vo.UserEmail;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository {

    void save(User user);

    Optional<User> get(UserPublicId userPublicId);

    Optional<User> getOneByEmail(UserEmail userEmail);

    List<User> getByPublicIds(Set<UserPublicId> userPublicIds);

    Page<User> search(Pageable pageable, String query);

    int updateLastSeenByPublicId(UserPublicId userPublicId, Instant lastSeen);

    List<User> getRecipientByConversationIdExcludingReader(ConversationPublicId conversationPublicId, UserPublicId readerPublicId);

    Optional<User> getOneByPublicId(UserPublicId userPublicId);

}

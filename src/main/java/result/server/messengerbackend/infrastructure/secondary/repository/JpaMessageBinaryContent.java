package result.server.messengerbackend.infrastructure.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import result.server.messengerbackend.infrastructure.secondary.entity.MessageContentBinaryEntity;

public interface JpaMessageBinaryContent extends JpaRepository<MessageContentBinaryEntity, Long> {
}
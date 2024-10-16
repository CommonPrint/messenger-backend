package result.server.messengerbackend.messaging.domain.message.aggregate;


import org.jilt.Builder;

import result.server.messengerbackend.messaging.domain.message.vo.ConversationPublicId;
import result.server.messengerbackend.messaging.domain.message.vo.MessageContent;
import result.server.messengerbackend.messaging.domain.message.vo.MessagePublicId;
import result.server.messengerbackend.messaging.domain.message.vo.MessageSendState;
import result.server.messengerbackend.messaging.domain.message.vo.MessageSentTime;
import result.server.messengerbackend.messaging.domain.user.vo.UserPublicId;
import result.server.messengerbackend.shared.error.domain.Assert;


@Builder
public class Message {

    private final MessageSentTime sentTime;

    private final MessageContent content;

    private final MessageSendState sendState;

    private final MessagePublicId publicId;

    private final UserPublicId sender;

    private final ConversationPublicId conversationId;


    public Message(MessageSentTime sentTime, MessageContent content,
                   MessageSendState sendState, MessagePublicId publicId,
                   UserPublicId sender, ConversationPublicId conversationId) {
        assertMandatoryFields(sentTime, content, sendState, publicId, sender, conversationId);
        this.sentTime = sentTime;
        this.content = content;
        this.sendState = sendState;
        this.publicId = publicId;
        this.sender = sender;
        this.conversationId = conversationId;
    }

    private void assertMandatoryFields(MessageSentTime sentTime,
                                       MessageContent content,
                                       MessageSendState state,
                                       MessagePublicId publicId,
                                       UserPublicId sender,
                                       ConversationPublicId conversationId) {
        Assert.notNull("sentTime", sentTime);
        Assert.notNull("content", content);
        Assert.notNull("state", state);
        Assert.notNull("publicId", publicId);
        Assert.notNull("sender", sender);
        Assert.notNull("conversationId", conversationId);
    }

    public MessageSentTime getSentTime() {
        return sentTime;
    }

    public MessageContent getContent() {
        return content;
    }

    public MessageSendState getSendState() {
        return sendState;
    }

    public MessagePublicId getPublicId() {
        return publicId;
    }

    public UserPublicId getSender() {
        return sender;
    }

    public ConversationPublicId getConversationId() {
        return conversationId;
    }
}
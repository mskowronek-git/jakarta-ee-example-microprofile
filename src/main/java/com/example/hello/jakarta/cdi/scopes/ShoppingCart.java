package com.example.hello.jakarta.cdi.scopes;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;

import java.io.Serializable;

@ConversationScoped
public class ShoppingCart implements Serializable {

    /* The conversation is the main part of the @ConversationScoped beans
    A conversation can be started to create a new bean instance, therefore having an id
    that can be reused across several calls
    A conversation can be ended to dispose the bean
     */
    @Inject
    private Conversation conversation;

    private Long itemNumber;

    @PostConstruct
    public void init() {
        this.itemNumber = 0L;
    }

    public void increaseItemNumber() {
        this.itemNumber++;
    }

    public Long getItemNumber() {
        return this.itemNumber;
    }

    public void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public String getConversationId() {
        return conversation.getId();
    }
}


package com.example.hello.jakarta.cdi.scopes;

import com.example.hello.jakarta.util.Logged;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/cdi")
@Singleton
public class CdiController {

    public static final String CONVERSATION_ID = "conversationId";
    public static final String ITEM_NUMBER = "itemNumber";
    public static final String MESSAGE = "message";

    @Inject
    private HelloWorldRequest helloWorldRequest;

    @Inject
    private HelloWorldSession helloWorldSession;

    @Inject
    private ShoppingCart shoppingCart;

    @GET
    @Path("/request")
    @Produces("application/json")
    public HelloWorldRequest getHelloWorldRequest() {
        return helloWorldRequest;
    }

    @GET
    @Path("/session")
    @Produces("application/json")
    public HelloWorldSession getHelloWorldSession() {
        return helloWorldSession;
    }

    @GET
    @Path("/conversation/start")
    @Produces("application/json")
    @Logged
    public Response startConversation() {
        shoppingCart.startConversation();

        Map<String, Object> response = new HashMap<>();
        response.put(CONVERSATION_ID, shoppingCart.getConversationId());
        response.put(ITEM_NUMBER, shoppingCart.getItemNumber());
        response.put(MESSAGE, "Conversation successfully created");
        return Response.ok(response)
                .build();
    }

    @GET
    @Path("/conversation/add")
    @Produces("application/json")
    public Response addItem(@QueryParam("cid") String cid) {
        /*the cid is "magic": it is making the bean being reused in the conversation
        The long-running conversation associated with a request may be propagated to any Servlet request via use of a request parameter named cid
        containing the unique identifier of the conversation. In this case, the application must manage this request parameter.
         */

        if (shoppingCart.getConversationId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        shoppingCart.increaseItemNumber();
        Map<String, Object> response = new HashMap<>();
        response.put(CONVERSATION_ID, shoppingCart.getConversationId());
        response.put(ITEM_NUMBER, shoppingCart.getItemNumber());
        response.put(MESSAGE, "Item added to cart");
        return Response.ok(response)
                .build();
    }

    @GET
    @Path("/conversation/end")
    @Produces("application/json")
    public Response endConversation(@QueryParam("cid") String cid) {
        shoppingCart.endConversation();

        Map<String, Object> response = new HashMap<>();
        response.put(CONVERSATION_ID, shoppingCart.getConversationId());
        response.put(ITEM_NUMBER, shoppingCart.getItemNumber());
        response.put(MESSAGE, "conversation ended");
        return Response.ok(response)
                .build();
    }


}

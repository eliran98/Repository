package com.prectise.t;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.prectise.model.bean.Message;
import com.prectise.model.dao.FactoryDAO;
import com.prectise.model.dao.FactoryDAO.eDB;
import com.prectise.model.dao.logic.IMsgDAO;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo/{userName}") 
public class MyWebSocketHandler {
	
	private static Hashtable<String,Session> mapSessions = new Hashtable<String,Session>();
	
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(@PathParam("userName") String userName,Session session){
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            session.getBasicRemote().sendText(userName + " is join");
            mapSessions.put(session.getId(),session);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        String sessionId = null;
        Session openSession = null;
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
        	Set<String> sessionIds = mapSessions.keySet();
        	Iterator<String> iterator = sessionIds.iterator();
        	while(iterator !=null && iterator.hasNext()){
        		sessionId = iterator.next();
        		openSession = mapSessions.get(sessionId);
        		openSession.getBasicRemote().sendText(message);
        	}
        	saveMsg(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
       
        try {
        	 System.out.println("Session " +session.getId()+" has ended");
			 session.close();
			 mapSessions.remove(session.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    
    private void saveMsg(String messageText){
    	if(messageText == null){
    		return;
    	}
    	String userName = messageText.split(":")[0];
    	Message message = new Message(messageText,userName,new Date());
    	
    	FactoryDAO factoryDAO = FactoryDAO.getFactory(eDB.MySQL);
    	IMsgDAO MsgDAO = factoryDAO.getMsgDAO();
    	MsgDAO.insertMessage(message);
    }
}
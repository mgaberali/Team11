/***********************************************************************
 * Module:  ChatController.java
 * Author:  Mohamed
 * Purpose: Defines the Class ChatController
 ***********************************************************************/

package controller;

import entity.UserEntity;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import services.ChatServiceInterface;


public class ChatController extends UnicastRemoteObject implements ChatControllerInterface, ChatServiceInterface {
 
    private ServerControllerInterface serverController;
   
   /** @param ServerController
   **/
    ChatController(ServerControllerInterface controller) throws RemoteException{
      this.serverController = controller;
   }

    @Override
    public RemoteObject getFriendRemoteObject(String friendName) throws RemoteException {
        
        return serverController.getUserRemoteObject(friendName);
    }

    @Override
    public Vector<RemoteObject> getGroupRemoteObjects(Vector<String> groupUsers) throws RemoteException {
        
        Vector<RemoteObject> group = new Vector<>();
        
        for(String username : groupUsers){
            
            RemoteObject userRemoteObject = serverController.getUserRemoteObject(username);
            group.addElement(userRemoteObject);
        }
            
        
        return group;
    }
}

package listeners;

import controllers.ConnectionController;
import java.io.Serializable;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCounter implements HttpSessionListener, Serializable {
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("session created: " + event.getSession().getId());
        
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("session destroyed: " + event.getSession().getId());
        ConnectionController connect = null;
        connect = (ConnectionController) event.getSession().getAttribute("connexion");
        if (connect != null && connect.getUser() != null) {
            connect.setIsLoggedIn(Boolean.FALSE);
            System.out.println("user sortie");
        }
    }
    
}

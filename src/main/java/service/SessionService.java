package service;
import jakarta.servlet.http.HttpSession;
import model.Client;

public interface SessionService {
    public void setClient(HttpSession session, Client client);

    public Client getClient(HttpSession session);

    public void invalidateSession(HttpSession session);
}

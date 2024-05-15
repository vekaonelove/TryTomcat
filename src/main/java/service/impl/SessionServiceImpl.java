package service.impl;

import jakarta.servlet.http.HttpSession;
import model.Client;
import service.SessionService;

public class SessionServiceImpl implements SessionService {

    @Override
    public void setClient(HttpSession session, Client client) {
        session.setAttribute("client", client);
    }

    @Override
    public Client getClient(HttpSession session) {
        return (Client) session.getAttribute("client");
    }

    @Override
    public void invalidateSession(HttpSession session) {
        session.invalidate();
    }
}
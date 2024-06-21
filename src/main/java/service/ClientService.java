package service;

import model.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);
    Client extractClientByUsernameAndPassword(String username, String password);
    void updateClient(Client client);
    void deleteClient(int id);
    public boolean checkAdmin(String username, String password);
    String extractRole(String username);
    List<Client> extractAllClients();

}

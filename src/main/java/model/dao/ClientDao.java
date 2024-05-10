package model.dao;

import model.Client;

import java.util.List;

public interface ClientDao {
    void addClient(Client client);
    List<Client> getAllClients();
    Client getClientByUsernameAndPassword(String username, String password);
    }
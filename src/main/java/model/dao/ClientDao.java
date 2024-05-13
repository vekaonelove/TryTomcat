package model.dao;

import model.Client;

import java.util.List;

public interface ClientDao {
    void addClient(Client client);
    List<Client> getAllClients();
    Client getClientByUsernameAndPassword(String username, String password);
    void updateClientUsername(int Id, String username);
    void updateClientFirstName(int Id, String first_name);
    void updateClientLastName(int Id, String last_name);
    void updateClientPassword(int Id, String password);
    void updateClientEmail(int clientId, String email);
    void deleteClient(int id);



}
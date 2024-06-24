package service;

import model.Curator;

import java.util.List;

public interface CuratorService {
    void addCurator(Curator curator);
    void updateCurator(Curator curator);
    void deleteCurator(int id);
    public List<Curator> getAllCurators();
    Curator extractCuratorById(int id);

}
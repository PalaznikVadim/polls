package com.netcracker.edu.fapi.models.viewModels;

public class ClonePoll {
    private int id;
    private int idUser;

    public ClonePoll() {
    }

    public ClonePoll(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

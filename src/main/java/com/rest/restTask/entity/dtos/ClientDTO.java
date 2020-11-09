package com.rest.restTask.entity.dtos;


import com.rest.restTask.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private long id;
    private String name;
    private String surname;
    private boolean statusActive;
    private long coins;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.surname = client.getSurname();
        this.name = client.getName();
        this.statusActive = client.isActive();
        this.coins = client.getCoins();
    }
}

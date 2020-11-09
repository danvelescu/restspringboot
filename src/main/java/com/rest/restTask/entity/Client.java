package com.rest.restTask.entity;




import com.rest.restTask.entity.dtos.ClientDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_client")
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "status")
    private boolean isActive;

    @Column(name = "coins")
    private long coins;

    public Client(ClientDTO client){
        this.surname = client.getSurname();
        this.name = client.getName();
        this.isActive = client.isStatusActive();
        this.coins = client.getCoins();
    }

}

package com.rest.restTask.service;
import com.rest.restTask.daos.ClientDAO;
import com.rest.restTask.entity.Client;
import com.rest.restTask.entity.dtos.ClientDTO;
import com.rest.restTask.exceptionHandler.CustomClientServiceException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Data
@RequiredArgsConstructor
public class ClientService {

    private final ClientDAO clientDAO;

    public List<ClientDTO> getAllClients(){
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientDAO.findAll().forEach(client -> clientDTOS.add(new ClientDTO(client)));
        return clientDTOS;
    }

    public ClientDTO getClientById(Long id) throws CustomClientServiceException {
        Optional<Client> optional  = clientDAO.findById(id);
        if(optional.isEmpty())
            throw new CustomClientServiceException("Client not found", HttpStatus.NOT_FOUND);
        else return new ClientDTO(optional.get());
    }

    public void addClient(ClientDTO clientDTO) throws CustomClientServiceException {
        try {
            clientDAO.save(new Client(clientDTO));
        }catch (Exception ex){
            throw new CustomClientServiceException("Data not saved",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteClient(Long id) throws CustomClientServiceException {
        try {
            clientDAO.deleteById(id);
        }catch (Exception e){
            throw new CustomClientServiceException("Invalid data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateClient(ClientDTO clientDTO) throws CustomClientServiceException {
        Optional<Client> client = clientDAO.findById(clientDTO.getId());
        if (!client.get().isActive()){
            throw new CustomClientServiceException("User is not active",HttpStatus.BAD_REQUEST);
        }
        Client clientToUpdate = client.get();
        clientToUpdate.setCoins(clientDTO.getCoins());
        clientToUpdate.setName(clientDTO.getName());
        clientToUpdate.setSurname(clientDTO.getSurname());
        clientToUpdate.setActive(clientDTO.isStatusActive());

        try {
            clientDAO.save(clientToUpdate);
        }catch (Exception e){
            throw new CustomClientServiceException("Invalid data",HttpStatus.BAD_REQUEST);
        }
    }

    public void addCoins(Long id) throws CustomClientServiceException {
        try{
            Optional<Client>client = clientDAO.findById(id);

            if (!client.get().isActive()){
                throw new CustomClientServiceException("User is not active",HttpStatus.BAD_REQUEST);
            }
            Client client1 = client.get();
            client1.setCoins(client1.getCoins()+10);
            clientDAO.save(client1);
        }catch (Exception e){
            throw new CustomClientServiceException("Coins dont added",HttpStatus.NOT_FOUND);
        }
    }
}

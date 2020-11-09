package com.rest.restTask.restControl;

import com.rest.restTask.entity.dtos.ClientDTO;
import com.rest.restTask.exceptionHandler.CustomClientServiceException;
import com.rest.restTask.service.ClientService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("rest/clients")
public class RestClient {
    private final ClientService clientService;


    @GetMapping("/")
    public ResponseEntity<Object> clients() throws Exception {

       return new ResponseEntity<>( clientService.getAllClients(),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody ClientDTO clientDTO) throws Exception {

        try{
            clientService.addClient(clientDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomClientServiceException("Data not inserted",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id ) throws CustomClientServiceException {
        clientService.deleteClient(id);
        return new ResponseEntity<>("Client deleted",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> getClient(@PathVariable Long id) throws CustomClientServiceException {
       return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id,@RequestBody ClientDTO clientDTO) throws CustomClientServiceException {
        clientDTO.setId(id);
        clientService.updateClient(clientDTO);
        return new ResponseEntity<>("Client updated",HttpStatus.OK);
    }

    @PutMapping("/addcoins/{id}")
    public ResponseEntity<Object> updateCoins(@PathVariable Long id) throws CustomClientServiceException {
        clientService.addCoins(id);
        return new ResponseEntity<>("Coins added",HttpStatus.OK);
    }

}

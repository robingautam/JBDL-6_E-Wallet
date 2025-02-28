package org.gfg.TransactionService.controller;

import jakarta.websocket.server.PathParam;
import org.gfg.TransactionService.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-service")
public class TransactionController {


    @Autowired
    TransactionService transactionService;


    @GetMapping("/initiate/transaction")
    public String initiateTransaction(@PathParam("receiver") String receiver,
                                      @PathParam("amount") String amount,
                                      @PathParam("purpose") String purpose){


      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String sender = userDetails.getUsername();

      return transactionService.initiateTransaction(sender,receiver,amount,purpose);

    }
}

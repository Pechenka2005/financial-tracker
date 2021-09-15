package sirius.tinkoff.financialTracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sirius.tinkoff.financialTracker.models.dto.CreateTransactionDTO;
import sirius.tinkoff.financialTracker.models.dto.TransactionDTO;
import sirius.tinkoff.financialTracker.services.TransactionService;

@RequestMapping("/transactions")
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO createTransaction(@RequestBody CreateTransactionDTO dto) {
        return transactionService.createTransaction(dto);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDTO updateTransaction(@PathVariable Long id, @RequestBody CreateTransactionDTO dto) {
        return transactionService.updateTransaction(id, dto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}

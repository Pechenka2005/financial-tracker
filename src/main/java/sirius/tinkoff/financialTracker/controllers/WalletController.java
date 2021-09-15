package sirius.tinkoff.financialTracker.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.WalletSum;
import sirius.tinkoff.financialTracker.models.dto.CreateWalletDTO;
import sirius.tinkoff.financialTracker.models.dto.TransactionDTO;
import sirius.tinkoff.financialTracker.models.dto.WalletDTO;
import sirius.tinkoff.financialTracker.services.TransactionService;
import sirius.tinkoff.financialTracker.services.WalletService;

import java.util.List;

@RequestMapping("/wallets")
@RequiredArgsConstructor
@RestController
public class WalletController {
    private final WalletService walletService;
    private final TransactionService transactionService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletDTO getWallet(@PathVariable Long id) {
        return walletService.getWallet(id);
    }

    @GetMapping(value = "/{id}/listTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDTO> listTransactions(@PathVariable Long id) {
        return transactionService.listTransactions(id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WalletDTO> listWallets(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return walletService.listWallets(user);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletDTO createWallet(@RequestBody CreateWalletDTO dto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return walletService.createWallet(dto, user);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletDTO updateWallet(@RequestBody CreateWalletDTO dto, @PathVariable Long id) {
        return walletService.updateWallet(dto, id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
    }

    @GetMapping(value = "/{id}/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletSum walletSum(@PathVariable Long id) {
        return walletService.walletSum(id);
    }

}

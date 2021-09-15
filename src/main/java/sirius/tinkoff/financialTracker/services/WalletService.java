package sirius.tinkoff.financialTracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sirius.tinkoff.financialTracker.converters.CreateWalletDTOToWalletConverter;
import sirius.tinkoff.financialTracker.converters.WalletToWalletDTOConverter;
import sirius.tinkoff.financialTracker.exceptions.EntityNotFoundException;
import sirius.tinkoff.financialTracker.exceptions.ValidationException;
import sirius.tinkoff.financialTracker.models.Transaction;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.WalletSum;
import sirius.tinkoff.financialTracker.models.dto.CreateWalletDTO;
import sirius.tinkoff.financialTracker.models.dto.TransactionDTO;
import sirius.tinkoff.financialTracker.models.dto.WalletDTO;
import sirius.tinkoff.financialTracker.repository.TransactionRepository;
import sirius.tinkoff.financialTracker.repository.WalletRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    private final CreateWalletDTOToWalletConverter createWalletConverter;
    private final WalletToWalletDTOConverter walletConverter = new WalletToWalletDTOConverter();


    public WalletDTO getWallet(Long id) {
        var dto = walletConverter.convert(
                walletRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Wallet с таким id не был найден"))
        );
        return dto.setBalance(sumToBalance(walletSum(id)).intValue());
    }

    private BigDecimal sumToBalance(WalletSum sum) {
        return sum.getIncome().subtract(sum.getExpenses());
    }

    public List<WalletDTO> listWallets(User user) {
        return walletRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(walletConverter::convert)
                .map(x -> x.setBalance(sumToBalance(walletSum(x.getId())).intValue()))
                .collect(Collectors.toList());
    }

    public WalletDTO createWallet(CreateWalletDTO dto, User user) {
        var wallet = createWalletConverter.convert(dto).orElseThrow(() -> new ValidationException("Невалидное поле currency"));
        wallet.setUserId(user.getId());
        return walletConverter.convert(walletRepository.save(wallet));
    }

    public WalletDTO updateWallet(CreateWalletDTO dto, Long walletId) {
        var wallet = createWalletConverter.convert(dto).orElseThrow(() -> new ValidationException("Невалидное поле currency"));
        wallet.setId(walletId);
        var currWallet = walletRepository.findById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet с таким id не был найден"));
        wallet.setUserId(currWallet.getUserId());
        var res = walletConverter.convert(walletRepository.save(wallet));
        res.setBalance(sumToBalance(walletSum(walletId)).intValue());
        return res;
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public WalletSum walletSum(Long id) {
        final var wallet = walletRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Wallet с таким id не был найден"));
        final var transactions = transactionRepository.findAllByWalletId(id);
        final var income = transactions
                .stream()
                .filter(Transaction::getIsIncome)
                .map(x -> x.getValue().multiply(x.getCurrency().getCoeff()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(wallet.getCurrency().getCoeff(), RoundingMode.FLOOR);
        final var expenses = transactions
                .stream()
                .filter(x -> !x.getIsIncome())
                .map(x -> x.getValue().multiply(x.getCurrency().getCoeff()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(wallet.getCurrency().getCoeff(), RoundingMode.FLOOR);
        return new WalletSum()
                .setExpenses(expenses)
                .setIncome(income);
    }
}

package sirius.tinkoff.financialTracker.services;

import org.springframework.stereotype.Service;
import sirius.tinkoff.financialTracker.converters.CreateTransactionDTOToTransactionConverter;
import sirius.tinkoff.financialTracker.converters.TransactionToTransactionDTOConverter;
import sirius.tinkoff.financialTracker.exceptions.EntityNotFoundException;
import sirius.tinkoff.financialTracker.exceptions.ValidationException;
import sirius.tinkoff.financialTracker.models.dto.CreateTransactionDTO;
import sirius.tinkoff.financialTracker.models.dto.TransactionDTO;
import sirius.tinkoff.financialTracker.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final TransactionToTransactionDTOConverter transactionConverter =
            new TransactionToTransactionDTOConverter();
    private  final CreateTransactionDTOToTransactionConverter createTransactionConverter;

    public TransactionService(TransactionRepository transactionRepository,
                              CreateTransactionDTOToTransactionConverter createTransactionConverter) {
        this.transactionRepository = transactionRepository;
        this.createTransactionConverter = createTransactionConverter;
    }

    public TransactionDTO getTransaction(Long id) {
        return transactionConverter.convert(
                transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transaction с таким id не существует"))
        );
    }

    public List<TransactionDTO> listTransactions(Long walletId) {
        return transactionRepository
                .findAllByWalletId(walletId)
                .stream()
                .map(transactionConverter::convert)
                .collect(Collectors.toList());
    }

    public TransactionDTO createTransaction(CreateTransactionDTO dto) {
        var transaction = createTransactionConverter
                .convert(dto)
                .orElseThrow(() -> new ValidationException(""));
        var res = transactionRepository.save(transaction);
        return transactionConverter.convert(res);
    }

    public TransactionDTO updateTransaction(Long transactionId, CreateTransactionDTO dto) {
        var transaction = createTransactionConverter
                .convert(dto)
                .orElseThrow(() -> new ValidationException(""))
                .setId(transactionId);
        return transactionConverter.convert(transactionRepository.save(transaction));
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}

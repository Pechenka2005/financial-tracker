package sirius.tinkoff.financialTracker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import sirius.tinkoff.financialTracker.converters.CreateTransactionDTOToTransactionConverter;
import sirius.tinkoff.financialTracker.converters.CreateWalletDTOToWalletConverter;
import sirius.tinkoff.financialTracker.repository.CategoryRepository;
import sirius.tinkoff.financialTracker.repository.CurrencyRepository;
import sirius.tinkoff.financialTracker.repository.WalletRepository;

@Configuration
@ComponentScan("sirius.tinkoff.financialTracker.services")
public class ServiceConfiguration {

    @Bean
    public CreateTransactionDTOToTransactionConverter createTransactionDTOToTransactionConverter(
            WalletRepository walletRepository,
            CurrencyRepository currencyRepository,
            CategoryRepository categoryRepository
    ) {
        return new CreateTransactionDTOToTransactionConverter(walletRepository, currencyRepository, categoryRepository);
    }

    @Bean
    public CreateWalletDTOToWalletConverter createWalletDTOToWalletConverter(CurrencyRepository currencyRepository) {
        return new CreateWalletDTOToWalletConverter(currencyRepository);
    }
}

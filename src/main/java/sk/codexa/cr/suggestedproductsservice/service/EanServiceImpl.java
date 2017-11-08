package sk.codexa.cr.suggestedproductsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.codexa.cr.suggestedproductsservice.model.ReceiptProduct;
import sk.codexa.cr.suggestedproductsservice.repository.EanRepository;
import sk.codexa.cr.suggestedproductsservice.utils.ProductUtils;
import sk.codexa.cr.suggestedproductsservice.model.Ean;

/**
 * Ean service implementation that communicates with ean repository
 * and is converting db products to client receipt specific product objects.
 */
@Service
public class EanServiceImpl implements EanService {

    private final EanRepository eanRepository;

    /**
     * Constructor.
     *
     * @param eanRepository repo for communicating with database
     */
    @Autowired
    public EanServiceImpl(EanRepository eanRepository) {
        this.eanRepository = eanRepository;
    }

    @Override
    public ReceiptProduct getProductByEan(String ean) {
        Ean foundEan = eanRepository.findByValue(ean);

        if (foundEan == null){
            return null;
        }

        return ProductUtils.convertProduct(foundEan.getProduct());
    }
}

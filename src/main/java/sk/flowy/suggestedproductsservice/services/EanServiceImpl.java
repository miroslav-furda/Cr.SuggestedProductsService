package sk.flowy.suggestedproductsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.ReceiptProduct;
import sk.flowy.suggestedproductsservice.repository.EanRepository;
import sk.flowy.suggestedproductsservice.utils.ProductUtils;

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

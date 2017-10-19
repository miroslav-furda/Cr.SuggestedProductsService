package sk.flowy.suggestedproductsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.flowy.suggestedproductsservice.model.Ean;
import sk.flowy.suggestedproductsservice.model.FoundProduct;
import sk.flowy.suggestedproductsservice.repository.EanRepository;
import sk.flowy.suggestedproductsservice.utils.ProductUtils;

@Service
public class EanServiceImpl implements EanService {

    private final EanRepository eanRepository;

    @Autowired
    public EanServiceImpl(EanRepository eanRepository) {
        this.eanRepository = eanRepository;
    }

    @Override
    public FoundProduct getProductByEan(String ean) {
        Ean foundEan = eanRepository.findByValue(ean);

        return ProductUtils.convertProduct(foundEan.getProduct());
    }
}

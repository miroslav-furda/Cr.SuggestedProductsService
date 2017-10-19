package sk.flowy.suggestedproductsservice.services;

import sk.flowy.suggestedproductsservice.model.FoundProduct;

public interface EanService {
    FoundProduct getProductByEan(String ean);
}

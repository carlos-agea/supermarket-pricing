package kata.supermarket;

import java.math.BigDecimal;

public class Product implements SaleableSKU {

    private final BigDecimal pricePerUnit;
    private String code;
    
    public Product(final BigDecimal pricePerUnit, String code) {
        this.pricePerUnit = pricePerUnit;
        this.code = code;
    }
    
    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

	@Override
	public String getSKU() {
		return code;
	}

	@Override
	public BigDecimal unityPrice() {
		return pricePerUnit();
	}
}

package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct implements SaleableSKU {

    private final BigDecimal pricePerKilo;
    private final String code;

    public WeighedProduct(final BigDecimal pricePerKilo, final String code) {
        this.pricePerKilo = pricePerKilo;
        this.code = code;
    }
   
    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

	@Override
	public String getSKU() {
		return code;
	}

	@Override
	public BigDecimal unityPrice() {
		return pricePerKilo();
	}
}

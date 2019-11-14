package kata.supermarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountEngineTest {

	@DisplayName("Calculating 2 double  discounts for different baskets...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountEngineProvidesWith2DoubleDiscounts(String description, String expectedDiscount, Iterable<Item> items) {
		// Given
		// a basket of items
        final Basket basket = new Basket();
        items.forEach(basket::add);
        
        // a discounts A and B
        Buy2Pay1Discount discountA = new Buy2Pay1Discount(new PintOfMilk());
        Buy3Pay2Discount discountB = new Buy3Pay2Discount(new PackOfDigestives());
        DiscountEngine discountEngine = new DiscountEngine();
        discountEngine.addDiscount(discountA);
        discountEngine.addDiscount(discountB);
        
        // Verify
        assertEquals(new BigDecimal(expectedDiscount), discountEngine.calculate(basket.items()));
    }

    static Stream<Arguments> discountEngineProvidesWith2DoubleDiscounts() {
        return Stream.of(
        		fourPintsOfMilkAnd6PacksOfDigestivesDiscounted(),
        		twoPintsOfMilkAnd6PacksOfDigestivesDiscounted()
        );
    }

    private static Arguments fourPintsOfMilkAnd6PacksOfDigestivesDiscounted() {
        return Arguments.of("4 pints of milk and 6 packs of digestives", "4.08",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()
                		, aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()
                		, aPintOfMilk(), aPintOfMilk(),aPintOfMilk(), aPintOfMilk()));
    }
    
    private static Arguments twoPintsOfMilkAnd6PacksOfDigestivesDiscounted() {
        return Arguments.of("2 pints of milk and 6 packs of digestives", "3.59",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()
                		, aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()
                		, aPintOfMilk(), aPintOfMilk()));
    }
    
	@DisplayName("Calculating 2 single discounts for different baskets...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountEngineProvidesWith2Discounts(String description, String expectedDiscount, Iterable<Item> items) {
		// Given
		// a basket of items
        final Basket basket = new Basket();
        items.forEach(basket::add);
        
        // a discounts A and B
        Buy2Pay1Discount discountA = new Buy2Pay1Discount(new PintOfMilk());
        Buy3Pay2Discount discountB = new Buy3Pay2Discount(new PackOfDigestives());
        DiscountEngine discountEngine = new DiscountEngine();
        discountEngine.addDiscount(discountA);
        discountEngine.addDiscount(discountB);
        
        // Verify
        assertEquals(new BigDecimal(expectedDiscount), discountEngine.calculate(basket.items()));
    }

    static Stream<Arguments> discountEngineProvidesWith2Discounts() {
        return Stream.of(
        		twoPintsOfMilkAnd3PacksOfDigestivesDiscounted(),
        		twoPintsOfMilkAnd2PacksOfDigestivesDiscounted()
        );
    }

    private static Arguments twoPintsOfMilkAnd3PacksOfDigestivesDiscounted() {
        return Arguments.of("2 pints of milk and 3 packs of digestives", "2.04",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()));
    }
    
    private static Arguments twoPintsOfMilkAnd2PacksOfDigestivesDiscounted() {
        return Arguments.of("2 pints of milk and 2 packs of digestives", "0.49",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()));
    }
    
    @DisplayName("Calculating 0 discounts for different baskets...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountEngineProvidesWith0Discount(String description, String expectedDiscount, Iterable<Item> items) {
		// Given
		// a basket of items
        final Basket basket = new Basket();
        items.forEach(basket::add);
        
        // no discounts
        DiscountEngine discountEngine = new DiscountEngine();
        
        // Verify
        assertEquals(new BigDecimal(expectedDiscount), discountEngine.calculate(basket.items()));
    }
    
    static Stream<Arguments> discountEngineProvidesWith0Discount() {
        return Stream.of(
        		twoPintsOfMilkAnd3PacksOfDigestivesNoDiscounted(),
        		twoPintsOfMilkAnd2PacksOfDigestivesNoDiscounted()
        );
    }
    
    private static Arguments twoPintsOfMilkAnd3PacksOfDigestivesNoDiscounted() {
        return Arguments.of("2 pints of milk and 3 packs of digestives", "0.00",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()));
    }
    
    private static Arguments twoPintsOfMilkAnd2PacksOfDigestivesNoDiscounted() {
        return Arguments.of("2 pints of milk and 2 packs of digestives", "0.00",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()));
    }
    
    private static Item aPintOfMilk() {
    	PintOfMilk pintOfMilk = new PintOfMilk();
        return new Product(pintOfMilk.unityPrice(), pintOfMilk.getSKU()).oneOf();
    }

    private static Item aPackOfDigestives() {
    	PackOfDigestives packOfDigestives = new PackOfDigestives();
        return new Product(packOfDigestives.unityPrice(), packOfDigestives.getSKU()).oneOf();
    }
}

class PackOfDigestives implements SaleableSKU {
	@Override
	public String getSKU() {
		return "23456";
	}

	@Override
	public BigDecimal unityPrice() {
		return new BigDecimal("1.55");
	}
}

class PintOfMilk implements SaleableSKU {
	@Override
	public String getSKU() {
		return "12345";
	}

	@Override
	public BigDecimal unityPrice() {
		return new BigDecimal("0.49");
	}
}

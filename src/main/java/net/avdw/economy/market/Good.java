package net.avdw.economy.market;

public class Good {
    private Float basePrice;

    Good(Float basePrice) {
        this.basePrice = basePrice;
    }

    Float price(Float quantity) {
        if (quantity == 0) {
            return Float.POSITIVE_INFINITY;
        }

        return basePrice / quantity;
    }

    Float quantity(Float price) {
        if (price == 0) {
            return Float.POSITIVE_INFINITY;
        }

        return basePrice / price;
    }
}

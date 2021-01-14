package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.utils.ProductTypes;

public class Strings extends Product {

    final private double gauge;
    final private long productId;

    private Strings(StringsBuilder builder) {
        super(builder);
        this.gauge = builder.gauge;
        productId = builder.productId;
    }

    public double getGauge() {
        return gauge;
    }

    public long getProductId() {
        return productId;
    }

    public static StringsBuilder builder() {
        return new StringsBuilder();
    }

    public static final class StringsBuilder extends GenericStringsBuilder<StringsBuilder> {

        @Override
        public Strings build() {
            return new Strings(this);
        }
    }

    protected static abstract class GenericStringsBuilder<T extends GenericStringsBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected double gauge;
        protected long productId;

        protected GenericStringsBuilder() {
            super(ProductTypes.STRINGS);
        }

        public T gauge(double gauge) {
            this.gauge = gauge;
            return this.self();
        }

        public T productId(long productId) {
            this.productId = productId;
            return this.self();
        }
    }
}

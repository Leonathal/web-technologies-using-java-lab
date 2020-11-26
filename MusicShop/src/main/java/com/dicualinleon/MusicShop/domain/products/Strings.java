package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Strings extends Product {

    private Strings(StringsBuilder builder) {
        super(builder);
    }

    public static final class StringsBuilder extends GenericStringsBuilder<StringsBuilder> {

        @Override
        public Strings build() {
            return new Strings(this);
        }
    }

    protected static abstract class GenericStringsBuilder<T extends GenericStringsBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected GenericStringsBuilder() {
            super(ProductTypes.STRINGS);
        }
    }
}

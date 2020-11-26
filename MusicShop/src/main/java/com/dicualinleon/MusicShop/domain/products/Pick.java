package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Pick extends Product {

    private Pick(PickBuilder builder) {
        super(builder);
    }

    public static final class PickBuilder extends GenericPickBuilder<PickBuilder> {

        @Override
        public Pick build() {
            return new Pick(this);
        }
    }

    protected static abstract class GenericPickBuilder<T extends GenericPickBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected GenericPickBuilder() {
            super(ProductTypes.PICK);
        }
    }
}

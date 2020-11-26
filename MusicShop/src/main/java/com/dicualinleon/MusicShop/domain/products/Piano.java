package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Piano extends Product {

    //region Constructors
    private Piano(PianoBuilder builder) {
        super(builder);
    }
    //endregion

    //region Builder
    public static final class PianoBuilder extends GenericPianoBuilder<PianoBuilder> {

        @Override
        public Piano build() {
            return new Piano(this);
        }
    }

    protected static abstract class GenericPianoBuilder<T extends GenericPianoBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected GenericPianoBuilder() {
            super(ProductTypes.PIANO);
        }
    }
    //endregion
}

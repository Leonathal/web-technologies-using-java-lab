package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.AbsProduct;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Piano extends AbsProduct {

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
        extends AbsProduct.GenericProductBuilder<T> {

        protected GenericPianoBuilder() {
            super(ProductTypes.PIANO);
        }
    }
    //endregion
}

package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.AbsProduct;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Drums extends AbsProduct {

    //region Constructors
    private Drums(DrumsBuilder builder) {
        super(builder);
    }
    //endregion

    //region Builder
    public static final class DrumsBuilder extends GenericDrumsBuilder<DrumsBuilder> {

        @Override
        public Drums build() {
            return new Drums(this);
        }
    }

    protected static abstract class GenericDrumsBuilder<T extends GenericDrumsBuilder<T>>
        extends AbsProduct.GenericProductBuilder<T> {

        protected GenericDrumsBuilder() {
            super(ProductTypes.DRUMS);
        }
    }
    //endregion
}

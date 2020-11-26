package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Bass extends Product {

    //region Constructors
    private Bass(BassBuilder builder) {
        super(builder);
    }
    //endregion

    //region Builder
    public static final class BassBuilder extends GenericBassBuilder<BassBuilder> {

        @Override
        public Bass build() {
            return new Bass(this);
        }
    }

    protected static abstract class GenericBassBuilder<T extends GenericBassBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected GenericBassBuilder() {
            super(ProductTypes.BASS);
        }
    }
    //endregion
}

package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import com.dicualinleon.MusicShop.utils.GuitarTypes;

public class Guitar extends Product {

    //region Data members
    final private GuitarTypes guitarType;
    //endregion

    //region Constructor
    private Guitar(GuitarBuilder builder) {
        super(builder);
        guitarType = builder.guitarType;
    }
    //endregion

    public GuitarTypes guitarType() {
        return guitarType;
    }

    //region Builder
    public static GuitarBuilder builder() {
        return new GuitarBuilder();
    }

    public final static class GuitarBuilder extends GenericGuitarBuilder<GuitarBuilder> {

        @Override
        public Guitar build() {
            return new Guitar(this);
        }
    }

    protected static abstract class GenericGuitarBuilder<T extends GenericGuitarBuilder<T>>
        extends Product.GenericProductBuilder<T> {

        protected GuitarTypes guitarType;

        protected GenericGuitarBuilder() {
            super(ProductTypes.GUITAR);
        }

        public T guitarType(GuitarTypes guitarType) {
            this.guitarType = guitarType;
            return this.self();
        }
    }
    //endregion
}

package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import com.dicualinleon.MusicShop.utils.GuitarTypes;

public class Guitar extends Product {

    //region Data members
    final private GuitarTypes types;
    //endregion

    //region Constructor
    private Guitar(GuitarBuilder builder) {
        super(builder);
        types = builder.types;
    }
    //endregion

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

        protected GuitarTypes types;

        protected GenericGuitarBuilder() {
            super(ProductTypes.GUITAR);
        }

        public T type(GuitarTypes types) {
            this.types = types;
            return this.self();
        }
    }
    //endregion
}

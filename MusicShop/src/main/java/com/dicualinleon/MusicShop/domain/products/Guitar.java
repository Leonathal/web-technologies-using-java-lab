package com.dicualinleon.MusicShop.domain.products;

import com.dicualinleon.MusicShop.domain.products.base.AbsProduct;
import com.dicualinleon.MusicShop.domain.products.base.ProductTypes;

public class Guitar extends AbsProduct {

    //region Data members
    public enum Types {
        CLASSIC,
        ACOUSTIC,
        ELECTRIC
    }

    final private Types types;
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
        extends AbsProduct.GenericProductBuilder<T> {

        protected Types types;

        protected GenericGuitarBuilder() {
            super(ProductTypes.GUITAR);
        }

        public T type(Types types) {
            this.types = types;
            return this.self();
        }
    }
    //endregion
}

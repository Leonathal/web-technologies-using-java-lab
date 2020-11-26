package com.dicualinleon.MusicShop.domain.products.base;

import com.dicualinleon.MusicShop.domain.Producer;
import com.dicualinleon.MusicShop.utils.ProductTypes;

abstract public class Product
    implements IProduct {

    //region Data Members
    final private String name;
    final private double price;
    final private String description;
    final private ProductTypes productTypes;
    final private Producer producer;
    //endregion

    //region Constructors
    protected Product(GenericProductBuilder builder) {
        this.productTypes = builder.productTypes;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.producer = builder.producer;
    }
    //endregion

    //region Getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ProductTypes getType() {
        return productTypes;
    }

    @Override
    public Producer getProducer() {
        return producer;
    }
    //endregion

    // Impossible to offer since we can not instantiate this type of class
    // ProductBuilder class is the extension of the GenericProductBuilder that can be instantiated
//    public static ProductBuilder builder();

    // A class should have two builders (a generic one and an actual one)
    // 1) the generic builder inherits the generic builder of the base in order to access build methods
    // also, the generic builder is protected to not be visible to the outside of the world
    // 2) the actual builder is an extension of the generic one inside the same class that is used to create
    // an object of the type of the host class (look at Guitar for example)
    // A class that can not be instantiated since it is abstract should not have an actual builder
    // 1.1) the generic builder is template for an extension of the generic builder, for example it could
    // receive as a template a GenericGuitarBuilder
    protected static abstract class GenericProductBuilder<T extends GenericProductBuilder<T>> {

        //region Data members
        private String name;
        private double price;
        private String description;
        final private ProductTypes productTypes;
        private Producer producer;
        //endregion

        //region Constructors
        // Constructor with parameter here, but no parameter for the derived classes, since ProductType is provided at
        // construction of the object and can not be changed.
        protected GenericProductBuilder(ProductTypes productTypes) {
            this.productTypes = productTypes;
        }
        //endregion

        //region Setters
        public T name(String name) {
            this.name = name;
            return this.self();
        }

        public T price(double price) {
            this.price = price;
            return this.self();
        }

        public T description(String description) {
            this.description = description;
            return this.self();
        }

        public T producer(Producer producer) {
            this.producer = producer;
            return this.self();
        }
        //endregion

        //region Methods
        // self is used in order to return the same type of builder in every setter.
        // doing so, it will keep the type even if we are setting a property from base
        // (a GuitarGenericBuilder sets the price,
        // we need to return a GuitarGenericBuilder and not a ProductGenericBuilder... thus self helps us out)
        @SuppressWarnings("unchecked") // unchecked cast causes warnings since T is template
        final public T self() {
            return (T) this;
        }

        // impossible to implement since the class is abstract
        // this will be required in the Actual Builder of a class that can be instantiated
        public abstract Product build();
    }
}

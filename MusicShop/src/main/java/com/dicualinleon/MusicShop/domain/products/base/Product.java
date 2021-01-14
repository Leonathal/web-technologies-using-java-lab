package com.dicualinleon.MusicShop.domain.products.base;

import com.dicualinleon.MusicShop.utils.ProductTypes;
public class Product
    implements IProduct {

    //region Data Members
    final private long id;
    final private String name;
    final private double price;
    final private String description;
    final private ProductTypes productTypes;
    final private String producer;
    final private int quantity;
    //endregion

    //region Constructors
    public Product(long id,
                   String name,
                   double price,
                   String description,
                   String producer,
                   ProductTypes productTypes,
                   int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productTypes = productTypes;
        this.producer = producer;
        this.quantity = quantity;
    }

    protected Product(GenericProductBuilder builder) {
        this.id = builder.id;
        this.productTypes = builder.productTypes;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.producer = builder.producer;
        this.quantity = builder.quantity;
    }
    //endregion

    //region Getters
    public long getId() {
        return id;
    }

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
    public String getProducer() {
        return producer;
    }

    @Override
    public int getQuantity() {
        return quantity;
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
        private long id;
        private String name;
        private double price;
        private String description;
        final private ProductTypes productTypes;
        private String producer;
        private int quantity;
        //endregion

        //region Constructors
        // Constructor with parameter here, but no parameter for the derived classes, since ProductType is provided at
        // construction of the object and can not be changed.
        protected GenericProductBuilder(ProductTypes productTypes) {
            this.productTypes = productTypes;
        }
        //endregion

        //region Setters
        public T id(long id) {
            this.id = id;
            return this.self();
        }

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

        public T producer(String producer) {
            this.producer = producer;
            return this.self();
        }

        public T quantity(int quantity) {
            this.quantity = quantity;
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

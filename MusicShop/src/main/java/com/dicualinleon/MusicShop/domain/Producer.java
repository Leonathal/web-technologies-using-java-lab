package com.dicualinleon.MusicShop.domain;

import com.dicualinleon.MusicShop.utils.ProductTypes;

import java.util.List;

public class Producer {

    //region Data members
    final private String name;
    final private String description;
    //endregion

    //region Constructors
    private Producer(ProducerBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }
    //endregion

    //region Getters / Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    //region Methods
    public List<ProductTypes> produces() {
        return null;
    }
    //endregion

    public class ProducerBuilder {
        private String name;
        private String description;

        public ProducerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProducerBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Producer build() {
            return new Producer(this);
        }
    }
}

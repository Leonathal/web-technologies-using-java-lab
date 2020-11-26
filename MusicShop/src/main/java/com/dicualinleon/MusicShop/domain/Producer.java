package com.dicualinleon.MusicShop.domain;

import com.dicualinleon.MusicShop.utils.ProductTypes;

import java.util.List;

public class Producer {

    //region Data members
    final private long id;
    final private String name;
    final private String description;
    //endregion

    //region Constructors
    private Producer(ProducerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }
    //endregion

    //region Getters / Setters
    public long getId() { return id; }

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

    public static ProducerBuilder builder() {
        return new ProducerBuilder();
    }

    public static class ProducerBuilder {
        private long id;
        private String name;
        private String description;

        public ProducerBuilder id(long id) {
            this.id = id;
            return this;
        }

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

package com.dicualinleon.MusicShop.domain;

public class Account {

    final private String username;
    final private String password;
    final private String email;

    private Account(AccountBuilder builder) {
        this.username = builder.username;;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public class AccountBuilder {

        private String username;
        private String password;
        private String email;

        public AccountBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}

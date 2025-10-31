package cg.park.simpleauth.domain.user;

import lombok.Data;

@Data
public class User {

    private String userName;
    private String userId;
    private int userAge;

    private User() {}
    private User(Builder builder) {
        this.userName = builder.userName;
        this.userId = builder.userId;
        this.userAge = builder.userAge;
    }

    public static class Builder {
        private String userName;
        private String userId;
        private int userAge;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userAge(int userAge) {
            this.userAge = userAge;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}

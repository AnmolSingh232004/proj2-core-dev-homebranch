//package org.tasos.proj2.domain.java8Ttests.optionalTests.classesCompositionNullChecks;
//
//import java.util.Optional;
//
//
//public class User {
//    private final Profile profile;
//    public User(Profile profile) {
//        this.profile = profile;
//    }
//    public Optional<Profile> getProfile() {
//        return Optional.ofNullable(profile);
//    }
//    public record Profile(Address address) { }
//    public record Address(String city) { }
//
//    public static void main(String[] args) {
//        // Create objects using records and constructors
//        Address address = new Address("New York");
//        Profile profile = new Profile(null); // Profile without an address
//        User user = new User(profile);
//
//        String city = Optional.of(user)
//          .flatMap(User::getProfile)
//          .map(Profile::address)
//          .map(Address::city)
//          .orElse("Dummy city");
//
//        System.out.println(city); // Output: City is not available
//    }
//}

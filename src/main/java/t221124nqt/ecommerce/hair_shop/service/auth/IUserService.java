package t221124nqt.ecommerce.hair_shop.service.auth;

import java.util.List;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;

public interface IUserService {
    // Fetch
    List<User> fetchAllUsers();

    User fetchUserById(long id);

    // Create
    User createUser(User user);

    // Update
    User updateUser(User user);

    // Delete
    void deleteUser(long id);

    // Find
}

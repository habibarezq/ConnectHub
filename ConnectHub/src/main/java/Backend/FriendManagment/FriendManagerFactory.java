package Backend.FriendManagment;

import Backend.FriendManagment.FriendManager;
import java.util.HashMap;
import java.util.Map;

public class FriendManagerFactory {

    private static final Map<String, FriendManager> cache = new HashMap<>();

    public static FriendManager getFriendManager(String userId) {
        // Reuse existing instances from the cache if available
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }

        // Create a new instance if not cached
        FriendManager friendManager = new FriendManager(userId);
        cache.put(userId, friendManager);

        return friendManager;
    }

    public static void removeFriendManager(String userId) {
        cache.remove(userId); // Remove from the cache if needed
    }
}

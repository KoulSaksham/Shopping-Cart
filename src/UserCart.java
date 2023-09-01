import java.util.HashMap;

interface UserCart {
    void displayOurList();

    void addItemToUserList(int quantity, String itemName);

    void removeItemFromUserList(HashMap<String, String> order, int quantity, String itemName);

    void displayUserList();

    boolean checkItemName(String item);
}


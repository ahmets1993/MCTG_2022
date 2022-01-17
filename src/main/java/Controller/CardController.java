package Controller;

import DataAccess.CardSQL;
import DataAccess.UserSQL;

import java.sql.SQLException;

public class CardController {

    public static void main(String[] args) throws SQLException {
        CardController cardController = new CardController();
        String cards = cardController.HoldingCards(1, 10);
        System.out.println(cards);
    }
    //---------------------------------------------List Card Store------------------------------------------------------

    public String listCardPackages() throws SQLException {
        CardSQL cardSQL = new CardSQL();
        String CardPackage = "";
        String allCardPackages = "";
        for (int i = 1; i <= 20; i++) {
            CardPackage = cardSQL.listCardPackages(i);
            allCardPackages = allCardPackages + "\n\n" + CardPackage;
        }
        return allCardPackages;
    }

    //---------------------------------------------Buy Card Package-----------------------------------------------------

    public int buyCardPackage(String username, int userCoin, int packID) throws SQLException {
        CardSQL cardSQL = new CardSQL();
        UserSQL userSQL = new UserSQL();
        int newCoinValue = cardSQL.addCardPackage(username, userCoin, packID);
        userSQL.updateUserCoin(username, newCoinValue);
        return newCoinValue;
    }

    //---------------------------------------------Show User Card Packages----------------------------------------------

    public String showCardPackages(String username) {
        CardSQL cardSQL = new CardSQL();
        String cardPackage = cardSQL.showUserCardPackages(username);
        if (cardPackage == null) {
            System.out.println("Empty box.");
        }
        return cardPackage;
    }

    //---------------------------------------------Show selected Card Package----------------------------------------------------

    public String selectedPackage(String username, int PackID) {

        CardSQL cardSQL = new CardSQL();
        String packId = cardSQL.selectedPackage(username, PackID);
        if (packId == null) {
            System.out.println("Empty box.");
        }
        return packId;
    }

    //---------------------------------------------Show Card Desk----------------------------------------------------
    public String HoldingCards(int uglyCard, int PackID) {
        CardSQL cardSQL = new CardSQL();
        String packId = cardSQL.holdingCards(uglyCard, PackID);
        if (packId == null) {
            System.out.println("Empty box.");
        }
        return packId;
    }
}

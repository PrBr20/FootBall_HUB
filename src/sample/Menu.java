package sample;


public class Menu {
    public void home(){
        System.out.println("Main Menu:");
        System.out.println("(1) Search Players");
        System.out.println("(2) Search Clubs");
        System.out.println("(3) Add Player");
        System.out.println("(4) Exit System");
    }

    public void submenu1(){
        System.out.println("Player Searching Options:");
        System.out.println("(1) By Player Name");
        System.out.println("(2) By Club and Country");
        System.out.println("(3) By Position");
        System.out.println("(4) By Salary Range");
        System.out.println("(5) Country-wise Player count");
        System.out.println("(6) Back to Main Menu");
    }

    public void submenu2(){
        System.out.println("Club Searching Options:");
        System.out.println("(1) Player(s) with maximum salary with a club ");
        System.out.println("(2) Player(s) with maximum age with a club ");
        System.out.println("(3) Player(s) with maximum height with a club");
        System.out.println("(4) Total yearly salary of a club");
        System.out.println("(5) Back to Main Menu");
    }
}

import java.util.Scanner;

public class App {
    public static Scanner tb = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        
        boolean run = true;
        String [][] seating = new String[20][4];
        for (int i = 0; i < seating.length; i++) {
            seating[i][0]="0";
        }
        while (run) {
            menu(seating);

            
        }
        
    }

    static void menu(String[][] seating){
        String menuRead;
        int menu_choice;
        System.out.println("1. Lägg till en passagerare - boka en obokad plats \n2. Skriv ut vilka lediga platser det finns\n3. Beräkna vinsten av antalet sålda biljetter\n4. Avlsuta programmet");
        menuRead = tb.nextLine();
        try{
           menu_choice = Integer.parseInt(menuRead);
        }
        catch(Exception e){
            System.out.println("Välj en siffra ditt orpon");
            menu_choice = 0;
        }
        if(menu_choice<1 || menu_choice>4){
            System.out.println("Välj val 1, 2, 3 eller 4");
            menu_choice = 0;
        }
       
        switch (menu_choice) {
            case 1:
                System.out.println("lägg till bokning");
                booking(seating);
                break;
            case 2:
                System.out.println("platser:");
                break;
            case 3:
                System.out.println("vinsten:");
                profit(seating);
                break;
            case 4:
                System.out.println("ha det bra:");
                System.exit(0);
        }
    }

    static void booking(String[][] seating){
        boolean valid_booking=false;
        int seat;
        while (valid_booking==false) {
            System.out.println("Skriv in din önskade plats");
            seat = tb.nextInt();
            if( seating[seat][0]== "0"){
                seating[seat][0]= "1";
                System.out.println("Skriv in ditt namn");
                seating[seat][1]= tb.nextLine();
                System.out.println("Skriv in ditt personnummer");
                seating[seat][2]= tb.nextLine();

                valid_booking=true;
            }
            else{
                System.out.println("Denna plats är antingen bokad eller otillgänglig");
            }
        }
    }
    static void profit(String[][] seating){
        
    }
}


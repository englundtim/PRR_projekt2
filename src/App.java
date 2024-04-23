import java.util.Scanner;

public class App {
    public static Scanner tb = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean run = true;
        String [][] seating = new String[20][4];
        seatingDef(seating);
        while (run) {
            menu(seating);
        }
    }
    static void seatingDef(String[][] seating){
        for (int i = 0; i < seating.length; i++) {
            seating[i][0]="0";
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
        if(menu_choice<1 || menu_choice>6){
            System.out.println("Välj val 1, 2, 3 4, 5 eller 6");
            menu_choice = 0;
        }
       
        switch (menu_choice) {
            case 1:
                System.out.println("lägg till bokning");
                createBooking(seating);
                break;
            case 2:
                System.out.println("platser:");
                viewPlacements(seating);
                break;
            case 3:
                System.out.println("vinsten:");
                calcProfit(seating);
                break;
            
            case 4:
                System.out.println("hitta bokning");
                findBooking(seating);
                break;

            case 5:
                System.out.println("avboka");
                cancelBooking(seating);
                break;

            case 6:
                System.out.println("ha det bra:");
                System.exit(0);
        }
    }

    static void createBooking(String[][] seating){
        boolean valid_booking=false;
        int seat;
        while (valid_booking==false) {
            System.out.println("Skriv in din önskade plats");
            seat = tb.nextInt()-1;
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

    static void viewPlacements(String[][] seating){
        String[] seatArrangement = new String[20];
        System.out.println("Bokade platser visas som X");
        for (int i = 0; i < seatArrangement.length; i++) {
            seatArrangement[i] = String.valueOf(i+1);
            if(seating[i][0]=="1"){
                seatArrangement[i]="X";
            }
            if (seatArrangement[i]== "X" && seatArrangement[i].length()<2){
                seatArrangement[i]="X"+seatArrangement[i];
            }
            else if (seatArrangement[i].length()<2) {
                seatArrangement[i]="0"+seatArrangement[i];
            }
            System.out.print("["+seatArrangement[i]+"]");
            if ((i+1) % 2 == 0) {
                System.out.print("   ");
            }
            if((i+1) % 4 == 0){
                System.out.println();
            }

        }
        System.out.println();
        
    }

    static void calcProfit(String[][] seating){
        
    }
    static void findBooking(String[][] seating){

    }
    static void cancelBooking(String[][] seating){

    }

}


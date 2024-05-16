import java.util.Scanner;

public class App {
    public static Scanner tb = new Scanner(System.in);
    

    public static void main(String[] args) throws Exception {
        final int TOTAL_SEATS = 20;
        boolean run = true;
        String [][] seating = new String[TOTAL_SEATS][4];
        seatingDef(seating);
        //Kör programmet, behöver ingen break då man stänger av med system exit
        while (run) {
            menu(seating);
        }
    }
    //Metod som gör alla plaster obokade
    static void seatingDef(String[][] seating){
        for (int i = 0; i < seating.length; i++) {
            seating[i][0]="0";
        }
    }
    //Metoden som skickar ut menyn efter varje metod
    static void menu(String[][] seating){
        final int MENU_CHOICE_COUNT =7;
        String menuRead;
        int menuChoice;
        System.out.println("\n1. Lägg till en passagerare - boka en obokad plats \n2. Skriv ut vilka lediga platser det finns\n3. Beräkna vinsten av antalet sålda biljetter\n4. Hitta en bokning\n5. Avboka en bokning \n6. Sortering\n7. Avlsuta programmet");
        menuRead = tb.nextLine();
        try{
            menuChoice = Integer.parseInt(menuRead);
         }
         catch(Exception e){
             System.out.println("Var vänlig och välj en siffra");
             menuChoice = 0;
         }
         if(menuChoice<1 || menuChoice>MENU_CHOICE_COUNT){
             System.out.println("Välj val 1, 2, 3 4, 5 eller 6");
             menuChoice = 0;
         }
       
        switch (menuChoice) {
            case 1:
                //Anropar metoden för att lägga till en bokning
                System.out.println("\nLägg till bokning");
                createBooking(seating);
                break;
            case 2:
                //Anropar metoden för att visa platser
                System.out.println("\nPlatser:");
                viewPlacements(seating);
                break;
            case 3:
                //Antropar metoden för att beräkna vinster
                System.out.println("\nVinsten:");
                int loopsLeft = seating.length;
                System.out.println(calcProfit(seating, loopsLeft)+"kr");
                break;
            
            case 4:
                //Anropar metoden för att hitta en bokning
                System.out.println("\nHitta bokning");
                findBooking(seating);
                break;

            case 5:
                //Anropar metoden för att avboka en en bokning
                System.out.println("\nAvboka");
                cancelBooking(seating);
                break;

            case 6:
                //Anropar metoden för att sortera bokningar efter ålder
                System.out.println("\nSortering");
                ageSorting(seating);
                break;

            case 7:
                //Avslutar programmet
                System.out.println("\nOkej, ha det bra:");
                System.exit(0);
        }
    }

    static void createBooking(String[][] seating){
        boolean validBooking=false;
        int seat=0;
        boolean wentToCatch = false;
        while (validBooking==false) {
            System.out.println("\nSkriv in din önskade plats");
            //Try-catch sats
            do {
                try {
                    wentToCatch = false;
                    seat = tb.nextInt();
                    seat--;
                    seating[seat][0]= "0";
                } 
                catch (Exception e) {
                    wentToCatch = true;
                    System.out.println("Var vänlig och välj en siffra");
                }
                if (seat<1 || seat>20) {
                    wentToCatch = true;
                    System.out.println("Välj en giltig plats");
                }
            } while (wentToCatch == true);

                
            //Om platsen är bokad eller inte
            if( seating[seat][0]== "0"){
                seating[seat][0]= "1";
                tb.nextLine();
                System.out.println("Skriv in ditt namn");
                //Ser till att ett namn har angetts
                while (true) {
                    seating[seat][1]= tb.nextLine();
                    if (seating[seat][1].length()<1) {
                        System.out.println("Var vänlig och ange korrekt data");
                    }
                    else{
                        break;
                    }
                }
                //Ser till rätt längd samt tal
                System.out.println("Skriv in ditt personnummer   (yyyymmddxxxx)");
                do {
                    try {
                        wentToCatch = false;
                        Long SSnumber = Long.parseLong(tb.nextLine());
                        seating[seat][2] = String.valueOf(SSnumber);
                    } 
                    catch (Exception e) {
                        wentToCatch = true;
                        System.out.println("Var vänlig att skriva in ett tal");
                    }
                    if (seating[seat][2].length()!=12 && wentToCatch==false) {
                        System.out.println("För många/få tecken");
                        wentToCatch=true;
                    }
                } while (wentToCatch == true);

                validBooking=true;

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
            //Ett extra X om ental
            if (seatArrangement[i]== "X" && seatArrangement[i].length()<2){
                seatArrangement[i]="X"+seatArrangement[i];
            }
            //En nolla framför om ental
            else if (seatArrangement[i].length()<2) {
                seatArrangement[i]="0"+seatArrangement[i];
            }
            System.out.print("["+seatArrangement[i]+"]");
            //Korridor mellan varannan plats
            if ((i+1) % 2 == 0) {
                System.out.print("   ");
            }
            //Radbrytning var fjärde säte
            if((i+1) % 4 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    static double calcProfit(String[][] seating, int loopsLeft){
        double profits=0;
        if (loopsLeft==0) {
            return profits;
        }
        //Barnpris för alla födda efter 2006 alltså >18år
        if (seating[loopsLeft-1][0].equals("1")) {
            if (Integer.parseInt(seating[loopsLeft-1][2].substring(0, 4))>2006) {
                profits+= 149.90;
            }
            else{
                profits+=299.99;
            }
            
        }
        //Anropar metoden igen för rekursion
        return profits +calcProfit(seating, loopsLeft-1);
            
        
    }
    static void findBooking(String[][] seating){
        boolean bookingFound=false;
        String choiceRead;
        int findBookingChoice;
        boolean wentToCatch = false;

        System.out.println("Vill du leta efter din bokning med: \n 1. Namn \n 2. Personnummer");
        choiceRead = tb.nextLine();
        //Try-catch sats
        do{
            try{
                findBookingChoice = Integer.parseInt(choiceRead);
            }
            catch(Exception e){
                System.out.println("Var vänlig och välj en siffra");
                findBookingChoice = 0;
            }
            if(findBookingChoice<1 || findBookingChoice>2){
                System.out.println("Välj val 1 eller 2");
                findBookingChoice = 0;
            }
        } while (wentToCatch==true);

        switch (findBookingChoice) {
            case 1:
                System.out.println("Ange namnet för din bokning");
                break;
            case 2:
                System.out.println("Ange personummer för platsen du vill avboka");
                break;
        }
        String searchKey = tb.nextLine();
        //Om en bokning matchar med nyckeln skrivs bokningen ut
        for (int i = 0; i < seating.length; i++) {
            if (searchKey.equals(seating[i][findBookingChoice])) {
                    System.out.println("Bokning hittad på plats: "+(i+1));
                    System.out.println("Namn: "+seating[i][1]);
                    System.out.println("Personnummer: "+seating[i][2]);
                    bookingFound=true;
                    break;
            }  
        }
        if(bookingFound==false){
            System.out.println("Ingen bokning hittades");
        }
    }
    static void cancelBooking(String[][] seating){
        boolean bookingFound= false;
        System.out.println("Skriv in personnummret eller namnet för bokningen");
        String searchKey = tb.nextLine();
        //Om bokning matchar nyckeln nollställs platsen
        for (int i = 0; i < seating.length; i++) {
            if (searchKey.equals(seating[i][2]) || searchKey.equals(seating[i][1])) {
                    System.out.println("Bokning hittad på plats: "+(i+1));
                    seating[i][0] = "0";
                    seating[i][1] = "";
                    seating[i][2] = "";
                    bookingFound=true;
                    System.out.println("Avbokning genomförd");
                    break;
            }  
        }
        if(bookingFound==false){
            System.out.println("Ingen bokning hittades");
        }   

    }
    static void ageSorting(String[][] seating){
        int[] seatingOrder = new int[20];
        int temp = 0;

        // Sparar platsen för bokningar
        int orderIndex = 0;
        for (int i = 0; i < seating.length; i++) {
            if (seating[i][0].equals("1")) {
                seatingOrder[orderIndex++] = i;
            }
        }
        System.out.println();
        // Bubblesortering
        for (int i = 0; i < orderIndex - 1; i++) {
            for (int j = i + 1; j < orderIndex; j++) {
                if (Long.parseLong(seating[seatingOrder[i]][2]) > Long.parseLong(seating[seatingOrder[j]][2])) {
                    temp = seatingOrder[i];
                    seatingOrder[i] = seatingOrder[j];
                    seatingOrder[j] = temp;
                }
            }
        }
        //Tillkallar bokningar i rätt ordning
        for (int i = 0; i < orderIndex; i++) {
            int index = seatingOrder[i];
            System.out.println("Namn: " + seating[index][1] + ", Personnummer: " + seating[index][2] + ", Plats: " + (index + 1));

        }
        
    }

}


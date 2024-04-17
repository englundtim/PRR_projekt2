import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner tb = new Scanner(System.in);
        boolean run = true;
        String menuRead;
        int menu_choice;
        while (run) {
            menu();

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
                    break;
                case 2:
                    System.out.println("platser:");
                    break;
                case 3:
                    System.out.println("platser:");
                    break;
                case 4:
                    System.out.println("ha det bra:");
                    System.exit(0);
            }
        }
        
    }

    static void menu(){
        System.out.println("1. Lägg till en passagerare - boka en obokad plats \n2. Skriv ut vilka lediga platser det finns\n3. Beräkna vinsten av antalet sålda biljetter\n4. Avlsuta programmet");
    }
}

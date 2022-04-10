package helper;

public class Custom {
    // contoh lambda parameter + penggunaan interface haha
    public static int getChoice(choiceMenu menu, int menuRange) {
        Integer choice = 0;
        do {
            menu.printMenu();
            try {
                choice = Integer.parseInt(IO.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Tolong masukkan dengan benar");
            }
        } while (choice < 0 || choice > menuRange);
        return choice;
    }
    
    public interface choiceMenu {
        void printMenu();
    }
    
    public static void enterToContinue() {
    	System.out.println("Enter to continue...");
    	IO.scanner.nextLine();
    }
}

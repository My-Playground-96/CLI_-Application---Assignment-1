import java.util.Scanner;

public class Cli_Assignment1{
private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "👷 Welcome to Smart Banking";
        final String ADD_ACC = "➕ Create New Account";
        final String DEPOSIT = "➕ Deposit";
        final String WITHDRAW = "➕ Withdraw";
        final String TRANSFER = " Transfer";
        final String CHECK_BALANCE = "🖨 Check Account Balance";
        final String REMOVE = "\u274C Remove Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[] customernames = new String[0];
        String[] customerAccnums = new String[0];
        double[] customerDeposits = new double[0];
        String name;
        double initialeDeposite ;

        do{
            System.out.println(CLEAR);
            final String APP_TITLE = screen;
            System.out.println("-".repeat(40));
            System.out.printf("\t%s%s%s\n",COLOR_BLUE_BOLD,APP_TITLE,RESET);
            System.out.println("-".repeat(40));

            switch (screen) {
                case DASHBOARD:
                    System.out.println("\t[1] Create New Account\n\t[2] Deposit\n\t[3] Withdraw\n\t[4] Transfer\n\t[5] Check Account Balance\n\t[6] Remove Account\n\t[7] Exit");
                    System.out.println();
                    System.out.print("Enter option: ");
                    
                    boolean valid ;
                    do{
                        valid = true;
                        int option = SCANNER.nextInt();
                        SCANNER.nextLine();
                        switch (option) {
                            case 1:screen = ADD_ACC;
                                break;
                            case 2:screen = DEPOSIT;
                                break;
                            case 3:screen = WITHDRAW;
                                break;
                            case 4:screen = TRANSFER;
                                break;
                            case 5:screen = CHECK_BALANCE;
                                break;
                            case 6:screen = REMOVE;
                                break;
                            case 7:;System.out.println(CLEAR);
                                System.exit(0);
                                break;
                            default:
                                continue;
                        }
                        
                        if (option<0 || option>7){
                            System.out.printf(ERROR_MSG,"Invalid option!!");
                            valid = false;
                        }

                    }while(!valid);
                break;
                
                case ADD_ACC:
                    String accNumber = String.format("SDB%04d",customernames.length+1 );
                    System.out.printf("\tAccount No: %s\n",accNumber );
                    valid = false;
                    do{
                        valid = true;
                        System.out.print("\tName: ");
                        name = SCANNER.nextLine().strip(); 
                        if(name.isBlank()){
                            System.out.printf(ERROR_MSG,"\tName can't be empty,\n\tEnter name again!\n");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                                System.out.printf(ERROR_MSG,"\tInvalid name!\n");
                                valid = false;
                                break;
                            }
                        }
                        break;
                    }while(!valid);
                    
                    boolean validDepo;
                    do{
                        validDepo = true;
                        System.out.print("\tInitiale Deposit: ");
                        initialeDeposite = SCANNER.nextInt();
                        SCANNER.nextLine();
                        if (initialeDeposite < 5000){
                            System.out.printf(ERROR_MSG,"\tInsufficient Amount,Minimum amount should be RS.5,000.00");
                            validDepo = false;
                            
                        }else
                        break;
        
                    }while(!validDepo);

                    String [] newCustomernames = new String[customernames.length+1];
                    String [] newCustomerAcnums = new String[customerAccnums.length+1];
                    double [] newDeposites = new double[customerDeposits.length+1];

                    for (int i = 0; i < customerDeposits.length; i++) {
                        newCustomernames[i] = customernames[i];
                        newCustomerAcnums[i] = customerAccnums[i];
                        newDeposites[i] = customerDeposits [i];
                    }
                    newCustomerAcnums[newCustomerAcnums.length-1] = accNumber;
                    newCustomernames[newCustomernames.length-1] = name;
                    newDeposites[newDeposites.length-1] = initialeDeposite;

                    customernames = newCustomernames;
                    customerAccnums = newCustomerAcnums;
                    customerDeposits = newDeposites;


                    System.out.printf(SUCCESS_MSG,"A/C-"+ accNumber +"," + name +" has been \n\tsuccessfully added....\n",accNumber,name);
                    System.out.print("Do you want add another Account (Y/n)?");
                    if(SCANNER.nextLine().strip().toUpperCase().equals("Y")){
                        continue;
                    }screen = DASHBOARD;
                    break;
                default:
                    break;
            }

        }while(true);
        

    }

}
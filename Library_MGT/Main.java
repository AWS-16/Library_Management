//Import table
import jdk.swing.interop.SwingInterOpUtils;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Book arrbook[] = new Book[5];

        Scanner cin = new Scanner(System.in);
        String NameOfLibrary , AddOfLibrary ;
        Date date= java.util.Calendar.getInstance().getTime();

        System.out.println("======================== SET UP LIBRARY ========================");
        System.out.print(" => Enter Library's Name     : ");
        NameOfLibrary= cin.nextLine();
        System.out.print(" => Enter Library's Address  : ");
        AddOfLibrary= cin.nextLine();

        System.out.println(" \""+ NameOfLibrary.toUpperCase()+"\" library is already created in \" " + AddOfLibrary.toUpperCase()+ "\" address successfully on "+date+".");



        String Option;
        int choice;
        boolean isNum ;
        boolean  condition= false;
        int numberOfObject=0 ;

            do {
              System.out.println("======================== "+ NameOfLibrary.toUpperCase() +","+ AddOfLibrary.toUpperCase()+" ========================");
              System.out.println("1. Add Book ");
              System.out.println("2. Show All Books");
              System.out.println("3. Show Available Books");
              System.out.println("4. Borrow Book");
              System.out.println("5. Return Book");
              System.out.println("6. Exit");
              System.out.println("---------------------------------------------------------------------------\n");
              System.out.print("=> Choose option (1-6):");
              Option = cin.nextLine();
              Pattern pattern = Pattern.compile(".*[^0-9].*");

                isNum= pattern.matcher(Option).matches();
                       if (!isNum){

                              choice= Integer.parseInt(Option);

                              if (choice<=6 && choice>0){

                                    switch (choice) {

                                        //add new book
                                        case 1 -> {
                                            String bookName, bookAuthor, bookYear;
                                            Book obj = new Book();
                                            if (obj.getId() <= arrbook.length) {
                                                System.out.println("======================== ADD BOOK INFO ========================");
                                                System.out.print("=> Book ID                    :" + obj.getId());
                                                System.out.print("\n=> Enter Book's Name          :");
                                                bookName = cin.nextLine();
                                                System.out.print("=> Enter Book Author          :");
                                                bookAuthor = cin.nextLine();
                                                System.out.print("=> Enter Published Year       :");
                                                bookYear = cin.nextLine();
                                                obj.setTitle(bookName);
                                                obj.setAuthor(bookAuthor);
                                                obj.setPublishedYear(bookYear);


                                                arrbook[obj.getId()-1] = obj;
                                                numberOfObject++;
                                                System.out.println("Book is added successfully");
                                            } else {
                                                System.out.println("The library is full, Can not add new book....");
                                            }
                                        }

                                        //show all book
                                        case 2 -> {
                                            System.out.println("======================== ALL BOOKS INFO ========================");
                                            CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                                            Table tb = new Table(5, BorderStyle.CLASSIC, ShownBorders.ALL);
                                            if (numberOfObject!=0){
                                                tb.addCell(" ID ",numberStyle);
                                                tb.addCell(" TITLE ",numberStyle);
                                                tb.addCell(" AUTHOR ",numberStyle);
                                                tb.addCell(" PUBLISHED DATE ",numberStyle);
                                                tb.addCell(" STATUS ",numberStyle);
                                                for (int i=0; i< numberOfObject ; i++){
                                                    tb.addCell(arrbook[i].getId()+"");
                                                    tb.addCell(arrbook[i].getTitle());
                                                    tb.addCell(arrbook[i].getAuthor());
                                                    tb.addCell(arrbook[i].getPublishedYear());
                                                    tb.addCell(arrbook[i].getStatus());
                                                  }
                                                System.out.println(tb.render());
                                            } else {
                                                System.out.println("No Book Available !!");
                                            }
                                        }

                                        //show available book
                                        case 3 -> {
                                            System.out.println("======================== AVAILABLE BOOKS INFO ========================");
                                            boolean isAvailable=false;
                                            CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                                            Table tb = new Table(5, BorderStyle.CLASSIC, ShownBorders.ALL);
                                            if (numberOfObject!=0 ){
                                                tb.addCell(" ID ",numberStyle);
                                                tb.addCell(" TITLE ",numberStyle);
                                                tb.addCell(" AUTHOR ",numberStyle);
                                                tb.addCell(" PUBLISHED DATE ",numberStyle);
                                                tb.addCell(" STATUS ",numberStyle);
                                                for (int i=0; i< numberOfObject ; i++){
                                                    if (arrbook[i].getStatus().equals("Available")){
                                                        tb.addCell(arrbook[i].getId()+"");
                                                        tb.addCell(arrbook[i].getTitle());
                                                        tb.addCell(arrbook[i].getAuthor());
                                                        tb.addCell(arrbook[i].getPublishedYear());
                                                        tb.addCell(arrbook[i].getStatus());
                                                        isAvailable=true;
                                                    }
                                                }
                                                if (isAvailable==true){
                                                    System.out.println(tb.render());
                                             }else {
                                                    System.out.println("No Book Available !!");
                                                }
                                            } else {
                                                System.out.println("No Book Available !!");
                                            }
                                        }

                                        //borrow book
                                        case 4 -> {
                                            String stBookID;
                                            int bookID;
                                            boolean borrowCondition =false;
                                            boolean availabilityCondition= false;
                                            int availableState=0;
                                            System.out.println("======================== BORROW BOOK INFO ========================");
                                            System.out.print("=> Enter Book ID to Borrow :");
                                            stBookID= cin.nextLine();
                                            isNum= pattern.matcher(stBookID).matches();
                                            if (!isNum) {
                                                bookID = Integer.parseInt(stBookID);
                                                for (int i = 0; i < numberOfObject; i++) {
                                                    if (bookID == arrbook[i].getId()) {
                                                        borrowCondition=true;
                                                        if (arrbook[i].getStatus().equals("Available")) {
                                                            System.out.print("\nBook ID         :" + " " + arrbook[i].getId());
                                                            System.out.print("\nBook Title      :" + " " + arrbook[i].getTitle());
                                                            System.out.print("\nBook Author     :" + " " + arrbook[i].getAuthor());
                                                            System.out.print("\nPublished Year  :" + " " + arrbook[i].getPublishedYear() + " is borrowed successfully...\n");
                                                            arrbook[i].setStatus("Unavailable");
                                                            availabilityCondition=true;
                                                        } else {
                                                            availableState=10;
                                                        }
                                                    } else {

                                                    }
                                                }
                                                if (availabilityCondition == false && availableState==10) {
                                                    System.out.println("Book ID:" + bookID + " is not available .....");
                                                }
                                                if (borrowCondition == false) {
                                                    System.out.println("Book ID:" + bookID + " doesn't not exist .....");
                                                }
                                            }  else {
                                                System.out.println("INPUT INVALID!");
                                            }
                                        }

                                        // Return Book
                                        case 5 -> {
                                            String stBookID;
                                            int bookID;
                                            boolean returnCondition =false;
                                            boolean availabilityCondition=false;
                                            int availabilityState=0;
                                            System.out.println("======================== RETURN BOOK INFO ========================");
                                            System.out.print(" => Enter Book ID to Return :");
                                            stBookID= cin.nextLine();
                                            isNum= pattern.matcher(stBookID).matches();
                                            if (!isNum){
                                                bookID= Integer.parseInt(stBookID);
                                                for (int i=0; i< numberOfObject;i++){
                                                    if (bookID==arrbook[i].getId()){
                                                        returnCondition=true;
                                                        if (arrbook[i].getStatus().equals("Unavailable")){
                                                            System.out.print("\nBook ID         :"+ " "+arrbook[i].getId());
                                                            System.out.print("\nBook Title      :"+ " "+arrbook[i].getTitle());
                                                            System.out.print("\nBook Author     :"+ " "+arrbook[i].getAuthor());
                                                            System.out.print("\nPublished Year  :"+ " "+arrbook[i].getPublishedYear() +" is return successfully...\n");
                                                            arrbook[i].setStatus("Available");
                                                            availabilityCondition=true;
                                                        }else {
                                                            availabilityState=10;
                                                        }
                                                    }
                                                }
                                                if (availabilityCondition==false && availabilityState==10){
                                                    System.out.println("Book ID:"+bookID +" is available and you can borrow it .....");
                                                }
                                                if (returnCondition==false){
                                                    System.out.println("Book ID:"+bookID +" doesn't not exist .....");
                                                }
                                            }else {
                                                System.out.println("INPUT INVALID !");
                                            }
                                        }
                                        case 6 -> condition = true;
                                    }
                                    System.out.println("Press \" ENTER \" to continue.......................");
                                    Scanner scanner= new Scanner(System.in);
                                    scanner.nextLine();
                              }else  {
                                  System.out.println("INPUT INVALID !");
                                  System.out.println("Press \" ENTER \" to continue.......................");
                                  Scanner scanner= new Scanner(System.in);
                                  scanner.nextLine();
                              }
                       }else {
                           System.out.println("INPUT INVALID !");
                           System.out.println("Press \" ENTER \" to continue.......................");
                           Scanner scanner= new Scanner(System.in);
                           scanner.nextLine();
                       }
            }while (!condition);
    }
}
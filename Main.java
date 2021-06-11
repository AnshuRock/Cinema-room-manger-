package academy.learnprogramming;

import java.util.Scanner;

public class Main {
    static int rows;
    static int count = 0;
    static int currentIncome = 0;
    static double percent;
    static int totalNumberOfSeats;
    static int seats;
    static int rowNumber;
    static int seatNumber;
    static Scanner scanner;
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter the number of rows:");
            rows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            seats = scanner.nextInt();
            if(rows > 9 || seats > 9){
                System.out.println("Error! rows and seats should be less than 10");
            }else {
                break;
            }
        }

        String[][] grid = new String[rows][seats];
        createGrid(grid);
        for (;;) {
            System.out.println("\n 1. Show the seats \n 2. Buy a ticket \n 3. Statistics \n 0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    displaySeats(grid);
                    break;
                case 2:
                    buyATicket(grid);
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    static void displaySeats(String[][] grid) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static void buyATicket(String[][] grid) {
        while(true) {
            System.out.println("\nEnter a row number:");
            rowNumber = new Scanner(System.in).nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = new Scanner(System.in).nextInt();


                if (rowNumber > rows || seatNumber > seats) {
                    System.out.println("Wrong input!");
                } else {
                    if (grid[rowNumber - 1][seatNumber - 1].equals(" B"))
                    {
                        System.out.println("\nThat ticket has already been purchased!");
                    } else {
                        break;
                    }
                }
        }
        System.out.print("Ticket price: ");
        totalNumberOfSeats = seats * rows;

        if (totalNumberOfSeats <= 60) {
            currentIncome += 10;
            System.out.println("$" + 10);
        } else {
            int firstRows = rows % 2 != 0 ? (rows - 1) / 2 : rows / 2;
            currentIncome += rowNumber <= firstRows ? 10 : 8;
            System.out.println("$" + (rowNumber <= firstRows ? 10 : 8));
        }
        count++;
        reserveSeat(grid);
    }

    static void createGrid(String[][] grid) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                grid[i][j] = " S";
            }
        }
    }

    static void reserveSeat(String[][] grid) {
        grid[rowNumber - 1][seatNumber - 1] = " B";
    }
    static void statistics(){
        System.out.println("Number of purchased tickets: " + count);
        if(count == 0){
            percent = 0;
        }else {
            percent = (count / (double) totalNumberOfSeats) * 100;
        }

        System.out.printf("Percentage: %.2f", percent);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        totalNumberOfSeats = rows * seats;
        if (totalNumberOfSeats <= 60) {
            System.out.println("Total income: $" + 10 * totalNumberOfSeats);
        } else {
            int firstRows = rows / 2;
            int restRows = rows - firstRows;
            int totalIncome = (firstRows * seats * 10) + (restRows * seats * 8);
            System.out.println("Total income: $" + totalIncome);
        }
    }
    }
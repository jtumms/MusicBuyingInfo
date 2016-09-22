package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ListenerInfo listener = new ListenerInfo();

//    static final String FILE_NAME = listener.firstName + listener.lastName + ".json";



    public static void main(String[] args) {
        boolean loopMain = true;
        while (loopMain) {
            System.out.println("First name:");
            String fName = scanner.nextLine();
            System.out.println("Last Name:");
            String lName = scanner.nextLine();
            listener.firstName = fName;
            listener.lastName = lName;
            String checkfile = String.valueOf(fName + lName + ".json");
            File listenerFileCheck = new File(checkfile);
            boolean exists = listenerFileCheck.exists();
            if (exists) {
                loadInfo();
                System.out.println("Welcome back to our survey!!");
                System.out.printf("First Name: %s\n", listener.firstName);
                System.out.printf("Last Name: %s\n", listener.lastName);
                System.out.printf("Last purchased album: %s\n", listener.album);
                System.out.printf("Your favorite music genres: %s\n", listener.genres);
                System.out.printf("Your favorite artist: %s\n", listener.artist);
                if (listener.willBuy){
                    System.out.println("You will be purchasing a music recording within 3 months");
                }
                else {
                    System.out.println("You will NOT be purhasing a music recording within 3 months");
                }
                if (listener.notify){
                    System.out.println("Hooray!! You are on our mailing list.");
                }
                else {
                    System.out.printf("Bummer... you have opted out of our mailing list\n\n\n\n");
                }
                System.out.println("Edit your data? (Y/N)");
                String editData = scanner.nextLine();
                if (editData.equalsIgnoreCase("y")){
                    editInfo();
                }
                else if (editData.equalsIgnoreCase("n")){
                    exists = false;
                }


            }
            System.out.printf("Welcome %s %s !\n \n \n", fName, lName);
            System.out.println("We would like to ask a few questions about the music you");
            System.out.println("listen to and albums you have recently purchased as a CD");
            System.out.printf("at iTunes, Google Play Store, Amazon Music, etc. \n \n \n \n");
            String proceed = String.format("Shall we get started, %s ? Y/N\n", fName);
            System.out.println(proceed);
            String input = scanner.nextLine();



            System.out.println("What is the last music cd or album you purchased either at a retailer");
            System.out.println("or an online retailer such as iTunes, Amazon, or Google Play?");
            String lastAlbum = scanner.nextLine();
            listener.album = lastAlbum;
            System.out.println("What is your first favorite music genre?");
            String firstFav = scanner.nextLine();
            System.out.println("What is your next favorite music genre?");
            String secondFav = scanner.nextLine();
            System.out.println("And one more gnere of music you enjoy?");
            String thirdFav = scanner.nextLine();
            (listener.genres).addAll(Arrays.asList(firstFav, secondFav, thirdFav));
            System.out.println("Thank you. Now, please tell us your favorite artist, band or musician.");
            String favArtist = scanner.nextLine();
            listener.artist = favArtist;
            System.out.printf("Ok %s. Do you plan to purchase a cd, or make an online purchase within the next three months? Y/N\n", fName);
            String yesOrNo = scanner.nextLine();
            if (yesOrNo.equalsIgnoreCase("y")) {
                listener.willBuy = true;
            } else if (yesOrNo.equalsIgnoreCase("n")) {
                listener.willBuy = false;
            }
            System.out.printf("Thanks for answering our survey, %s!!\n", fName);
            System.out.println("Would you like to be placed on our mailing list?");
            String option = scanner.nextLine();

            switch (option) {
                case "y":
                    listener.notify = true;
                    saveInfo();
                    break;
                case "n":
                    listener.notify = false;
                    saveInfo();
                    break;
                default:
                    System.out.println("Invalid response. Please try again.");
                    break;

            }


        }

    }


    static void saveInfo() {
        String createFile = String.valueOf(listener.firstName + listener.lastName + ".json");
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(listener);
        File listenerFile = new File(createFile);
        try {
            FileWriter fw = new FileWriter(listenerFile);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't save to file!");
        }
    }
    static void loadInfo() {
        String loadFile = String.valueOf(listener.firstName + listener.lastName + ".json");
        File listenerFile = new File(loadFile);
        try {
            FileReader fr = new FileReader(listenerFile);
            int fileSize = (int) listenerFile.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
            listener = parser.parse(contents, ListenerInfo.class);
        } catch (Exception e) {
            System.out.println("File not found");
        }

    }
    static void editInfo() {
        System.out.println("1 - Edit first name");
        System.out.println("2 - Edit Last name");
        System.out.println("3 - Add a new Genre");
        System.out.println("4 - Edit your favorite artist");
        String editChoice = scanner.nextLine();
        switch (editChoice) {
            case "1":
                listener.firstName = scanner.nextLine();
                break;
            case "2":
                listener.firstName = scanner.nextLine();
                break;
            case "3":
                listener.genres.add(scanner.nextLine());
                break;
            case "4":
                listener.artist = scanner.nextLine();
                break;

        }
    }
}
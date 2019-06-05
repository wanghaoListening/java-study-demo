package com.haothink.protobuf;

import java.io.*;

/**
 * @author wanghao
 * @date 2019年06月03日 15:35
 * description:
 * protoc -I=/Users/admin/credit_ease/routine_exe/src/main/resources --java_out=/Users/admin/credit_ease/routine_exe/src/main/java /Users/admin/credit_ease/routine_exe/src/main/resources/proto/addressbook.proto
 */
public class ProtoBufWriter {

    // This function fills in a Person message based on user input.
    private static AddressBookProto.Person promptForAddress(BufferedReader stdin,
                                                    PrintStream stdout) throws IOException {
        AddressBookProto.Person.Builder person = AddressBookProto.Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            AddressBookProto.Person.PhoneNumber.Builder phoneNumber =
                    AddressBookProto.Person.PhoneNumber.newBuilder().setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if (type.equals("mobile")) {
                phoneNumber.setType(AddressBookProto.Person.PhoneType.MOBILE);
            } else if (type.equals("home")) {
                phoneNumber.setType(AddressBookProto.Person.PhoneType.HOME);
            } else if (type.equals("work")) {
                phoneNumber.setType(AddressBookProto.Person.PhoneType.WORK);
            } else {
                stdout.println("Unknown phone type.  Using default.");
            }

            person.addPhones(phoneNumber);
        }

        return person.build();
    }

    // Main function:  Reads the entire address book from a file,
    //   adds one person based on user input, then writes it back out to the same
    //   file.
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        AddressBookProto.AddressBook.Builder addressBook = AddressBookProto.AddressBook.newBuilder();

        // Read the existing address book.
        try {
            addressBook.mergeFrom(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + ": File not found.  Creating a new file.");
        }

        // Add an address.
        addressBook.addPeople(
                promptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                        System.out));

        // Write the new address book back to disk.
        FileOutputStream output = new FileOutputStream(args[0]);
        addressBook.build().writeTo(output);
        output.close();
    }
}

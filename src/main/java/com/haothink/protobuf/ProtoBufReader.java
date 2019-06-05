package com.haothink.protobuf;

import java.io.FileInputStream;

/**
 * @author wanghao
 * @date 2019年06月03日 16:23
 * description:
 */
public class ProtoBufReader {

    // Iterates though all people in the AddressBook and prints info about them.
    private static void print(AddressBookProto.AddressBook addressBook) {
        for (AddressBookProto.Person person: addressBook.getPeopleList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("  Name: " + person.getName());
            if (person.hasEmail()) {
                System.out.println("  E-mail address: " + person.getEmail());
            }

            for (AddressBookProto.Person.PhoneNumber phoneNumber : person.getPhonesList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("  Work phone #: ");
                        break;
                        default:
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    // Main function:  Reads the entire address book from a file and prints all
    //   the information inside.
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        // Read the existing address book.
        AddressBookProto.AddressBook addressBook =
                AddressBookProto.AddressBook.parseFrom(new FileInputStream(args[0]));

        print(addressBook);
    }
}

package addressBook;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class AD_Book_Main {
	private static ArrayList<PersonContact> list = new ArrayList<PersonContact>();

	// Add contact details in AddressBookContact ::
	public void AddContactsDetails() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the First Name => ");
		String firstName = userInput.nextLine();
		System.out.println("Enter the Last Name => ");
		String lastName = userInput.nextLine();
		System.out.println("Enter the Address => ");
		String address = userInput.nextLine();
		System.out.println("Enter the City => ");
		String city = userInput.nextLine();
		System.out.println("Enter the State => ");
		String state = userInput.nextLine();
		System.out.println("Enter the Zip Code => ");
		int zip = userInput.nextInt();
		System.out.println("Enter the Phone Number => ");
		long phoneNumber = userInput.nextLong();
		System.out.println("Enter the Email => ");
		String email = userInput.nextLine();
		email = userInput.nextLine();
		PersonContact details = new PersonContact(firstName, lastName, address, city, state, zip, phoneNumber,
				email);
		list.add(details);
		
	}

	// Edit contact details in AddressBookContact::
	private void editContact() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter firstname to edit contact => ");
		String name = userInput.nextLine();
		for (PersonContact search : list) {
			if (name.equalsIgnoreCase(search.getFirstName())) {
				System.out.println("Enter the updated first name => ");
				String firstName = userInput.next();
				search.setFirstName(firstName);
				System.out.println("Enter the updated last name => ");
				String lastName = userInput.next();
				search.setLastName(lastName);
				System.out.println("Enter the updated address => ");
				String address = userInput.next();
				search.setAddress(address);
				System.out.println("Enter the updated city => ");
				String city = userInput.next();
				search.setCity(city);
				System.out.println("Enter the updated state => ");
				String state = userInput.next();
				search.setState(state);
				System.out.println("Enter the updated zipcode => ");
				int zip = userInput.nextInt();
				search.setZip(zip);
				System.out.println("Enter the updated phoneNumber => ");
				long phoneNumber = userInput.nextInt();
				search.setPhoneNo(phoneNumber);
				System.out.println("Enter the updated emailID => ");
				String email = userInput.next();
				search.setEmail(email);
				search.toString();
			} else {
				System.out.println("Entered name not found in the AddressBook");
			}
		}
	}

	// Delete contact details in AddressBookContact ::
	private void deleteContact() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter firstname to Delete Contact => ");
		String name = userInput.nextLine();
		for (PersonContact search : list) {
			if (name.equalsIgnoreCase(search.getFirstName())) {
				System.out.println("Entered name found in the Address Book, deleting contact");
				list.remove(search);
			} else {
				System.out.println("Entered name not found in the Address Book");
			}
		}
	}

	// Check duplicate entry in AddressBookContact::
	private void checkDuplicateEntry() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter firstname to Check duplicate entry Contact: ");
		String name = userInput.nextLine();
		for (PersonContact search : list) {
			if (name.equalsIgnoreCase(search.getFirstName())) {
				System.out.println("Entered name found in the Address Book");
			} else {
				System.out.println("Entered name not found in the Address Book");
			}
		}
	}

	// Search person in AddressBookContact::
	private void searchPerson() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city to search person entry in Contact: ");
		String city = sc.nextLine();
		for (PersonContact search : list) {
			if (city.equalsIgnoreCase(search.getCity())) {
				System.out.println("Entered city found in the Address Book");
				search.toString();
			} else {
				System.out.println("Entered city not found in the Address Book");
			}
		}
	}

	// View persons by state wise ::
	private void viewPersons() {
		Collections.sort(list, (o1, o2) -> (o1.getState().compareTo(o2.getState())));
		for (PersonContact search : list) {
			System.out.println("The person name is " + search.getFirstName() + " from " + search.getState() + " State");
		}
	}

	// View persons contact number by state wise ::
	private void viewPersonsContactNumber() {
		Collections.sort(list, (o1, o2) -> (o1.getState().compareTo(o2.getState())));
		for (PersonContact search : list) {
			System.out.println(" ");
			System.out.println("Person name :: " + search.getFirstName() + "|| Phone no :: " + search.getPhoneNo()
					+ "|| State :: " + search.getState());
		}
	}

	// View Address book by persons name ::
	private void viewAddressBook_by_PersonsName() {
		Collections.sort(list, (o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
		for (PersonContact search : list) {
			System.out.println(" ");
			System.out.println("|| lastName :: " + search.getLastName() + "|| Address :: " + search.getAddress()
					+ "|| City :: " + search.getCity() + "|| State :: " + search.getState() + "|| zipcode :: "
					+ search.getZip() + "|| Phone no :: " + search.getPhoneNo() + "|| email-ID :: "
					+ search.getEmail() + "Person name :: " + search.getFirstName());
		}
	}

	// View Address book by by City,State or Zip ::
	private void viewAddressBook_by_City_Sate() {
		Comparator<PersonContact> compareByCitySate = Comparator.comparing(PersonContact::getCity)
				.thenComparing(PersonContact::getState);

		List<PersonContact> sortedviewAddressBook_by_City_Sate = list.stream().sorted(compareByCitySate)
				.collect(Collectors.toList());
		for (PersonContact result : list) {
			System.out.println("|| FirstName :: " + result.getFirstName() + "|| lastName :: " + " "
					+ result.getLastName() + "|| Address :: " + result.getAddress() + "|| zipcode :: " + result.getZip()
					+ "|| Phone no :: " + result.getPhoneNo() + "|| email-ID :: " + result.getEmail()
					+ "|| City :: " + result.getCity() + "|| State :: " + result.getState());
		}
	}

	private void IO_File() throws IOException {
		FileWriter csvWriter = new FileWriter("addressBook.csv");
		csvWriter.append("firstName");
		csvWriter.append(",");
		csvWriter.append("lastName");
		csvWriter.append(",");
		csvWriter.append("address");
		csvWriter.append(",");
		csvWriter.append("city");
		csvWriter.append(",");
		csvWriter.append("state");
		csvWriter.append(",");
		csvWriter.append("Zip Code");
		csvWriter.append(",");
		csvWriter.append("Phone no");
		csvWriter.append(",");
		csvWriter.append("email-ID");
		csvWriter.append("\n");
		for (PersonContact rowData : list) {
			csvWriter.append(String.join(",",
					rowData.getFirstName() + "," + rowData.getLastName() + "," + rowData.getAddress() + ","
							+ rowData.getCity() + "," + rowData.getState() + "," + rowData.getZip() + ","
							+ rowData.getPhoneNo() + "," + rowData.getEmail()));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		HashMap<String, AD_Book_Main> addressBooks = new HashMap<>();
		AD_Book_Main book1 = new AD_Book_Main();
		AD_Book_Main book2 = new AD_Book_Main();
		AD_Book_Main book3 = new AD_Book_Main();
		addressBooks.put("AddressBook1", book1);
		addressBooks.put("AddressBook2", book2);
		addressBooks.put("AddressBook3", book3);
		System.out.println("Choose your Address Book => AddressBook(1)/ AddressBook(2)/ AddressBook(3)");
		int chooseAddressBook = userInput.nextInt();
		System.out.println("Choose your Address Book => Add/Edit/Delete/DuplicateEntry/SearchPerson");
		while (chooseAddressBook >= 1) {
			System.out.println(
					"Enter Your Choice => Add Contacts (1)/Edit Contacts (2)/Delete Contacts (3)/ Check Duplicate Entry (4)/ Search person (5)");
			int choice = userInput.nextInt();
			switch (chooseAddressBook) {
			case 1:
				if (choice == 1) {
					book1.AddContactsDetails();
				} else if (choice == 2) {
					book1.editContact();
				} else if (choice == 3) {
					book1.deleteContact();
				} else if (choice == 4) {
					book1.checkDuplicateEntry();
				} else if (choice == 5) {
					book1.searchPerson();
				}
				break;
			case 2:
				if (choice == 1) {
					book2.AddContactsDetails();
				} else if (choice == 2) {
					book2.editContact();
				} else if (choice == 3) {
					book2.deleteContact();
				} else if (choice == 4) {
					book2.checkDuplicateEntry();
				} else if (choice == 5) {
					book2.searchPerson();
				}
				break;
			case 3:
				if (choice == 1) {
					book3.AddContactsDetails();
				} else if (choice == 2) {
					book3.editContact();
				} else if (choice == 3) {
					book3.deleteContact();
				} else if (choice == 4) {
					book3.checkDuplicateEntry();
				} else if (choice == 5) {
					book3.searchPerson();
				}
				break;
			default:
				System.out.println("Give proper input");
				break;
			}
			System.out.println("1) AddressBook 1");
			System.out.println("2) AddressBook 2");
			System.out.println("3) AddressBook 3");
			System.out.println("0) Exit");
			AD_Book_Main myobj = new AD_Book_Main();
//			
			try {
				myobj.IO_File();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chooseAddressBook = userInput.nextInt();

		}
	}

}
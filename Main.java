import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Contact[] person = new Contact[100];
	static int id = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Displays the main menu and handles the user's selection in a loop
		while (true) {
			System.out.println("Select your option");
			System.out.println("1. Add new contact");
			System.out.println("2. View every Contact");
			System.out.println("3. Search contact");
			System.out.println("4. Delete Contact");
			System.out.println("5. Write Contact");
			System.out.println("6. Exit");
			int sel = scan.nextInt();
			// Consume the leftover newline character
			scan.nextLine();

			switch (sel) {
				case 1:
					inputContact();
					break;
				case 2:
					viewContact();
					break;
				case 3:
					searchContact();
					break;
				case 4:
					deleteContact();
					break;
				case 5:
					writeContact();
					break;
				default:
					System.out.println("End program.");
					return;
			}
		}
	}

	
	// Adds a new contact to the person array.
	// Reads name, phone, and email from user input.
	private static void inputContact() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name: ");
		String name = scan.nextLine();
		System.out.print("Phone number: ");
		String phone = scan.nextLine();
		System.out.print("Email: ");
		String email = scan.nextLine();

		// Store the new contact and increment the contact count (id)
		person[id++] = new Contact(name, phone, email);
	}

	// Displays information for all stored contacts.
	private static void viewContact() {
		System.out.println("View Every contact");
		for (int i = 0; i < id; i++) {
			person[i].showInfo();
		}
		System.out.println("Done.");
	}

	// Searches for a contact by name and displays the result if found.
	private static void searchContact() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name: ");
		String inputName = scan.nextLine();

		for (int i = 0; i < id; i++) {
			if (person[i].getName().equals(inputName)) {
				person[i].showInfo();
				break;
			}
		}
		System.out.println("Contact Search done.");
	}

	// Deletes a contact by name from the person array.
	// Shifts subsequent elements to fill the gap.
	private static void deleteContact() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name: ");
		String inputName = scan.nextLine();
		int findId = -1;

		// Search for the contact's index
		for (int i = 0; i < id; i++) {
			if (person[i].getName().equals(inputName)) {
				findId = i;
				break;
			}
		}

		// If found, shift remaining contacts to overwrite the deleted one
		if (findId != -1) {
			for (int i = findId; i < id - 1; i++) {
				person[i] = person[i + 1];
			}
			id--;
		}
		System.out.println("Contact Deleted.");
	}

	// Writes the phone number and email of a contact (by name) to a file.
	private static void writeContact() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("Name: ");
			String inputName = scan.nextLine();
			System.out.print("Enter file name to write down: ");
			String filename = scan.nextLine();

			FileWriter out = new FileWriter(filename);

			// Write the matching contact's phone and email to the file
			for (int i = 0; i < id; i++) {
				if (person[i].getName().equals(inputName)) {
					out.write(person[i].getPhone() + "\n");
					out.write(person[i].getEmail() + "\n");
				}
			}

			out.close();
			System.out.println("Contact information written to file successfully.");
		} catch (IOException e) {
			System.out.println("Error occurred: IOException");
		} catch (Exception e) {
			System.out.println("Error occurred: Exception");
		}
	}
}
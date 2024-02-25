import json

class Contact:
    def _init_(self, name, phone_number, email):
        self.name = name
        self.phone_number = phone_number
        self.email = email
    
    def _str_(self):
        return f"Name: {self.name}, Phone: {self.phone_number}, Email: {self.email}"

class AddressBook:
    def _init_(self):
        self.contacts = []

    def add_contact(self, contact):
        self.contacts.append(contact)

    def remove_contact(self, name):
        for contact in self.contacts:
            if contact.name == name:
                self.contacts.remove(contact)
                return True
        return False

    def search_contact(self, name):
        for contact in self.contacts:
            if contact.name == name:
                return contact
        return None

    def display_all_contacts(self):
        for contact in self.contacts:
            print(contact)

    def save_to_file(self, filename):
        with open(filename, 'w') as f:
            json.dump([vars(contact) for contact in self.contacts], f)

    def load_from_file(self, filename):
        with open(filename, 'r') as f:
            data = json.load(f)
            self.contacts = [Contact(**contact_data) for contact_data in data]

# Example usage:
address_book = AddressBook()

# Add contacts
contact1 = Contact("John Doe", "1234567890", "john@example.com")
contact2 = Contact("Jane Smith", "9876543210", "jane@example.com")
address_book.add_contact(contact1)
address_book.add_contact(contact2)

# Display all contacts
address_book.display_all_contacts()

# Search for a contact
contact = address_book.search_contact("John Doe")
if contact:
    print("Found contact:", contact)
else:
    print("Contact not found.")

# Remove a contact
address_book.remove_contact("Jane Smith")

# Save to file
address_book.save_to_file("contacts.json")

# Load from file
address_book.load_from_file("contacts.json")
package callsManagement;

import java.time.LocalDate;
import java.util.List;

public interface IDevice {

    // Get a contact by its phone number
    Contact getContactByPhoneNumber(int num);

    Contact getContactByKeyWord(String keyword);

    double totalCallsCostByContact(Contact contact);

    double totalCost(List<Call> calls);

    double totalCallsCost();

    // Get the calls made between 2 dates inside a calls list
    List<Call> getCallsBetween(LocalDate startDate, LocalDate endDate);

    // Calls total cost between 2 dates :
    double totalCallsCostInBetween(LocalDate startDate, LocalDate endDate);

    void addContact(Contact contact);

    void addCall(Call call);

}
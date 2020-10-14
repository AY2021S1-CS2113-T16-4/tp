package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

/**
 * Adds a patient to the list of patients.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private final String trimmedCommand;

    public AddCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Adds a new patient to the patient list using details inputted by the user.
     *
     * @param patients List of patients.
     * @param ui       Ui object of the program.
     * @param storage  Storage object used for saving data to files.
     * @throws RexException If there is an error in the NRIC inputted by the user, the data fails
     *                      to save successfully, or the NRIC already exists in the patient list.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) throws RexException {
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        if (patients.isExistingPatient(nric)) {
            throw new RexException("A patient with this NRIC is already registered!");
        }

        patients.addNewPatient(ui.getPatientName(), nric, ui.getPatientDateOfBirth());
        ui.showLine();

        ui.showPatientAdded(patients.getPatientUsingIndex(patients.getSize() - 1));
        storage.save(patients);
    }
}

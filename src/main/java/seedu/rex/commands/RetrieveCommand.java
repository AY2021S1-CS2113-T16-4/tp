package seedu.rex.commands;

import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;

/**
 * Retrieves patient details.
 */
public class RetrieveCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";
    private final String trimmedCommand;

    public RetrieveCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Retrieves patient from patient list using details inputted by the user.
     *
     * @param patients PatientList object.
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @throws RexException If there is issue executing command.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage) throws RexException {
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        int index = patients.getExistingPatient(nric);
        assert index > -2 : "Unexpected index!";
        if (index < 0) {
            throw new RexException("No such patient!");
        }
        ui.showPatient(patients.getPatientUsingIndex(index));
    }
}

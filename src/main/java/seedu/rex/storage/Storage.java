package seedu.rex.storage;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Patient;
import seedu.rex.parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * Loads and saves data to file.
 */
public class Storage {
    public static final String LOAD_ERROR = "Error loading file.";
    private static final String READ_ERROR = "Error reading file.";
    private static final String DIRECTORY_ERROR = "Error creating directory.";
    private static final String WRITE_ERROR = "Error writing file.";
    private static final String APPOINTMENTS_FILE = "appointments.txt";
    private final String folder;
    private final String file;

    /**
     * Initializes path of folder and file.
     *
     * @param filePath Full file path.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.equals("") : "Invalid filepath";

        int index = filePath.lastIndexOf("/");
        folder = filePath.substring(0, index);
        file = filePath.substring(index + 1);
    }

    /**
     * Loads patients, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of patients.
     * @throws RexException If there is problem reading file.
     */
    public ArrayList<Patient> load() throws RexException {
        Path path = Paths.get(folder, file);
        ArrayList<Patient> patients = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Patient patient = Parser.readPatient(line);
                    patients.add(patient);
                }
            } catch (IOException e) {
                throw new RexException(READ_ERROR);
            }
        }

        return patients;
    }

    /**
     * Saves patients into file.
     *
     * @param patients ArrayList of patients to save.
     * @throws RexException If there is problem writing or saving file.
     */
    public void save(PatientList patients) throws RexException {
        assert patients != null : "Saving null patients ArrayList";

        StringBuilder patientsFileContent = new StringBuilder();

        for (int i = 0; i < patients.getSize(); i++) {
            // Need to format tasks
            patientsFileContent.append(patients.getPatientUsingIndex(i));
            patientsFileContent.append('\n');
            Vector<Appointment> patientAppointments = patients.getPatientUsingIndex(i).getAppointmentHistory();
        }

        Path folderPath = Paths.get(folder);
        if (!Files.exists(folderPath) && !new File(folder).mkdir()) {
            throw new RexException(DIRECTORY_ERROR);
        }

        Path filePath = Paths.get(folder, file);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            bufferedWriter.write(patientsFileContent.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RexException(WRITE_ERROR);
        }
    }

    public void saveAppointments(ArrayList<Appointment> appointments) throws IOException {
        StringBuilder textToWrite = new StringBuilder();
        for (Appointment appointment : appointments) {
            textToWrite.append(appointment.toString());
            textToWrite.append(System.lineSeparator());
        }
        String appointmentsFilePath = folder + "/" + APPOINTMENTS_FILE;

        FileWriter fw = new FileWriter(appointmentsFilePath);
        fw.write(String.valueOf(textToWrite));
        fw.close();
    }

    public ArrayList<Appointment> loadAppointments() throws FileNotFoundException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        File appointmentsFile = new File(folder + "/" + APPOINTMENTS_FILE);
        Scanner s = new Scanner(appointmentsFile);
        while (s.hasNext()) {
            Appointment appointment = Parser.readAppointment(s.nextLine());
            appointments.add(appointment);
        }
        return appointments;
    }
}

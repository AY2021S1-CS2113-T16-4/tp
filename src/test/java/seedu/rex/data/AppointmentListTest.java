package seedu.rex.data;

import org.junit.jupiter.api.Test;
import seedu.rex.data.hospital.Appointment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentListTest {

    @Test
    void isEmpty_empty_true() {
        AppointmentList appointmentList = new AppointmentList();
        assertTrue(appointmentList.isEmpty());
    }

    @Test
    void isEmpty_1Appointment_false() {
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.addAppointment(new Appointment(LocalDate.now()));
        assertFalse(appointmentList.isEmpty());
    }
}
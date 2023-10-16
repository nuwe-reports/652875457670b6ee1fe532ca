package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

    @Test
    void shouldCreateDoctor() {
        String firstName = "Paulino";
        String lastName = "Antunez";
        int age = 24;
        String email = "p.antunez@hospital.accwe";

        Doctor doctor = new Doctor(firstName, lastName, age, email);

        assertThat(doctor.getFirstName()).isEqualTo(firstName);
        assertThat(doctor.getLastName()).isEqualTo(lastName);
        assertThat(doctor.getAge()).isEqualTo(age);
        assertThat(doctor.getEmail()).isEqualTo(email);
        assertThat(doctor.getId()).isInstanceOf(Long.class);
    }

    @Test
    void shouldChangeDoctorId() {
        Doctor doctor = new Doctor("Paulino", "Antunez", 24, "p.antunez@hospital.accwe");
        long doctorId = doctor.getId();
        long newDoctorId = doctorId + 1; // arbitrary number so there's no possibility to be the same;

        doctor.setId(newDoctorId);

        assertThat(doctor.getId()).isEqualTo(newDoctorId);
    }

    @Test
    void shouldCreatePatient() {
        String firstName = "Miren";
        String lastName = "Amalia";
        int age = 33;
        String email = "m.amalia@hospital.accwe";

        Patient patient = new Patient(firstName, lastName, age, email);

        assertThat(patient.getFirstName()).isEqualTo(firstName);
        assertThat(patient.getLastName()).isEqualTo(lastName);
        assertThat(patient.getAge()).isEqualTo(age);
        assertThat(patient.getEmail()).isEqualTo(email);
        assertThat(patient.getId()).isInstanceOf(Long.class);
    }

    @Test
    void shouldChangePatientId() {
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        long patiendId = patient.getId();
        long newPatientId = patiendId + 1; // arbitrary number so there's no possibility to be the same;

        patient.setId(newPatientId);

        assertThat(patient.getId()).isEqualTo(newPatientId);
    }

    @Test
    void shouldCreateRoom() {
        String roomName = "Dermatology";

        Room room = new Room(roomName);

        assertThat(room.getRoomName()).isEqualTo(roomName);
    }

    @Test
    void shouldCreateAppointment() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        assertThat(appointment.getDoctor()).isEqualTo(doctor);
        assertThat(appointment.getPatient()).isEqualTo(patient);
        assertThat(appointment.getRoom()).isEqualTo(room);
        assertThat(appointment.getStartsAt()).isEqualTo(startsAt);
        assertThat(appointment.getFinishesAt()).isEqualTo(finishesAt);
        assertThat(appointment.getId()).isInstanceOf(Long.class);
    }

    @Test
    void shouldChangeAppointmentId() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        long appointmentId = appointment.getId();
        long newAppointmentId = appointmentId + 1; // arbitrary number so there's no possibility to be the same;

        doctor.setId(newAppointmentId);

        assertThat(doctor.getId()).isEqualTo(newAppointmentId);
    }

    @Test
    void shouldChangeAppointmentDoctor() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        Doctor newDoctor = new Doctor("Jose Luis", "Olaya", 37, "j.olaya@hospital.accwe");

        appointment.setDoctor(newDoctor);

        assertThat(appointment.getDoctor()).isEqualTo(newDoctor);
    }

    @Test
    void shouldChangeAppointmentPatient() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        Patient newPatient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@hospital.accwe");

        appointment.setPatient(newPatient);

        assertThat(appointment.getPatient()).isEqualTo(newPatient);
    }

    @Test
    void shouldChangeAppointmentRoom() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        Room newRoom = new Room("Rheumatology");

        appointment.setRoom(newRoom);

        assertThat(appointment.getRoom()).isEqualTo(newRoom);
    }

    @Test
    void shouldChangeAppointmentStartTime() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        LocalDateTime newStartAt= LocalDateTime.parse("19:40 24/04/2023", formatter);

        appointment.setStartsAt(newStartAt);

        assertThat(appointment.getStartsAt()).isEqualTo(newStartAt);
    }

    @Test
    void shouldChangeAppointmentFinishTime() {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        LocalDateTime newFinishesAt= LocalDateTime.parse("19:40 24/04/2023", formatter);

        appointment.setFinishesAt(newFinishesAt);

        assertThat(appointment.getFinishesAt()).isEqualTo(newFinishesAt);
    }

    @Test
    void shouldCheckAppointmentOverlaps() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        Doctor firstDoctor = new Doctor("Paulino", "antunez", 21, "p.antunez@hospital.accwe");
        Patient firstPatient = new Patient("Miren", "Amalia", 31, "m.amalia@hospital.accwe");
        Room firstRoom = new Room("Dermatology");

            LocalDateTime firstStartsAt= LocalDateTime.parse("17:20 24/04/2023", formatter);
            LocalDateTime firstFinishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment firstAppointment = new Appointment(firstPatient, firstDoctor, firstRoom, firstStartsAt, firstFinishesAt);

        Doctor secondDoctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient secondPatient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room secondRoom = new Room("Dermatology");

            LocalDateTime secondStartsAt= LocalDateTime.parse("18:20 24/04/2023", formatter);
            LocalDateTime secondFinishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment secondAppointment = new Appointment(secondPatient, secondDoctor, secondRoom, secondStartsAt, secondFinishesAt);

        Doctor thirthDotor = new Doctor("Paulino", "antunez", 27, "p.antunez@hospital.accwe");
        Patient thirthPatient = new Patient("Miren", "Amalia", 37, "m.amalia@hospital.accwe");
        Room thirthRoom = new Room("Dermatology");

            LocalDateTime thirthStartsAt= LocalDateTime.parse("18:20 24/04/2023", formatter);
            LocalDateTime thirthFinishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment thirthAppointment = new Appointment(thirthPatient, thirthDotor, thirthRoom, thirthStartsAt, thirthFinishesAt);

        /// True when:
        // Case 1: A.starts == B.starts
        // Case 2: A.finishes == B.finishes
        // Case 3: A.starts < B.finishes && B.finishes < A.finishes
        // Case 4: B.starts < A.starts && A.finishes < B.finishes

        assertThat(thirthAppointment.overlaps(secondAppointment)).isTrue();
        assertThat(thirthAppointment.overlaps(firstAppointment)).isTrue();
        assertThat(firstAppointment.overlaps(secondAppointment)).isTrue();
        assertThat(secondAppointment.overlaps(firstAppointment)).isTrue();

    }

}

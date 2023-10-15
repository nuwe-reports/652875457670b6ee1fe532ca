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

    private Appointment firstAppointment;
    private Appointment secondAppointment;
    private Appointment thirthAppointment;

    //Doctor tests
    @Test
    void shouldCreateDoctor() throws Exception {
        String firstName = "Paulino";
        String lastName = "Antunez";
        int age = 24;
        String email = "p.antunez@hospital.accwe";

        Doctor doctor = new Doctor(firstName, lastName, age, email);

        assertThat(doctor.getFirstName()).isEqualTo(firstName);
        assertThat(doctor.getLastName()).isEqualTo(lastName);
        assertThat(doctor.getAge()).isEqualTo(age);
        assertThat(doctor.getEmail()).isEqualTo(email);
        assertThat(doctor.getId()).isInstanceOf(long.class);
    }

    @Test
    void shouldChangeDoctorId() {
        Doctor doctor = new Doctor("Paulino", "Antunez", 24, "p.antunez@hospital.accwe");
        long doctorId = doctor.getId();
        long newDoctorId = doctorId + 1; // arbitrary number so there's no possibility to be the same;

        doctor.setId(newDoctorId);

        assertThat(doctor.getId()).isEqualTo(newDoctorId);
    }

//patient tests
    @Test
    void shouldCreatePatient() throws Exception {
        String firstName = "Miren";
        String lastName = "Amalia";
        int age = 33;
        String email = "m.amalia@hospital.accwe";

        Patient patient = new Patient(firstName, lastName, age, email);

        assertThat(patient.getFirstName()).isEqualTo(firstName);
        assertThat(patient.getLastName()).isEqualTo(lastName);
        assertThat(patient.getAge()).isEqualTo(age);
        assertThat(patient.getEmail()).isEqualTo(email);
        assertThat(patient.getId()).isInstanceOf(long.class);
    }

    @Test
    void shouldChangePatientId(){
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        long patiendId = patient.getId();
        long newPatientId = patiendId + 1; // arbitrary number so there's no possibility to be the same;

        patient.setId(newPatientId);

        assertThat(patient.getId()).isEqualTo(newPatientId);
    }
//room test
    @Test
    void shouldCreateRoom() throws Exception {
        String roomName = "Dermatology";

        Room room = new Room(roomName);

        assertThat(room.getRoomName()).isEqualTo(roomName);
    }
//appointment test
    @Test
    void shouldCreateAppointment() throws Exception {
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
        assertThat(appointment.getId()).isInstanceOf(long.class);
    }

    @Test
    void shouldChangeAppointmentId() throws Exception{
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
    void shouldChangeAppointmentDoctor() throws Exception {
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
    void shouldChangeAppointmentPatient() throws Exception {
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
    void shouldChangeAppointmentRoom(){
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
    void shouldChangeAppointmentStartTime() throws Exception {
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
    void shouldChangeAppointmentFinishTime() throws Exception {
        Doctor doctor = new Doctor("Paulino", "antunez", 24, "p.antunez@hospital.accwe");
        Patient patient = new Patient("Miren", "Amalia", 33, "m.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:20 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("21:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        LocalDateTime newFinishesAt= LocalDateTime.parse("19:40 24/04/2023", formatter);

        appointment.setStartsAt(newFinishesAt);

        assertThat(appointment.getFinishesAt()).isEqualTo(newFinishesAt);
    }

    @Test
    void shouldCheckAppointmentOverlaps(){

    }

    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */
}

package ca.sheridancolege.kayamu.bootstrap;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancolege.kayamu.beans.Appointment;
import ca.sheridancolege.kayamu.repositories.AppointmentRepository;


@Component
public class BootstrapData implements CommandLineRunner {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	//TODO - This method add data o production data
	public void run(String... args) throws Exception {
		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-10-01"), 
                LocalTime.parse("02:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Simon Hood",
                "Subject",
                1000.00,
                "Bramton"));


		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-09-01"), 
                LocalTime.parse("02:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Elizabeth Tailor",
                "Subject",
                1100.00,
                "Oakville"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-08-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "John Wayne",
                "Subject",
                1200.00,
                "Toronto"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-08-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Marilyn Monreo",
                "Subject",
                1300.00,
                "Etobicoke"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-10-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Jeniffer Lopez",
                "York",
                1400.00,
                "Bramton"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-12-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Kayahan",
                "Subject",
                1500.00,
                "North York"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-01-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "LIv Tailor",
                "Subject",
                1600.00,
                "Toronto GTA"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-02-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Bruce Wills",
                "Subject",
                1700.00,
                "Hamilton"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-01-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Matthew Kaya",
                "Subject",
                1800.00,
                "Niagara"));

		appointmentRepository.save(new Appointment("Visit", 
                LocalDate.parse("2022-04-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Matthew Kaya",
                "Subject",
                1900.00,
                "Bramton"));
		
		appointmentRepository.save(new Appointment("Meeting", 
                LocalDate.parse("2022-04-01"), 
                LocalTime.parse("01:00:00"), 
                LocalDate.parse("2022-11-01"), 
                LocalTime.parse("01:00:00"),
                "Matthew Kaya",
                "Subject",
                2000.00,
                "Bramton"));
		
		
		
	}

}


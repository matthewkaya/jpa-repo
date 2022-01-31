package ca.sheridancolege.kayamu.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
	@NonNull
	private String purpose;
    
    @NonNull    
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;
    
    @NonNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime startTime;

    @NonNull    
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;
    
    @NonNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime endTime;
    
    @NonNull
	private String person;
    
    @NonNull
    @Lob
	private String subject;

    @NonNull
	private Double fee;
    
    @NonNull
	private String location;
    
    

    
    
    
}

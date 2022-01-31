package ca.sheridancolege.kayamu.repositories;

import ca.sheridancolege.kayamu.beans.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	

	

//	@Query("SELECT a FROM Appointment a WHERE LOWER(a.person) = LOWER(:person)")
//	public Appointment retrieveIgnoringCase(@Param("person") String person);
//
//  @Query("SELECT a.startDate FROM Appointment a WHERE a.startDate = :startDate AND a.startTime = :startTime")
//  public List<Appointment> findByStartDateAndStartTime(@Param("startDate") LocalDate startDate, @Param("startTime") LocalTime startTime, Pageable paging);
//
//	public Page<Appointment> findByPurpose(@NonNull String purpose, Pageable paging);
//
//	public Page<Appointment> findByPurposeAndStartDateAndStartTime(@NonNull String purpose,
//			@NonNull LocalDate startDate, @NonNull LocalTime startTime, Pageable paging);
//
//	public Page<Appointment> findByPurposeAndPerson(@NonNull String purpose, @NonNull String person, Pageable paging);
//
//	public Page<Appointment> findByStartDate(@NonNull LocalDate startDate, Pageable paging);
//
//	public Page<Appointment> findByStartTime(@NonNull LocalTime startTime, Pageable paging);
//
//	public Page<Appointment> findByPersonAndStartDateAndStartTime(@NonNull String person, @NonNull LocalDate startDate,
//			@NonNull LocalTime startTime, Pageable paging);
//
//	public Page<Appointment> findByPurposeAndPersonAndStartDateAndStartTime(@NonNull String purpose,
//			@NonNull String person, @NonNull LocalDate startDate, @NonNull LocalTime startTime, Pageable paging);
//
//	public Page<Appointment> findByStartDateAndStartTime(@NonNull LocalDate startDate, @NonNull LocalTime startTime,
//			Pageable paging);
//
//	public Page<Appointment> findByPerson(@NonNull String person, Pageable paging);

//	public List<Appointment> findBySubject(String subject);
//	
//	public List<Appointment> findByEndDate(LocalDate endDate);
//	
//	public List<Appointment> findByEndTime(LocalTime endtime);		

}

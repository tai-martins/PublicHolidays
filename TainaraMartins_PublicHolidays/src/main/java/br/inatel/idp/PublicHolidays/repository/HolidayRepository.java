package br.inatel.idp.PublicHolidays.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer>{

	List<Holiday> findByLocal(String cityName);
	
	List<Holiday> findByDate(LocalDate date);
	
}

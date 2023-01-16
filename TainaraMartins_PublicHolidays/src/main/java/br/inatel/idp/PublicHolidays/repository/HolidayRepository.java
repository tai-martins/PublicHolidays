package br.inatel.idp.PublicHolidays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, String>{

}

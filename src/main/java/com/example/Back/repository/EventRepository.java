package com.example.Back.repository;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.enums.EType;
import com.example.Back.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findAllByUser(User user);

    @Query("""
            select e from Event e
            where (upper(e.location) like upper(concat('%', ?1, '%')) or upper(e.title) like upper(concat('%', ?2, '%'))) and e.type = ?3""")
    public List<Event> findByLocationIsContainingIgnoreCaseOrTitleContainingIgnoreCaseAndType(String location, String title, EType type);

    @Query("""
            select e from Event e
            where upper(e.location) like upper(concat('%', ?1, '%')) or upper(e.title) like upper(concat('%', ?2, '%'))""")
    public List<Event> findByLocationIsContainingIgnoreCaseOrTitleContainingIgnoreCase(String location, String title);

}

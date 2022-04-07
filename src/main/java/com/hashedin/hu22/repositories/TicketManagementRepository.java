package com.hashedin.hu22.repositories;

import com.hashedin.hu22.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketManagementRepository extends JpaRepository<Ticket,Integer> {
}

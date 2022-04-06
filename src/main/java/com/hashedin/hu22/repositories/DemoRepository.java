package com.hashedin.hu22.repositories;

import com.hashedin.hu22.entities.Demo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DemoRepository extends JpaRepository<Demo, Long> {
}

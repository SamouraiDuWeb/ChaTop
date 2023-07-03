package com.leomouda.chatop.repositories;

import com.leomouda.chatop.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}

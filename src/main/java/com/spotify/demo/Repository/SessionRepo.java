package com.spotify.demo.Repository;

import com.spotify.demo.entity.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.tokens.Token;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, String> {
    public Optional<CurrentUserSession> findByUuId(String uuId);

}
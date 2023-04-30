package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AbstractTokenRepository extends JpaRepository<Token,Long> {

//    @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
    @Query("FROM Token t JOIN t.person p WHERE p.id = :id AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokenByUser(long id);

    Optional<Token> findByToken(String token);

    Optional<Token> findByPerson(Person person);
}

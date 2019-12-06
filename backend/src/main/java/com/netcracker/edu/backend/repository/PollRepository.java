package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.Collection;

public interface PollRepository extends PagingAndSortingRepository<Poll,Integer> {

    @Query("select p from Poll p where p.idUser=:idUser and"+
            "(:substr is null or (upper(p.name) like concat('%', upper(:substr), '%') or (upper(p.description) like " +
            "concat('%', upper(:substr), '%')) or (upper(p.dataTime) like concat('%', upper(:substr), '%'))))")
    Page<Poll> findPollsByIdUser(@Param("idUser") Integer idUser,@Param("substr") String substr, Pageable pageable);
    Poll findByLink(String link);

    @Query("select p from Poll p where p.shared='Yes' and p.idTheme=(select t.id from Theme t where t.name=:theme)")
    Collection<Poll> findAllTemplateByTheme(@Param("theme") String theme);

//    @Query("select p from Poll p where p.idUser=:idUser and (upper(p.name) like concat('%', upper(:substr), '%') or (upper(p.description) like concat('%', upper(:substr), '%')) or (upper(p.dataTime) like concat('%', upper(:substr), '%')))" )
//    Page<Poll> searchPollsBySubstr(@Param("substr") String substr,@Param("idUser") Integer idUser, Pageable pageable);



    @Query("select p from Poll p where p.idUser=:idUser and p.idTheme=(select t.id from Theme t where t.name=:theme) and " +
            "(:substr is null or (upper(p.name) like concat('%', upper(:substr), '%') or (upper(p.description) like " +
            "concat('%', upper(:substr), '%')) or (upper(p.dataTime) like concat('%', upper(:substr), '%'))))")
    Page<Poll> findAllByThemeAndIdUser(@Param("theme") String theme,@Param("idUser") Integer idUser, @Param("substr") String substr, Pageable pageable);

    @Query("select p from Poll p where p.idUser=:idUser and p.link is null and "+
            "(:substr is null or (upper(p.name) like concat('%', upper(:substr), '%') or (upper(p.description) like " +
            "concat('%', upper(:substr), '%')) or (upper(p.dataTime) like concat('%', upper(:substr), '%'))))")
    Page<Poll> findDraftsByUserId(@Param("substr") String substr,@Param("idUser") Integer idUser,Pageable pageable);

    @Query("select p from Poll p where p.idUser=:idUser and p.link is not null and"+
            "(:substr is null or (upper(p.name) like concat('%', upper(:substr), '%') or (upper(p.description) like " +
            "concat('%', upper(:substr), '%')) or (upper(p.dataTime) like concat('%', upper(:substr), '%'))))")
    Page<Poll> findActivePollsByUserId(@Param("substr") String substr,@Param("idUser") Integer idUser,Pageable pageable);

}

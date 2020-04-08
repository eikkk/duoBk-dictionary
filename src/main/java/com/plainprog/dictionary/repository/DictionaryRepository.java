package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Dictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictionaryRepository extends CrudRepository<Dictionary,Integer> {

    @Query(value = "SELECT A.id FROM duobkdb.dictionary A left join duobkdb.user B on a.user_id = b.id where access_id =:accessId",nativeQuery = true)
    public Integer getDictIdByAccessId(@Param(value = "accessId") String accessId);
}

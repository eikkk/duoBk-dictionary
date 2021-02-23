package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Dictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictionaryRepository extends CrudRepository<Dictionary,Integer> {

    @Query(value = "SELECT A.id FROM duobkdb.dictionary A left join duobkdb.user B on A.user_id = B.id where access_id =:accessId",nativeQuery = true)
    public Integer getDictIdByAccessId(@Param(value = "accessId") String accessId);

//    @Query(value = "CALL create_dict_if_not_exist_and_return_id(:accessId,@id); select @id as id;",nativeQuery = true)
//    public Integer getOrCreateAndGetDictIdByAccessId(@Param(value = "accessId") String accessId);

    @Procedure("create_dict_if_not_exist_and_return_id")
    int getOrCreateAndGetDictIdByAccessId(String accessId);
}

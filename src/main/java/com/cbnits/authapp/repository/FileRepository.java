package com.cbnits.authapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbnits.authapp.model.Doc;

/**
 * @author cbnits-154
 *
 */
public interface FileRepository  extends JpaRepository<Doc,Integer>{

}

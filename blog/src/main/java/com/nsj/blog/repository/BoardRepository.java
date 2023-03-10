package com.nsj.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nsj.blog.model.Board;
import com.nsj.blog.model.User;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}


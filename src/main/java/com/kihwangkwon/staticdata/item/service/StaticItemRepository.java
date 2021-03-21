package com.kihwangkwon.staticdata.item.service;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kihwangkwon.staticdata.item.domain.StaticItem;

public interface StaticItemRepository extends JpaRepository<StaticItem,Long>{

}

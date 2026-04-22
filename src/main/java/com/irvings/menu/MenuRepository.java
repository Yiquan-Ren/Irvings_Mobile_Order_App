package com.irvings.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, String> {
    // 自动继承：save(), findById(), findAll(), delete() 等方法

    // 新增：按分类查询菜单（可选，方便前端）
    List<MenuItem> findByCategory(String category);

    // 新增：只查询可用的菜单（可选）
    List<MenuItem> findByAvailableTrue();
}

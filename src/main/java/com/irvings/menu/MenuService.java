package com.irvings.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // ==============================================
    // 启动时自动检查：如果数据库为空，用 data.sql 初始化
    // ==============================================
    @PostConstruct
    public void initMenu() {
        long count = menuRepository.count();
        if (count == 0) {
            System.out.println("[MenuService] Database is empty, data.sql should have initialized it");
        } else {
            System.out.println("[MenuService] Loaded " + count + " menu items from database");
        }
    }

    // ==============================================
    // 核心业务方法（完全兼容现有代码）
    // ==============================================

    // 获取所有菜单（优先从数据库读取）
    public List<MenuItem> getFullMenu() {
        return menuRepository.findAll();
    }

    // 根据 ID 获取单个菜单项
    public Optional<MenuItem> getMenuItemById(String itemId) {
        return menuRepository.findById(itemId);
    }

    // 检查菜单项是否可用（兼容你原来的 Cart.java 逻辑）
    public boolean isItemAvailable(String itemId) {
        Optional<MenuItem> item = menuRepository.findById(itemId);
        return item.isPresent() && item.get().isAvailable();
    }

    // 按分类查询菜单（新增，方便前端）
    public List<MenuItem> getMenuByCategory(String category) {
        return menuRepository.findByCategory(category);
    }

    // 保存菜单项（用于更新可用性）
    
    public void saveMenuItem(MenuItem menuItem) {
        menuRepository.save(menuItem);
    }
}

package com.irvings.controller;

import com.irvings.menu.MenuItem;
import com.irvings.menu.MenuService;
import com.irvings.menu.CustomizationOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    // 获取完整菜单
    @GetMapping("/list")
    public List<MenuItem> getMenu() {
        return MenuItem.getStubMenu();
    }

    // 根据 ID 获取单个菜单项
    @GetMapping("/{itemId}")
    public MenuItem getMenuItemById(@PathVariable String itemId) {
        Optional<MenuItem> item = menuService.getMenuItemById(itemId);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Menu item not found: " + itemId);
        }
        return item.get();
    }

    // 新增：按分类查询菜单（可选，不影响现有接口）
    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuByCategory(@PathVariable String category) {
        return menuService.getMenuByCategory(category);
    }

    // 修改商品可用性
    @PutMapping("/{itemId}/availability")
    public void setAvailability(@PathVariable String itemId, @RequestParam boolean available) {
        // 从数据库读取，而不是从 Stub
        Optional<MenuItem> item = menuService.getMenuItemById(itemId);
        if (item.isPresent()) {
             MenuItem menuItem = item.get();
            menuItem.setAvailability(available);
            // 保存回数据库
            menuService.saveMenuItem(menuItem);
            System.out.println("[MenuController] Updated availability for " + itemId + " to " + available);
        } else {
            throw new IllegalArgumentException("Menu item not found: " + itemId);
        }
    }

    // 获取商品定制选项
    @GetMapping("/{itemId}/customization")
    public List<CustomizationOption> getCustomization(@PathVariable String itemId) {
        // 从数据库读取，而不是从 Stub
        Optional<MenuItem> item = menuService.getMenuItemById(itemId);
        if (item.isPresent()) {
            return item.get().getCustomizationOptions();
        } else {
            throw new IllegalArgumentException("商品不存在：" + itemId);
        }
    }
}

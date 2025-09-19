package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result<String> add(@RequestBody CategoryDTO categoryDTO){
        log.info("添加分类：{}",categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }

    /**
     * 启用禁用套餐
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result update(@PathVariable Integer status, Long id){
        log.info("启用禁用套餐：{},{}",status,id);
        categoryService.startOrStop(status,id);
        return Result.success();
    }
    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询分类，页码：{}，页大小：{}",categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 编辑分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("编辑员工信息：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> getById(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

    @DeleteMapping
    public Result delete(Long id){
        log.info("删除分类：{}",id);
        categoryService.deleteById(id);
        return Result.success();
    }
}

package com.example.category_service.controller;

import com.example.category_service.dto.CategoryDTO;
import com.example.category_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Tag(name = "test with swagger", description = "APIs REST test of category and interactive documentation with swagger")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    @Operation(summary = "add category", description = "save category in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved category successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<CategoryDTO>create(@RequestBody CategoryDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.createCategory(dto));
    }

    @GetMapping()
    @Operation(summary = "get categories", description = "get all categories in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "gate categories successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<List<CategoryDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "get category by id", description = "get the category in the database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved category successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/update/{categoryId}")
    @Operation(summary = "update category", description = "save category in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved category successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<CategoryDTO>update(@PathVariable int categoryId, @RequestBody CategoryDTO dto){
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.categoryService.updateCategory(categoryId,dto));
    }

    @DeleteMapping("/delete/{categoryId}")
    @Operation(summary = "delete category", description = "save category in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved category successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int categoryId){
        boolean exist = this.categoryService.deleteCategory(categoryId);
        if (exist){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

    }
}

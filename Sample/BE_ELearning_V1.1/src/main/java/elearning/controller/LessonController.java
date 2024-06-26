package elearning.controller;

import elearning.dto.CommentDto;
import elearning.dto.LessonDto;
import elearning.exception.CustomException;
import elearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/lesson")
@RestController
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/save")
    public ResponseEntity<LessonDto> save(@RequestBody LessonDto request) throws CustomException {
        LessonDto ret = lessonService.saveLesson(request);
        return ResponseEntity.ok(ret);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LessonDto> update(@RequestBody LessonDto request, @PathVariable Long id) throws CustomException {
        LessonDto ret = lessonService.upDateLesson(request, id);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws CustomException {
        lessonService.deleteLesson(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<LessonDto>> getAll() {
        List<LessonDto> ret = lessonService.getAllLesson();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<LessonDto>> paging(@PageableDefault(page = 0, size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String title) {
        Page<LessonDto> ret = lessonService.pagingLessonDto(pageable, title);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> get(@PathVariable("id") Long id) throws CustomException {
        LessonDto ret = lessonService.getLessonDtoById(id);
        return ResponseEntity.ok(ret);
    }

}

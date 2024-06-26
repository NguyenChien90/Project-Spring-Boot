package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Lesson;
import lombok.*;


@Getter
@Setter
public class LessonDto  extends BaseObjectDto {
    private String title;
    private String video;
    private String resources;
    private String description;
    private ChapterDto chapterDto;
    private Long chapterId;
    private String document;

    public LessonDto() {
    }

    public LessonDto(Lesson entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.video = entity.getVideo();
        this.resources = entity.getResources();
        this.description = entity.getDescription();
        this.document =entity.getDocument();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if(entity.getChapter() != null){
//            this.chapterDto = new ChapterDto(entity.getChapter());
            this.chapterId = entity.getChapter().getId();
        }
    }

    public LessonDto(Lesson entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.video = entity.getVideo();
        this.resources = entity.getResources();
        this.description = entity.getDescription();
        this.document =entity.getDocument();
        if(entity.getChapter() != null){
//            this.chapterDto = new ChapterDto(entity.getChapter());
            this.chapterId = entity.getChapter().getId();
        }
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }

}

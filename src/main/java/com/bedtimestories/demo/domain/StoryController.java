package com.bedtimestories.demo.domain;

import com.bedtimestories.demo.domain.request.StoryRequest;
import com.bedtimestories.demo.model.Story;
import com.bedtimestories.demo.services.StoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }
    @ApiOperation(value = "Get the latest stories", notes = "Get the latest stories by numOfLast")
    @GetMapping("/last-stories/{numOfLast}")
    public ResponseEntity<List<Story>> stories(@PathVariable int numOfLast) {
        return new ResponseEntity<>(storyService.stories(numOfLast), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all stories in English")
    @GetMapping("/stories/eng")
    public ResponseEntity<List<Story>> storiesEng() {
        return new ResponseEntity<>(storyService.engStories(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all stories in Russian or Ukrainian")
    @GetMapping("/stories/cyril")
    public ResponseEntity<List<Story>> storiesCyril() {
        return new ResponseEntity<>(storyService.ukrStories(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all stories")
    @GetMapping("/stories")
    public ResponseEntity<List<Story>> stories() {
        return new ResponseEntity<>(storyService.stories(), HttpStatus.OK);
    }
    @ApiOperation(value = "Create story")
    @PostMapping("/story")
    public ResponseEntity<List<Story>> add(@RequestBody StoryRequest storyRequest) {
        return new ResponseEntity<>(storyService.add(storyRequest), HttpStatus.OK);
    }
    @ApiOperation(value = "Update story")
    @PutMapping("/story/{id}")
    public ResponseEntity<Story> modify(@PathVariable Long id, @RequestBody StoryRequest storyRequest) {
        Optional<Story> modifiedStory = storyService.modify(id, storyRequest);
        return modifiedStory.map(story ->
                new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation(value = "Delete story")
    @DeleteMapping("/story/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean isDeleted = storyService.delete(id);
        if (isDeleted) return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Not founded", HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Get stories with minNumOfLikes and higher")
    @GetMapping("/stories/{minNumOfLikes}")
    public ResponseEntity<List<Story>> minNumOfLikes(@PathVariable int minNumOfLikes) {
         return new ResponseEntity<>(storyService.find(minNumOfLikes), HttpStatus.OK);
    }
    @ApiOperation(value = "Like story")
    @PatchMapping("/increment-likes/{idStory}/{idUser}")
    public ResponseEntity<Story> incrementLikes(@PathVariable Long idStory, @PathVariable Long idUser) {
        Optional<Story> result = storyService.like(idStory, idUser);
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation(value = "Remove like")
    @PatchMapping("/decrement-likes/{idStory}/{idUser}")
    public ResponseEntity<Story> decrementLikes(@PathVariable Long idStory, @PathVariable Long idUser) {
        Optional<Story> result = storyService.removeLike(idStory, idUser);
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Listen story")
    @PostMapping("/add-to-listened/{idStory}/{idUser}")
    public ResponseEntity<List<Story>> addToListened(@PathVariable Long idStory, @PathVariable Long idUser) {
        Optional<List<Story>> result = Optional.ofNullable(storyService.listen(idStory, idUser));
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation(value = "Remove from listened")
    @DeleteMapping("/remove-from-listened/{idStory}/{idUser}")
    public ResponseEntity<List<Story>> removeFromListened(@PathVariable Long idStory, @PathVariable Long idUser) {
        Optional<List<Story>> result = Optional.ofNullable(storyService.removeFromListened(idStory, idUser));
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation(value = "Get listened stories")
    @GetMapping("/listened-stories/{idUser}")
    public ResponseEntity<List<Story>> listenedStories( @PathVariable Long idUser) {
        Optional<List<Story>> result = Optional.of(storyService.getListened(idUser));
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @ApiOperation(value = "Get liked stories")
    @GetMapping("/liked-stories/{idUser}")
    public ResponseEntity<List<Story>> likedStories( @PathVariable Long idUser) {
        Optional<List<Story>> result = Optional.of(storyService.getLiked(idUser));
        return result.map(story -> new ResponseEntity<>(story, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
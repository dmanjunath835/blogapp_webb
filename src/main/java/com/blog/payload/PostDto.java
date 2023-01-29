package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
	private long id;
	@NotNull
	@Size(min = 2,message = "title should be only two character!")
private String title;
	@NotNull
	@Size(min = 4,message = "description should be 4 character!")
private String description;
	@NotNull
	@NotEmpty
private String content;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

}

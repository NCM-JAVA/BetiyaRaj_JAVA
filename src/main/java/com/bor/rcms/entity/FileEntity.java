package com.bor.rcms.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "objection_id", nullable = true)
    // Make it nullable temporarily
    @JsonBackReference
    private NewObjection newObjection;
    private String file1;
    private String file2;
    private String file3;
    private String file4;
    private String file5;

    public FileEntity() {}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getFile4() {
		return file4;
	}

	public void setFile4(String file4) {
		this.file4 = file4;
	}

	public String getFile5() {
		return file5;
	}

	public void setFile5(String file5) {
		this.file5 = file5;
	}

	public NewObjection getNewObjection() {
		return newObjection;
	}

	public void setNewObjection(NewObjection newObjection) {
		this.newObjection = newObjection;
	}

	@Override
	public String toString() {
		return "FileEntity [fileId=" + fileId + ", user=" + user + ", newObjection=" + newObjection + ", file1=" + file1
				+ ", file2=" + file2 + ", file3=" + file3 + ", file4=" + file4 + ", file5=" + file5 + "]";
	}

	
}

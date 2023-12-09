package com.r2s.findInternship.data.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationDTO implements Serializable {
	private List<?> contents;
	private boolean isFirst;
	private boolean isLast;
	private long totalPages;
	private long totalItems;
	private long limit;
	private int no;
}

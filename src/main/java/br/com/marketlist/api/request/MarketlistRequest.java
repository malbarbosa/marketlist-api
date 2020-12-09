package br.com.marketlist.api.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MarketlistRequest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6121444082544212243L;
	private String id;
	@NotBlank
	@Size(max = 30)
	private String name;
	@Valid
	private List<ItemRequest> items;

}

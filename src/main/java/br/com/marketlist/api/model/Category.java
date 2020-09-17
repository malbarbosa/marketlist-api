package br.com.marketlist.api.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@DynamoDBTable(tableName = "categories")
public class Category extends AbstractModel implements Cloneable,Serializable, Comparable<Category>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1066568526996715965L;
	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	@Getter @Setter
	private String id;
	
	
	@Override
	public Category clone(){
		
		try {
			return (Category) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
			
		}
	}

	@Override
	public int compareTo(Category c1) {
		return (this.version <= c1.getVersion()?1:-1);
	}
	
}

package contracts;

import java.util.List;

import javax.ejb.Local;

import ideca.entity.FXOptions;

@Local
public interface FXOptionsLocal {
	
	boolean addFXOption(FXOptions F) ; 
	boolean updateFXOption(FXOptions f) ; 
	boolean deleteFXOption(int id) ;
	FXOptions findById(int id ) ; 
	List<FXOptions> findAllFXOption();
	
	float pricingCallFXOptions(FXOptions f);
	float pricingPutFXOptions(FXOptions f);


}

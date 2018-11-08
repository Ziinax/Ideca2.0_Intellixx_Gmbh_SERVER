package contracts;
import java.util.List;

import javax.ejb.Remote;

import ideca.entity.FXOptions;

@Remote
public interface FXOptionsRemote {
	
	boolean addFXOption(FXOptions F) ; 
	boolean updateFXOption(FXOptions f) ; 
	boolean deleteFXOption(int id) ;
	FXOptions findById(int id ) ; 
	List<FXOptions> findAllFXOption();
	
	float pricingCallFXOptions(FXOptions f);
	float pricingPutFXOptions(FXOptions f);
}

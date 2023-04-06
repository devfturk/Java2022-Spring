package kodlama.io.rentAcar.business.abstracts;

import java.util.List;

import kodlama.io.rentAcar.business.requests.CreateModelRequest;
import kodlama.io.rentAcar.business.responses.GetAllModelsResponse;

public interface ModelService {

	List<GetAllModelsResponse> getAll();
	void add(CreateModelRequest createModelRequest);
}
